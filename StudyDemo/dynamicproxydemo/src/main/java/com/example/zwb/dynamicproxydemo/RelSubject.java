package com.example.zwb.dynamicproxydemo;

import android.util.Log;

/***************************************
 * Author zhouweibin
 * Description .实际 动态代理类
 * Date:2016/5/17
 ***************************************/
public class RelSubject implements Subject {

    @Override
    public Test sayHello(String str) {
        Log.i("info", "RelSubject---------sayHello：" + str);
        Test test = new Test();
        Log.i("info", "=====方法执行中的test=====" + test);
        return test;
    }
}
