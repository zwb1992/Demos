package com.iwanna.learn.view.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.http.NetApi;
import com.iwanna.learn.utils.ProgressDialog;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractBaseFragment;
import com.zwb.zwbframe.mvvm.AbstractViewMode;
import com.zwb.zwbframe.mvvm.IView;

import butterknife.ButterKnife;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public abstract class BaseFragment<T extends IView, VM extends AbstractViewMode<T>> extends AbstractBaseFragment<T, VM> {
    private Context context;
    private Activity activity;

    protected ProgressDialog progressDialog;

    @Override
    public boolean useButterknife() {
        return true;
    }

    public NetApi net(boolean flag){
        if(flag && progressDialog != null){
            progressDialog.show();
        }
        return Net.get(this);
    }

    @Override
    protected void injectView(View mRootView) {
        ButterKnife.bind(this, mRootView);
        context = getActivity();
        activity = getActivity();
        progressDialog = new ProgressDialog(activity);
    }
    public NetApi net(){
        return Net.get(this);
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
