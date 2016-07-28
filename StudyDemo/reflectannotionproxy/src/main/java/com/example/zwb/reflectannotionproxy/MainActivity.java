package com.example.zwb.reflectannotionproxy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Proxy;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//反射+注解+动态代理
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bt)
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt)
    public void onClick(View view) {
        NetUtil netUtil = new NetUtil();
        final NetApi netApi = (NetApi)Proxy.newProxyInstance(NetApi.class.getClassLoader(),new Class[]{NetApi.class},netUtil);

        Result result = netApi.getData("get方法","这是一个get方法");
        Toast.makeText(this,result.toString(),Toast.LENGTH_SHORT).show();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                Result result = netApi.postData("post方法","现在执行的是post方法");
                Toast.makeText(MainActivity.this,result.toString(),Toast.LENGTH_SHORT).show();
            }
        },3000);
    }
}
