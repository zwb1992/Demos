package com.iwanna.learn.viewmodel;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Actitivities;
import com.iwanna.learn.view.activity.MyJoinedListActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 16/8/28.
 */
public class MyJoinedListVM extends AbstractViewMode<MyJoinedListActivity> {

    public void getJoinedList(){
        Net.get(this).getJoineds(DataCache.getInstance().getUserID());
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(getView() == null){
            return;
        }
        getView().dissMissDialog();
        List<Actitivities> actitivitiesList = (List<Actitivities>)netEvent.getResult();
        getView().setData(actitivitiesList);
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        if(getView() != null) {
            getView().dissMissDialog();
            getView().getRlRefresh().endRefreshing();
        }
        return super.netfailed(netEvent);
    }
}
