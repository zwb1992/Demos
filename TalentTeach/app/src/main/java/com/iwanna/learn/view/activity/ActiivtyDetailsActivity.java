package com.iwanna.learn.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.Actitivities;
import com.iwanna.learn.utils.CalendarUtils;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.view.customview.WheelView;
import com.iwanna.learn.viewmodel.ActivityDetailsVM;
import com.zwb.zwbframe.http.HttpRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 活动详情
 */
public class ActiivtyDetailsActivity extends BaseActivity<ActiivtyDetailsActivity, ActivityDetailsVM> implements View.OnClickListener{

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_activity)
    ImageView imgActivity;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_select)
    TextView tvSelect;
    @Bind(R.id.tv_details)
    TextView tvDetails;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.ed_name)
    EditText edName;
    private Actitivities actitivities;
    private PopupWindow mPopupWindow;
    private List<String> list = new ArrayList<>();
    private WheelView wl_sex;
    @Override
    public Class<ActivityDetailsVM> getVMClass() {
        return ActivityDetailsVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_actiivty_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.activity_details);
        actitivities = (Actitivities) getIntent().getSerializableExtra("act");
        initData();
    }
    private void initData(){
        if (actitivities == null){
            return;
        }
        String imgUrl = "";
        if(!TextUtils.isEmpty(actitivities.getActivityHeadImg())) {
            imgUrl = Net.NetInstance.IMG_URL + "/" + actitivities.getActivityHeadImg();

        }else {
            imgUrl = Net.NetInstance.IMG_URL + "/" + actitivities.getActivityImg();
        }
        imgActivity.setTag(imgUrl);
        Net.imageLoader(imgUrl, imgActivity,
                DensityUtils.getW(ActiivtyDetailsActivity.this),DensityUtils.dip2px(ActiivtyDetailsActivity.this,260),
                R.mipmap.default_activity, R.mipmap.default_activity, HttpRequest.ImageShapeType.NORMAL);

        String startTime = actitivities.getStartTime();
        String endTime = actitivities.getEndTime();
        if(!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)){
            startTime = startTime.replace("T"," ");
            endTime = endTime.replace("T"," ");
            Date stDate = CalendarUtils.getDate(startTime);
            Date enDate = CalendarUtils.getDate(endTime);
            if(stDate != null && enDate != null){
                startTime = CalendarUtils.getTime1(stDate);
                endTime = CalendarUtils.getTime1(enDate);
                if(startTime.substring(0,6).equals(endTime.substring(0,6))){
                    tvTime.setText(startTime.substring(0)+"-"+endTime.substring(7));
                }else {
                    tvTime.setText(startTime+"-"+endTime);
                }
            }
        }
        if(!TextUtils.isEmpty(actitivities.getActivityName())){
            tvName.setText(actitivities.getActivityName());
        }
        if(!TextUtils.isEmpty(actitivities.getActivityAddress())){
            tvAddress.setText(actitivities.getActivityAddress());
        }
        if(!TextUtils.isEmpty(actitivities.getActivityContent())){
            tvDetails.setText(actitivities.getActivityContent());
        }
    }

    @OnClick({R.id.img_back, R.id.tv_select, R.id.bt_join})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.tv_select:
                showPeoplePopupWindow();
                break;
            case R.id.btn_cancel:
                mPopupWindow.dismiss();
                break;
            case R.id.btn_sure:
                tvSelect.setText(wl_sex.getSeletedItem());
                mPopupWindow.dismiss();
                break;
            case R.id.bt_join:
                String name = edName.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(this,R.string.name2,Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone = edPhone.getText().toString();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(this,R.string.activity_phone,Toast.LENGTH_SHORT).show();
                    return;
                }
                String num = tvSelect.getText().toString();
                if(TextUtils.isEmpty(num)){
                    Toast.makeText(this,R.string.input_join_people,Toast.LENGTH_SHORT).show();
                    return;
                }
                showDialog();
                getViewModel().join(actitivities.getActivityID(),name,phone,num);
                break;
        }
    }
    /**
     * 显示人数
     */
    private void showPeoplePopupWindow() {
        if (mPopupWindow != null) {
            mPopupWindow.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
        } else {
            for (int i = 1;i<=100;i++){
                list.add(i+"");
            }
            View window = getLayoutInflater().inflate(R.layout.popup_setsex, null);
            RelativeLayout main = (RelativeLayout) window.findViewById(R.id.main);
            RelativeLayout content = (RelativeLayout) window.findViewById(R.id.rl_content);
            wl_sex = (WheelView) window.findViewById(R.id.wl_sex);
            TextView btn_cancel = (TextView) window.findViewById(R.id.btn_cancel);
            TextView btn_sure = (TextView) window.findViewById(R.id.btn_sure);
            mPopupWindow = new PopupWindow(window,
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            wl_sex.setOffset(1);
            wl_sex.setSelectColor(getResources().getColor(R.color.black));
            wl_sex.setItems(list);
            wl_sex.setSeletion(0);
            btn_cancel.setOnClickListener(this);
            btn_sure.setOnClickListener(this);
            main.setOnClickListener(this);
            content.setOnClickListener(this);
            mPopupWindow.setFocusable(true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
        }
    }
}
