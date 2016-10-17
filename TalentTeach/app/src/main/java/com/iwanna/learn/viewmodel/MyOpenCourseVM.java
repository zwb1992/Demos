package com.iwanna.learn.viewmodel;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.OpenClass;
import com.iwanna.learn.view.activity.MyCollectionListActivity;
import com.iwanna.learn.view.activity.MyOpenCourseActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 16/8/28.
 */
public class MyOpenCourseVM extends AbstractViewMode<MyOpenCourseActivity> {

    public void getOpenCourse(){
        Net.get(this).getOpenCourse(DataCache.getInstance().getUserID());
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(getView() == null){
            return;
        }
        getView().dissMissDialog();
        List<OpenClass> openClassList = (List<OpenClass>)netEvent.getResult();
        getView().setData(openClassList);
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        getView().dissMissDialog();
        getView().getRlRefresh().endRefreshing();
        return super.netfailed(netEvent);
    }
}
