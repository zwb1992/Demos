package com.zwb.volley.http;

import android.util.Log;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zwb.volley.net.NetInfo;
import com.zwb.volley.net.NetRequest;

import org.json.JSONObject;

/**
 * Created by zwb
 * Description 自定义回调代替volley的回调
 * Date 16/10/22.
 */
public class HttpListener implements Response.Listener<String>,Response.ErrorListener{
    private HttpCallBack httpCallBack;
    private NetInfo netInfo;

    public HttpListener(HttpCallBack httpCallBack) {
        this.httpCallBack = httpCallBack;
    }

    @Override
    public void onResponse(String response) {
        Log.e("info","===="+response);
        try {
            netInfo = new NetInfo();
            JSONObject object = new JSONObject(response);
            netInfo.setCode(object.getInt("code"));
            netInfo.setMsg(object.getString("msg"));
            netInfo.setObj(object.getJSONObject("data"));
        }catch (Exception e){
            e.printStackTrace();
            netInfo.setCode(NetRequest.RESPONSE_STATUS.PARSER_ERROR.getValue());
            netInfo.setMsg("json parse failed");
        }finally {
            httpCallBack.onResponse(netInfo);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        netInfo = new NetInfo();
        if (error instanceof NoConnectionError) {
            netInfo.setCode(NetRequest.RESPONSE_STATUS.NOT_NET.getValue());
            netInfo.setMsg("无网络连接");
        } else {
            netInfo.setCode(NetRequest.RESPONSE_STATUS.ERROR.getValue());
            netInfo.setMsg("服务器出错");
        }
        httpCallBack.onResponse(netInfo);
    }
}
