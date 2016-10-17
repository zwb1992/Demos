package com.iwanna.learn.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.OpenClass;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.MyOpenCourseVM;
import com.zwb.pulltorefreshlibrary.refreshlayout.NormalRefreshViewHolder;
import com.zwb.pulltorefreshlibrary.refreshlayout.RefreshLayout;
import com.zwb.zwbframe.adapter.CommonAdapter;
import com.zwb.zwbframe.adapter.ViewHolder;
import com.zwb.zwbframe.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的开课表
 */
public class MyOpenCourseActivity extends BaseActivity<MyOpenCourseActivity, MyOpenCourseVM> implements RefreshLayout.RefreshLayoutDelegate {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.rl_refresh)
    RefreshLayout rlRefresh;
    @Bind(R.id.no_data)
    LinearLayout noData;
    @Bind(R.id.rl_tip)
    RelativeLayout rlTip;

    private List<OpenClass> openClassList = new ArrayList<>();
    private CommonAdapter<OpenClass> adapter;

    @Override
    public Class<MyOpenCourseVM> getVMClass() {
        return MyOpenCourseVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_my_openclass;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.start_teach);
        initAdapter();
        showDialog();
        getViewModel().getOpenCourse();
    }

    private void initAdapter() {
        NormalRefreshViewHolder normalRefreshViewHolder = new NormalRefreshViewHolder(this, true);
        rlRefresh.setRefreshViewHolder(normalRefreshViewHolder);
        rlRefresh.setIsShowLoadingMoreView(false);//是否显示加载控件
        rlRefresh.setDelegate(this);//设置下拉刷新
        adapter = new CommonAdapter<OpenClass>(this, R.layout.item_open_class, getLayoutInflater(), openClassList) {
            @Override
            public void convert(ViewHolder holder, OpenClass openClass) {
                holder.setText(R.id.tv_name, openClass.getCourseName());

                TextView textView = holder.getView(R.id.tv_content);
                OpenClass.PriceBean priceBean = openClass.getPrice().get(0);
                if("价格面议".equals(priceBean.getServiceType())){
                    textView.setText("价格面议");
                }else {
                    String text = "￥" + priceBean.getPrice() + "/" + "学期" + "/" + priceBean.getCourseTime() + "课时";
                    SpannableString spanString = new SpannableString(text);
                    ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.tip_yellow_color));
                    spanString.setSpan(span, 0, text.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.setText(spanString);
                }

                holder.setText(R.id.tv_viewer,openClass.getGlanceOver()+"次浏览");
                holder.setText(R.id.tv_baoming,openClass.getApplyNumber()+"人报名");
                holder.setText(R.id.tv_pinglun,openClass.getSum()+"156条评论");

                ImageView imageView = holder.getView(R.id.img_bg);
                imageView.setTag(Net.NetInstance.IMG_URL + "/" + openClass.getCourseHeadImg());
                Net.imageLoader(Net.NetInstance.IMG_URL + "/" + openClass.getCourseHeadImg(),
                        imageView,
                        DensityUtils.dip2px(MyOpenCourseActivity.this,95),DensityUtils.dip2px(MyOpenCourseActivity.this,66),
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
                OpenClass openClass = openClassList.get(i);
                Intent intent = new Intent(MyOpenCourseActivity.this,CouresDetailsActivity.class);
                intent.putExtra("courseId",openClass.getCourseID());
                intent.putExtra("title",openClass.getCourseName());
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.img_back,R.id.img_delete})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.img_delete:
                rlTip.setVisibility(View.GONE);
                break;
        }
    }

    //下拉刷新
    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        getViewModel().getOpenCourse();
    }

    //上拉加载
    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        return false;
    }

    public RefreshLayout getRlRefresh() {
        return rlRefresh;
    }

    public void setData(List<OpenClass> data){
        rlRefresh.endRefreshing();
        if(data == null){
            return;
        }
        openClassList.clear();
        openClassList.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
