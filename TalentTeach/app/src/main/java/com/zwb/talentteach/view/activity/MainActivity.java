package com.zwb.talentteach.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.zwb.talentteach.R;
import com.zwb.talentteach.http.Net;
import com.zwb.talentteach.utils.FragmentFactory;
import com.zwb.talentteach.viewmodel.MainVM;
import com.zwb.zwbframe.http.HttpListener;
import com.zwb.zwbframe.http.HttpRequest;
import com.zwb.zwbframe.mvvm.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainActivity, MainVM> implements HttpListener {

    @Bind(R.id.image)
    ImageView image;
    private FragmentManager fragmentManager;

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
        fragmentManager = getSupportFragmentManager();
        FragmentFactory.showFragment(0, fragmentManager);
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

    @OnClick({R.id.rb_find, R.id.rb_talent, R.id.rb_activity, R.id.rb_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_find:
                FragmentFactory.showFragment(0, fragmentManager);
                break;
            case R.id.rb_talent:
                FragmentFactory.showFragment(1, fragmentManager);
                break;
            case R.id.rb_activity:
                FragmentFactory.showFragment(2, fragmentManager);
                break;
            case R.id.rb_mine:
                FragmentFactory.showFragment(3, fragmentManager);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentFactory.clearFragmentMap();
    }

    private long exitTime = 0;

    //连续按两次退出
    @Override
    public boolean onKeyDown(int key, KeyEvent event) {
        switch (key) {
            case KeyEvent.KEYCODE_BACK:
                if ((System.currentTimeMillis() - exitTime) > 3000) { // 按一次返回键
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    // 连续按两次返回键
                    this.finish();
                }
                break;
        }
        return false;
    }
}
