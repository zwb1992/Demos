package com.iwanna.learn;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.iwanna.learn.model.UserInfo;
import com.zwb.zwbframe.ApplicationManager;

/**
 * Created by zwb
 * Description 存储数据
 * Date 16/8/15.
 */
public class DataCache {

    public static final String SHARENAME = "iwannalearn";

    private static volatile DataCache sInstance;

    private static SharedPreferences sPreferences;

    private DataCache() {
        if (sPreferences == null) {
            sPreferences = ApplicationManager.getApplication().getSharedPreferences(SHARENAME, Context.MODE_PRIVATE);
            if (mUserInfo == null) {
                getUserFromLocal();
            }
        }
    }

    /**
     * 保证多线程安全，及高性能，volatile变量的操作，是不允许和它之前的读写操作打乱顺序。
     *
     * @return
     */
    public static DataCache getInstance() {
        DataCache instance = sInstance;
        if (sInstance == null) {
            synchronized (DataCache.class) {
                instance = sInstance;
                if (instance == null) {
                    instance = new DataCache();
                    sInstance = instance;
                }
            }
        }
        return instance;
    }


    private UserInfo mUserInfo = null;

    public UserInfo getmUserInfo() {
        return mUserInfo;
    }

    public void setmUserInfo(UserInfo mUserInfo) {
        this.mUserInfo = mUserInfo;
    }

    /**
     * 保存已登录用户到本地
     */
    public void saveUserToLocal() {
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putInt("UserID", mUserInfo.getUserID());
        editor.putString("LoginUserName", mUserInfo.getLoginUserName());
        editor.putString("LoginPhone", mUserInfo.getLoginPhone());
        editor.putInt("UserType", mUserInfo.getUserType());
        editor.putString("NickName", mUserInfo.getNickName());
        editor.putString("UserImg", mUserInfo.getUserImg());
        editor.putString("UserSex", mUserInfo.getUserSex());
        editor.putString("UserLike", mUserInfo.getUserLike());
        Log.d("info","--mUserInfo.getUserLike()--"+mUserInfo.getUserLike());
        editor.putString("UserAge", mUserInfo.getUserAge());
        editor.commit();
    }

    /**
     * 获取用户
     *
     * @return
     */
    public void getUserFromLocal() {
        mUserInfo = new UserInfo();

        mUserInfo.setUserID(sPreferences.getInt("UserID",0));
        mUserInfo.setLoginUserName(sPreferences.getString("LoginUserName",null));
        mUserInfo.setLoginPhone(sPreferences.getString("LoginPhone",null));
        mUserInfo.setNickName(sPreferences.getString("NickName",null));
        mUserInfo.setUserType(sPreferences.getInt("UserType",2));
        mUserInfo.setUserSex(sPreferences.getString("UserSex",null));
        mUserInfo.setUserImg(sPreferences.getString("UserImg",null));
        mUserInfo.setUserLike(sPreferences.getString("UserLike",null));
        Log.d("info","--sPreferences.getString(\"UserLike\",null)--"+sPreferences.getString("UserLike",null));
        mUserInfo.setUserAge(sPreferences.getString("UserAge",null));
    }

    public int getUserID(){
        return sPreferences.getInt("UserID",-1);
    }

    public void setUserID(int userID){
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putInt("UserID", userID);
        editor.commit();
    }

    public void setLoginPhone(String LoginPhone){
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putString("LoginPhone", LoginPhone);
        editor.commit();
    }
    public String getLoginPhone(){
        return sPreferences.getString("LoginPhone",null);
    }

    public int getUserType(){
        return sPreferences.getInt("UserType",2);
    }
}
