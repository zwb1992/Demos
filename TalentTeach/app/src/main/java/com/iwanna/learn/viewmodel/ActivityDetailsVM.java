package com.iwanna.learn.viewmodel;

import android.app.Activity;
import android.widget.Toast;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.view.activity.ActiivtyDetailsActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/**
 * Created by zwb
 * Description
 * Date 16/8/28.
 */
public class ActivityDetailsVM extends AbstractViewMode<ActiivtyDetailsActivity>{

    public void join(int act_id,String name,String phone,String num){
        Net.get(this).join(act_id, DataCache.getInstance().getUserID(),phone,name,num);
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        getView().dissMissDialog();
        Toast.makeText(getView(), R.string.join_successed,Toast.LENGTH_SHORT).show();
        getView().setResult(Activity.RESULT_OK);
        getView().finish();
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        getView().dissMissDialog();
        return super.netfailed(netEvent);
    }
}
