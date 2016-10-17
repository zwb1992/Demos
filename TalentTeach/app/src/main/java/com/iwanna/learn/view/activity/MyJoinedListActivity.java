package com.iwanna.learn.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Actitivities;
import com.iwanna.learn.utils.CalendarUtils;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.MyJoinedListVM;
import com.zwb.pulltorefreshlibrary.refreshlayout.NormalRefreshViewHolder;
import com.zwb.pulltorefreshlibrary.refreshlayout.RefreshLayout;
import com.zwb.zwbframe.adapter.CommonAdapter;
import com.zwb.zwbframe.adapter.ViewHolder;
import com.zwb.zwbframe.http.HttpRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我参加的活动
 */
public class MyJoinedListActivity extends BaseActivity<MyJoinedListActivity, MyJoinedListVM> implements RefreshLayout.RefreshLayoutDelegate{

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.rl_refresh)
    RefreshLayout rlRefresh;
    @Bind(R.id.no_data)
    LinearLayout noData;

    private List<Actitivities> actitivities = new ArrayList<>();
    private CommonAdapter<Actitivities> adapter;

    @Override
    public Class<MyJoinedListVM> getVMClass() {
        return MyJoinedListVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_my_joined_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.mine_join);
        initAdapter();
        showDialog();
        getViewModel().getJoinedList();
    }
    private void initAdapter(){
        NormalRefreshViewHolder normalRefreshViewHolder = new NormalRefreshViewHolder(this, true);
        rlRefresh.setRefreshViewHolder(normalRefreshViewHolder);
        rlRefresh.setIsShowLoadingMoreView(false);//是否显示加载控件
        rlRefresh.setDelegate(this);//设置下拉刷新
        adapter = new CommonAdapter<Actitivities>(this,R.layout.item_joined, getLayoutInflater(),actitivities) {
            @Override
            public void convert(ViewHolder holder, Actitivities actitivities) {
                holder.setText(R.id.tv_name,actitivities.getActivityName());
                if(actitivities.isStates()){
                    holder.setText(R.id.tv_status,getResources().getString(R.string.joined));
                }else {
                    holder.setText(R.id.tv_status,getResources().getString(R.string.shixiao));
                }

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
                holder.setText(R.id.tv_num,actitivities.getJoinNumber()+"人参加");

                ImageView imageView = holder.getView(R.id.img_bg);
                imageView.setTag(Net.NetInstance.IMG_URL+"/"+actitivities.getActivityImg());
                Net.imageLoader(Net.NetInstance.IMG_URL+"/"+actitivities.getActivityImg(),
                        imageView,
                        DensityUtils.dip2px(MyJoinedListActivity.this,95),DensityUtils.dip2px(MyJoinedListActivity.this,66),
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
                Intent intent = new Intent(MyJoinedListActivity.this, ActiivtyDetailsActivity.class);
                intent.putExtra("act",actitivity);
                startActivity(intent);
            }
        });
    }


    @OnClick(R.id.img_back)
    public void onClick() {
        onBackPressed();
    }

    //下拉刷新
    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        getViewModel().getJoinedList();
    }

    //上拉加载
    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        return false;
    }

    public RefreshLayout getRlRefresh() {
        return rlRefresh;
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
}
