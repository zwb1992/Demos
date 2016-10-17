package com.iwanna.learn.model;

import java.io.Serializable;

/**
 * Created by zwb
 * Description 活动列表
 * Date 16/8/28.
 */
public class Actitivities implements Serializable{

    /**
     * ActivityHeadImg : null
     * ActivityID : 1
     * ActivityName : 禁地广场跳舞
     * StartTime : 2016-08-18T00:00:00
     * EndTime : 2016-08-19T00:00:00
     * ActivityAddress : 禁地广场
     * ActivityContent : 详情
     * States : true
     * type : null
     */

    private String ActivityHeadImg;
    private String ActivityImg;
    private int ActivityID;
    private int JoinNumber;
    private String ActivityName;
    private String StartTime;
    private String EndTime;
    private String ActivityAddress;
    private String ActivityContent;
    private boolean States;
    private String type;

    public String getActivityHeadImg() {
        return ActivityHeadImg;
    }

    public void setActivityHeadImg(String ActivityHeadImg) {
        this.ActivityHeadImg = ActivityHeadImg;
    }

    public String getActivityImg() {
        return ActivityImg;
    }

    public void setActivityImg(String activityImg) {
        ActivityImg = activityImg;
    }

    public int getJoinNumber() {
        return JoinNumber;
    }

    public void setJoinNumber(int joinNumber) {
        JoinNumber = joinNumber;
    }

    public int getActivityID() {
        return ActivityID;
    }

    public void setActivityID(int ActivityID) {
        this.ActivityID = ActivityID;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String ActivityName) {
        this.ActivityName = ActivityName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public String getActivityAddress() {
        return ActivityAddress;
    }

    public void setActivityAddress(String ActivityAddress) {
        this.ActivityAddress = ActivityAddress;
    }

    public String getActivityContent() {
        return ActivityContent;
    }

    public void setActivityContent(String ActivityContent) {
        this.ActivityContent = ActivityContent;
    }

    public boolean isStates() {
        return States;
    }

    public void setStates(boolean States) {
        this.States = States;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Actitivities{" +
                "ActivityHeadImg='" + ActivityHeadImg + '\'' +
                ", ActivityImg='" + ActivityImg + '\'' +
                ", ActivityID=" + ActivityID +
                ", JoinNumber=" + JoinNumber +
                ", ActivityName='" + ActivityName + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", ActivityAddress='" + ActivityAddress + '\'' +
                ", ActivityContent='" + ActivityContent + '\'' +
                ", States=" + States +
                ", type='" + type + '\'' +
                '}';
    }
}
