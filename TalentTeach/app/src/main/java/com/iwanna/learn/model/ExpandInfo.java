package com.iwanna.learn.model;

/**
 * Created by zwb
 * Description 首页推广内容
 * Date 16/8/11.
 */
public class ExpandInfo {

    /**
     * TypeName : 钢琴
     * StypeId : 50
     * img : /Images/leibie/201608/201608111436533653.jpg
     * Introduce : 有“乐器之王”的美称
     * sort : 1
     */

    private String TypeName;
    private int StypeId;
    private String img;
    private String Introduce;
    private int sort;

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public int getStypeId() {
        return StypeId;
    }

    public void setStypeId(int StypeId) {
        this.StypeId = StypeId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String Introduce) {
        this.Introduce = Introduce;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "ExpandInfo{" +
                "TypeName='" + TypeName + '\'' +
                ", StypeId=" + StypeId +
                ", img='" + img + '\'' +
                ", Introduce='" + Introduce + '\'' +
                ", sort=" + sort +
                '}';
    }
}
