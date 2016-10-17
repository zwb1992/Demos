package com.iwanna.learn.model;

import java.util.List;

/**
 * Created by zwb
 * Description 课程详情
 * Date 16/8/31.
 */
public class CourseDetails {

    /**
     * isTeacher : false
     * CourseID : 4
     * CourseHeadImg : Images/kecheng/201607/2016071818040444.png
     * UserID : 5
     * CourseName : 公共课课程
     * TeachNumber : 4-6人
     * TeachingObject : 4-7岁,8-11岁,12-15岁,16-20岁
     * FreeStudy : true
     * TeachAdress : 深圳市南山区深圳湾体育中心L135
     * LongitudeCoordinates : 114.066112
     * LatitudeCoordinates : 22.548515
     * AgencyID : 1
     * AgencyName : 万国击剑
     * Img : Images/touxiang/201607/201607181723562356.png
     * CourseIntroducing : 改课程为一年期，一年中无限期课时。
     公共课程是根据学员击剑程度的不同提供给各个级别的学员集体参加的课程，训练课程细分为三种课程：技术课程、体能课程、实战课程。课程长度为90分钟/节。
     三种课程的安排是以时段为单位进行轮换循环。周二至周五上体能课程，则本周六至周日技术课程，在下周二云南周五为实战课程，以此类推循环往复。
     * SiteImg : [{"Id":40,"UserId":4,"Type":7,"ImgTittle":"Images/changdi/201607/c68f3709-d6b0-40b3-b23c-0e0d31b165d3_small.png","ImgBig":"Images/changdi/201607/c68f3709-d6b0-40b3-b23c-0e0d31b165d3.png","CreateName":"万国击剑","CreateTime":"2016-07-18T00:00:00"},{"Id":41,"UserId":4,"Type":7,"ImgTittle":"Images/changdi/201607/5d59909d-bd94-40e7-975e-f1d22cfcfd14_small.png","ImgBig":"Images/changdi/201607/5d59909d-bd94-40e7-975e-f1d22cfcfd14.png","CreateName":"万国击剑","CreateTime":"2016-07-18T00:00:00"},{"Id":42,"UserId":4,"Type":7,"ImgTittle":"Images/changdi/201607/d6c577d1-5ab9-4f0a-aaa7-337ee430eb51_small.png","ImgBig":"Images/changdi/201607/d6c577d1-5ab9-4f0a-aaa7-337ee430eb51.png","CreateName":"万国击剑","CreateTime":"2016-07-18T00:00:00"},{"Id":43,"UserId":4,"Type":7,"ImgTittle":"Images/changdi/201607/b834fa6d-3284-45a7-96d8-da1f61d24a66_small.png","ImgBig":"Images/changdi/201607/b834fa6d-3284-45a7-96d8-da1f61d24a66.png","CreateName":"万国击剑","CreateTime":"2016-07-18T00:00:00"}]
     * Price : [{"id":10,"CourseID":4,"ServiceType":"学生上门","Price":16670,"CourseTime":365,"ClassTime":90}]
     * Content : null
     * ContactPerson : 女士
     * ContactPhone : 0755-86207035
     * "TeacherID": 2,
     *"TeacherName": "super kids",
     *"TeacherHeadImg": "Images/touxiang/201607/20160718160811811.png",
     * UserImg : null
     */

    private boolean isTeacher;
    private boolean isCollect;
    private int CourseID;
    private String CourseHeadImg;
    private int UserID;
    private String CourseName;
    private String TeachNumber;
    private String TeachingObject;
    private boolean FreeStudy;
    private String TeachAdress;
    private double LongitudeCoordinates;
    private double LatitudeCoordinates;
    private int AgencyID;
    private String AgencyName;
    private String Img;
    private String CourseIntroducing;
    private String ContactPerson;
    private String ContactPhone;
    private String UserImg;
    private int TeacherID;
    private String TeacherName;
    private String TeacherHeadImg;
    private int GlanceOver;
    private int ApplyNumber;
    /**
     * Id : 40
     * UserId : 4
     * Type : 7
     * ImgTittle : Images/changdi/201607/c68f3709-d6b0-40b3-b23c-0e0d31b165d3_small.png
     * ImgBig : Images/changdi/201607/c68f3709-d6b0-40b3-b23c-0e0d31b165d3.png
     * CreateName : 万国击剑
     * CreateTime : 2016-07-18T00:00:00
     */

