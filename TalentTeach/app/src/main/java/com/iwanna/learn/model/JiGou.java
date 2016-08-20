package com.iwanna.learn.model;

/**
 * Created by zwb
 * Description 机构
 * Date 16/8/18.
 */
public class JiGou {

    /**
     * AgencyID : 1
     * AgencyName : 万国击剑
     * Adress : 深圳市南山区深圳湾体育中心L135
     * Img : Images/touxiang/201607/201607181723562356.png
     */

    private int AgencyID;
    private String AgencyName;
    private String Adress;
    private String Img;

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

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    @Override
    public String toString() {
        return "JiGou{" +
                "AgencyID=" + AgencyID +
                ", AgencyName='" + AgencyName + '\'' +
                ", Adress='" + Adress + '\'' +
                ", Img='" + Img + '\'' +
                '}';
    }
}
