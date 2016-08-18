package com.iwanna.learn.viewmodel;

import android.util.Log;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.ExpandInfo;
import com.iwanna.learn.view.activity.MainActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

import java.util.List;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class MainVM extends AbstractViewMode<MainActivity> {
    private NetRequest request;

    public void getData() {
        request = Net.get(this).expand();
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        List<ExpandInfo> expandInfo = (List<ExpandInfo>) netEvent.getResult();
        Log.i("info",expandInfo.toString());
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        return super.netfailed(netEvent);
    }
}
