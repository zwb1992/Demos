package com.iwanna.learn.model;

/**
 * Created by zwb
 * Description
 * Date 16/9/1.
 */
public class JigouIntroduce {

    /**
     * AgencyID : 1
     * UserID : 5
     * AgencyName : 万国击剑
     * AgencyLegalPerson : 晨银
     * ContactInformation : 15669085533
     * AgencyIntroduction : 1、国际上规模最大、设备最完善的击剑运动专业培训机构；
     2、深圳南山中心坐落于深圳湾体育中心，场馆面积4000平方米，拥有22条国际比赛标准剑道；
     3、深圳福田中心左路与深圳市体育中心，场馆面积13000平方米，拥有24条比晒标准剑道；
     4、有前国家队主教练和海外知名教练带领的80余人的精英教练团队。
     * Adress : 深圳市南山区深圳湾体育中心L135
     * LongitudeCoordinates : 114.066112
     * LatitudeCoordinates : 22.548515
     * CreateTime : 2016-07-18T17:23:56.123
     * UpdateName : null
     * UpdateTime : null
     */

    private int AgencyID;
    private int UserID;
    private String AgencyName;
    private String AgencyLegalPerson;
    private String ContactInformation;
    private String AgencyIntroduction;
    private String Adress;
    private String LongitudeCoordinates;
    private String LatitudeCoordinates;
    private String CreateTime;
    private String UpdateName;
    private String UpdateTime;

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

    public String getAgencyLegalPerson() {
        return AgencyLegalPerson;
    }

    public void setAgencyLegalPerson(String AgencyLegalPerson) {
        this.AgencyLegalPerson = AgencyLegalPerson;
    }

    public String getContactInformation() {
        return ContactInformation;
    }

    public void setContactInformation(String ContactInformation) {
        this.ContactInformation = ContactInformation;
    }

    public String getAgencyIntroduction() {
        return AgencyIntroduction;
    }

    public void setAgencyIntroduction(String AgencyIntroduction) {
        this.AgencyIntroduction = AgencyIntroduction;
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
}
