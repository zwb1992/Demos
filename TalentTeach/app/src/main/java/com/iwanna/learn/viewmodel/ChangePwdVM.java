package com.iwanna.learn.viewmodel;

import android.widget.Toast;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.view.activity.ChangePwdActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/**
 * Created by zwb
 * Description
 * Date 16/8/27.
 */
public class ChangePwdVM extends AbstractViewMode<ChangePwdActivity> {

    public void changePwd(String oldPwd,String newPwd,String confirmPwd){
        Net.get(this).changePwd(
                DataCache.getInstance().getUserID(),
                oldPwd,newPwd,confirmPwd
        );
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        getView().dissMissDialog();
        Toast.makeText(getView(), R.string.change_pwd_successed,Toast.LENGTH_SHORT).show();
        getView().finish();
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        getView().dissMissDialog();
        return super.netfailed(netEvent);
    }
}
