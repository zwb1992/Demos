package com.iwanna.learn.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.iwanna.learn.R;
import com.iwanna.learn.Service.LocationService;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.WelcomeVM;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class WelcomeActivity extends BaseActivity<WelcomeActivity,WelcomeVM> {

    @Override
    public Class<WelcomeVM> getVMClass() {
        return null;
    }

    @Override
    public int tellMeLayout() {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("isFirst",true);
        startActivity(intent);
        finish();
        return R.layout.activity_wlcome;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

}
