package com.iwanna.learn.viewmodel;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Likes;
import com.iwanna.learn.view.activity.LikesActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 16/9/13.
 */
public class LikesVM extends AbstractViewMode<LikesActivity> {

    public void getLikes(){
        Net.get(this).getLikes();
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(getView() == null){
            return;
        }
        getView().dissMissDialog();
        List<Likes> likes = (List<Likes>)netEvent.getResult();
        getView().setData(likes);
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        if(getView() != null){
            getView().dissMissDialog();
        }
        return super.netfailed(netEvent);
    }
}
