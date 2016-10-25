package com.zwb.volley.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.zwb.volley.net.NetUtil;
import com.zwb.volley.utils.RequestInstance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zwb
 * Description 实现网络请求
 * Date 16/10/21.
 */
public class HttpRequest {

    public enum RequestType{POST,GET}

    public static String getUrl(final HashMap<String,String> params,String url){
        StringBuffer buffer = new StringBuffer(url);
        Set<Map.Entry<String,String>> set = params.entrySet();
        Iterator<Map.Entry<String,String>> iterator = set.iterator();
        if(iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            buffer.append("?"+entry.getKey()+"="+entry.getValue());
        }
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            buffer.append("&"+entry.getKey()+"="+entry.getValue());
        }
        String path = "";
        try {
            path = new String(buffer.toString().getBytes(),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }
    public static Request reqeust_get(String url,HttpCallBack callBack,HashMap<String,String> params,Object tag){
        HttpListener listener = new HttpListener(callBack);
        String geturl = getUrl(params, url);
        Log.i(tag.toString(), geturl);
        StringRequest request = new StringRequest(Request.Method.GET,geturl,listener,listener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                if (NetUtil.defaultRequestMaps != null)
                    map.putAll(NetUtil.defaultRequestMaps);
                return map;
            }
        };
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(8000, 0, 0));
        RequestInstance.getInstance().addToQueue(request, tag);

        return request;
    }
    public static Request reqeust_post(String url, HttpCallBack callBack, final HashMap<String,String> params, Object tag){
        HttpListener listener = new HttpListener(callBack);
        StringRequest request = new StringRequest(Request.Method.POST,url,listener,listener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                if (NetUtil.defaultRequestMaps != null)
                    map.putAll(NetUtil.defaultRequestMaps);
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(8000, 0, 0));
        RequestInstance.getInstance().addToQueue(request, tag);

        return request;
    }

    public static void process(String url, RequestType requestType, HashMap<String,String> params,Object tag,HttpCallBack callBack){
        Log.e("info","==process====");
        switch (requestType){
            case POST:
                reqeust_post(url,callBack,params,tag);
                break;
            case GET:
                reqeust_get(url,callBack,params,tag);
                break;
        }
    }
}
