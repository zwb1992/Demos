package com.example.zwb.databindingdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.zwb.databindingdemo.MainBinding;


public class MainActivity extends AppCompatActivity {
    private MainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User("周伟斌", 1);
        binding.setViewModel(new ViewModel());
        binding.getViewModel().user.set(user);
    }

}
