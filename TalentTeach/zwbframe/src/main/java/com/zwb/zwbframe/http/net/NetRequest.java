package com.zwb.zwbframe.http.net;

import com.zwb.zwbframe.http.HttpListener;
import com.zwb.zwbframe.http.HttpRequest;

import java.lang.reflect.Type;
import java.util.Map;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/16
 ***************************************/
public class NetRequest<T> {

    public enum RESPONSE_STATUS {
        NOT_NET(100),//手机网络连接异常
        PARSE_ERROR(300),//json解析出错
        OK(200),//请求成功
        ERROR(404);//服务器响应错误

        RESPONSE_STATUS(int value) {
            this.value = value;
        }

        private int value;

        public int getValue() {
            return value;
        }
    }

    Type[] types;

    public Type[] getTypes() {
        return types;
    }

    public void setTypes(Type[] types) {
        this.types = types;
    }

    NetCallBack callBack = new NetCallBack(this);

    private OnNetEventListener netEventListener;

    public void setNetEventListener(OnNetEventListener netEventListener) {
        this.netEventListener = netEventListener;
    }

    public OnNetEventListener getNetEventListener() {
        return netEventListener;
    }

    public void getData(Map map, String url, HttpRequest.RequstType type, boolean shouldCache) {
        switch (type) {
            case GET:
                HttpRequest.requestGet(callBack, map, url, this, shouldCache);
                break;
            case POST:
                HttpRequest.requestPost(callBack, map, url, this, shouldCache);
                break;
        }
    }
}
