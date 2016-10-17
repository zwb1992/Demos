package com.iwanna.learn.viewmodel;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Entry;
import com.iwanna.learn.view.activity.MyEntryListActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 16/8/28.
 */
public class MyEntryListVM extends AbstractViewMode<MyEntryListActivity> {

    public void getMyEntrys(){
        Net.get(this).getEntrys(DataCache.getInstance().getUserID());
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(getView() == null){
            return;
        }
        getView().dissMissDialog();
        List<Entry> entryList = (List<Entry>)netEvent.getResult();
        getView().setData(entryList);
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
