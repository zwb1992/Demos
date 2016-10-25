package com.zwb.frameutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zwb.frameutils.http.Net;
import com.zwb.volley.net.NetEvent;
import com.zwb.volley.net.OnNetEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Net.get(new OnNetEventListener() {
            @Override
            public void netSuccess(NetEvent netEvent) {

            }

            @Override
            public boolean netfailed(NetEvent netEvent) {
                return false;
            }

            @Override
            public void tokenVerifyFailed() {

            }
        }).get();
    }
}
