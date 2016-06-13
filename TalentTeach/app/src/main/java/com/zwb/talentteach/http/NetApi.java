package com.zwb.talentteach.http;

import com.zwb.zwbframe.http.net.GET;
import com.zwb.zwbframe.http.net.PARAMS;
import com.zwb.zwbframe.http.net.POST;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/20
 ***************************************/
public interface NetApi {
    public static String HTTP_FORTEST = "http://192.168.1.150";
    @GET("https://www.baidu.com/")
    void getData();

    @POST("/api/reflectannotionproxy/postData")
    void postData(@PARAMS("type") String type, @PARAMS("description") String description);
}
