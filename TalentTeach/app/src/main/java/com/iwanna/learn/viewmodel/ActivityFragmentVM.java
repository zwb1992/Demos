package com.iwanna.learn.viewmodel;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Actitivities;
import com.iwanna.learn.view.fragment.ActivityFragment;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

import java.util.List;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class ActivityFragmentVM extends AbstractViewMode<ActivityFragment> {

    public void getActiivties(){
        Net.get(this).getActivities();
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        getView().dissMissDialog();
        List<Actitivities> actitivities = (List<Actitivities>)netEvent.getResult();
        getView().setData(actitivities);
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        getView().dissMissDialog();
        getView().getRlRefresh().endRefreshing();
        return super.netfailed(netEvent);
    }
}
