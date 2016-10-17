package com.iwanna.learn.model;

/**
 * Created by zwb
 * Description
 * Date 16/9/2.
 */
public class Teacher {

    /**
     * TeacherID : 2
     * UserID : 1
     * TeacherName : super kids
     * HobbyTypePId : 26
     * HobbyTypeSId : 30
     * Major : 艺术
     * Seniority : 5
     * SelfIntroduction : SUPER KIDS 课程介绍 运球 传统基本功训练与游戏相结合，寓教于乐，让孩子快乐运动 投篮 掌握正确的投篮技巧，别看我个子小，我投篮可准着呢！
     体质 儿童体适能，用科学的训练方法改善孩子的身体体质。
     * Affiliation : super kids篮球俱乐部
     * UpdateTime : 2016-07-18T15:54:59.253
     * AgencyName : 个人
     * Isdelete : false
     * isdis : false
     * Degree : 2
     * GraduteSchool : 北京航空航天大学
     * Adress : null
     * LongitudeCoordinates : null
     * LatitudeCoordinates : null
     */

    private int TeacherID;
    private int UserID;
    private String TeacherName;
    private int HobbyTypePId;
    private int HobbyTypeSId;
    private String Major;
    private int Seniority;
    private String SelfIntroduction;
    private String Affiliation;
    private String UpdateTime;
    private String AgencyName;
    private boolean Isdelete;
    private boolean isdis;
    private int Degree;
    private String GraduteSchool;
    private String Adress;
    private String LongitudeCoordinates;
    private String LatitudeCoordinates;

    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int TeacherID) {
        this.TeacherID = TeacherID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }

    public int getHobbyTypePId() {
        return HobbyTypePId;
    }

    public void setHobbyTypePId(int HobbyTypePId) {
        this.HobbyTypePId = HobbyTypePId;
    }

    public int getHobbyTypeSId() {
        return HobbyTypeSId;
    }

    public void setHobbyTypeSId(int HobbyTypeSId) {
        this.HobbyTypeSId = HobbyTypeSId;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }

    public int getSeniority() {
        return Seniority;
    }

    public void setSeniority(int Seniority) {
        this.Seniority = Seniority;
    }

    public String getSelfIntroduction() {
        return SelfIntroduction;
    }

    public void setSelfIntroduction(String SelfIntroduction) {
        this.SelfIntroduction = SelfIntroduction;
    }

    public String getAffiliation() {
        return Affiliation;
    }

    public void setAffiliation(String Affiliation) {
        this.Affiliation = Affiliation;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public String getAgencyName() {
        return AgencyName;
    }

    public void setAgencyName(String AgencyName) {
        this.AgencyName = AgencyName;
    }

    public boolean isIsdelete() {
        return Isdelete;
    }

    public void setIsdelete(boolean Isdelete) {
        this.Isdelete = Isdelete;
    }

    public boolean isIsdis() {
        return isdis;
    }

    public void setIsdis(boolean isdis) {
        this.isdis = isdis;
    }

    public int getDegree() {
        return Degree;
    }

    public void setDegree(int Degree) {
        this.Degree = Degree;
    }

    public String getGraduteSchool() {
        return GraduteSchool;
    }

    public void setGraduteSchool(String GraduteSchool) {
        this.GraduteSchool = GraduteSchool;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }

    public String getLongitudeCoordinates() {
        return LongitudeCoordinates;
    }

    public void setLongitudeCoordinates(String LongitudeCoordinates) {
        this.LongitudeCoordinates = LongitudeCoordinates;
    }

    public String getLatitudeCoordinates() {
        return LatitudeCoordinates;
    }

    public void setLatitudeCoordinates(String LatitudeCoordinates) {
        this.LatitudeCoordinates = LatitudeCoordinates;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "TeacherID=" + TeacherID +
                ", UserID=" + UserID +
                ", TeacherName='" + TeacherName + '\'' +
                ", HobbyTypePId=" + HobbyTypePId +
                ", HobbyTypeSId=" + HobbyTypeSId +
                ", Major='" + Major + '\'' +
                ", Seniority=" + Seniority +
                ", SelfIntroduction='" + SelfIntroduction + '\'' +
                ", Affiliation='" + Affiliation + '\'' +
                ", UpdateTime='" + UpdateTime + '\'' +
                ", AgencyName='" + AgencyName + '\'' +
                ", Isdelete=" + Isdelete +
                ", isdis=" + isdis +
                ", Degree=" + Degree +
                ", GraduteSchool='" + GraduteSchool + '\'' +
                ", Adress='" + Adress + '\'' +
                ", LongitudeCoordinates='" + LongitudeCoordinates + '\'' +
                ", LatitudeCoordinates='" + LatitudeCoordinates + '\'' +
                '}';
    }
}
