package com.iwanna.learn.viewmodel;

import android.widget.Toast;

import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.view.activity.RegistActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/**
 * Created by zwb
 * Description
 * Date 16/8/22.
 */
public class RegistVM extends AbstractViewMode<RegistActivity> {

    private NetRequest registNR;
    private NetRequest getCodeNR;

    /**
     * 注册
     * @param nickname
     * @param phone
     * @param pwd
     */
    public void regist(String nickname,String phone,String pwd){
        registNR = Net.get(this).regist(nickname,phone,pwd);
    }

    public void getCode(String phone,int code){
        getCodeNR = Net.get(this).getVerCode(phone,code);
    }
    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        getView().dissMissDialog();
        if (netEvent.whatEqual(registNR)){
            Toast.makeText(getView(), R.string.regist_successed,Toast.LENGTH_SHORT).show();
            getView().registSucccess();
        }else if (netEvent.whatEqual(getCodeNR)){
            Toast.makeText(getView(), R.string.get_vercode_success,Toast.LENGTH_SHORT).show();
            getView().updateTvGetCode(true);
        }
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        getView().dissMissDialog();
        if (netEvent.whatEqual(registNR)){

        }else if (netEvent.whatEqual(getCodeNR)){
            getView().updateTvGetCode(false);
        }
        return super.netfailed(netEvent);
    }
}
