package com.iwanna.learn.viewmodel;

import android.widget.Toast;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.UserInfo;
import com.iwanna.learn.view.activity.LoginActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/**
 * Created by zwb
 * Description
 * Date 16/8/22.
 */
public class LoginVM extends AbstractViewMode<LoginActivity> {

    private String phone;
    public void login(String phone,String password){
        this.phone = phone;
        Net.get(this).login(phone,password);
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        getView().dissMissDialog();
        Toast.makeText(getView(), R.string.login_success,Toast.LENGTH_SHORT).show();
        UserInfo userInfo = (UserInfo)netEvent.getResult();
        DataCache.getInstance().setmUserInfo(userInfo);
        DataCache.getInstance().saveUserToLocal();
        getView().loginSuccess();
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        getView().dissMissDialog();
        return super.netfailed(netEvent);
    }
}
