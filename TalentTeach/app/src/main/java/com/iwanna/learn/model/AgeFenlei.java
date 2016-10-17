package com.iwanna.learn.model;

/**
 * Created by zwb
 * Description
 * Date 16/8/30.
 */
public class AgeFenlei {

    /**
     * TeachingObject : 全部
     * number : 00
     */

    private String TeachingObject;
    private String number;

    public String getTeachingObject() {
        return TeachingObject;
    }

    public void setTeachingObject(String TeachingObject) {
        this.TeachingObject = TeachingObject;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "AgeFenlei{" +
                "TeachingObject='" + TeachingObject + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
