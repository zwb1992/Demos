package com.example.zwb.dynamicproxydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.lang.reflect.Proxy;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 动态代理机制
 */
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
    public void onClick() {
        Subject subject = (Subject)Proxy.newProxyInstance(
                Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new DynamicProxy(new RelSubject()));
        Test test = subject.sayHello("world!");
        Log.i("info","====实际返回的test========"+test);
    }
}
