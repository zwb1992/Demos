package com.zwb.volley.net;

import com.zwb.volley.http.HttpRequest;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by zwb
 * Description
 * Date 16/10/24.
 */
public class NetRequestData {
    String url;
    Type[] types;
    HashMap<String,String> params;
    HttpRequest.RequestType requestType;
    int request_type;
    OnNetEventListener context;

    public NetRequestData() {
    }

    public NetRequestData(String url, Type[] types, HashMap<String, String> params, HttpRequest.RequestType requestType, int request_type) {
        this.url = url;
        this.types = types;
        this.params = params;
        this.requestType = requestType;
        this.request_type = request_type;
    }

    @Override
    public String toString() {
        return "NetRequestData{" +
                "url='" + url + '\'' +
                ", types=" + Arrays.toString(types) +
                ", params=" + params +
                ", requestType=" + requestType +
                ", request_type=" + request_type +
                ", context=" + context +
                '}';
    }
}
