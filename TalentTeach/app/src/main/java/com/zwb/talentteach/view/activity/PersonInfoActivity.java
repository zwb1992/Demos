package com.zwb.talentteach.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zwb.imagepickerlib.Cropper;
import com.zwb.talentteach.R;
import com.zwb.talentteach.viewmodel.PersonInfoVM;
import com.zwb.zwbframe.mvvm.BaseActivity;

import butterknife.Bind;
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
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_introduce)
    TextView tvIntroduce;

    private PopupWindow mPopupWindow;
    private Cropper mCropper = null;

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
        //  初始化图片拍照
        mCropper = new Cropper(activity, Cropper.ImageType.HEAD_PIC);
    }

    @OnClick({R.id.ll_info, R.id.ll_nick, R.id.ll_address, R.id.ll_name, R.id.ll_sex, R.id.ll_age, R.id.ll_introduce})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_info:
                shouPopuWindow();
                break;
            case R.id.ll_nick:
                getViewModel().goChange(ChangeInfoActivity.NICKNAME);
                break;
            case R.id.ll_address:
                getViewModel().goChange(ChangeInfoActivity.ADDRESS);
                break;
            case R.id.ll_name:
                getViewModel().goChange(ChangeInfoActivity.NAME);
                break;
            case R.id.ll_sex:
                getViewModel().goChange(ChangeInfoActivity.SEX);
                break;
            case R.id.ll_age:
                getViewModel().goChange(ChangeInfoActivity.AGE);
                break;
            case R.id.ll_introduce:
                getViewModel().goChange(ChangeInfoActivity.INTRODUCE);
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
        //        图片压缩地址返回
        String compressPath = mCropper.getCompressPath(requestCode, resultCode, data);

        if (compressPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(compressPath);
            ivFace.setImageBitmap(bitmap);
            Log.i("info", "=====" + compressPath);
        }
        mPopupWindow.dismiss();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        mCropper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
