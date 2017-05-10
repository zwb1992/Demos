package com.zwb.thirdpartydemo.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zwb.thirdpartydemo.R;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        //延迟2秒钟进入主界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goMain();
            }
        },2000);
    }

    private void goMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
