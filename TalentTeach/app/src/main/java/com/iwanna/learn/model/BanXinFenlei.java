package com.iwanna.learn.model;

/**
 * Created by zwb
 * Description
 * Date 16/8/30.
 */
public class BanXinFenlei {

    /**
     * ClassType : 0
     * TeachNumber : 全部
     */

    private int ClassType;
    private String TeachNumber;

    public int getClassType() {
        return ClassType;
    }

    public void setClassType(int ClassType) {
        this.ClassType = ClassType;
    }

    public String getTeachNumber() {
        return TeachNumber;
    }

    public void setTeachNumber(String TeachNumber) {
        this.TeachNumber = TeachNumber;
    }

    @Override
    public String toString() {
        return "BanXinFenlei{" +
                "ClassType=" + ClassType +
                ", TeachNumber='" + TeachNumber + '\'' +
                '}';
    }
}
