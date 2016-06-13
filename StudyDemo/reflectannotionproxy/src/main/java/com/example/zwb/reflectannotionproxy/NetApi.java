package com.example.zwb.reflectannotionproxy;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/20
 ***************************************/
public interface NetApi {

    @Get("/api/reflectannotionproxy/getData")
    Result getData(@Parames("type") String type, @Parames("description") String description);

    @Post("/api/reflectannotionproxy/postData")
    Result postData(@Parames("type") String type, @Parames("description") String description);
}
