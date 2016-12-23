package com.zwb.bluetoothdemo4;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 连接蓝牙的服务
 */
public class BluetoothLeService extends Service {
    private final static String TAG = BluetoothLeService.class.getSimpleName();
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothGatt bluetoothGatt;
    private BluetoothLeScanner mScanner;
    private BluetoothDevice bluetoothDevice;
    BluetoothGattCharacteristic notifyCharacteristic;
    BluetoothGattCharacteristic writeCharacteristic;
    public BluetoothLeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * 初始化蓝牙
     * @return
     */
    public boolean init(){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)){
            Toast.makeText(this,"当前手机不支持蓝牙功能",Toast.LENGTH_SHORT).show();
            return false;
        }

        bluetoothManager = (BluetoothManager)getSystemService(BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        if(!bluetoothAdapter.isEnabled()){
            Toast.makeText(this,"你的蓝牙还未打开,请先打开蓝牙",Toast.LENGTH_SHORT).show();
            return false;
        }

        mScanner = bluetoothAdapter.getBluetoothLeScanner();
        if(mScanner == null){
            Toast.makeText(this,"本次搜索失败",Toast.LENGTH_SHORT).show();
            return false;
        }
        startScan();
        return true;
    }
    public class LocalBinder extends Binder {
        BluetoothLeService getService() {
            return BluetoothLeService.this;
        }
    }

    private final IBinder mBinder = new LocalBinder();

    /**
     * 开始扫描蓝牙设备
     */
    public void startScan(){
        Toast.makeText(this,"开始扫描设备",Toast.LENGTH_SHORT).show();
        List<ScanFilter> filters = new ArrayList<>();
        filters.add(new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(C.CLIENT_CHARACTERISTIC_CONFIG)).build());
        ScanSettings settings = new ScanSettings.Builder().build();
        mScanner.startScan(filters, settings, new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
                Log.e("info","=====扫描结果===="+result.getDevice());
                bluetoothDevice = result.getDevice();
                if(bluetoothDevice != null){
                    Log.e(TAG,"===getAddress===="+bluetoothDevice.getAddress());
                    Log.e(TAG,"===getName===="+bluetoothDevice.getName());
                    Log.e(TAG,"===getBluetoothClass===="+bluetoothDevice.getBluetoothClass());
                    Log.e(TAG,"===getBondState===="+bluetoothDevice.getBondState());
                    Log.e(TAG,"===getType==="+bluetoothDevice.getType());
                    Log.e(TAG,"===getUuids==="+bluetoothDevice.getUuids());
                    brodcastName(bluetoothDevice.getName());
                    /**
                     * 搜索到匹配的蓝牙就停止搜索
                     */
                    mScanner.stopScan(this);
                }
            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                Toast.makeText(getApplicationContext(),"本次搜索失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 开始连接
     */
    public void connect(){
        if(bluetoothDevice == null){
            Toast.makeText(getApplicationContext(),"连接失败",Toast.LENGTH_SHORT).show();
            return;
        }
        bluetoothGatt = bluetoothDevice.connectGatt(this,false,bluetoothGattCallback);
    }

    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        //连接状态改变
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                brodcastConnectState(true);
                gatt.discoverServices();//连接成功，开始搜索服务，一定要调用此方法，否则获取不到服务
            }else {
                brodcastConnectState(false);
            }
        }

        /**
         * 搜索到BLE终端服务的事件
         */
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            if(gatt.getServices() == null){
                return;
            }
            Log.e(TAG," size " + gatt.getServices().size());
            for (int i = 0;i<gatt.getServices().size();i++){
                Log.e(TAG,"======================================"+i);
                BluetoothGattService service = gatt.getServices().get(i);
                if(service.getUuid().toString().equalsIgnoreCase(C.CLIENT_CHARACTERISTIC_CONFIG)) {
                    Log.e(TAG, "=== uuid ===" + service.getUuid().toString());
                    notifyCharacteristic = service.getCharacteristic(UUID.fromString("d44bc439-abfd-45a2-b575-925416129601"));
                    enableNotify(notifyCharacteristic,true);

                    writeCharacteristic = service.getCharacteristic(UUID.fromString("D44BC439-ABFD-45A2-B575-925416129600"));
                    byte[] bytes = new byte[]{(byte)0x83};
                    writeCharacteristic.setValue(bytes);
                    bluetoothGatt.writeCharacteristic(writeCharacteristic);

                }else if (service.getUuid().toString().equalsIgnoreCase(C.OTA_UUID)){
                    Log.e(TAG, "=== uuid ===" + service.getUuid().toString());
                    BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString("003784CF-F7E3-55B4-6C4C-9FD140100A16"));
                    enableNotify(characteristic,true);
                }
            }

        }
        //当读取设备时会回调该函数
        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);

        }
        //当向Characteristic写数据时会回调该函数
        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }
        //设备发出通知时会调用到该接口
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            broadcastUpdate(MainActivity.EXTRA_DATA,characteristic);
        }
    };

    /**
     * enable特性的notify功能
     *
     * @param enable
     * @return
     */
    public boolean enableNotify(BluetoothGattCharacteristic characteristic, boolean enable) {
        if (bluetoothGatt == null || characteristic == null) {
            return false;
        }
        if (bluetoothGatt.setCharacteristicNotification(characteristic, enable)) {

            BluetoothGattDescriptor clientConfig = characteristic
                    .getDescriptor(UUID.fromString(C.GATT_DESCRIPTOR));//固定uuid，固定写死
            if (clientConfig != null) {

                if (enable) {
                    clientConfig.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                } else {
                    clientConfig.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                }
                return bluetoothGatt.writeDescriptor(clientConfig);
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(bluetoothGatt != null){
            bluetoothGatt.disconnect();
        }
    }

    private void brodcastName(String name){
        Intent intent = new Intent(MainActivity.DEV_NAME);
        intent.putExtra("name",name);
        sendBroadcast(intent);
    }
    private void brodcastConnectState(boolean flag){
        Intent intent = new Intent(MainActivity.DEV_NAME);
        intent.putExtra("isConnect",flag);
        sendBroadcast(intent);
    }
    private void broadcastUpdate(final String action,
                                 final BluetoothGattCharacteristic characteristic) {
        final Intent intent = new Intent(action);

        byte[] heartRate = characteristic.getValue();
        String data = MainActivity.bytesToHexString(heartRate);
        if (data != null ) {
            intent.putExtra(MainActivity.EXTRA_DATA, data);
        }
        sendBroadcast(intent);
    }

}
