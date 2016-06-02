package com.zwb.mvpdemo2.view.activity;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

import com.zwb.mvpdemo2.R;
import com.zwb.mvpdemo2.base.BaseActivity;
import com.zwb.mvpdemo2.presenter.MainPresenter;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainActivity, MainPresenter> {

    @Bind(R.id.tv)
    Button tv;

    @Override
    public Class<MainPresenter> getPresenterClass() {
        return MainPresenter.class;
    }

    @Override
    protected int tellMeLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tv.setText("点击一下!");
    }

    @OnClick(R.id.tv)
    public void onClick() {
        getPresenter().show();
    }
}
