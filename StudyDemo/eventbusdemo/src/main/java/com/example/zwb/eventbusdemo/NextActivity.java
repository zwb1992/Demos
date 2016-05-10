package com.example.zwb.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

import event.MyEvent;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        EventBus.getDefault().post(new MyEvent("失败","原因","成功"));
    }
}
