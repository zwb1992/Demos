package com.zwb.bluetoothdemo3;

import android.Manifest;
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
import android.os.Build;
import android.os.ParcelUuid;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_dev,tv_status;
    private Button button,bt_notify,bt_write;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner scanner;
    private BluetoothDevice device;
    private BluetoothGatt mGatt;
    private static final String TAG = "lanya";
    private static final String DATA_UUID = "0000FEE9-0000-1000-8000-00805F9B34FB";//数据通信
    private static final String OTA_UUID = "0000FEE8-0000-1000-8000-00805F9B34FB";//OTA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_dev = (TextView) findViewById(R.id.tv_dev);
        tv_dev.setOnClickListener(this);
        button = (Button)findViewById(R.id.bt_search);
        button.setOnClickListener(this);
        bt_notify = (Button)findViewById(R.id.bt_notify);
        bt_notify.setOnClickListener(this);
        bt_write = (Button)findViewById(R.id.bt_write);
        bt_write.setOnClickListener(this);

        initBluetooth();
    }

    /**
     * 初始化蓝牙
     */
    private void initBluetooth(){
        bluetoothManager = (BluetoothManager)getSystemService(BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_search:
                if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)){
                    Toast.makeText(this,"当前手机不支持蓝牙功能",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!bluetoothAdapter.isEnabled()){
                    Toast.makeText(this,"你的蓝牙还未打开,请先打开蓝牙",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                    startActivityForResult(intent,123);
                    return;
                }
                startScan();
                break;
            case R.id.tv_dev:
                connect();
                break;
            case R.id.bt_notify:
                if(notifyCharacteristic != null){
                    enableNotify(notifyCharacteristic,true);
                }
                break;
            case R.id.bt_write:
                if(writeCharacteristic != null){
                    mGatt.writeCharacteristic(writeCharacteristic);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123){
            if(!bluetoothAdapter.isEnabled()){
                Toast.makeText(this,"蓝牙还未开启",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"蓝牙已经开启",Toast.LENGTH_SHORT).show();
                startScan();
            }
        }
    }

    /**
     * Enables or disables notification on a give characteristic.
     *
     * @param characteristic
     *            Characteristic to act on.
     * @param enabled
     *            If true, enable notification. False otherwise.
     */
    public void setCharacteristicNotification(
            BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (bluetoothAdapter == null || mGatt == null) {
            Log.d(TAG, "BluetoothAdapter为空");
            return;
        }
        mGatt.setCharacteristicNotification(characteristic, enabled);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID
                .fromString("D44BC439-ABFD-45A2-B575-925416129601"));
        if (descriptor != null) {
            byte[] data = {0x01, 0x02,0x03,0x04,0x05,0x06,(byte)0x80,(byte)0x81,(byte)0x82};
            descriptor.setValue(data);
            mGatt.writeDescriptor(descriptor);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        startScan();
    }
    /**
     * enable特性的notify功能
     *
     * @param enable
     * @return
     */
    public boolean enableNotify(BluetoothGattCharacteristic characteristic, boolean enable) {
        if (mGatt == null || characteristic == null) {
            return false;
        }
        if (mGatt.setCharacteristicNotification(
                characteristic, enable)) {

            BluetoothGattDescriptor clientConfig = characteristic
                    .getDescriptor(UUID
                            .fromString("00002902-0000-1000-8000-00805f9b34fb"));//固定uuid，固定写死
            if (clientConfig != null) {

                if (enable) {
                    clientConfig
                            .setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                } else {
                    clientConfig
                            .setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                }
                return mGatt.writeDescriptor(clientConfig);
            }
        }
        return false;
    }
    /**
     * 开始搜索蓝牙
     */
    private void startScan(){
        if(Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 333);
            }
            return;
        }
        scanner = bluetoothAdapter.getBluetoothLeScanner();
        if(scanner == null){
            Toast.makeText(this,"本次搜索失败",Toast.LENGTH_SHORT).show();
            return;
        }
        List<ScanFilter> filters = new ArrayList<>();
        filters.add(new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString("0000fee9-0000-1000-8000-00805f9b34fb")).build());
        ScanSettings settings = new ScanSettings.Builder().build();
        scanner.startScan(filters,settings,new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
                device = result.getDevice();
                if(device != null){
                    Log.e("info","===getAddress===="+device.getAddress());
                    Log.e("info","===getName===="+device.getName());
                    Log.e("info","===getBluetoothClass===="+device.getBluetoothClass());
                    Log.e("info","===getBondState===="+device.getBondState());
                    Log.e("info","===getType==="+device.getType());
                    Log.e("info","===getUuids==="+device.getUuids());
                    tv_dev.setText(TextUtils.isEmpty(device.getName())?"unknown device":device.getName());
                    /**
                     * 搜索到匹配的蓝牙就停止搜索
                     */
                    scanner.stopScan(this);
                }
            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                Log.e("info","===onScanFailed====");
            }
        });
    }

    BluetoothGattCharacteristic notifyCharacteristic;
    BluetoothGattCharacteristic writeCharacteristic;
    /**
     * 开始连接
     */
    private void connect(){
        if(device == null){
            return;
        }
        mGatt = device.connectGatt(this, false, new BluetoothGattCallback() {
            @Override
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                super.onConnectionStateChange(gatt, status, newState);
                Log.e(TAG,gatt.getDevice().getName() + " onConnectionStateChange ");
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    gatt.discoverServices();//连接成功，开始搜索服务，一定要调用此方法，否则获取不到服务
                }
            }

            /**
             * 搜索到BLE终端服务的事件
             */
            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                super.onServicesDiscovered(gatt, status);
                /**
                 * 0000fee9-0000-1000-8000-00805f9b34fb
                 * 这是读取心率，计步的uuid
                 */
                Log.e(TAG, " =====onServicesDiscovered==== "+gatt.getDevice().getName());
                if(gatt.getServices() == null){
                    return;
                }
                Log.e(TAG," size " + gatt.getServices().size());
                for (int i = 0;i<gatt.getServices().size();i++){
                    Log.e(TAG,"======================================"+i);
                    BluetoothGattService service = gatt.getServices().get(i);
                    if(service.getUuid().toString().equalsIgnoreCase(DATA_UUID)) {
                        Log.e(TAG, "=== uuid ===" + service.getUuid().toString());
                        notifyCharacteristic = service.getCharacteristic(UUID.fromString("d44bc439-abfd-45a2-b575-925416129601"));


                        writeCharacteristic = service.getCharacteristic(UUID.fromString("D44BC439-ABFD-45A2-B575-925416129600"));


                    }else if (service.getUuid().toString().equalsIgnoreCase(OTA_UUID)){
                        Log.e(TAG, "=== uuid ===" + service.getUuid().toString());
                        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString("003784CF-F7E3-55B4-6C4C-9FD140100A16"));
                        mGatt.setCharacteristicNotification(characteristic,true);
                    }
                }
            }
            //当读取设备时会回调该函数
            @Override
            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicRead(gatt, characteristic, status);
                Log.e(TAG,gatt.getDevice().getName() + " recieved " + new String(characteristic.getValue()));
            }
            //当向Characteristic写数据时会回调该函数
            @Override
            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicWrite(gatt, characteristic, status);
                Log.e(TAG,gatt.getDevice().getName() + " write successfully");
            }
            //设备发出通知时会调用到该接口
            @Override
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                super.onCharacteristicChanged(gatt, characteristic);
                byte[] data = characteristic.getValue();
                Log.e(TAG,"===数据=======================================");
                Log.e(TAG,"===数据======================================="+bytesToHexString(data));
                for (byte d : data){
                    Log.e(TAG,"===数据==="+d);
                }
            }

            @Override
            public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorRead(gatt, descriptor, status);
                Log.e(TAG,  "===onDescriptorRead===");
            }
            //当向设备Descriptor中写数据时，会回调该函数
            @Override
            public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorWrite(gatt, descriptor, status);
                Log.e(TAG,  "===onDescriptorWrite===");
            }
        });
    }

    /** * 将16进制的字符串转换为10进制字符串 *
     * @param str * @return 字节数组 */
    public static String getHexBytes(String str) {
        int len = str.length() / 2;
        char[] chars = str.toCharArray();
        String[] hexStr = new String[len];
        byte[] bytes = new byte[len];
        for (int i = 0, j = 0; j < len; i += 2, j++) {
            hexStr[j] = "" + chars[i] + chars[i + 1];
            bytes[j] = (byte) Integer.parseInt(hexStr[j], 16);
        }
        return new String(bytes);
    }
    public static String bytesToHexString(byte[] bytes) {
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            String hexString = Integer.toHexString(bytes[i] & 0xFF);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            result += hexString.toUpperCase();
        }
        return result;
    }
}
