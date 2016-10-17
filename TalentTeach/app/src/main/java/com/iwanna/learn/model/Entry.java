package com.iwanna.learn.model;

import java.util.List;

/**
 * Created by zwb
 * Description 报名表
 * Date 16/9/2.
 */
public class Entry {

    /**
     * CourseHeadImg : Images/kecheng/201608/20160816120917917.jpg
     * CourseID : 48
     * UserID : 1
     * CourseName : 福永暑假班少儿街舞少儿爵士舞 青少年日韩mv培训
     * AgencyName : 丁丁流行舞蹈
     * CourseState : false
     * Price : [{"id":86,"CourseID":48,"ServiceType":"价格面议","Price":0,"CourseTime":0,"ClassTime":0}]
     */

    private String CourseHeadImg;
    private int CourseID;
    private int UserID;
    private String CourseName;
    private String AgencyName;
    private boolean CourseState;
    /**
     * id : 86
     * CourseID : 48
     * ServiceType : 价格面议
     * Price : 0
     * CourseTime : 0
     * ClassTime : 0
     */

    private List<PriceBean> Price;

    public String getCourseHeadImg() {
        return CourseHeadImg;
    }

    public void setCourseHeadImg(String CourseHeadImg) {
        this.CourseHeadImg = CourseHeadImg;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public String getAgencyName() {
        return AgencyName;
    }

    public void setAgencyName(String AgencyName) {
        this.AgencyName = AgencyName;
    }

    public boolean isCourseState() {
        return CourseState;
    }

    public void setCourseState(boolean CourseState) {
        this.CourseState = CourseState;
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
        return "Entry{" +
                "CourseHeadImg='" + CourseHeadImg + '\'' +
                ", CourseID=" + CourseID +
                ", UserID=" + UserID +
                ", CourseName='" + CourseName + '\'' +
                ", AgencyName='" + AgencyName + '\'' +
                ", CourseState=" + CourseState +
                ", Price=" + Price +
                '}';
    }
}
