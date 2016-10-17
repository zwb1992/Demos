package com.iwanna.learn.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.iwanna.learn.R;
import com.iwanna.learn.model.JigouIntroduce;
import com.iwanna.learn.model.Teacher;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.JigouOrTeacherVM;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 机构或者教师详情
 */
public class JigouOrTeacherActivity extends BaseActivity<JigouOrTeacherActivity, JigouOrTeacherVM> {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_content)
    TextView tvContent;

    private int id;
    private String title;
    private boolean isTeacher;
    @Override
    public Class<JigouOrTeacherVM> getVMClass() {
        return JigouOrTeacherVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_jigou_or_teacher;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        title = getIntent().getStringExtra("title");
        tvTitle.setText(title);
        id = getIntent().getIntExtra("id",-1);
        isTeacher = getIntent().getBooleanExtra("isTeacher",false);
        showDialog();
        if(isTeacher){
            getViewModel().getTeacherData(id,isTeacher);
        }else {
            getViewModel().getData(id,isTeacher);
        }
    }


    @OnClick(R.id.img_back)
    public void onClick() {
        onBackPressed();
    }

    public void setData(JigouIntroduce jigouOrTeacher){
        if(jigouOrTeacher == null){
            return;
        }
        if(!TextUtils.isEmpty(jigouOrTeacher.getAgencyLegalPerson())){
            tvName.setText(jigouOrTeacher.getAgencyLegalPerson());
        }
        if(!TextUtils.isEmpty(jigouOrTeacher.getAgencyIntroduction())){
            tvContent.setText(jigouOrTeacher.getAgencyIntroduction());
        }
    }

    public void setData(Teacher teacher){
        if(teacher == null){
            return;
        }
        if(!TextUtils.isEmpty(teacher.getAffiliation())){
            tvName.setText(teacher.getAffiliation());
        }
        if(!TextUtils.isEmpty(teacher.getSelfIntroduction())){
            tvContent.setText(teacher.getSelfIntroduction());
        }
    }
}
