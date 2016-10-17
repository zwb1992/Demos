package com.iwanna.learn.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.LatLng;
import com.iwanna.learn.MyApplication;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.CourseDetails;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.view.fragment.CourseDescribeFragment;
import com.iwanna.learn.view.fragment.CourseDianpingFragment;
import com.iwanna.learn.view.fragment.CourseSiteFragment;
import com.iwanna.learn.viewmodel.CouresDetailsVM;
import com.zwb.zwbframe.http.HttpRequest;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class CouresDetailsActivity extends BaseActivity<CouresDetailsActivity, CouresDetailsVM> {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.img_course)
    ImageView imgCourse;
    @Bind(R.id.img_touxiang)
    ImageView imgTouxiang;
    @Bind(R.id.tv_TeachingObject)
    TextView tvTeachingObject;
    @Bind(R.id.tv_banxing)
    TextView tvBanxing;
    @Bind(R.id.tv_service_type)
    TextView tvServiceType;
    @Bind(R.id.tv_AgencyName)
    TextView tvAgencyName;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.v_course_describe1)
    View vCourseDescribe1;
    @Bind(R.id.v_course_describe2)
    View vCourseDescribe2;
    @Bind(R.id.v_teach_site1)
    View vTeachSite1;
    @Bind(R.id.v_teach_site2)
    View vTeachSite2;
    @Bind(R.id.v_dianpin1)
    View vDianpin1;
    @Bind(R.id.v_dianpin2)
    View vDianpin2;
    @Bind(R.id.tv_dianpin)
    TextView tvDianpin;
    @Bind(R.id.ll_bottom1)
    LinearLayout llBottom1;
    @Bind(R.id.tv_viewer)
    TextView tvViewer;
    @Bind(R.id.tv_baoming_num)
    TextView tvBaomingNum;
    @Bind(R.id.ll_bottom2)
    LinearLayout llBottom2;
    private int courseId;
    private String title;
    private CourseDetails courseDetails;

    private CourseDescribeFragment describeFragment;
    private CourseSiteFragment siteFragment;
    private CourseDianpingFragment dianpingFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private boolean isCollected = false;

    @Override
    public Class<CouresDetailsVM> getVMClass() {
        return CouresDetailsVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_coures_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        describeFragment = new CourseDescribeFragment();
        transaction.add(R.id.fl_fragment1, describeFragment);
        transaction.commitAllowingStateLoss();

        transaction = manager.beginTransaction();
        siteFragment = new CourseSiteFragment();
        transaction.add(R.id.fl_fragment1, siteFragment);
        transaction.commitAllowingStateLoss();

        transaction = manager.beginTransaction();
        dianpingFragment = new CourseDianpingFragment();
        transaction.add(R.id.fl_fragment1, dianpingFragment);
        transaction.commitAllowingStateLoss();

        transaction = manager.beginTransaction();
        transaction.hide(siteFragment);
        transaction.hide(dianpingFragment);
        transaction.show(describeFragment);
        transaction.commitAllowingStateLoss();

        courseId = getIntent().getIntExtra("courseId", -1);
        title = getIntent().getStringExtra("title");
        tvTitle.setText(title);
        tvRight.setBackgroundResource(R.mipmap.collect_empty);
        tvRight.setVisibility(View.VISIBLE);
        showDialog();
        getViewModel().getCourseDetails(courseId);
    }


    public void setData(CourseDetails courseDetails) {
        if (courseDetails == null) {
            return;
        }
        this.courseDetails = courseDetails;
        try {
            isCollected = courseDetails.isCollect();
            tvRight.setBackgroundResource(isCollected ? R.mipmap.coolect_full : R.mipmap.collect_empty);

            imgCourse.setTag(Net.NetInstance.IMG_URL + "/" + courseDetails.getCourseHeadImg());
            Net.imageLoader(Net.NetInstance.IMG_URL + "/" + courseDetails.getCourseHeadImg(), imgCourse,
                    DensityUtils.getW(CouresDetailsActivity.this),DensityUtils.dip2px(CouresDetailsActivity.this,260),
                    R.mipmap.default_img, R.mipmap.default_img, HttpRequest.ImageShapeType.NORMAL);
            describeFragment.setData(courseDetails.isFreeStudy(), courseDetails.getCourseIntroducing());
            siteFragment.setData(courseDetails.getSiteImg());
            dianpingFragment.setData(courseDetails.getContent());
            if (courseDetails.getContent() != null) {
                setDianpingNum(courseDetails.getContent().size());
            }

            if (!courseDetails.isIsTeacher()) {
                imgTouxiang.setTag(Net.NetInstance.IMG_URL + "/" + courseDetails.getImg());
                Net.imageLoader(Net.NetInstance.IMG_URL + "/" + courseDetails.getImg(), imgTouxiang,
                        DensityUtils.dip2px(CouresDetailsActivity.this,50),DensityUtils.dip2px(CouresDetailsActivity.this,50),
                        R.mipmap.default_face, R.mipmap.default_face, HttpRequest.ImageShapeType.ROUND);
                if (!TextUtils.isEmpty(courseDetails.getAgencyName())) {
                    tvAgencyName.setText(courseDetails.getAgencyName());
                }
                if (!TextUtils.isEmpty(courseDetails.getTeachAdress())) {
                    tvAddress.setText(courseDetails.getTeachAdress());
                }
                if (MyApplication.Latitude != 0 && MyApplication.Longitude != 0 &&
                        courseDetails.getLatitudeCoordinates() != 0 && courseDetails.getLongitudeCoordinates() != 0) {
                    float km = MainActivity.caculateDistance(new LatLng(MyApplication.Latitude, MyApplication.Longitude)
                            , new LatLng(courseDetails.getLatitudeCoordinates(), courseDetails.getLongitudeCoordinates()));
                    km = km / 1000.0f;
                    DecimalFormat df = new DecimalFormat("0.00");
                    km = Float.parseFloat(df.format(km));
                    tvAddress.setText(courseDetails.getTeachAdress() + " 约" + km + "km");
                }
            } else {
                llBottom1.setVisibility(View.GONE);
                llBottom2.setVisibility(View.VISIBLE);
                tvViewer.setText(courseDetails.getGlanceOver()+"次浏览");
                tvBaomingNum.setText(courseDetails.getApplyNumber()+"人报名");
                imgTouxiang.setTag(Net.NetInstance.IMG_URL + "/" + courseDetails.getTeacherHeadImg());
                Net.imageLoader(Net.NetInstance.IMG_URL + "/" + courseDetails.getTeacherHeadImg(), imgTouxiang, R.mipmap.default_face, R.mipmap.default_face, HttpRequest.ImageShapeType.ROUND);
                if (!TextUtils.isEmpty(courseDetails.getTeacherName())) {
                    tvAgencyName.setText(courseDetails.getTeacherName());
                }
                tvAddress.setVisibility(View.GONE);
            }
            tvTeachingObject.setText("授课对象：" + courseDetails.getTeachingObject());
            tvBanxing.setText("班型：" + courseDetails.getTeachNumber());

            CourseDetails.PriceBean priceBean = courseDetails.getPrice().get(0);
            if("价格面议".equals(priceBean.getServiceType())){
                tvServiceType.setText("价格面议");
            }else {
                String text = priceBean.getServiceType() + "：￥" + priceBean.getPrice() + "/" + "学期" + "/" + priceBean.getCourseTime() + "课时";
                SpannableString spanString = new SpannableString(text);
                ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.tip_yellow_color));
                spanString.setSpan(span, priceBean.getServiceType().length() + 1, text.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvServiceType.setText(spanString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.img_back, R.id.tv_right, R.id.tv_course_describe, R.id.tv_teach_site,
            R.id.tv_dianpin, R.id.tv_call, R.id.tv_baoming, R.id.ll_details})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.tv_right:
                if (courseDetails == null) {
                    return;
                }
                showDialog();
                getViewModel().collect(isCollected, courseId);
                break;
            case R.id.tv_course_describe:
                vCourseDescribe1.setVisibility(View.VISIBLE);
                vCourseDescribe2.setVisibility(View.VISIBLE);
                vTeachSite1.setVisibility(View.INVISIBLE);
                vTeachSite2.setVisibility(View.INVISIBLE);
                vDianpin1.setVisibility(View.INVISIBLE);
                vDianpin2.setVisibility(View.INVISIBLE);

                transaction = manager.beginTransaction();
                transaction.show(describeFragment);
                transaction.hide(siteFragment);
                transaction.hide(dianpingFragment);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.tv_teach_site:
                vCourseDescribe1.setVisibility(View.INVISIBLE);
                vCourseDescribe2.setVisibility(View.INVISIBLE);
                vTeachSite1.setVisibility(View.VISIBLE);
                vTeachSite2.setVisibility(View.VISIBLE);
                vDianpin1.setVisibility(View.INVISIBLE);
                vDianpin2.setVisibility(View.INVISIBLE);

                transaction = manager.beginTransaction();
                transaction.hide(describeFragment);
                transaction.hide(dianpingFragment);
                transaction.show(siteFragment);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.tv_dianpin:
                vCourseDescribe1.setVisibility(View.INVISIBLE);
                vCourseDescribe2.setVisibility(View.INVISIBLE);
                vTeachSite1.setVisibility(View.INVISIBLE);
                vTeachSite2.setVisibility(View.INVISIBLE);
                vDianpin1.setVisibility(View.VISIBLE);
                vDianpin2.setVisibility(View.VISIBLE);

                transaction = manager.beginTransaction();
                transaction.hide(describeFragment);
                transaction.hide(siteFragment);
                transaction.show(dianpingFragment);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.tv_call:
                if (courseDetails != null && !TextUtils.isEmpty(courseDetails.getContactPhone())) {
                    Uri uri = Uri.parse("tel:" + courseDetails.getContactPhone());
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(callIntent);
                } else {
                    Toast.makeText(this, R.string.ask_phone_is_null, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_baoming:
                if (courseDetails == null) {
                    return;
                }
                Intent baomingIntent = new Intent(this,EntryActivity.class);
                baomingIntent.putExtra("phone",courseDetails.getContactPhone());
                baomingIntent.putExtra("content",courseDetails.getAgencyName());
                baomingIntent.putExtra("img",courseDetails.getImg());
                baomingIntent.putExtra("name",courseDetails.getCourseName());
                baomingIntent.putExtra("CourseId",courseId);
                startActivity(baomingIntent);
                break;
            case R.id.ll_details:
                if (courseDetails == null) {
                    return;
                }
                Intent intent = new Intent(this, JigouOrTeacherActivity.class);
                intent.putExtra("id", courseDetails.isIsTeacher() ? courseDetails.getTeacherID() : courseDetails.getAgencyID());
                intent.putExtra("title", courseDetails.isIsTeacher() ? courseDetails.getTeacherName() : courseDetails.getAgencyName());
                intent.putExtra("isTeacher", courseDetails.isIsTeacher());
                startActivity(intent);
                break;
        }
    }

    public void isCollected(boolean isCollected) {
        this.isCollected = isCollected;
        if (isCollected) {
            tvRight.setBackgroundResource(R.mipmap.coolect_full);
        } else {
            tvRight.setBackgroundResource(R.mipmap.collect_empty);
        }
    }

    public int getCourseId() {
        return courseId;
    }

    public void setDianpingNum(int i) {
        tvDianpin.setText("用户点评（" + i + "）");
    }


}