    private List<SiteImgBean> SiteImg;
    private List<ContentBean> Content;

    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int teacherID) {
        TeacherID = teacherID;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getTeacherHeadImg() {
        return TeacherHeadImg;
    }

    public void setTeacherHeadImg(String teacherHeadImg) {
        TeacherHeadImg = teacherHeadImg;
    }

    /**
     * id : 10
     * CourseID : 4
     * ServiceType : 学生上门
     * Price : 16670
     * CourseTime : 365
     * ClassTime : 90
     */

    private List<PriceBean> Price;

    public boolean isIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }
    public boolean isCollect() {
        return isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getCourseHeadImg() {
        return CourseHeadImg;
    }

    public void setCourseHeadImg(String CourseHeadImg) {
        this.CourseHeadImg = CourseHeadImg;
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

    public String getTeachNumber() {
        return TeachNumber;
    }

    public void setTeachNumber(String TeachNumber) {
        this.TeachNumber = TeachNumber;
    }

    public String getTeachingObject() {
        return TeachingObject;
    }

    public void setTeachingObject(String TeachingObject) {
        this.TeachingObject = TeachingObject;
    }

    public boolean isFreeStudy() {
        return FreeStudy;
    }

    public void setFreeStudy(boolean FreeStudy) {
        this.FreeStudy = FreeStudy;
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

    public double getLatitudeCoordinates() {
        return LatitudeCoordinates;
    }

    public void setLatitudeCoordinates(double LatitudeCoordinates) {
        this.LatitudeCoordinates = LatitudeCoordinates;
    }

    public int getAgencyID() {
        return AgencyID;
    }

    public void setAgencyID(int AgencyID) {
        this.AgencyID = AgencyID;
    }

    public String getAgencyName() {
        return AgencyName;
    }

    public void setAgencyName(String AgencyName) {
        this.AgencyName = AgencyName;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public String getCourseIntroducing() {
        return CourseIntroducing;
    }

    public void setCourseIntroducing(String CourseIntroducing) {
        this.CourseIntroducing = CourseIntroducing;
    }

    public List<ContentBean> getContent() {
        return Content;
    }

    public void setContent(List<ContentBean> Content) {
        this.Content = Content;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String ContactPerson) {
        this.ContactPerson = ContactPerson;
    }

    public String getContactPhone() {
        return ContactPhone;
    }

    public void setContactPhone(String ContactPhone) {
        this.ContactPhone = ContactPhone;
    }

    public String getUserImg() {
        return UserImg;
    }

    public void setUserImg(String UserImg) {
        this.UserImg = UserImg;
    }

    public List<SiteImgBean> getSiteImg() {
        return SiteImg;
    }

    public void setSiteImg(List<SiteImgBean> SiteImg) {
        this.SiteImg = SiteImg;
    }

    public List<PriceBean> getPrice() {
        return Price;
    }

    public void setPrice(List<PriceBean> Price) {
        this.Price = Price;
    }

    public int getGlanceOver() {
        return GlanceOver;
    }

    public void setGlanceOver(int glanceOver) {
        GlanceOver = glanceOver;
    }

    public int getApplyNumber() {
        return ApplyNumber;
    }

    public void setApplyNumber(int applyNumber) {
        ApplyNumber = applyNumber;
    }

    public static class SiteImgBean {
        private int Id;
        private int UserId;
        private int Type;
        private String ImgTittle;
        private String ImgBig;
        private String CreateName;
        private String CreateTime;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getImgTittle() {
            return ImgTittle;
        }

        public void setImgTittle(String ImgTittle) {
            this.ImgTittle = ImgTittle;
        }

        public String getImgBig() {
            return ImgBig;
        }

        public void setImgBig(String ImgBig) {
            this.ImgBig = ImgBig;
        }

        public String getCreateName() {
            return CreateName;
        }

        public void setCreateName(String CreateName) {
            this.CreateName = CreateName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
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
    public static class ContentBean{

        /**
         * CourseID : 1
         * UserID : 1
         * UserName : super kids
         * CommentContent : 对对对
         * UserImg : Images/touxiang/201607/201607181322562256.jpg
         */

        private int CourseID;
        private int UserID;
        private String UserName;
        private String CommentContent;
        private String UserImg;
        private String CreateTime;
        private String UpdateTime;

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

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String updateTime) {
            UpdateTime = updateTime;
        }

        public String getUserImg() {
            return UserImg;
        }

        public void setUserImg(String UserImg) {
            this.UserImg = UserImg;
        }

        @Override
        public String toString() {
            return "ContentBean{" +
                    "CourseID=" + CourseID +
                    ", UserID=" + UserID +
                    ", UserName='" + UserName + '\'' +
                    ", CommentContent='" + CommentContent + '\'' +
                    ", UserImg='" + UserImg + '\'' +
                    ", CreateTime='" + CreateTime + '\'' +
                    ", UpdateTime='" + UpdateTime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CourseDetails{" +
                "isTeacher=" + isTeacher +
                ", CourseID=" + CourseID +
                ", CourseHeadImg='" + CourseHeadImg + '\'' +
                ", UserID=" + UserID +
                ", CourseName='" + CourseName + '\'' +
                ", TeachNumber='" + TeachNumber + '\'' +
                ", TeachingObject='" + TeachingObject + '\'' +
                ", FreeStudy=" + FreeStudy +
                ", TeachAdress='" + TeachAdress + '\'' +
                ", LongitudeCoordinates=" + LongitudeCoordinates +
                ", LatitudeCoordinates=" + LatitudeCoordinates +
                ", AgencyID=" + AgencyID +
                ", AgencyName='" + AgencyName + '\'' +
                ", Img='" + Img + '\'' +
                ", CourseIntroducing='" + CourseIntroducing + '\'' +
                ", Content=" + Content +
                ", ContactPerson='" + ContactPerson + '\'' +
                ", ContactPhone='" + ContactPhone + '\'' +
                ", UserImg='" + UserImg + '\'' +
                ", TeacherID=" + TeacherID +
                ", TeacherName='" + TeacherName + '\'' +
                ", TeacherHeadImg='" + TeacherHeadImg + '\'' +
                ", SiteImg=" + SiteImg +
                ", Price=" + Price +
                '}';
    }
}
