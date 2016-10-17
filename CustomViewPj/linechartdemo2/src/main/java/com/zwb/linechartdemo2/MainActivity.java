package com.zwb.linechartdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LineChartView lct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lct = (LineChartView)findViewById(R.id.lct);
        List<String> yList = new ArrayList<>();
        yList.add("500");
        yList.add("250");
        yList.add("0");
        lct.setYTextList(yList);

        List<String> xList = new ArrayList<>();
        xList.add("01");
        xList.add("02");
        xList.add("03");
        xList.add("04");
        xList.add("05");
        xList.add("06");
        xList.add("07");
        xList.add("08");
        xList.add("09");
        xList.add("10");
        xList.add("11");
        xList.add("12");
        lct.setXTextList(xList);

        List<Float> numbers = new ArrayList<>();
        numbers.add(0.3f);
        numbers.add(0.2f);
        numbers.add(0.0f);
        numbers.add(0.0f);
        numbers.add(0.3f);
        numbers.add(0.5f);
        numbers.add(0.4f);
        numbers.add(0.2f);
        numbers.add(0.0f);
        numbers.add(0.1f);
        numbers.add(0.6f);
        numbers.add(1.0f);
        lct.setData(numbers);

        lct.setLeftMaxLengthText("500");
        lct.setBottomMaxLengthText("01");
        lct.setBottomLeftText("15");
    }
}
