package com.zwb.talentteach.viewmodel;

import android.util.Log;

import com.zwb.talentteach.http.Net;
import com.zwb.talentteach.view.activity.MainActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class MainVM extends AbstractViewMode<MainActivity> {
    private NetRequest request;

    public void getData() {
        request = Net.get(this).login("18779103333", 123456);
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        return super.netfailed(netEvent);
    }
}
