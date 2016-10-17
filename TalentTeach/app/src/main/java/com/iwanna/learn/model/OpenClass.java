package com.iwanna.learn.model;

import java.util.List;

/**
 * Created by zwb
 * Description 我的开课表
 * Date 16/9/2.
 */
public class OpenClass {

    /**
     * CourseHeadImg : Images/kecheng/201607/201607181617291729.jpg
     * CourseID : 1
     * CourseName : super kids篮球
     * ApplyNumber : 0
     * GlanceOver : 135
     * Price : [{"id":6,"CourseID":1,"ServiceType":"学生上门","Price":2880,"CourseTime":25,"ClassTime":60}]
     * comment : [{"id":6,"CourseID":1,"UserID":1,"UserName":"super kids","CommentContent":"对对对","ParentNode":0,"Level":1,"CreateTime":"2016-09-02T15:37:35.94","UpdateTime":"2016-09-02T15:37:35.94","UodateName":"super kids"},{"id":8,"CourseID":1,"UserID":1,"UserName":"super kids","CommentContent":"不错不错","ParentNode":0,"Level":1,"CreateTime":"2016-09-02T16:24:30.857","UpdateTime":"2016-09-02T16:24:30.857","UodateName":"super kids"},{"id":9,"CourseID":1,"UserID":1,"UserName":"super kids","CommentContent":"下次v好紧急集合","ParentNode":0,"Level":1,"CreateTime":"2016-09-02T16:26:51.977","UpdateTime":"2016-09-02T16:26:51.977","UodateName":"super kids"},{"id":10,"CourseID":1,"UserID":1,"UserName":"super kids","CommentContent":"吃vvvv滚滚滚","ParentNode":0,"Level":1,"CreateTime":"2016-09-02T16:28:28.073","UpdateTime":"2016-09-02T16:28:28.073","UodateName":"super kids"},{"id":11,"CourseID":1,"UserID":1,"UserName":"super kids","CommentContent":"，吃吃吃","ParentNode":0,"Level":1,"CreateTime":"2016-09-02T16:29:41.857","UpdateTime":"2016-09-02T16:29:41.857","UodateName":"super kids"}]
     * sum : 5
     * UserID : 1
     */

    private String CourseHeadImg;
    private int CourseID;
    private String CourseName;
    private int ApplyNumber;
    private int GlanceOver;
    private int sum;
    private int UserID;
    /**
     * id : 6
     * CourseID : 1
     * ServiceType : 学生上门
     * Price : 2880
     * CourseTime : 25
     * ClassTime : 60
     */

    private List<PriceBean> Price;
    /**
     * id : 6
     * CourseID : 1
     * UserID : 1
     * UserName : super kids
     * CommentContent : 对对对
     * ParentNode : 0
     * Level : 1
     * CreateTime : 2016-09-02T15:37:35.94
     * UpdateTime : 2016-09-02T15:37:35.94
     * UodateName : super kids
     */

    private List<CommentBean> comment;

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

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public int getApplyNumber() {
        return ApplyNumber;
    }

    public void setApplyNumber(int ApplyNumber) {
        this.ApplyNumber = ApplyNumber;
    }

    public int getGlanceOver() {
        return GlanceOver;
    }

    public void setGlanceOver(int GlanceOver) {
        this.GlanceOver = GlanceOver;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public List<PriceBean> getPrice() {
        return Price;
    }

    public void setPrice(List<PriceBean> Price) {
        this.Price = Price;
    }

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
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

    public static class CommentBean {
        private int id;
        private int CourseID;
        private int UserID;
        private String UserName;
        private String CommentContent;
        private int ParentNode;
        private int Level;
        private String CreateTime;
        private String UpdateTime;
        private String UodateName;

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

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int UserID) {
            this.UserID = UserID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getCommentContent() {
            return CommentContent;
        }

        public void setCommentContent(String CommentContent) {
            this.CommentContent = CommentContent;
        }

        public int getParentNode() {
            return ParentNode;
        }

        public void setParentNode(int ParentNode) {
            this.ParentNode = ParentNode;
        }

        public int getLevel() {
            return Level;
        }

        public void setLevel(int Level) {
            this.Level = Level;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getUodateName() {
            return UodateName;
        }

        public void setUodateName(String UodateName) {
            this.UodateName = UodateName;
        }

        @Override
        public String toString() {
            return "CommentBean{" +
                    "id=" + id +
                    ", CourseID=" + CourseID +
                    ", UserID=" + UserID +
                    ", UserName='" + UserName + '\'' +
                    ", CommentContent='" + CommentContent + '\'' +
                    ", ParentNode=" + ParentNode +
                    ", Level=" + Level +
                    ", CreateTime='" + CreateTime + '\'' +
                    ", UpdateTime='" + UpdateTime + '\'' +
                    ", UodateName='" + UodateName + '\'' +
                    '}';
        }
    }
}
