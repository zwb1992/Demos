package com.iwanna.learn.viewmodel;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.ExpandInfo;
import com.iwanna.learn.model.JiGou;
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
    private NetRequest jiGouRequest;

    public void getExpandData() {
        request = Net.get(this).expand();
    }

    public void getJiGouData(){
        jiGouRequest = Net.get(this).jiGou();
    }
    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(netEvent.whatEqual(request)) {
            List<ExpandInfo> expandInfo = (List<ExpandInfo>) netEvent.getResult();
            Log.i("info", expandInfo.toString());
            getView().initHeadView(expandInfo);
        } else if (netEvent.whatEqual(jiGouRequest)){
            List<JiGou> jiGous = (List<JiGou>) netEvent.getResult();
            Log.i("info", jiGous.toString());
            getView().refreshData(jiGous);
        }
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        if(netEvent.whatEqual(request)){
            handler.postDelayed(runnable,3000);
        }else if(netEvent.whatEqual(jiGouRequest)){
            Toast.makeText(getView().getActivity(),""+netEvent.getRepMsg(),Toast.LENGTH_SHORT).show();
        }
        getView().getRlRefresh().endRefreshing();
        return super.netfailed(netEvent);
    }
    private static Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getExpandData();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
