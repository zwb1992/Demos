package com.iwanna.learn.viewmodel;

import android.widget.Toast;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.view.activity.ChangePhoneActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/**
 * Created by zwb
 * Description
 * Date 16/8/27.
 */
public class ChangePhoneVM extends AbstractViewMode<ChangePhoneActivity> {

    private NetRequest changePhoneNR;
    private NetRequest getCodeNR;
    private String tempPhone;
    public void changePhone(String phone){
        tempPhone = phone;
        changePhoneNR = Net.get(this).changePhone(DataCache.getInstance().getUserID(),phone);
    }

    public void getCode(String phone,int code){
        getCodeNR = Net.get(this).getVerCode(phone,code);
    }
    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        getView().dissMissDialog();
        if (netEvent.whatEqual(changePhoneNR)){
            DataCache.getInstance().setLoginPhone(tempPhone);
            DataCache.getInstance().getUserFromLocal();
            Toast.makeText(getView(), R.string.chang_phone_successed,Toast.LENGTH_SHORT).show();
            getView().changeSucccess();
        }else if (netEvent.whatEqual(getCodeNR)){
            Toast.makeText(getView(), R.string.get_vercode_success,Toast.LENGTH_SHORT).show();
            getView().updateTvGetCode(true);
        }
    }
    @Override
    public boolean netfailed(NetEvent netEvent) {
        getView().dissMissDialog();
        if (netEvent.whatEqual(changePhoneNR)){

        }else if (netEvent.whatEqual(getCodeNR)){
            getView().updateTvGetCode(false);
        }
        return super.netfailed(netEvent);
    }

}
