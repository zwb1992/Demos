package com.example.zwb.enumdemo;

import android.util.Log;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/10
 ***************************************/
public enum MyEnum {
    instance;

    MyEnum() {
        Log.i("info","enum的构造函数");
    }
}
