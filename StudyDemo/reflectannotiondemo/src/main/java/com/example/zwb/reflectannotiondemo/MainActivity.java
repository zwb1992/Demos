package com.example.zwb.reflectannotiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * java反射与注解
 */
@ContentView(layout = R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewInject(id = R.id.tv)
    private TextView tv;
    @ViewInject(id = R.id.bt)
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.init(this);
//        tv.setOnClickListener(this);
//        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str;
        if(v instanceof TextView){
            str = ((TextView)v).getText().toString();
        }else if(v instanceof Button){
            str = ((Button)v).getText().toString();
        }else{
            str = "空";
        }
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
    @OnClick({R.id.tv,R.id.bt})
    public void onMyClick(View v){
        String str;
        if(v instanceof TextView){
            str = ((TextView)v).getText().toString();
        }else if(v instanceof Button){
            str = ((Button)v).getText().toString();
        }else{
            str = "空";
        }
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
