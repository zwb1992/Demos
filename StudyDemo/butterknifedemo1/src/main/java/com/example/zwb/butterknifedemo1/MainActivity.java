package com.example.zwb.butterknifedemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fl)
    FrameLayout fl;
    private TextView tv1;
    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        View view = View.inflate(this, R.layout.view_layout, null);
        tv1 = ButterKnife.findById(view,R.id.tv1);
        tv2 = ButterKnife.findById(view,R.id.tv2);
        tv1.setText("被butterKnife设置的第一个");
        tv2.setText("被butterKnife设置的第二个");

        fl.addView(view);
    }
}
