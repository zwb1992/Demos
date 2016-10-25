package com.zwb.volley.net;

import android.util.Log;

import com.zwb.volley.http.HttpCallBack;

/**
 * Created by zwb
 * Description
 * Date 16/10/24.
 */
public class NetCallBack implements HttpCallBack {
    private NetRequest netRequest;

    public NetCallBack(NetRequest netRequest) {
        this.netRequest = netRequest;
    }

    @Override
    public void onResponse(NetInfo netInfo) {
        Log.e("onResponse","=="+netInfo.toString());
    }
}
