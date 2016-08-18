package com.iwanna.learn.viewmodel;

import android.util.Log;
import android.widget.Toast;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.ExpandInfo;
import com.iwanna.learn.view.fragment.IndexFragment;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

import java.util.List;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class IndexFragmentVM extends AbstractViewMode<IndexFragment> {

    private NetRequest request;

    public void getData() {
        request = Net.get(this).expand();
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        List<ExpandInfo> expandInfo = (List<ExpandInfo>) netEvent.getResult();
        Log.i("info",expandInfo.toString());
        getView().initHeadView(expandInfo);
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        Toast.makeText(getView().getActivity(),""+netEvent.getRepMsg(),Toast.LENGTH_SHORT).show();
        getView().getRlRefresh().endRefreshing();
        return super.netfailed(netEvent);
    }
}
