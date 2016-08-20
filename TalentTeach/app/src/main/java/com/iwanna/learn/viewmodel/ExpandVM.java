package com.iwanna.learn.viewmodel;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.view.activity.ExpandActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/**
 * Created by zwb
 * Description
 * Date 16/8/19.
 */
public class ExpandVM extends AbstractViewMode<ExpandActivity> {

    private NetRequest hobbyNR = null;

    public void getHobbys(int HobbyTypeSId){
        hobbyNR = Net.get(this).getHobbys(HobbyTypeSId);
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(netEvent.whatEqual(hobbyNR)){

        }
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        return super.netfailed(netEvent);
    }
}
