package com.iwanna.learn.model;

/**
 * Created by zwb
 * Description 课程分类
 * Date 16/8/29.
 */
public class CourseFenlei {

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
    private String CreateTime;
    private String UpdateName;
    private String UpdateTime;

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

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getUpdateName() {
        return UpdateName;
    }

    public void setUpdateName(String UpdateName) {
        this.UpdateName = UpdateName;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    @Override
    public String toString() {
        return "CourseFenlei{" +
                "TypeID=" + TypeID +
                ", TypeName='" + TypeName + '\'' +
                ", level=" + level +
                ", ParentID=" + ParentID +
                ", InnerOrderID=" + InnerOrderID +
                ", GlobalOrderID=" + GlobalOrderID +
                ", CreateTime='" + CreateTime + '\'' +
                ", UpdateName='" + UpdateName + '\'' +
                ", UpdateTime='" + UpdateTime + '\'' +
                '}';
    }
}
