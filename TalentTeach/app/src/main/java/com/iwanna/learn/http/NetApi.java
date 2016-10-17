package com.iwanna.learn.http;

import com.iwanna.learn.model.Actitivities;
import com.iwanna.learn.model.AgeFenlei;
import com.iwanna.learn.model.BanXinFenlei;
import com.iwanna.learn.model.Course;
import com.iwanna.learn.model.CourseDetails;
import com.iwanna.learn.model.CourseFenlei;
import com.iwanna.learn.model.Entry;
import com.iwanna.learn.model.ExpandInfo;
import com.iwanna.learn.model.JiGou;
import com.iwanna.learn.model.JigouIntroduce;
import com.iwanna.learn.model.Likes;
import com.iwanna.learn.model.OpenClass;
import com.iwanna.learn.model.Teacher;
import com.iwanna.learn.model.UserInfo;
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

    /**
     * 推广信息课程列表
     * @param HobbyTypeSId
     * @return
     */
    @POST("/CourseListTuiGuang.aspx")
    NetRequest<List<Course>> getHobbys(@PARAMS("flag") int HobbyTypeSId);

    /**
     * 机构课程列表
     * @param AgencyID
     * @return
     */
    @POST("/SchoolCourseListTuiGuang.aspx")
    NetRequest<List<Course>> getAgencys(@PARAMS("flag") int AgencyID);

    //  课程详情页
    @POST("/CourseDetailInfoTuiGuang.aspx")
    NetRequest<CourseDetails> getCoureDetails(@PARAMS("flag") int courseId,@PARAMS("userid") int userid);

    //机构或老师简介页面
    @POST("/IntroduceInfoTuiGuang.aspx")
    NetRequest getIntroduce(@PARAMS("flag") int id,@PARAMS("isTeacher") String isTeacher);

    @POST("/LoginInfo.aspx")
    NetRequest<UserInfo> login(@PARAMS("phone") String phone, @PARAMS("passWord") String password);

    @POST("/RegisterReturnUserID.aspx")
    NetRequest regist(
            @PARAMS("nick") String nick,
            @PARAMS("phone") String phone,
            @PARAMS("passWord") String password
    );

    @POST("/ReceiveCode.aspx")
    NetRequest getVerCode(@PARAMS("phone") String phone, @PARAMS("code") int vercode);

    //修改手机号码
    @POST("/PhoneEdit.aspx")
    NetRequest changePhone(@PARAMS("flag") int user_id,@PARAMS("phone") String phone);

    //修改密码
    @POST("/PassWordEdit.aspx")
    NetRequest changePwd(
            @PARAMS("flag") int user_id,
            @PARAMS("oldPassword") String oldPassword,
            @PARAMS("newPassWord") String newPassWord,
            @PARAMS("confirmPassword") String confirmPassword
    );

    //活动列表
    @GET("/ActivityListTuiGuang.aspx")
    NetRequest<List<Actitivities>> getActivities();

    //活动详情
    @POST("/ActivityDetailTuiGuang.aspx")
    NetRequest activityDetail(@PARAMS("flag") int act_id);

    //参与活动
    @POST("/MyActivitySave.aspx")
    NetRequest join(
            @PARAMS("Activityid") int Activityid,
            @PARAMS("userid") int userid,
            @PARAMS("phone") String phone,
            @PARAMS("name") String name,
            @PARAMS("num") String num
    );
    //我参加的活动
    @POST("/MyApplyActivityListTuiGuang.aspx")
    NetRequest<List<Actitivities>> getJoineds(@PARAMS("flag") int user_id);

    //我的报名表
    @POST("/MyApplyCourseListTuiGuang.aspx")
    NetRequest<List<Entry>> getEntrys(@PARAMS("flag") int user_id);

    //我的收藏列表
    @POST("/MyCourseCollectionListTuiGuang.aspx")
    NetRequest<List<Course>> getCollections(@PARAMS("flag") int user_id);

    //我的开课表
    @POST("/MyCourseListTuiGuang.aspx")
    NetRequest<List<OpenClass>> getOpenCourse(@PARAMS("flag") int user_id);

    //找回密码
    @POST("/ReceivePassword.aspx")
    NetRequest resetPwd(@PARAMS("phone") String phone, @PARAMS("passWord") String password);

    //发现
    @POST("/FaXianCourseListTuiGuang.aspx")
    NetRequest<List<Course>> faxian(
            @PARAMS("fenlei") String fenlei,
            @PARAMS("banxing") String banxing,
            @PARAMS("nianling") String nianling);

    @GET("/CourseHobby.aspx")
    NetRequest<List<CourseFenlei>> courseFenlei();

    @GET("/BanXingTuiGuang.aspx")
    NetRequest<List<BanXinFenlei>> banxinFenlei();

    @GET("/AgeTuiGuang.aspx")
    NetRequest<List<AgeFenlei>> ageFenlei();

    //机构或老师简介页面
    @POST("/IntroduceInfoTuiGuang.aspx")
    NetRequest<JigouIntroduce> jigouIntroduce(@PARAMS("flag") int id, @PARAMS("isTeacher") boolean isTeacher);

    //老师介绍简介
    //机构或老师简介页面
    @POST("/IntroduceInfoTuiGuang.aspx")
    NetRequest<Teacher> teacherIntroduce(@PARAMS("flag") int id, @PARAMS("isTeacher") boolean isTeacher);

    //收藏
    @POST("/CourseCollectionAdd.aspx")
    NetRequest collect(@PARAMS("flag") int userID,@PARAMS("courseId") int courseId);

    //课程点评
    @POST("/CourseComentAdd.aspx")
    NetRequest dianping(@PARAMS("uid") int uid,@PARAMS("courseId") int courseId,@PARAMS("content") String content);

    //修改性别
    @POST("/SexEdit.aspx")
    NetRequest editSex(@PARAMS("phone") String phone, @PARAMS("sex") String sex);

    //修改年龄
    @POST("/AgeEdit.aspx")
    NetRequest editAge(@PARAMS("phone") String phone, @PARAMS("age") String age);

    //修改偏好
    @POST("/LikeEdit.aspx")
    NetRequest editLike(@PARAMS("phone") String phone, @PARAMS("like") String like);
    //nickname

    @POST("/NickNameEdit.aspx")
    NetRequest editNickName(@PARAMS("phone") String phone, @PARAMS("nickname") String nickname);

    //偏好信息
    @GET("/LikeInfo.aspx")
    NetRequest<List<Likes>> getLikes();
}
