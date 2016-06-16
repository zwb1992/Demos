package com.zwb.zwbframe.http.net;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zwb.zwbframe.ApplicationManager;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.HttpCallBack;
import com.zwb.zwbframe.http.NetInfo;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/16
 ***************************************/
public class NetCallBack implements HttpCallBack {
    private NetRequest netRequest;

    public NetCallBack(NetRequest netRequest) {
        this.netRequest = netRequest;
    }

    @Override //统一处理请求结果
    public void response(NetInfo netInfo) {
        if (netInfo == null) {
            return;
        }
        Log.v("response",netInfo.toString());
        Observable.just(netInfo).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                map(new Func1<NetInfo, NetEvent>() {
                    @Override
                    public NetEvent call(NetInfo netInfo) {
                        int code = netInfo.getCode();
                        NetRequest.RESPONSE_STATUS status = null;
                        Object result = null;
                        if (code == NetRequest.RESPONSE_STATUS.OK.getValue()) {
                            status = NetRequest.RESPONSE_STATUS.OK;
                            String dataString = netInfo.getData();
                            if (!TextUtils.isEmpty(dataString) && netRequest.getTypes() != null) {
                                result = JSON.parseObject(dataString, netRequest.getTypes()[0]);
                            }
                        } else if (code == NetRequest.RESPONSE_STATUS.ERROR.getValue()) {
                            status = NetRequest.RESPONSE_STATUS.ERROR;
                        } else if (code == NetRequest.RESPONSE_STATUS.PARSE_ERROR.getValue()) {
                            status = NetRequest.RESPONSE_STATUS.PARSE_ERROR;
                        } else if (code == NetRequest.RESPONSE_STATUS.NOT_NET.getValue()) {
                            status = NetRequest.RESPONSE_STATUS.NOT_NET;
                        }
                        NetEvent event = new NetEvent();
                        event.setCode(code);
                        event.setResult(result);
                        event.what(netRequest);
                        event.setStatus(status);
                        event.setRepMsg(netInfo.getMsg());
                        return event;
                    }
                }).
                subscribe(new Action1<NetEvent>() {
                    @Override
                    public void call(NetEvent netEvent) {
                        if (netEvent.getStatus() == NetRequest.RESPONSE_STATUS.OK) {
                            if (netRequest.getNetEventListener() != null) {
                                netRequest.getNetEventListener().netSuccess(netEvent);
                            }
                        } else {
                            if (netEvent.getStatus() == NetRequest.RESPONSE_STATUS.ERROR) {
                                Toast.makeText(ApplicationManager.getApplication().getApplicationContext(), "服务器未响应", Toast.LENGTH_SHORT).show();
                            } else if (netEvent.getStatus() == NetRequest.RESPONSE_STATUS.PARSE_ERROR) {
                                Toast.makeText(ApplicationManager.getApplication().getApplicationContext(), "Json解析出错", Toast.LENGTH_SHORT).show();
                            } else if (netEvent.getStatus() == NetRequest.RESPONSE_STATUS.NOT_NET){
                                Toast.makeText(ApplicationManager.getApplication().getApplicationContext(), "无网络连接", Toast.LENGTH_SHORT).show();
                            }else if(!netRequest.getNetEventListener().netfailed(netEvent)){
                                if(!TextUtils.isEmpty(netEvent.getRepMsg())){
                                    Toast.makeText(ApplicationManager.getApplication().getApplicationContext(), netEvent.getRepMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
    }
}
