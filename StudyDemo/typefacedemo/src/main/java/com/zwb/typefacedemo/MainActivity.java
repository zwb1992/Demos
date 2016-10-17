package com.zwb.typefacedemo;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1,tv2,tv3,tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);

        Typeface a = Typeface.createFromAsset(getAssets(),"fonts/a.ttf");
        Typeface b = Typeface.createFromAsset(getAssets(),"fonts/b.ttf");
        Typeface c = Typeface.createFromAsset(getAssets(),"fonts/c.ttf");
        Typeface d = Typeface.createFromAsset(getAssets(),"fonts/d.ttf");

        tv1.setTypeface(a);
        tv2.setTypeface(b);
        tv3.setTypeface(c);
        tv4.setTypeface(d);
    }
}
