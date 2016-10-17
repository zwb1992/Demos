package com.iwanna.learn.view.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.maps2d.model.LatLng;
import com.iwanna.learn.MyApplication;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.AgeFenlei;
import com.iwanna.learn.model.BanXinFenlei;
import com.iwanna.learn.model.Course;
import com.iwanna.learn.model.CourseFenlei;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.activity.CouresDetailsActivity;
import com.iwanna.learn.view.activity.MainActivity;
import com.iwanna.learn.view.base.BaseFragment;
import com.iwanna.learn.viewmodel.FindFragmentVM;
import com.zwb.pulltorefreshlibrary.refreshlayout.NormalRefreshViewHolder;
import com.zwb.pulltorefreshlibrary.refreshlayout.RefreshLayout;
import com.zwb.zwbframe.adapter.CommonAdapter;
import com.zwb.zwbframe.adapter.ViewHolder;
import com.zwb.zwbframe.http.HttpRequest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***************************************
 * Author zhouweibin
 * Description .发现
 * Date:2016/6/13
 ***************************************/
public class FindFragment extends BaseFragment<FindFragment, FindFragmentVM> implements RefreshLayout.RefreshLayoutDelegate {

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.rl_refresh)
    RefreshLayout rlRefresh;
    @Bind(R.id.no_data)
    LinearLayout noData;
    @Bind(R.id.cb_cours)
    CheckBox cbCours;
    @Bind(R.id.cb_banxin)
    CheckBox cbBanxin;
    @Bind(R.id.cb_age)
    CheckBox cbAge;
    @Bind(R.id.rb_select)
    RadioGroup rbSelect;

    private List<Course> courseList = new ArrayList<>();
    private CommonAdapter<Course> adapter;
    private String fenlei = "0";
    private String banxin = "0";
    private String age = "00";

    private List<CourseFenlei> courseFenleiList = new ArrayList<>();
    private List<BanXinFenlei> banXinFenleiList = new ArrayList<>();
    private List<AgeFenlei> ageFenleiList = new ArrayList<>();
    private PopupWindow courePwindow;
    private PopupWindow banxingPwindow;
    private PopupWindow agePwindow;
    private CommonAdapter<CourseFenlei> courseFenleiCommonAdapter;
    private CommonAdapter<BanXinFenlei> banXinFenleiCommonAdapter;
    private CommonAdapter<AgeFenlei> ageFenleiCommonAdapter;

    @Override
    public int tellMeLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public Class<FindFragmentVM> getVMClass() {
        return FindFragmentVM.class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initAdapter();
        initCourseFenleiPwindow();
        initBanXingFenleiPwindow();
        initAgeFenleiPwindow();
        showDialog();
        getViewModel().getCours(fenlei, banxin, age);
        getViewModel().fenlei();
        getViewModel().banxing();
        getViewModel().age();
    }

    private void initAdapter() {
        NormalRefreshViewHolder normalRefreshViewHolder = new NormalRefreshViewHolder(mContext, true);
        rlRefresh.setRefreshViewHolder(normalRefreshViewHolder);
        rlRefresh.setIsShowLoadingMoreView(false);//是否显示加载控件
        rlRefresh.setDelegate(this);//设置下拉刷新
        adapter = new CommonAdapter<Course>(mContext, R.layout.item_view_index, LayoutInflater.from(mContext), courseList) {
            @Override
            public void convert(ViewHolder holder, Course course) {
                holder.setText(R.id.tv_title, course.getCourseName());

                try {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
                holder.setVisible(R.id.tv_location, true);
                holder.setText(R.id.tv_location, course.getTeachAdress());
                if(MyApplication.Latitude != 0 && MyApplication.Longitude != 0 &&
                        course.getLatitudeCoordinates() != 0 && course.getLongitudeCoordinates() != 0){
                    float km = MainActivity.caculateDistance(new LatLng(MyApplication.Latitude,MyApplication.Longitude)
                    ,new LatLng(course.getLatitudeCoordinates(),course.getLongitudeCoordinates()));
                    km = km/1000.0f;
                    DecimalFormat df = new DecimalFormat("0.00");
                    km = Float.parseFloat(df.format(km));
                    holder.setText(R.id.tv_location, course.getTeachAdress()+" 约"+km+"km");
                }
                ImageView imageView = holder.getView(R.id.img_expand);
                imageView.setTag(Net.NetInstance.IMG_URL + "/" + course.getImg());
                Net.imageLoader(Net.NetInstance.IMG_URL + "/" + course.getImg(), imageView, R.mipmap.default_img, R.mipmap.default_img, HttpRequest.ImageShapeType.NORMAL);
            }
        };
        listView.setEmptyView(noData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course course = courseList.get(i);
                Intent intent = new Intent(getActivity(),CouresDetailsActivity.class);
                intent.putExtra("courseId",course.getCourseID());
                intent.putExtra("title",course.getCourseName());
                startActivity(intent);
            }
        });
    }

    public void setData(List<Course> data) {
        rlRefresh.endRefreshing();
        if (data == null) {
            return;
        }
        courseList.clear();
        courseList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    //下拉刷新
    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        getViewModel().getCours(fenlei, banxin, age);
    }

    //上拉加载
    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        return false;
    }

    public RefreshLayout getRlRefresh() {
        return rlRefresh;
    }

    private void initCourseFenleiPwindow() {
        if (courePwindow != null) {
            if (courePwindow.isShowing()) {
                courePwindow.dismiss();
            } else {
                courePwindow.showAsDropDown(rbSelect, 0, 0);
            }
        } else {
            View view = View.inflate(getActivity(), R.layout.popupwindow_fenlei, null);
            ListView listView = (ListView) view.findViewById(R.id.listView);
            courseFenleiCommonAdapter = new CommonAdapter<CourseFenlei>(mContext,R.layout.item_fenlei,LayoutInflater.from(mContext),courseFenleiList) {
                @Override
                public void convert(ViewHolder holder, CourseFenlei courseFenlei) {
                    holder.setText(R.id.tv_fenlei,courseFenlei.getTypeName());
                }
            };
            listView.setAdapter(courseFenleiCommonAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    CourseFenlei courseFenlei = courseFenleiList.get(i);
                    int type  = courseFenlei.getLevel();
                    if(type == 1){
                        fenlei = "f"+courseFenlei.getTypeID();
                    }else {
                        fenlei = "z"+courseFenlei.getTypeID();
                    }
                    courePwindow.dismiss();
                    showDialog();
                    getViewModel().getCours(fenlei,banxin,age);
                }
            });
            courePwindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,(int) (200 * DensityUtils.getDensity(getActivity())));
            courePwindow.setFocusable(true);
            courePwindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
            courePwindow.setOutsideTouchable(false);
            courePwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    cbCours.setChecked(false);
                }
            });
        }
    }
    private void initBanXingFenleiPwindow() {
        if (banxingPwindow != null) {
            if (banxingPwindow.isShowing()) {
                banxingPwindow.dismiss();
            } else {
                banxingPwindow.showAsDropDown(rbSelect, 0, 0);
            }
        } else {
            View view = View.inflate(getActivity(), R.layout.popupwindow_fenlei, null);
            ListView listView = (ListView) view.findViewById(R.id.listView);
            banXinFenleiCommonAdapter = new CommonAdapter<BanXinFenlei>(mContext,R.layout.item_fenlei,LayoutInflater.from(mContext),banXinFenleiList) {
                @Override
                public void convert(ViewHolder holder, BanXinFenlei banXinFenlei) {
                    holder.setText(R.id.tv_fenlei,banXinFenlei.getTeachNumber());
                }
            };
            listView.setAdapter(banXinFenleiCommonAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    BanXinFenlei banXinFenlei = banXinFenleiList.get(i);
                    banxin = banXinFenlei.getClassType()+"";
                    banxingPwindow.dismiss();
                    showDialog();
                    getViewModel().getCours(fenlei,banxin,age);
                }
            });
            banxingPwindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,(int) (200 * DensityUtils.getDensity(getActivity())));
            banxingPwindow.setFocusable(true);
            banxingPwindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
            banxingPwindow.setOutsideTouchable(false);
            banxingPwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    cbBanxin.setChecked(false);
                }
            });
        }
    }
    private void initAgeFenleiPwindow() {
        if (agePwindow != null) {
            if (agePwindow.isShowing()) {
                agePwindow.dismiss();
            } else {
                agePwindow.showAsDropDown(rbSelect, 0, 0);
            }
        } else {
            View view = View.inflate(getActivity(), R.layout.popupwindow_fenlei, null);
            ListView listView = (ListView) view.findViewById(R.id.listView);
            ageFenleiCommonAdapter = new CommonAdapter<AgeFenlei>(mContext,R.layout.item_fenlei,LayoutInflater.from(mContext),ageFenleiList) {
                @Override
                public void convert(ViewHolder holder, AgeFenlei ageFenlei) {
                    holder.setText(R.id.tv_fenlei,ageFenlei.getTeachingObject());
                }
            };
            listView.setAdapter(ageFenleiCommonAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    AgeFenlei ageFenlei = ageFenleiList.get(i);
                    age = ageFenlei.getNumber();
                    agePwindow.dismiss();
                    showDialog();
                    getViewModel().getCours(fenlei,banxin,age);
                }
            });
            agePwindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,(int) (200 * DensityUtils.getDensity(getActivity())));
            agePwindow.setFocusable(true);
            agePwindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
            agePwindow.setOutsideTouchable(false);
            agePwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    cbAge.setChecked(false);
                }
            });
        }
    }


    /**
     * 课程分类
     *
     * @param courseFenleis
     */
    public void setCoursFenlei(List<CourseFenlei> courseFenleis) {
        courseFenleiList.clear();
        courseFenleiList.addAll(courseFenleis);
        courseFenleiCommonAdapter.notifyDataSetChanged();
    }

    /**
     * 班型分类
     *
     * @param banXinFenleis
     */
    public void setBanxinFenlei(List<BanXinFenlei> banXinFenleis) {
        banXinFenleiList.clear();
        banXinFenleiList.addAll(banXinFenleis);
        banXinFenleiCommonAdapter.notifyDataSetChanged();
    }

    /**
     * 年龄分类
     *
     * @param ageFenleis
     */
    public void setAgeFenlei(List<AgeFenlei> ageFenleis) {
        ageFenleiList.clear();
        ageFenleiList.addAll(ageFenleis);
        ageFenleiCommonAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.cb_banxin, R.id.cb_age, R.id.cb_cours})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_banxin:
                cbAge.setChecked(false);
                cbCours.setChecked(false);
                if(banXinFenleiList.size() == 0){
                    getViewModel().banxing();
                }else {
                    initBanXingFenleiPwindow();
                }
                break;
            case R.id.cb_age:
                cbBanxin.setChecked(false);
                cbCours.setChecked(false);
                if(ageFenleiList.size() == 0){
                    getViewModel().age();
                }else {
                    initAgeFenleiPwindow();
                }
                break;
            case R.id.cb_cours:
                cbAge.setChecked(false);
                cbBanxin.setChecked(false);
                if(courseFenleiList.size() == 0){
                    getViewModel().fenlei();
                }else {
                    initCourseFenleiPwindow();
                }
                break;
        }
    }
}
