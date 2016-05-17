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
        Log.i("info","调用真实对象之前:#########################################");
        Log.i("info", "method==========" + method);
        Test object = (Test)method.invoke(subject, args);
        object = new Test();
        Log.i("info","调用真实对象之后:##############object###########################"+object);
        Log.i("info","调用真实对象之后:#########################################\n");
//        return object;
        return null;
    }
}
