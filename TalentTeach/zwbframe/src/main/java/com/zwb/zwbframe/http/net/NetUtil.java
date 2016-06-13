package com.zwb.zwbframe.http.net;

import android.util.Log;

import com.zwb.zwbframe.http.HttpListener;
import com.zwb.zwbframe.http.HttpRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class NetUtil implements InvocationHandler {
    public static Map<String, String> defaultRequestMaps;
    private HttpListener listener;
    static String HOST = "http://192.168.1.70";

    boolean shouldCache = true;

    public NetUtil(String host) {
        HOST = host;
    }

    public void setShouldCache(boolean shouldCache) {
        this.shouldCache = shouldCache;
    }

    private static final java.lang.String TAG = "NetUtil";

    public void setListener(HttpListener listener) {
        this.listener = listener;
    }

    public static void setDefaultParams(Map<String, String> defaultRequestMap) {
        defaultRequestMaps = defaultRequestMap;
    }

    public static void addDefaultRequestMaps(String key, String value) {
        if (defaultRequestMaps == null) return;
        if (defaultRequestMaps.containsKey(key) && value == null) {//如果以前包含了token的值，这时候设置value是null就清除以前的token
            defaultRequestMaps.remove(key);
        } else if (value != null) {
            defaultRequestMaps.put(key, value);
        }
    }

    public static void setToken(String key, String value) {
        addDefaultRequestMaps(key, value);
    }

    public static void setUserId(String key, String value) {
        addDefaultRequestMaps(key, value);
    }

    public static void clearDefaultParams() {
        if (defaultRequestMaps != null) {
            defaultRequestMaps.clear();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String url = null;
        HttpRequest.RequstType type = null;
        if (method.isAnnotationPresent(POST.class)) {
            POST post = method.getAnnotation(POST.class);
            url = post.value();
            type = HttpRequest.RequstType.POST;
        }

        if (method.isAnnotationPresent(GET.class)) {
            GET post = method.getAnnotation(GET.class);
            url = post.value();
            type = HttpRequest.RequstType.GET;
        }
        switch (type) {
            case GET:
                HttpRequest.requestGet(listener, defaultProcess(method, args, url, type), url, this, shouldCache);
                break;
            case POST:
                HttpRequest.requestPost(listener, defaultProcess(method, args, url, type), url, this, shouldCache);
                break;
        }
        return null;
    }

    /**
     * 没有文件的请求处理
     *
     * @param method
     * @param objs
     * @param url
     * @param type
     */
    private Map defaultProcess(Method method, Object[] objs, String url, HttpRequest.RequstType type) {

        List<String> paramsName = getMethodParameterNamesByAnnotation(method);
        Map<String, String> params = new HashMap<>();
        int i = 0;
        for (String name : paramsName) {
            if (objs == null || objs[i] == null) {
                params.put(name, "为空");
            } else {
                params.put(name, objs[i].toString() + "");
            }
            Log.d(TAG, "-*******---");

            i++;
        }


        return params;
    }

    /**
     * 获取指定方法的参数名
     *
     * @param method 要获取参数名的方法
     * @return 按参数顺序排列的参数名列表
     */
    public static List<String> getMethodParameterNamesByAnnotation(Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            return new ArrayList<>();
        }
        List<String> parameteNames = new ArrayList<>();
        int i = 0;
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof PARAMS) {
                    PARAMS param = (PARAMS) annotation;
                    parameteNames.add(param.value());
                }
            }
        }
        return parameteNames;
    }
}
