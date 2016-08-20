package com.iwanna.learn.http;

import com.iwanna.learn.model.ExpandInfo;
import com.iwanna.learn.model.JiGou;
import com.zwb.zwbframe.http.help.GET;
import com.zwb.zwbframe.http.help.PARAMS;
import com.zwb.zwbframe.http.help.POST;
import com.zwb.zwbframe.http.net.NetRequest;

import java.util.List;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/20
 ***************************************/
public interface NetApi {

    @GET("/IndexTuiGuang.aspx")
    NetRequest<List<ExpandInfo>> expand();//首页推广信息

    @GET("/SchoolTuiGuang.aspx")
    NetRequest<List<JiGou>> jiGou();//首页机构

    @POST("/CourseListTuiGuang.aspx") //推广信息课程列表
    NetRequest getHobbys(@PARAMS("HobbyTypeSId") int HobbyTypeSId);

    @POST("/SchoolCourseListTuiGuang.aspx")//机构课程列表
    NetRequest getAgencys(@PARAMS("AgencyID") int AgencyID);
}
