package com.example.zwb.reflectannotionproxy;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

/***************************************
 * Author zhouweibin
 * Description .动态代理调用类
 * Date:2016/5/20
 ***************************************/
public class NetUtil implements InvocationHandler {
    private static final String URL = "http://192.168.11.20:8080";
    private RequestType requestType;

    public NetUtil() {
        requestType = RequestType.GET;
    }

    public NetUtil(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public Result invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Result result = new Result();
        String url = "";
        String type = "";
        String description = "";
        if (method.isAnnotationPresent(Get.class)) {
            Get get = method.getAnnotation(Get.class);
            requestType = RequestType.GET;
            url = URL + get.value();
        }
        if (method.isAnnotationPresent(Post.class)) {
            Post post = method.getAnnotation(Post.class);
            requestType = RequestType.POST;
            url = URL + post.value();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Annotation[][] annotations = method.getParameterAnnotations();
        if (annotations != null && annotations.length > 0) {
            for (int i = 0;i<annotations.length;i++) {
                Annotation[] annotations1 = annotations[i];
                Log.i("info", "annotations1========" + annotations1);
                Log.i("info", "annotations1.length========" + annotations1.length);
                for (Annotation annotation : annotations1) {
                    Log.i("info", "annotation========" + annotation);
                    Log.i("info", "annotation instanceof Parames========" + (annotation instanceof Parames?true:false));
                    if (annotation instanceof Parames) {
                        Parames parames = ((Parames) annotation);
                        arrayList.add(parames.value()+"-"+args[i]);
                    }
                }
            }
        }
        result.setType(arrayList.get(0));
        result.setDescription(arrayList.get(1));
        Log.i("info",url);
        return result;
    }
}
