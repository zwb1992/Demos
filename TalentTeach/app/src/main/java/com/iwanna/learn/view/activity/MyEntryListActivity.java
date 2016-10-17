package com.iwanna.learn.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Actitivities;
import com.iwanna.learn.model.Entry;
import com.iwanna.learn.utils.CalendarUtils;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.MyEntryListVM;
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
 * 我的报名表
 */
public class MyEntryListActivity extends BaseActivity<MyEntryListActivity,MyEntryListVM>  implements RefreshLayout.RefreshLayoutDelegate{


    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.rl_refresh)
    RefreshLayout rlRefresh;
    @Bind(R.id.no_data)
    LinearLayout noData;

    private List<Entry> entryList = new ArrayList<>();
    private CommonAdapter<Entry> adapter;
    @Override
    public Class<MyEntryListVM> getVMClass() {
        return MyEntryListVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_my_entry_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.mine_entry_form);
        initAdapter();
        showDialog();
        getViewModel().getMyEntrys();
    }
    private void initAdapter(){
        NormalRefreshViewHolder normalRefreshViewHolder = new NormalRefreshViewHolder(this, true);
        rlRefresh.setRefreshViewHolder(normalRefreshViewHolder);
        rlRefresh.setIsShowLoadingMoreView(false);//是否显示加载控件
        rlRefresh.setDelegate(this);//设置下拉刷新
        adapter = new CommonAdapter<Entry>(this,R.layout.item_entry, getLayoutInflater(),entryList) {
            @Override
            public void convert(ViewHolder holder, Entry entry) {
                holder.setText(R.id.tv_name,entry.getCourseName());
                holder.setText(R.id.tv_content,entry.getAgencyName());

                Entry.PriceBean priceBean = entry.getPrice().get(0);
                TextView textView = holder.getView(R.id.tv_price);
                if("价格面议".equals(priceBean.getServiceType())){
                    textView.setText("价格面议");
                }else {
                    String text = "￥" + priceBean.getPrice() + "/" + "学期" + "/" + priceBean.getCourseTime() + "课时";
                    SpannableString spanString = new SpannableString(text);
                    ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.tip_yellow_color));
                    spanString.setSpan(span, 0, text.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.setText(spanString);
                }
                String text = priceBean.getServiceType() + "：￥" + priceBean.getPrice() + "/" + "学期" + "/" + priceBean.getCourseTime() + "课时";
                SpannableString spanString = new SpannableString(text);
                ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.tip_yellow_color));
                spanString.setSpan(span, priceBean.getServiceType().length() + 1, text.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                holder.setVisible(R.id.tv_status,actitivities.isStates());

                ImageView imageView = holder.getView(R.id.img_bg);
                imageView.setTag(Net.NetInstance.IMG_URL+"/"+entry.getCourseHeadImg());
                Net.imageLoader(Net.NetInstance.IMG_URL+"/"+entry.getCourseHeadImg(),
                        imageView,
                        DensityUtils.dip2px(MyEntryListActivity.this,95),DensityUtils.dip2px(MyEntryListActivity.this,66),
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
//                Entry entry = entryList.get(i);
//                Intent intent = new Intent(MyEntryListActivity.this, ActiivtyDetailsActivity.class);
//                intent.putExtra("act",actitivity);
//                startActivity(intent);
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
        getViewModel().getMyEntrys();
    }
    public void setData(List<Entry> data){
        rlRefresh.endRefreshing();
        if(data == null){
            return;
        }
        this.entryList.clear();
        this.entryList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    //上拉加载
    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        return false;
    }

    public RefreshLayout getRlRefresh() {
        return rlRefresh;
    }
}
