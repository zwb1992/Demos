package com.zwb.volley.net;

import android.util.Log;

import com.zwb.volley.http.HttpRequest;

/**
 * Created by zwb
 * Description
 * Date 16/10/22.
 */
public class NetRequest<T> {
    public enum RESPONSE_STATUS{
        NOT_NET(300,"无网络连接"),
        PARSER_ERROR(500,"json parse failed"),
        ERROR(400,"服务器出错");

        private String msg;
        private int code;

        RESPONSE_STATUS(int code,String msg) {
            this.msg = msg;
            this.code = code;
        }

        public int getValue() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    OnNetEventListener context;
    NetCallBack callBack = new NetCallBack(this);
    private NetRequestData data;

    public void setContext(OnNetEventListener context) {
        this.context = context;
    }

    public NetRequestData getData() {
        return data;
    }

    public void setData(NetRequestData data) {
        Log.e("info","==setData=="+data.toString());
        this.data = data;
        if(data != null){
            setContext(data.context);
            switch (data.request_type){
                case NetUtil.REQUEST_DEFAULT:
                    HttpRequest.process(data.url,data.requestType,data.params,this,callBack);
                    break;
                case NetUtil.REQUEST_FILE:
                    break;
            }
        }
    }
}
