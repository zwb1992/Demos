package com.example.flowlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.flowlayout.view.FlowLayout;

public class MainActivity extends AppCompatActivity {
    private String[] values = new String[]{"Hello", "Text", "FlowLayout", "ViewGroup", "Hello World",
            "Button Text", "Ok Http", "Glide", "RecyclerView", "Welcome to China",
            "Thank you", "NDK", "MainActivity", "gradle", "Android"};
    private FlowLayout flow_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flow_layout = (FlowLayout) findViewById(R.id.flow_layout);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < values.length; i++) {
            TextView textView = (TextView)inflater.inflate(R.layout.item,flow_layout,false);
            textView.setText(values[i]);
            flow_layout.addView(textView);
        }
    }
}
