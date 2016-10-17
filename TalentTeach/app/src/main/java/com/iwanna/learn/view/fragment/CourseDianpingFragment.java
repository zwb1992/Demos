package com.iwanna.learn.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.CourseDetails;
import com.iwanna.learn.utils.CalendarUtils;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.activity.CouresDetailsActivity;
import com.iwanna.learn.view.base.BaseFragment;
import com.iwanna.learn.view.customview.ScListView;
import com.iwanna.learn.viewmodel.CourseDianpingVM;
import com.zwb.zwbframe.adapter.CommonAdapter;
import com.zwb.zwbframe.adapter.ViewHolder;
import com.zwb.zwbframe.http.HttpRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


public class CourseDianpingFragment extends BaseFragment<CourseDianpingFragment, CourseDianpingVM> {

    @Bind(R.id.listView)
    ScListView listView;
    @Bind(R.id.ed_content)
    EditText edContent;

    private List<CourseDetails.ContentBean> contentBeanList = new ArrayList<>();
    private CommonAdapter<CourseDetails.ContentBean> adapter;

    @Override
    public Class<CourseDianpingVM> getVMClass() {
        return CourseDianpingVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.fragment_course_dianping;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new CommonAdapter<CourseDetails.ContentBean>(getActivity(), R.layout.item_dianping, getActivity().getLayoutInflater(), contentBeanList) {
            @Override
            public void convert(ViewHolder holder, CourseDetails.ContentBean contentBean) {
                ImageView imageView = holder.getView(R.id.img_face);
                imageView.setTag(Net.NetInstance.IMG_URL + "/" + contentBean.getUserImg());
                Net.imageLoader(Net.NetInstance.IMG_URL + "/" + contentBean.getUserImg(), imageView,
                        DensityUtils.dip2px(getActivity(),25),DensityUtils.dip2px(getActivity(),25),
                        R.mipmap.default_face_50, R.mipmap.default_face_50, HttpRequest.ImageShapeType.ROUND);

                holder.setText(R.id.tv_name,contentBean.getUserName());
                holder.setText(R.id.tv_content,contentBean.getCommentContent());

                String time = contentBean.getCreateTime();
                if(!TextUtils.isEmpty(time)){
                    time = time.replace("T"," ");
                    holder.setText(R.id.tv_time, CalendarUtils.getTimeTip(time));
                }

            }
        };
        listView.setAdapter(adapter);
    }

    public void setData(List<CourseDetails.ContentBean> contentBeen) {
        if (contentBeen != null && contentBeen.size() > 0) {
            this.contentBeanList.addAll(contentBeen);
            adapter.notifyDataSetChanged();
        }
    }


    @OnClick(R.id.bt_dianping)
    public void onClick() {
        String content = edContent.getText().toString();
        if(TextUtils.isEmpty(content)){
            Toast.makeText(getActivity(),R.string.fabiao_is_not_null,Toast.LENGTH_SHORT).show();
        }
        showDialog();
        getViewModel().dianping(((CouresDetailsActivity)getActivity()).getCourseId(),content);
    }

    public void dianpingSuccessed(String content){
        edContent.setText("");
        CourseDetails.ContentBean contentBean = new CourseDetails.ContentBean();
        contentBean.setCourseID(((CouresDetailsActivity)getActivity()).getCourseId());
        contentBean.setCommentContent(content);
        contentBean.setUserID(DataCache.getInstance().getUserID());
        contentBean.setCreateTime(CalendarUtils.getTime(new Date()));
        contentBean.setUserName(DataCache.getInstance().getmUserInfo().getNickName());
        contentBean.setUserImg(DataCache.getInstance().getmUserInfo().getUserImg());
        contentBeanList.add(0,contentBean);
        adapter.notifyDataSetChanged();
        ((CouresDetailsActivity)getActivity()).setDianpingNum(contentBeanList.size());
    }
}
