package com.iwanna.learn.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.UserInfo;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.view.customview.WheelView;
import com.iwanna.learn.viewmodel.PersonInfoVM;
import com.zwb.imagepickerlib.Cropper;
import com.zwb.zwbframe.http.HttpRequest;
import com.zwb.zwbframe.utils.BitmapTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/14
 ***************************************/
public class PersonInfoActivity extends BaseActivity<PersonInfoActivity, PersonInfoVM> implements View.OnClickListener {
    @Bind(R.id.iv_face)
    ImageView ivFace;
    @Bind(R.id.tv_nick)
    TextView tvNick;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_introduce)
    TextView tvIntroduce;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private PopupWindow mPopupWindow;
    private Cropper mCropper = null;

    private UserInfo mUserInfo;
    private WheelView wl_sex;
    private PopupWindow sexPopupWindow;
    private List<String> sexList = new ArrayList<>();

    private WheelView wl_age;
    private PopupWindow agePopupWindow;
    private List<String> ageList = new ArrayList<>();


    @Override
    public int tellMeLayout() {
        return R.layout.activity_person_info;
    }

    @Override
    public Class<PersonInfoVM> getVMClass() {
        return PersonInfoVM.class;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.person_info);
        //  初始化图片拍照
        mCropper = new Cropper(activity, Cropper.ImageType.HEAD_PIC);
        mUserInfo = DataCache.getInstance().getmUserInfo();
        Log.i("info",mUserInfo.toString());
        initData();
    }
    private void initData(){

        ivFace.setTag(Net.NetInstance.IMG_URL + "/" + mUserInfo.getUserImg());
        Net.imageLoader(Net.NetInstance.IMG_URL + "/" + mUserInfo.getUserImg(), ivFace,
                DensityUtils.dip2px(PersonInfoActivity.this,68),DensityUtils.dip2px(PersonInfoActivity.this,68),
                R.mipmap.default_face_50, R.mipmap.default_face_50, HttpRequest.ImageShapeType.ROUND);

        if(!TextUtils.isEmpty(mUserInfo.getNickName())){
            tvNick.setText(mUserInfo.getNickName());
        }
        if(!TextUtils.isEmpty(mUserInfo.getUserAge())){
            tvAge.setText(mUserInfo.getUserAge());
        }
        if(!TextUtils.isEmpty(mUserInfo.getUserSex())){
            tvSex.setText(mUserInfo.getUserSex());
        }
        if(!TextUtils.isEmpty(mUserInfo.getUserLike())){
            tvIntroduce.setText(mUserInfo.getUserLike());
        }
    }
    @OnClick({R.id.img_back,R.id.ll_info, R.id.ll_nick, R.id.ll_sex, R.id.ll_age, R.id.ll_introduce})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.ll_info:
                shouPopuWindow();
                break;
            case R.id.ll_nick:
                readyGoForResult(ChangeInfoActivity.class,123);
                break;
            case R.id.ll_sex:
                showSexPopupWindow();
                break;
            case R.id.ll_age:
                showAgePopupWindow();
                break;
            case R.id.ll_introduce:
                readyGoForResult(LikesActivity.class,333);
                break;
            case R.id.btn_cancel:
                mPopupWindow.dismiss();
                break;
            case R.id.btn_photo:
                mCropper.getPhotoFromPictureLibrary();
                mPopupWindow.dismiss();
                break;
            case R.id.btn_takePhoto:
                mCropper.getPhotoFromCamera();
                mPopupWindow.dismiss();
                break;
        }
    }

    /**
     * 显示选择图片窗口
     */
    private void shouPopuWindow() {

        View window = getLayoutInflater().inflate(R.layout.popup_getpic, null);

        Button btn_takePhoto, btn_photo, btn_cancel;

        btn_cancel = (Button) window.findViewById(R.id.btn_cancel);
        btn_photo = (Button) window.findViewById(R.id.btn_photo);
        btn_takePhoto = (Button) window.findViewById(R.id.btn_takePhoto);

        mPopupWindow = new PopupWindow(window,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        btn_cancel.setOnClickListener(this);
        btn_photo.setOnClickListener(this);
        btn_takePhoto.setOnClickListener(this);

        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(findViewById(R.id.ll_personinfo), Gravity.CENTER, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 333 && resultCode == RESULT_OK){
            String like = data.getStringExtra("like");
            tvIntroduce.setText(like);
            getViewModel().editLike(like);
        } else if(requestCode == 123){
            mUserInfo = DataCache.getInstance().getmUserInfo();
            if(!TextUtils.isEmpty(mUserInfo.getNickName())){
                tvNick.setText(mUserInfo.getNickName());
            }
        }else {
            //        图片压缩地址返回
            String compressPath = mCropper.getCompressPath(requestCode, resultCode, data);

            if (compressPath != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(compressPath);
                ivFace.setImageBitmap(BitmapTools.toRound(bitmap));
                Log.i("info", "=====" + compressPath);
            }
            if(mPopupWindow != null) {
                mPopupWindow.dismiss();
            }
        }

    }
    /**
     * 显示人数
     */
    private void showSexPopupWindow() {
        if (sexPopupWindow != null) {
            sexPopupWindow.showAtLocation(findViewById(R.id.ll_personinfo), Gravity.CENTER, 0, 0);
        } else {
            sexList.add("男");
            sexList.add("女");
            View window = getLayoutInflater().inflate(R.layout.popup_setsex, null);
            RelativeLayout main = (RelativeLayout) window.findViewById(R.id.main);
            RelativeLayout content = (RelativeLayout) window.findViewById(R.id.rl_content);
            wl_sex = (WheelView) window.findViewById(R.id.wl_sex);
            TextView btn_cancel = (TextView) window.findViewById(R.id.btn_cancel);
            TextView btn_sure = (TextView) window.findViewById(R.id.btn_sure);
            sexPopupWindow = new PopupWindow(window,
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            wl_sex.setOffset(1);
            wl_sex.setSelectColor(getResources().getColor(R.color.black));
            wl_sex.setItems(sexList);
            wl_sex.setSeletion(0);
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sexPopupWindow.dismiss();
                }
            });
            btn_sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sexPopupWindow.dismiss();
                    String sex = wl_sex.getSeletedItem();
                    tvSex.setText(sex);
                    getViewModel().editSex(sex);
                }
            });
            main.setOnClickListener(this);
            content.setOnClickListener(this);
            sexPopupWindow.setFocusable(true);
            sexPopupWindow.setOutsideTouchable(true);
            sexPopupWindow.showAtLocation(findViewById(R.id.ll_personinfo), Gravity.CENTER, 0, 0);
        }
    }
    /**
     * 显示年龄
     */
    private void showAgePopupWindow() {
        if (agePopupWindow != null) {
            agePopupWindow.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
        } else {
            for (int i = 0;i<100;i++){
                ageList.add(i+"");
            }
            View window = getLayoutInflater().inflate(R.layout.popup_setsex, null);
            RelativeLayout main = (RelativeLayout) window.findViewById(R.id.main);
            RelativeLayout content = (RelativeLayout) window.findViewById(R.id.rl_content);
            wl_age = (WheelView) window.findViewById(R.id.wl_sex);
            TextView btn_cancel = (TextView) window.findViewById(R.id.btn_cancel);
            TextView btn_sure = (TextView) window.findViewById(R.id.btn_sure);
            agePopupWindow = new PopupWindow(window,
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            wl_age.setOffset(1);
            wl_age.setSelectColor(getResources().getColor(R.color.black));
            wl_age.setItems(ageList);
            wl_age.setSeletion(0);
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    agePopupWindow.dismiss();
                }
            });
            btn_sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    agePopupWindow.dismiss();
                    String age = wl_age.getSeletedItem();
                    tvAge.setText(age);
                    getViewModel().editAge(age);
                }
            });
            main.setOnClickListener(this);
            content.setOnClickListener(this);
            agePopupWindow.setFocusable(true);
            agePopupWindow.setOutsideTouchable(true);
            agePopupWindow.showAtLocation(findViewById(R.id.ll_personinfo), Gravity.CENTER, 0, 0);
        }
    }
    public void editSexFailed(){
        mUserInfo = DataCache.getInstance().getmUserInfo();
        if(!TextUtils.isEmpty(mUserInfo.getUserSex())){
            tvSex.setText(mUserInfo.getUserSex());
        }
    }
    public void editAgeFailed(){
        mUserInfo = DataCache.getInstance().getmUserInfo();
        if(!TextUtils.isEmpty(mUserInfo.getUserAge())){
            tvAge.setText(mUserInfo.getUserAge());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        mCropper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
