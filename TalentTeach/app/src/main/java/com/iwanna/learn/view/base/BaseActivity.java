package com.iwanna.learn.view.base;


import android.os.Bundle;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.http.NetApi;
import com.iwanna.learn.utils.ProgressDialog;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractBaseActivity;
import com.zwb.zwbframe.mvvm.AbstractViewMode;
import com.zwb.zwbframe.mvvm.IView;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/3
 ***************************************/
public abstract class BaseActivity<T extends IView, VM extends AbstractViewMode<T>> extends AbstractBaseActivity<T, VM> {

    protected ProgressDialog progressDialog;
    @Override
    public boolean useButterknife() {
        return true;
    }

    public NetApi net(){
        return Net.get(this);
    }

    public NetApi net(boolean flag){
        if(flag && progressDialog != null){
            progressDialog.show();
        }
        return Net.get(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        dissMissDialog();
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        dissMissDialog();
        return super.netfailed(netEvent);
    }
    public void showDialog(){
        if(progressDialog != null) {
            progressDialog.show();
        }
    }
    public void dissMissDialog(){
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
