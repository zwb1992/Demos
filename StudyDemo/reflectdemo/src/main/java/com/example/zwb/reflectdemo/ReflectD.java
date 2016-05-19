package com.example.zwb.reflectdemo;

import android.util.Log;

/***************************************
 * Author zhouweibin
 * Description .反射测试的类
 * Date:2016/5/18
 ***************************************/
public class ReflectD {
    private String name;
    private int id;

    public ReflectD(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public ReflectD() {
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

    @Override
    public String toString() {
        return "ReflectD{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
    private void print(){
        Log.i("info","reflectD-----------print");
    }
}
