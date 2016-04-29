package com.zwb.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ButterKnife 批量操作控件
 */
public class MainActivity extends AppCompatActivity {

    @Bind({R.id.tv1, R.id.tv2})
    List<TextView> textViews;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        ButterKnife.apply(textViews, new MySetter(), list);
        ButterKnife.apply(textViews, new MyAction());
    }

    private void initData() {
        list = new ArrayList<>();
        list.add("yi");
        list.add("er");
    }
}
