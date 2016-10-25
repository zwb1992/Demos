package com.zwb.volley.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by zwb
 * Description
 * Date 16/10/17.
 */
public class RequestInstance {
    private static RequestInstance requestInstance = null;
    private RequestQueue requestQueue;
    private SimpleImageLoader loader;

    public RequestInstance(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        loader = SimpleImageLoader.getInstance(requestQueue);
    }
    public static RequestInstance getInstance(Context context){
        if(requestInstance == null){
            synchronized (RequestInstance.class){
                if(requestInstance == null){
                    requestInstance = new RequestInstance(context);
                }
            }
        }
        return requestInstance;
    }
    public static RequestInstance getInstance(){
        return requestInstance;
    }

    public SimpleImageLoader getLoader() {
        return loader;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    /**
     * 添加请求
     * @param request
     */
    public void addToQueue(Request request,Object tag){
        request.setTag(tag);
        getRequestQueue().add(request);
        Log.i("info","=====addToRequestQueue=====");
    }

    /**
     * 取消某个请求
     * @param tag
     */
    public void cancelRequest(String tag){
        getRequestQueue().cancelAll(tag);
    }

    /**
     * 请求图片
     * @param url
     * @param listener
     */
    public void getImage(String url, ImageLoader.ImageListener listener){
        getLoader().get(url,listener);
    }
}
