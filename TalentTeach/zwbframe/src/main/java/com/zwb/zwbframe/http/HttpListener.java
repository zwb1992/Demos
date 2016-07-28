package com.zwb.zwbframe.http;

import android.util.Log;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zwb.zwbframe.http.net.NetRequest;

import org.json.JSONObject;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class HttpListener implements Response.Listener<String>, Response.ErrorListener {

    HttpCallBack callBack;
    private NetInfo netInfo = null;

    public HttpListener(HttpCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //NoConnectionError 客户端没有网络连接。
        //SERVERERROR 服务器的响应的一个错误，最有可能的4xx或5xx HTTP状态代码。
        //TimeoutError：Socket超时，服务器太忙或网络延迟会产生这个异常。默认情况下，Volley的超时时间为2.5秒。如果得到这个错误可以使用RetryPolicy。
        netInfo = new NetInfo();
        if (error instanceof NoConnectionError) {
            netInfo.setCode(NetRequest.RESPONSE_STATUS.NOT_NET.getValue());
            netInfo.setMsg("无网络连接");
        } else {
            netInfo.setCode(NetRequest.RESPONSE_STATUS.ERROR.getValue());
            netInfo.setMsg("服务器出错");
        }
        callBack.response(netInfo);
    }

    @Override
    public void onResponse(String response) {
        try {
            netInfo = new NetInfo();
            JSONObject object = new JSONObject(response);
            netInfo.setCode((int) object.get("code"));
            netInfo.setMsg((String) object.get("msg"));
            netInfo.setData(object.getString("data"));
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("json parse failed");
            netInfo.setMsg("json parse failed");
            netInfo.setCode(NetRequest.RESPONSE_STATUS.PARSE_ERROR.getValue());
        } finally {
            callBack.response(netInfo);
        }
    }
}
