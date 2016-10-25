package com.zwb.circleprogresswithbitmap;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CircleProgressWithBitmap cpb;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cpb = (CircleProgressWithBitmap)findViewById(R.id.cpb);
        bt = (Button)findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cpb.isStart()){
                    cpb.start();
                }else {
                    cpb.stop();
                }
            }
        });
        cpb.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
    }
}
