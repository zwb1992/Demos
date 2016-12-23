package com.zwb.bluetoothdemo4;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String DEV_NAME = "COM.BLUETOOTHDEMO.DEV_NAME";
    public static final String CONNECT_STATE = "COM.BLUETOOTHDEMO.CONNECT_STATE";
    public static final String EXTRA_DATA = "COM.BLUETOOTHDEMO.EXTRA_DATA";
    private Button btConnect;
    private TextView tv_name,tvConnect;
    private BluetoothLeService leService;
    private BluetoothReceiver bluetoothReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btConnect = (Button) findViewById(R.id.bt_connect);
        btConnect.setOnClickListener(this);

        tvConnect = (TextView) findViewById(R.id.tv_connect);
        tvConnect.setOnClickListener(this);
        tv_name = (TextView)findViewById(R.id.tv_name);

        bluetoothReceiver = new BluetoothReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(DEV_NAME);
        registerReceiver(bluetoothReceiver,filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_connect:
                /**
                 * android 6.0 权限申请
                 */
                if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 333);
                    return;
                }
                Intent intent = new Intent(this, BluetoothLeService.class);
                boolean flag = bindService(intent, connection, Context.BIND_AUTO_CREATE);
                Log.e("info","service启动"+flag);
                break;
            case R.id.tv_name:
                if(leService != null){
                    Toast.makeText(this,"正在连接",Toast.LENGTH_SHORT).show();
                    leService.connect();
                }else {
                    Toast.makeText(this,"连接失败",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 333 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(this, BluetoothLeService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }else {
                Toast.makeText(this,"扫描失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 启动 bindService连接服务
     */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("info","建立连接");
            leService = ((BluetoothLeService.LocalBinder)service).getService();
            if(!leService.init()){//蓝牙初始化失败，断开连接
                unbindService(this);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("info","断开连接");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unbindService(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        unregisterReceiver(bluetoothReceiver);
    }

    /**
     * 接收蓝牙数据
     */
    private class BluetoothReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(DEV_NAME.equals(action)){
                String name = intent.getStringExtra("name");
                tv_name.setText(name);
                tv_name.setVisibility(View.VISIBLE);
                tvConnect.setText("未连接");
                tvConnect.setVisibility(View.VISIBLE);
            }else if(CONNECT_STATE.equals(action)){
                boolean isConnect = intent.getBooleanExtra("isConnect",false);
                tvConnect.setText(isConnect ? "已连接连接":"未连接");
            }else if(EXTRA_DATA.equals(action)){
                String data = intent.getStringExtra("EXTRA_DATA");
                Log.e("data",data);
            }
        }
    }

    /**
     * 将字节数组转换成16进制字符串
     * @param bytes
     * @return
     */
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

    /**
     * 将16进制 转换成10进制
     *
     * @param str
     * @return
     */
    public static String print10(String str) {

        StringBuffer buff = new StringBuffer();
        String array[] = str.split(" ");
        for (int i = 0; i < array.length; i++) {
            int num = Integer.parseInt(array[i], 16);
            buff.append(String.valueOf((char) num));
        }
        return buff.toString();
    }
}
