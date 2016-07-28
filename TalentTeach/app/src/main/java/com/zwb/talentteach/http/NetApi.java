package com.zwb.talentteach.http;

import com.zwb.zwbframe.http.help.GET;
import com.zwb.zwbframe.http.help.PARAMS;
import com.zwb.zwbframe.http.help.POST;
import com.zwb.zwbframe.http.net.NetRequest;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/20
 ***************************************/
public interface NetApi {

    @GET("/api/user/login")
    NetRequest login(@PARAMS("mobile") String mobile, @PARAMS("code") int code);

}
