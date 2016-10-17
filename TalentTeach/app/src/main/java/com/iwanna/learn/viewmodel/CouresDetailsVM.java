package com.iwanna.learn.viewmodel;

import android.widget.Toast;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.MyApplication;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.CourseDetails;
import com.iwanna.learn.view.activity.CouresDetailsActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/**
 * Created by zwb
 * Description
 * Date 16/8/31.
 */
public class CouresDetailsVM extends AbstractViewMode<CouresDetailsActivity> {

    private NetRequest getCourseDetailsNR = null;
    private NetRequest collectNR = null;
    public void getCourseDetails(int courseId){
        getCourseDetailsNR = Net.get(this).getCoureDetails(courseId,DataCache.getInstance().getUserID());
    }

    private boolean isCollected;
    public void collect(boolean isCollected,int courseId){
        this.isCollected = isCollected;
        collectNR = Net.get(this).collect(DataCache.getInstance().getUserID(),courseId);
    }
    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(getView() == null){
            return;
        }
        getView().dissMissDialog();
        if(netEvent.whatEqual(getCourseDetailsNR)){
            CourseDetails courseDetails = (CourseDetails)netEvent.getResult();
            getView().setData(courseDetails);
        }else if (netEvent.whatEqual(collectNR)){
            if(!isCollected){
                Toast.makeText(getView(), R.string.collect_successed,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getView(), R.string.cancel_collect,Toast.LENGTH_SHORT).show();
            }
            getView().isCollected(!isCollected);
        }
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        if(getView() != null){
            getView().dissMissDialog();
        }
        return super.netfailed(netEvent);
    }
}
