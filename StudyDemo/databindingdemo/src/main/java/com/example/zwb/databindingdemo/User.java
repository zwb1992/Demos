package com.example.zwb.databindingdemo;

import android.databinding.BaseObservable;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/24
 ***************************************/
public class User extends BaseObservable {

    private String name;
    private int id;

    public User() {
    }

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        notifyPropertyChanged(com.example.zwb.databindingdemo.BR.user);
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
        notifyPropertyChanged(com.example.zwb.databindingdemo.BR._all);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
