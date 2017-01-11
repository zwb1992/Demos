package com.example.foregroundservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button bt_start, bt_stop;
    private MyReceiver myReceiver;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("msg");
        registerReceiver(myReceiver, filter);
        tv = (TextView) findViewById(R.id.tv);
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_stop = (Button) findViewById(R.id.bt_stop);

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Foreground", "=======启动service======");
                Intent intent = new Intent(MainActivity.this, ForegroundService.class);
                MainActivity.this.startService(intent);
            }
        });
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Foreground", "=======停止service======");
                Intent intent = new Intent(MainActivity.this, ForegroundService.class);
                MainActivity.this.stopService(intent);
            }
        });
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("Foreground","====MyReceiver=======");
            tv.setText(intent.getStringExtra("text"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
