package com.zwb.thirdpartydemo.activity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.zwb.thirdpartydemo.R;
import com.zwb.thirdpartydemo.base.BaseFragment;
import com.zwb.thirdpartydemo.fragment.CommonFragment;
import com.zwb.thirdpartydemo.fragment.CustomeFragment;
import com.zwb.thirdpartydemo.fragment.OtherFragment;
import com.zwb.thirdpartydemo.fragment.ThirdPartyFragment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton rb_common, rb_thirdParty, rb_custom, rb_orther;
    private Map<String, BaseFragment> baseFragments = new HashMap<>();
    private int current = 0;
    private BaseFragment currentFragmnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("info", "==onCreate=====" + savedInstanceState);
        File file = new File(Environment.getExternalStorageDirectory(), "test.txt");
        try {
//            file.createNewFile();
            Uri uri = Uri.fromFile(file);
            Log.e("info", "==uri=====" + uri);
            //file:///storage/emulated/0/test.txt
        } catch (Exception e) {

        }
        initView();
        baseFragments.clear();
        if (savedInstanceState != null) {
            current = savedInstanceState.getInt("current", 0);
            Log.e("info", "==current=====" + current);
            FragmentManager manager = getSupportFragmentManager();
            baseFragments.put("0", (CommonFragment) manager.findFragmentByTag("0"));
            baseFragments.put("1", (ThirdPartyFragment) manager.findFragmentByTag("1"));
            baseFragments.put("2", (CustomeFragment) manager.findFragmentByTag("2"));
            baseFragments.put("3", (OtherFragment) manager.findFragmentByTag("3"));
            currentFragmnt = baseFragments.get(current + "");
            if (currentFragmnt == null) {
                if (current == 0) {
                    currentFragmnt = new CommonFragment();
                } else if (current == 1) {
                    currentFragmnt = new ThirdPartyFragment();
                } else if (current == 2) {
                    currentFragmnt = new CustomeFragment();
                } else {
                    currentFragmnt = new OtherFragment();
                }
                baseFragments.put("" + current, currentFragmnt);
            }
        } else {
            initFragment();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("info", "----onSaveInstanceState------" + outState);
        outState.putInt("current", current);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_common:
                current = 0;
                break;
            case R.id.rb_thirdParty:
                current = 1;
                break;
            case R.id.rb_custom:
                current = 2;
                break;
            case R.id.rb_orther:
                current = 3;
                break;
        }
        showFragment(baseFragments.get(current + ""));
    }

    private void initView() {
        rb_common = (RadioButton) findViewById(R.id.rb_common);
        rb_common.setOnClickListener(this);
        rb_thirdParty = (RadioButton) findViewById(R.id.rb_thirdParty);
        rb_thirdParty.setOnClickListener(this);
        rb_custom = (RadioButton) findViewById(R.id.rb_custom);
        rb_custom.setOnClickListener(this);
        rb_orther = (RadioButton) findViewById(R.id.rb_orther);
        rb_orther.setOnClickListener(this);
    }

    private void initFragment() {
        baseFragments.put("0", new CommonFragment());
        baseFragments.put("1", new ThirdPartyFragment());
        baseFragments.put("2", new CustomeFragment());
        baseFragments.put("3", new OtherFragment());
        current = 0;
        currentFragmnt = baseFragments.get(current + "");
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment, currentFragmnt, "" + current).commit();
    }

    private void showFragment(BaseFragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (to == null) {
            if (current == 0) {
                to = new CommonFragment();
            } else if (current == 1) {
                to = new ThirdPartyFragment();
            } else if (current == 2) {
                to = new CustomeFragment();
            } else {
                to = new OtherFragment();
            }
            baseFragments.put("" + current, to);
        }
        Log.e("info", "----showFragment------" + to.isAdded());
        if (!to.isAdded()) {
            transaction.hide(currentFragmnt);
            transaction.add(R.id.fl_fragment, to, current + "");
            transaction.commit();
        } else {
            transaction.hide(currentFragmnt);
            transaction.show(to);
            transaction.commit();
        }
        currentFragmnt = to;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.e("info", "==onSaveInstanceState=====");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.e("info", "====onRestoreInstanceState===");
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("info", "==onDestroy=====");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("info", "==onStart=====");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("info", "==onRestart=====");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("info", "==onResume=====");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("info", "==onPause=====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("info", "==onStop=====");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("info", "==onConfigurationChanged=====");
    }
}
