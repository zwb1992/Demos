package com.zwb.circleprogressdemo;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private CircleProgressBar cpb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cpb = (CircleProgressBar)findViewById(R.id.pb);
        handler.post(runnable);
    }
    int i = 0;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(i>100){
                i = 0;
            }
            cpb.setCurrentKd(i);
            i++;
            handler.postDelayed(this,100);
        }
    };
}
