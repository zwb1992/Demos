package com.example.zwb.dynamicproxydemo;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/17
 ***************************************/
public class DynamicProxy implements InvocationHandler {
    private Object subject;
    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Test invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i("info", "method==========" + method);
        Test object = (Test)method.invoke(subject, args);
        Log.i("info","=====动态代理中获取的test====="+object);
        object = new Test();
//        return object;
        return null;
    }
}
