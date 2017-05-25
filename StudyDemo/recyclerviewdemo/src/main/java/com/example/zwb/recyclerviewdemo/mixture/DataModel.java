package com.example.zwb.recyclerviewdemo.mixture;

/**
 * Created by zwb
 * Description
 * Date 17/5/19.
 */

public class DataModel {

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;

    private String title;
    private String content;
    private int image;
    private int type ;

    public DataModel(String title, String content, int image, int type) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image=" + image +
                '}';
    }
}
