package com.example.zwb.databindingdemo;

import android.databinding.BaseObservable;

import com.example.zwb.databindingdemo.BR;

/***************************************
 * Author zhouweibin
 * Description .用这种方式需要继承BaseObservable，并使用notifyPropertyChanged(BR._all)方法;
 * Date:2016/5/24
 ***************************************/
public class User1 extends BaseObservable {

    private String name;
    private int id;

    public User1() {
    }

    public User1(String name, int id) {
        this.name = name;
        this.id = id;
        notifyPropertyChanged(BR._all);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR._all);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
