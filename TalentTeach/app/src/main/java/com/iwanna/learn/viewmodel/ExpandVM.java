package com.iwanna.learn.viewmodel;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Course;
import com.iwanna.learn.view.activity.ExpandActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

import java.util.List;

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

    public void getAgencys(int AgencyID){
        hobbyNR = Net.get(this).getAgencys(AgencyID);
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        getView().dissMissDialog();
        List<Course> courseList = (List<Course>)netEvent.getResult();
        getView().setData(courseList);
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        getView().dissMissDialog();
        getView().getRlRefresh().endRefreshing();
        return super.netfailed(netEvent);
    }
}
