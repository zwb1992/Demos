package com.zwb.activityfragmentdemo;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    private BlankFragment blankFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("info","==MainActivity=======onCreate==="+this);
        Log.d("info","==MainActivity=======onCreate======savedInstanceState=="+savedInstanceState);
        manager = getSupportFragmentManager();
        blankFragment = new BlankFragment();
        manager.beginTransaction().add(R.id.fl_fragment,blankFragment,"fr").commitAllowingStateLoss();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("info","==MainActivity=======onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("info","==MainActivity=======onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("info","==MainActivity=======onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("info","==MainActivity=======onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("info","==MainActivity=======onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("info","==MainActivity=======onSaveInstanceState");
    }
}
