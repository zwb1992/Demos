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
import android.widget.TextView;

import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Course;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.MyCollectionListVM;
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
 * 我的报名表
 */
public class MyCollectionListActivity extends BaseActivity<MyCollectionListActivity,MyCollectionListVM>  implements RefreshLayout.RefreshLayoutDelegate{


    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.rl_refresh)
    RefreshLayout rlRefresh;
    @Bind(R.id.no_data)
    LinearLayout noData;

    private CommonAdapter<Course> adapter;
    private List<Course> courseList = new ArrayList<>();
    @Override
    public Class<MyCollectionListVM> getVMClass() {
        return MyCollectionListVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_my_entry_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.mine_collection);
        initAdapter();
        showDialog();
        getViewModel().getCollections();
    }
    private void initAdapter(){
        NormalRefreshViewHolder normalRefreshViewHolder = new NormalRefreshViewHolder(this, true);
        rlRefresh.setRefreshViewHolder(normalRefreshViewHolder);
        rlRefresh.setIsShowLoadingMoreView(false);//是否显示加载控件
        rlRefresh.setDelegate(this);//设置下拉刷新
        adapter = new CommonAdapter<Course>(this,R.layout.item_view_index, getLayoutInflater(),courseList) {
            @Override
            public void convert(ViewHolder holder, Course course) {
                holder.setText(R.id.tv_title,course.getCourseName());

                try{
                    TextView textView = holder.getView(R.id.tv_content);
                    Course.PriceBean priceBean = course.getPrice().get(0);
                    if("价格面议".equals(priceBean.getServiceType())){
                        textView.setText("价格面议");
                    }else {
                        String text = "￥" + priceBean.getPrice() + "/" + "学期" + "/" + priceBean.getCourseTime() + "课时";
                        SpannableString spanString = new SpannableString(text);
                        ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.tip_yellow_color));
                        spanString.setSpan(span, 0, text.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        textView.setText(spanString);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                holder.setVisible(R.id.tv_location,true);
                holder.setText(R.id.tv_location,course.getTeachAdress());
                ImageView imageView = holder.getView(R.id.img_expand);
                imageView.setTag(Net.NetInstance.IMG_URL+"/"+course.getCourseHeadImg());
                Net.imageLoader(Net.NetInstance.IMG_URL+"/"+course.getCourseHeadImg(),imageView,R.mipmap.default_img,R.mipmap.default_img, HttpRequest.ImageShapeType.NORMAL);
            }
        };
        listView.setEmptyView(noData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course course = courseList.get(i);
                Intent intent = new Intent(MyCollectionListActivity.this,CouresDetailsActivity.class);
                intent.putExtra("courseId",course.getCourseID());
                intent.putExtra("title",course.getCourseName());
                startActivity(intent);
            }
        });
    }
    public void setData(List<Course> data){
        rlRefresh.endRefreshing();
        if(data == null){
            return;
        }
        courseList.clear();
        courseList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.img_back)
    public void onClick() {
        onBackPressed();
    }

    //下拉刷新
    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        getViewModel().getCollections();
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
