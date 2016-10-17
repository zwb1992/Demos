package com.iwanna.learn.model;

/**
 * Created by zwb
 * Description 偏好信息
 * Date 16/9/13.
 */
public class Likes {

    /**
     * TypeID : 1
     * TypeName : 音乐
     * level : 1
     * ParentID : 0
     * InnerOrderID : 2
     * GlobalOrderID : 2
     * CreateTime : null
     * UpdateName : null
     * UpdateTime : null
     */

    private int TypeID;
    private String TypeName;
    private int level;
    private int ParentID;
    private int InnerOrderID;
    private int GlobalOrderID;
    private Object CreateTime;
    private Object UpdateName;
    private Object UpdateTime;

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int ParentID) {
        this.ParentID = ParentID;
    }

    public int getInnerOrderID() {
        return InnerOrderID;
    }

    public void setInnerOrderID(int InnerOrderID) {
        this.InnerOrderID = InnerOrderID;
    }

    public int getGlobalOrderID() {
        return GlobalOrderID;
    }

    public void setGlobalOrderID(int GlobalOrderID) {
        this.GlobalOrderID = GlobalOrderID;
    }

    public Object getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Object CreateTime) {
        this.CreateTime = CreateTime;
    }

    public Object getUpdateName() {
        return UpdateName;
    }

    public void setUpdateName(Object UpdateName) {
        this.UpdateName = UpdateName;
    }

    public Object getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Object UpdateTime) {
        this.UpdateTime = UpdateTime;
    }
}
