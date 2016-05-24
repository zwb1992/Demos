package com.example.zwb.rxjavademo;

import java.util.List;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/24
 ***************************************/
public class Student {
    private String name;
    private List<Course> list;

    public Student() {
    }

    public Student(String name, List<Course> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }
}
