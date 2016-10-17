package com.iwanna.learn.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Actitivities;
import com.iwanna.learn.utils.CalendarUtils;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.activity.ActiivtyDetailsActivity;
import com.iwanna.learn.viewmodel.ActivityFragmentVM;
import com.iwanna.learn.R;
import com.iwanna.learn.view.base.BaseFragment;
import com.zwb.pulltorefreshlibrary.refreshlayout.NormalRefreshViewHolder;
import com.zwb.pulltorefreshlibrary.refreshlayout.RefreshLayout;
import com.zwb.zwbframe.adapter.CommonAdapter;
import com.zwb.zwbframe.adapter.ViewHolder;
import com.zwb.zwbframe.http.HttpRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

/***************************************
 * Author zhouweibin
 * Description .活动
 * Date:2016/6/13
 ***************************************/
public class ActivityFragment extends BaseFragment<ActivityFragment, ActivityFragmentVM> implements RefreshLayout.RefreshLayoutDelegate{

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.rl_refresh)
    RefreshLayout rlRefresh;
    @Bind(R.id.no_data)
    LinearLayout noData;

    private List<Actitivities> actitivities = new ArrayList<>();
    private CommonAdapter<Actitivities> adapter;
    @Override
    public int tellMeLayout() {
        return R.layout.fragment_activity;
    }

    @Override
    public Class<ActivityFragmentVM> getVMClass() {
        return ActivityFragmentVM.class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initAdapter();
        showDialog();
        getViewModel().getActiivties();
    }
    private void initAdapter() {
        NormalRefreshViewHolder normalRefreshViewHolder = new NormalRefreshViewHolder(mContext, true);
        rlRefresh.setRefreshViewHolder(normalRefreshViewHolder);
        rlRefresh.setIsShowLoadingMoreView(false);//是否显示加载控件
        rlRefresh.setDelegate(this);//设置下拉刷新
        adapter = new CommonAdapter<Actitivities>(mContext,R.layout.item_activities, LayoutInflater.from(mContext),actitivities) {
            @Override
            public void convert(ViewHolder holder, Actitivities actitivities) {
                holder.setText(R.id.tv_name,actitivities.getActivityName());
                holder.setText(R.id.tv_address,actitivities.getActivityAddress());
                String startTime = actitivities.getStartTime();
                String endTime = actitivities.getEndTime();
                if(!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)){
                    startTime = startTime.replace("T"," ");
                    endTime = endTime.replace("T"," ");
                    Date stDate = CalendarUtils.getDate(startTime);
                    Date enDate = CalendarUtils.getDate(endTime);
                    if(stDate != null && enDate != null){
                        startTime = CalendarUtils.getTime1(stDate);
                        endTime = CalendarUtils.getTime1(enDate);
                        if(startTime.substring(0,6).equals(endTime.substring(0,6))){
                            holder.setText(R.id.tv_time,startTime.substring(0)+"-"+endTime.substring(7));
                        }else {
                            holder.setText(R.id.tv_time,startTime+"-"+endTime);
                        }
                    }
                }
                holder.setVisible(R.id.tv_status,actitivities.isStates());

                ImageView imageView = holder.getView(R.id.img_activity);
                imageView.setTag(Net.NetInstance.IMG_URL+"/"+actitivities.getActivityHeadImg());
                Net.imageLoader(Net.NetInstance.IMG_URL+"/"+actitivities.getActivityHeadImg(),
                        imageView,
                        DensityUtils.getW(getActivity()),DensityUtils.dip2px(getActivity(),200),
                        R.mipmap.default_activity,
                        R.mipmap.default_activity,
                        HttpRequest.ImageShapeType.NORMAL);
            }
        };
        listView.setEmptyView(noData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Actitivities actitivity = actitivities.get(i);
                Intent intent = new Intent(getActivity(), ActiivtyDetailsActivity.class);
                intent.putExtra("act",actitivity);
                startActivityForResult(intent,102);
            }
        });
    }
    public void setData(List<Actitivities> data){
        rlRefresh.endRefreshing();
        if(data == null){
            return;
        }
        actitivities.clear();
        actitivities.addAll(data);
        adapter.notifyDataSetChanged();
    }
    //下拉刷新
    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        getViewModel().getActiivties();
    }

    //上拉加载
    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 102 && resultCode == Activity.RESULT_OK){
            getViewModel().getActiivties();
        }
    }

    public RefreshLayout getRlRefresh() {
        return rlRefresh;
    }
}
