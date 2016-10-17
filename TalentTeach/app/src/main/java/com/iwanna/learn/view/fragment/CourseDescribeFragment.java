package com.iwanna.learn.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.iwanna.learn.R;
import com.iwanna.learn.view.base.BaseFragment;
import com.iwanna.learn.viewmodel.CourseDescribeVM;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CourseDescribeFragment extends BaseFragment<CourseDescribeFragment, CourseDescribeVM> {

    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_tip)
    TextView tvTip;

    @Override
    public Class<CourseDescribeVM> getVMClass() {
        return CourseDescribeVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.fragment_course_describe;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    public void setData(boolean isFree,String content){
        if(!isFree){
            tvTip.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(content)){
            tvContent.setText(content);
        }
    }
}
