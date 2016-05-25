package com.example.zwb.databindingdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zwb.databindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User("周伟斌", 1);
        binding.setUser(user);
    }

    private int i = 1;
    public void bindData(View view) {
        binding.getUser().setName("数据第"+i+"次绑定");
        i++;
    }
}
