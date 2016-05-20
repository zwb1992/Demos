package com.example.zwb.reflectannotionproxy;

/***************************************
 * Author zhouweibin
 * Description .返回的结果
 * Date:2016/5/20
 ***************************************/
public class Result {
    private String type;
    private String description;

    @Override
    public String toString() {
        return "Result{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
