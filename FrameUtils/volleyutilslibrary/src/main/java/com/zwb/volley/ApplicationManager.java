package com.zwb.volley;

import android.os.AsyncTask;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.zwb.volley.utils.RequestInstance;

/**
 * Created by zwb
 * Description
 * Date 16/10/24.
 */
public class ApplicationManager extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        RequestInstance.getInstance(this);
    }

    @Override
    public void onLowMemory() {
        Log.e("runtesttag", "\n===================================");
        Log.e("runtesttag", "========!!!!!!!!!!!!!!!!!!=========");
        Log.e("runtesttag", "=                                 =");
        Log.e("runtesttag", "====系统内存过低,全局参数即将被回收========");
        Log.e("runtesttag", "=                                 =");
        Log.e("runtesttag", "===================================");
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        Log.e("runtesttag", "\n===================================");
        Log.e("runtesttag", "========!!!!!!!!!!!!!!!!!!=========");
        Log.e("runtesttag", "=                                 =");
        Log.e("runtesttag", "====系统内存过低,application被回收======");
        Log.e("runtesttag", "=                                 =");
        Log.e("runtesttag", "===================================");
        super.onTerminate();
    }
}
