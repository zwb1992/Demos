package com.zwb.talentteach;

import android.app.Application;

import com.zwb.zwbframe.http.RequestInstance;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RequestInstance.getInstance(this);
    }
}
