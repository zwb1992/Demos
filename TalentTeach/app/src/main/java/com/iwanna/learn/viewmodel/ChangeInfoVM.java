package com.iwanna.learn.viewmodel;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.view.activity.ChangeInfoActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/14
 ***************************************/
public class ChangeInfoVM extends AbstractViewMode<ChangeInfoActivity> {

    private String str;
    public void editName(String name){
        str = name;
        Net.get(this).editNickName(DataCache.getInstance().getLoginPhone(),name);
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(getView() == null){
            return;
        }
        getView().dissMissDialog();
        getView().editSuccess(str);
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        if(getView() != null){
            getView().dissMissDialog();
        }
        return super.netfailed(netEvent);
    }
}
