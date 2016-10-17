package com.iwanna.learn.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.ExpandInfo;
import com.iwanna.learn.model.JiGou;
import com.iwanna.learn.view.activity.ExpandActivity;
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
    @Bind(R.id.no_data)
    LinearLayout noData;

    private List<ExpandInfo> expandInfoList = new ArrayList<>();
    private List<JiGou> jiGouList = new ArrayList<>();
    private CommonAdapter<JiGou> adapter;

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
        showDialog();
        getViewModel().getExpandData();
        getViewModel().getJiGouData();
    }

    private void initAdapter(){
        NormalRefreshViewHolder normalRefreshViewHolder = new NormalRefreshViewHolder(mContext,true);
        rlRefresh.setRefreshViewHolder(normalRefreshViewHolder);
        rlRefresh.setIsShowLoadingMoreView(false);//是否显示加载控件
        rlRefresh.setDelegate(this);//设置下拉刷新
        adapter = new CommonAdapter<JiGou>(mContext,R.layout.item_view_index,LayoutInflater.from(mContext),jiGouList) {
            @Override
            public void convert(ViewHolder holder, JiGou jiGou) {
                holder.setText(R.id.tv_title,jiGou.getAgencyName());
                holder.setText(R.id.tv_content,jiGou.getAdress());
                ImageView imageView = holder.getView(R.id.img_expand);
                imageView.setTag(Net.NetInstance.IMG_URL+"/"+jiGou.getImg());
                Net.imageLoader(Net.NetInstance.IMG_URL+"/"+jiGou.getImg(),imageView,R.mipmap.default_img,R.mipmap.default_img, HttpRequest.ImageShapeType.NORMAL);
            }
        };
        listView.setEmptyView(noData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i < expandInfoList.size()){
                    ExpandInfo expandInfo = expandInfoList.get(i);
                    Intent hobbyIntent = new Intent(getActivity(), ExpandActivity.class);
                    hobbyIntent.putExtra("type",ExpandActivity.XINGQU);
                    hobbyIntent.putExtra("id",expandInfo.getStypeId());
                    hobbyIntent.putExtra("title",expandInfo.getTypeName());
                    startActivity(hobbyIntent);
                }else if(i > expandInfoList.size()){
                    JiGou jiGou = jiGouList.get(i - expandInfoList.size() - 1);
                    Intent hobbyIntent = new Intent(getActivity(), ExpandActivity.class);
                    hobbyIntent.putExtra("type",ExpandActivity.JIGOU);
                    hobbyIntent.putExtra("id",jiGou.getAgencyID());
                    hobbyIntent.putExtra("title",jiGou.getAgencyName());
                    startActivity(hobbyIntent);
                }
            }
        });
    }

    //下拉刷新
    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        getViewModel().getJiGouData();
    }

    //上拉加载
    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        return false;
    }

    public void refreshData(List<JiGou> jiGous){
        rlRefresh.endRefreshing();
        jiGouList.clear();
        jiGouList.addAll(jiGous);
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
