package com.iwanna.learn.view.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.iwanna.learn.R;
import com.iwanna.learn.model.ExpandInfo;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.ExpandVM;
import com.zwb.pulltorefreshlibrary.refreshlayout.NormalRefreshViewHolder;
import com.zwb.pulltorefreshlibrary.refreshlayout.RefreshLayout;
import com.zwb.zwbframe.adapter.CommonAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 推广页面
 */
public class ExpandActivity extends BaseActivity<ExpandActivity, ExpandVM> implements RefreshLayout.RefreshLayoutDelegate{

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.rl_refresh)
    RefreshLayout rlRefresh;

    private ExpandInfo expandInfo;
    private CommonAdapter adapter;
    @Override
    public Class<ExpandVM> getVMClass() {
        return ExpandVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_expand;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        expandInfo = (ExpandInfo) getIntent().getSerializableExtra("expandInfo");
        tvTitle.setText(expandInfo.getTypeName());
        getViewModel().getHobbys(expandInfo.getStypeId());
    }
    private void initAdapter(){
        NormalRefreshViewHolder normalRefreshViewHolder = new NormalRefreshViewHolder(activity,true);
        rlRefresh.setRefreshViewHolder(normalRefreshViewHolder);
        rlRefresh.setIsShowLoadingMoreView(true);//是否显示加载控件
        rlRefresh.setDelegate(this);//设置下拉刷新
    }
    @OnClick(R.id.img_back)
    public void onClick() {
        finish();
    }

    //下拉刷新
    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {

    }

    //上拉加载
    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        return false;
    }

}
