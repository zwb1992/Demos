package com.iwanna.learn.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.ExpandInfo;
import com.iwanna.learn.view.base.BaseFragment;
import com.iwanna.learn.viewmodel.IndexFragmentVM;
import com.zwb.pulltorefreshlibrary.refreshlayout.NormalRefreshViewHolder;
import com.zwb.pulltorefreshlibrary.refreshlayout.RefreshLayout;
import com.zwb.zwbframe.adapter.CommonAdapter;
import com.zwb.zwbframe.adapter.ViewHolder;
import com.zwb.zwbframe.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/***************************************
 * Author zhouweibin
 * Description .首页
 * Date:2016/6/13
 ***************************************/
public class IndexFragment extends BaseFragment<IndexFragment, IndexFragmentVM> implements RefreshLayout.RefreshLayoutDelegate{
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.rl_refresh)
    RefreshLayout rlRefresh;

    private List<ExpandInfo> expandInfoList = new ArrayList<>();
    private CommonAdapter<ExpandInfo> adapter;

    @Override
    public int tellMeLayout() {
        return R.layout.fragment_index;
    }

    @Override
    public Class<IndexFragmentVM> getVMClass() {
        return IndexFragmentVM.class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initAdapter();
        getViewModel().getData();
    }

    private void initAdapter(){
        NormalRefreshViewHolder normalRefreshViewHolder = new NormalRefreshViewHolder(mContext,true);
        rlRefresh.setRefreshViewHolder(normalRefreshViewHolder);
        rlRefresh.setIsShowLoadingMoreView(false);//是否显示加载控件
        rlRefresh.setDelegate(this);//设置下拉刷新
        adapter = new CommonAdapter<ExpandInfo>(mContext,R.layout.item_view_index,LayoutInflater.from(mContext),expandInfoList) {
            @Override
            public void convert(ViewHolder holder, ExpandInfo expandInfo) {
                holder.setText(R.id.tv_title,expandInfo.getTypeName());
                holder.setText(R.id.tv_content,expandInfo.getIntroduce());
                ImageView imageView = holder.getView(R.id.img_expand);
                imageView.setTag(Net.NetInstance.IMG_URL+expandInfo.getImg());
                Net.imageLoader(Net.NetInstance.IMG_URL+expandInfo.getImg(),imageView,R.mipmap.default_img,R.mipmap.default_img, HttpRequest.ImageShapeType.NORMAL);
            }
        };
        listView.setAdapter(adapter);
    }

    //下拉刷新
    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        getViewModel().getData();
    }

    //上拉加载
    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        return false;
    }

    public void refreshData(List<ExpandInfo> expandInfos){
        rlRefresh.endRefreshing();
        expandInfoList.clear();
        expandInfoList.addAll(expandInfos);
        adapter.notifyDataSetChanged();
    }

    public void initHeadView(List<ExpandInfo> expandInfos){
        expandInfoList.clear();
        expandInfoList.addAll(expandInfos);
        View view = null;
        ExpandInfo expandInfo = null;
        for (int i = 0;i < expandInfoList.size();i++){
            expandInfo = expandInfoList.get(i);
            view = View.inflate(getActivity(),R.layout.item_view_index,null);
            TextView tvTitle = (TextView)view.findViewById(R.id.tv_title);
            tvTitle.setText(expandInfo.getTypeName());
            TextView tvContent = (TextView)view.findViewById(R.id.tv_content);
            tvContent.setText(expandInfo.getIntroduce());
            ImageView imageView = (ImageView)view.findViewById(R.id.img_expand);
            Log.i("info","============="+Net.NetInstance.IMG_URL+expandInfo.getImg());
            imageView.setTag(Net.NetInstance.IMG_URL+expandInfo.getImg());
            Net.imageLoader(Net.NetInstance.IMG_URL+expandInfo.getImg(),imageView,R.mipmap.default_img,R.mipmap.default_img, HttpRequest.ImageShapeType.NORMAL);
            listView.addHeaderView(view);
        }
        view  = View.inflate(getActivity(),R.layout.item_jigou,null);
        listView.addHeaderView(view);
    }
    public RefreshLayout getRlRefresh() {
        return rlRefresh;
    }
}
