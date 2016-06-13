package com.zwb.talentteach.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.zwb.talentteach.R;
import com.zwb.talentteach.http.Net;
import com.zwb.talentteach.viewmodel.MainVM;
import com.zwb.zwbframe.http.HttpListener;
import com.zwb.zwbframe.http.HttpRequest;
import com.zwb.zwbframe.mvvm.AbstractBaseActivity;
import com.zwb.zwbframe.mvvm.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainActivity, MainVM> implements HttpListener {

    @Bind(R.id.image)
    ImageView image;

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

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("info", "=====onErrorResponse======" + error.toString());
    }

    @Override
    public void onResponse(String response) {
        Log.i("info", "=====onResponse======" + response.toString());
    }

    @OnClick(R.id.bt)
    public void onClick() {
        image.setTag("https://www.baidu.com/img/bd_logo1.png");
        Net.imageLoader("https://www.baidu.com/img/bd_logo1.png", image, R.mipmap.ic_launcher, R.mipmap.ic_launcher, HttpRequest.ImageShapeType.ROUND);
        getViewModel().getData();
    }
}
