package com.example.zwb.butterknifedemo3;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnPageChange;

/**
 * ButterKnife引用资源,选择监听器的摸一个方法
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.img)
    ImageView img;

    @BindString(R.string.app_name)
    String name;
    @BindDrawable(R.mipmap.ic_launcher)
    Drawable drawable;
    @Bind(R.id.cb)
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tv.setText(name);
        img.setImageDrawable(drawable);
        cb.setText(name);
    }
   /* @OnPageChange(value = R.id.cb,callback = OnPageChange.Callback.PAGE_SELECTED)
    void PAGE_SELECTED(int position){

    }*/
    @OnCheckedChanged(R.id.cb)
    void OnCheckedChanged(CheckBox cb,boolean b){
        Log.i("info",""+cb.isChecked()+"================"+b);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("Back");
    }

}
