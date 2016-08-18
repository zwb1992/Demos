package com.iwanna.learn.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.utils.FragmentFactory;
import com.iwanna.learn.utils.SystemBarUtils;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.MainVM;
import com.zwb.zwbframe.http.HttpRequest;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainActivity, MainVM> {

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    private FragmentManager fragmentManager;

    @Override
    public int tellMeLayout() {
        SystemBarUtils.initSystemBar(this, R.color.white);
        return R.layout.activity_main;
    }

    @Override
    public Class<MainVM> getVMClass() {
        return MainVM.class;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        imgBack.setVisibility(View.GONE);
        tvTitle.setBackgroundResource(R.mipmap.logo);
        fragmentManager = getSupportFragmentManager();
        FragmentFactory.showFragment(0, fragmentManager);
    }

   /* @OnClick(R.id.bt)
    public void onClick() {
        image.setTag("https://www.baidu.com/img/bd_logo1.png");
        Net.imageLoader("https://www.baidu.com/img/bd_logo1.png", image, R.mipmap.ic_launcher, R.mipmap.ic_launcher, HttpRequest.ImageShapeType.ROUND);
        getViewModel().getData();
    }*/

    @OnClick({R.id.rb_find, R.id.rb_index, R.id.rb_activity, R.id.rb_mine,R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_index:
                FragmentFactory.showFragment(0, fragmentManager);
                break;
            case R.id.rb_find:
                FragmentFactory.showFragment(1, fragmentManager);
                break;
            case R.id.rb_activity:
                FragmentFactory.showFragment(2, fragmentManager);
                break;
            case R.id.rb_mine:
                FragmentFactory.showFragment(3, fragmentManager);
                break;
            case R.id.tv_right:

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
