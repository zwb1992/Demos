package com.zwb.zwbframe;

import android.app.Application;

import com.zwb.zwbframe.http.RequestInstance;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/16
 ***************************************/
public class ApplicationManager extends Application {
    private static Application application;

    public static Application getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RequestInstance.getInstance(this);
        application = this;
    }
}
