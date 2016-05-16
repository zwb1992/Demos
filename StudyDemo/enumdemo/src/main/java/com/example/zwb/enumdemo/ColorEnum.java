package com.example.zwb.enumdemo;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/10
 ***************************************/
public enum ColorEnum {
    RED(1, "红色"),BLACK(2,"黑色"),BLUE(3,"蓝色"),WHITE(4,"白色");

    private int id;
    private String name;

    ColorEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
