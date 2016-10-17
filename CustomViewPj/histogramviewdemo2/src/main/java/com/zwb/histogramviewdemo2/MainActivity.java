package com.zwb.histogramviewdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private HistogramView hgv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hgv = (HistogramView)findViewById(R.id.hgv);
        List<String> list = new ArrayList<>();
        list.add("100");
        list.add("50");
        list.add("0");
        hgv.setyTextList(list);
        hgv.setMaxNum(100);
        List<Integer> integers = new ArrayList<>();
        for (int i = 0;i<100;i++){
            integers.add(new Random().nextInt(100));
        }
        hgv.setData(integers);
        hgv.setLeftMaxLengthText("500");
    }
}
