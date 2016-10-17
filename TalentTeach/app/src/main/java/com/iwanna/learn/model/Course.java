package com.iwanna.learn.model;

import java.util.List;

/**
 * Created by zwb
 * Description 课程列表
 * Date 16/8/28.
 */
public class Course {

    /**
     * AgencyID : 0
     * UserID : 1
     * AgencyName : 个人
     * CourseID : 1
     * CourseName : super kids篮球
     * HobbyTypeSId : 0
     * TeachAdress : 深圳市南山区南油大厦东北证卷3楼儿童运动馆
     * LongitudeCoordinates : 22.548515
     * LatitudeCoordinates : 114.066112
     * Img : Images/kecheng/201607/201607181617291729.jpg
     * Price : [{"id":6,"CourseID":1,"ServiceType":"学生上门","Price":2880,"CourseTime":25,"ClassTime":60}]
     */

    private int AgencyID;
    private int UserID;
    private String AgencyName;
    private int CourseID;
    private String CourseName;
    private int HobbyTypeSId;
    private String TeachAdress;
    private double LongitudeCoordinates;
    private double LatitudeCoordinates;
    private String Img;
    private String CourseHeadImg;
    /**
     * id : 6
     * CourseID : 1
     * ServiceType : 学生上门
     * Price : 2880
     * CourseTime : 25
     * ClassTime : 60
     */

    private List<PriceBean> Price;

    public int getAgencyID() {
        return AgencyID;
    }

    public void setAgencyID(int AgencyID) {
        this.AgencyID = AgencyID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getAgencyName() {
        return AgencyName;
    }

    public void setAgencyName(String AgencyName) {
        this.AgencyName = AgencyName;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public int getHobbyTypeSId() {
        return HobbyTypeSId;
    }

    public void setHobbyTypeSId(int HobbyTypeSId) {
        this.HobbyTypeSId = HobbyTypeSId;
    }

    public String getTeachAdress() {
        return TeachAdress;
    }

    public void setTeachAdress(String TeachAdress) {
        this.TeachAdress = TeachAdress;
    }

    public double getLongitudeCoordinates() {
        return LongitudeCoordinates;
    }

    public void setLongitudeCoordinates(double LongitudeCoordinates) {
        this.LongitudeCoordinates = LongitudeCoordinates;
    }

    public String getCourseHeadImg() {
        return CourseHeadImg;
    }

    public void setCourseHeadImg(String courseHeadImg) {
        CourseHeadImg = courseHeadImg;
    }

    public double getLatitudeCoordinates() {
        return LatitudeCoordinates;
    }

    public void setLatitudeCoordinates(double LatitudeCoordinates) {
        this.LatitudeCoordinates = LatitudeCoordinates;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public List<PriceBean> getPrice() {
        return Price;
    }

    public void setPrice(List<PriceBean> Price) {
        this.Price = Price;
    }

    public static class PriceBean {
        private int id;
        private int CourseID;
        private String ServiceType;
        private int Price;
        private int CourseTime;
        private int ClassTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCourseID() {
            return CourseID;
        }

        public void setCourseID(int CourseID) {
            this.CourseID = CourseID;
        }

        public String getServiceType() {
            return ServiceType;
        }

        public void setServiceType(String ServiceType) {
            this.ServiceType = ServiceType;
        }

        public int getPrice() {
            return Price;
        }

        public void setPrice(int Price) {
            this.Price = Price;
        }

        public int getCourseTime() {
            return CourseTime;
        }

        public void setCourseTime(int CourseTime) {
            this.CourseTime = CourseTime;
        }

        public int getClassTime() {
            return ClassTime;
        }

        public void setClassTime(int ClassTime) {
            this.ClassTime = ClassTime;
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "AgencyID=" + AgencyID +
                ", UserID=" + UserID +
                ", AgencyName='" + AgencyName + '\'' +
                ", CourseID=" + CourseID +
                ", CourseName='" + CourseName + '\'' +
                ", HobbyTypeSId=" + HobbyTypeSId +
                ", TeachAdress='" + TeachAdress + '\'' +
                ", LongitudeCoordinates=" + LongitudeCoordinates +
                ", LatitudeCoordinates=" + LatitudeCoordinates +
                ", Img='" + Img + '\'' +
                ", CourseHeadImg='" + CourseHeadImg + '\'' +
                ", Price=" + Price +
                '}';
    }
}
