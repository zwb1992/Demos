package com.iwanna.learn.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.CourseDetails;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.base.BaseFragment;
import com.iwanna.learn.view.customview.ScListView;
import com.iwanna.learn.viewmodel.CourseSiteVM;
import com.zwb.zwbframe.adapter.CommonAdapter;
import com.zwb.zwbframe.adapter.ViewHolder;
import com.zwb.zwbframe.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CourseSiteFragment extends BaseFragment<CourseSiteFragment, CourseSiteVM> {

    @Bind(R.id.listView)
    ScListView listView;

    private List<CourseDetails.SiteImgBean> imgBeanList = new ArrayList<>();
    private CommonAdapter<CourseDetails.SiteImgBean> adapter;
    @Override
    public Class<CourseSiteVM> getVMClass() {
        return CourseSiteVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.fragment_course_site;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new CommonAdapter<CourseDetails.SiteImgBean>(getActivity(),R.layout.item_course_site,getActivity().getLayoutInflater(),imgBeanList) {
            @Override
            public void convert(ViewHolder holder, CourseDetails.SiteImgBean siteImgBean) {
                ImageView imageView = holder.getView(R.id.img_course);
                imageView.setTag(Net.NetInstance.IMG_URL + "/" + siteImgBean.getImgBig());
                Net.imageLoader(Net.NetInstance.IMG_URL + "/" + siteImgBean.getImgBig(), imageView,
                        DensityUtils.getW(getActivity()),DensityUtils.dip2px(getActivity(),233),
                        R.mipmap.default_img, R.mipmap.default_img, HttpRequest.ImageShapeType.NORMAL);
            }
        };
        listView.setAdapter(adapter);
    }

    public void setData(List<CourseDetails.SiteImgBean> imgBeanList) {
        if(imgBeanList != null && imgBeanList.size()>0){
            this.imgBeanList.addAll(imgBeanList);
            adapter.notifyDataSetChanged();
        }
    }

}
