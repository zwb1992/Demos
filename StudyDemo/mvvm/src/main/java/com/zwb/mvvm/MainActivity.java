package com.zwb.mvvm;

import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainActivity, MainVM> {


    @Bind(R.id.bt)
    Button bt;

    @Override
    public int tellMeLayout() {
        return R.layout.activity_main;
    }

    @Override
    public Class<MainVM> getVMClass() {
        return MainVM.class;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        bt.setText("测试MVVM框架");
    }

    @OnClick(R.id.bt)
    public void onClick() {
        getViewModel().toast();
    }
}
