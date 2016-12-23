package com.zwb.bluetoothdemo2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BluetoothManager bluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private LeDeviceListAdapter mLeDeviceListAdapter;
    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView)findViewById(R.id.listview);
        mLeDeviceListAdapter = new LeDeviceListAdapter(this);
        listview.setAdapter(mLeDeviceListAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BluetoothDevice device = (BluetoothDevice)adapterView.getItemAtPosition(i);
                device.connectGatt(MainActivity.this, false, new BluetoothGattCallback() {
                    @Override
                    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                        super.onConnectionStateChange(gatt, status, newState);
                        if (newState == BluetoothProfile.STATE_CONNECTED) {//当蓝牙设备已经连接
                            Log.d("info","设备已经连接==="+gatt.discoverServices());
//                            Toast.makeText(MainActivity.this,"设备已经连接",Toast.LENGTH_SHORT).show();
//                                    gatt.discoverServices();
                        } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {//当设备无法连接
//                            Toast.makeText(MainActivity.this,"设备无法连接",Toast.LENGTH_SHORT).show();
                            Log.d("info","设备无法连接===");
                        }
                    }
                    @Override
                    // 发现新服务端
                    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
//                        if (status == BluetoothGatt.GATT_SUCCESS) {
//                            broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
//                        } else {
                            Log.w("info", "onServicesDiscovered received: " + status);
//                        }
                    }
                    @Override
                    // 读写特性
                    public void onCharacteristicRead(BluetoothGatt gatt,
                                                     BluetoothGattCharacteristic characteristic,
                                                     int status) {
                        if (status == BluetoothGatt.GATT_SUCCESS) {
//                            broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
                        }
                        Log.w("info", "onCharacteristicRead received: " + status);
                    }

                });
            }
        });
        // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "蓝牙不支持", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
        // BluetoothAdapter through BluetoothManager.
        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        // Checks if Bluetooth is supported on the device.
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "蓝牙不支持", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        //开启蓝牙
        mBluetoothAdapter.enable();
    }
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, final byte[] scanRecord) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("bluetooth","搜索成功");
                    try {
//                        String struuid = NumberUtils.bytes2HexString(NumberUtils.reverseBytes(scanRecord)).replace("-", "").toLowerCase();
//                        if (device!=null && struuid.contains(DEVICE_UUID_PREFIX.toLowerCase())) {
//                            mBluetoothDevices.add(device);
//                        }
                        if(device != null){
                            mLeDeviceListAdapter.addDevice(device);
                            mLeDeviceListAdapter.notifyDataSetChanged();
                            Log.d("bluetooth",device.toString());
                            Log.d("bluetooth",device.getAddress());
                            Log.d("bluetooth",device.getName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    public void search(View view){
        mBluetoothAdapter.startLeScan(mLeScanCallback);
    }
    public void stopSearch(View view){
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }
    private boolean mScanning;
    private Handler mHandler = new Handler();
    // 10秒后停止寻找.
    private static final long SCAN_PERIOD = 10000;

    /*private void scanLeDevice(final boolean enable) {
        if (enable) {
            // 经过预定扫描期后停止扫描
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                }
            }, SCAN_PERIOD);
            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }*/

}
