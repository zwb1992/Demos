package com.iwanna.learn.viewmodel;

import android.widget.Toast;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.view.fragment.CourseDianpingFragment;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/**
 * Created by zwb
 * Description
 * Date 16/9/1.
 */
public class CourseDianpingVM extends AbstractViewMode<CourseDianpingFragment> {

    private String content;
    public void dianping(int courseId,String content){
        this.content = content;
        Net.get(this).dianping(DataCache.getInstance().getUserID(),courseId,content);
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(getView() == null){
            return;
        }
        getView().dissMissDialog();
        Toast.makeText(getView().getActivity(), R.string.dianping_successed,Toast.LENGTH_SHORT).show();
        getView().dianpingSuccessed(content);
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        if(getView() != null){
            getView().dissMissDialog();
        }
        return super.netfailed(netEvent);
    }
}
