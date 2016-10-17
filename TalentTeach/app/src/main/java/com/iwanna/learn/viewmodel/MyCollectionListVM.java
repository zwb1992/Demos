package com.iwanna.learn.viewmodel;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Course;
import com.iwanna.learn.view.activity.MyCollectionListActivity;
import com.iwanna.learn.view.activity.MyEntryListActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 16/8/28.
 */
public class MyCollectionListVM extends AbstractViewMode<MyCollectionListActivity> {

    public void getCollections(){
        Net.get(this).getCollections(DataCache.getInstance().getUserID());
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(getView() == null){
            return;
        }
        getView().dissMissDialog();
        List<Course> courseList = (List<Course>)netEvent.getResult();
        getView().setData(courseList);
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
