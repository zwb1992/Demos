package com.zwb.volley.net;

import android.graphics.Bitmap;

import com.zwb.volley.help.GET;
import com.zwb.volley.help.PARAMS;
import com.zwb.volley.help.POST;
import com.zwb.volley.http.HttpRequest;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 16/10/22.
 */
public class NetUtil implements InvocationHandler {
    private String HOST;
    public static final int REQUEST_DEFAULT = 1;
    public static final int REQUEST_FILE = 2;
    private OnNetEventListener onNetEventListener;
    public NetUtil(String HOST) {
        this.HOST = HOST;
    }

    public void setOnNetEventListener(OnNetEventListener onNetEventListener) {
        this.onNetEventListener = onNetEventListener;
    }
    public static HashMap<String,String> defaultRequestMaps = null;

    @Override
    public NetRequest invoke(Object o, Method method, Object[] objects) throws Throwable {
        NetRequest netRequest = new NetRequest();
        String url = "";
        HttpRequest.RequestType type = null;
        NetRequestData data = null;
        int request_type = containFileOrBitmap(objects);
        //先判断是post请求还是get请求
        if(method.isAnnotationPresent(POST.class)){
            POST post = method.getAnnotation(POST.class);
            url = post.value();
            type = HttpRequest.RequestType.POST;
        }else if(method.isAnnotationPresent(GET.class)){
            GET get = method.getAnnotation(GET.class);
            url = get.value();
            type = HttpRequest.RequestType.GET;
        }
        switch (request_type){
            case REQUEST_FILE:
                data = new NetRequestData();
                break;
            case REQUEST_DEFAULT:
                data = getParams(HOST+url,getType(method),type,request_type,method,objects);
                break;
        }
        data.context = onNetEventListener;
        netRequest.setData(data);
        return netRequest;
    }

    /**
     * 判断方法的参数是否有文件或者bitmap
     *
     * @param objects
     * @return
     */
    private int containFileOrBitmap(Object[] objects) {
        if (objects == null)
            return REQUEST_DEFAULT;
        for (Object obj : objects) {
            if (File.class.isInstance(obj) || Bitmap.class.isInstance(obj)) {
                return REQUEST_FILE;
            }
        }

        return REQUEST_DEFAULT;
    }
    /**
     * 获取参数
     * @return
     */
    private NetRequestData getParams(String url,Type[] types,HttpRequest.RequestType type,int reqeust_type,Method method,Object[] objects){
        HashMap<String,String> params = new HashMap<>();
        List<String> paramsName = getMethodParameterNamesByAnnotation(method);
        for(int i = 0;i<paramsName.size();i++){
            if(objects[i] == null){
                params.put(paramsName.get(i),"为空");
            }else {
                params.put(paramsName.get(i), objects[i].toString());
            }
        }
        return new NetRequestData(url,types,params,type,reqeust_type);
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

    public HashMap<String,Object> getFileParams(Method method,Object[] objects){
        HashMap<String,Object> params = new HashMap<>();
        List<String> paramsName = getMethodParameterNamesByAnnotation(method);
        for(int i = 0;i<paramsName.size();i++){
            if(objects[i] == null){
                params.put(paramsName.get(i),"为空");
            }else {
                if(objects[i] instanceof File){

                }else if(objects[i] instanceof Bitmap){

                }else {
                    params.put(paramsName.get(i), objects[i].toString());
                }
            }
        }
        return params;
    }

    private Type[] getType(Method method){
        Type[] types = null;
        try {
            Type returnType = method.getGenericReturnType();// 返回类型
            types = ((ParameterizedType) returnType).getActualTypeArguments();//如果支持泛型，返回表示此类型实际类型参数的Type对象的数组
        } catch (GenericSignatureFormatError error) {
            error.printStackTrace();
        } catch (TypeNotPresentException e) {
            e.printStackTrace();
        } catch (MalformedParameterizedTypeException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
        }
        return types;
    }
}
