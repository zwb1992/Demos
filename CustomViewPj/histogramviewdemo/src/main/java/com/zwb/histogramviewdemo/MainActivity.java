package com.zwb.histogramviewdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private HistogramView hgv;
    private int where = 1;
    private List<Integer> list = new ArrayList<>();
    private Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = new Random();
        hgv = (HistogramView)findViewById(R.id.hgv);
        handler.post(runnable);
    }
    private static Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            initData();
            initWhere();
            hgv.setData(where,list);
            handler.postDelayed(this,1500);
        }
    };

    private void initData(){
        list.clear();
        for (int i = 0;i<6;i++){
            list.add(random.nextInt(200000));
        }
    }

    private void initWhere(){
        where = random.nextInt(3)+1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
