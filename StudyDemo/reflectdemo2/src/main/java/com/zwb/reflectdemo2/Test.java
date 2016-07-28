package com.zwb.reflectdemo2;

import android.util.Log;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/26
 ***************************************/
public class Test {
    private String name;
    private int id;

    public Test(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Test() {
    }

    public void print(String str) {
        System.out.print("====print====" + str);
    }
    public static void print1(String str) {
        Log.i("info","==静态==print====" + str);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
