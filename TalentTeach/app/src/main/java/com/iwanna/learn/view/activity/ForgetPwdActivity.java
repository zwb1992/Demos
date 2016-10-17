package com.iwanna.learn.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iwanna.learn.R;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.ForgetPwdVM;
import com.iwanna.learn.viewmodel.RegistVM;

import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivity<ForgetPwdActivity, ForgetPwdVM> {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.ed_vercode)
    EditText edVercode;
    @Bind(R.id.tv_getvercode)
    TextView tvGetvercode;
    @Bind(R.id.ed_password)
    EditText edPassword;
    @Bind(R.id.ed_password2)
    EditText edPassword2;

    private int code ;

    @Override
    public Class<ForgetPwdVM> getVMClass() {
        return ForgetPwdVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.forget_password1);
    }

    @OnClick({R.id.img_back, R.id.tv_getvercode, R.id.bt_regist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_getvercode:
                i = 60;
                String phone1 = edPhone.getText().toString();
                if(TextUtils.isEmpty(phone1)){
                    Toast.makeText(activity,R.string.phone_cannot_be_null,Toast.LENGTH_SHORT).show();
                    return;
                }
                randomCode();
                showDialog();
                getViewModel().getCode(phone1,code);
                break;
            case R.id.bt_regist:
                String phone = edPhone.getText().toString();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(activity,R.string.phone_cannot_be_null,Toast.LENGTH_SHORT).show();
                    return;
                }
                String vercode = edVercode.getText().toString();
                if(TextUtils.isEmpty(vercode)){
                    Toast.makeText(activity,R.string.vercode_cannot_be_null,Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = edPassword.getText().toString();
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(activity,R.string.password_cannot_be_null,Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd2 = edPassword2.getText().toString();
                if(!pwd.equals(pwd2)){
                    Toast.makeText(activity,R.string.pwd_not_be_same,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(""+code).equals(vercode)){
                    Toast.makeText(activity,R.string.vercode_is_error,Toast.LENGTH_SHORT).show();
                    return;
                }
                showDialog();
                getViewModel().resetPwd(phone,pwd);
                break;
        }
    }

    /**
     * 获取验证码成功或者失败
     * @param flag
     */
    public void updateTvGetCode(boolean flag){
        if(flag){
            handler.post(runnable);
        }else {
            tvGetvercode.setEnabled(true);
        }
    }
    private static Handler handler = new Handler();
    private int i;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (i < 0) {
                if(tvGetvercode != null) {
                    tvGetvercode.setEnabled(true);
                    tvGetvercode.setText(getString(R.string.get_vercode));
                }
                handler.removeCallbacks(this);
            } else {
                if(tvGetvercode != null) {
                    tvGetvercode.setEnabled(false);
                    tvGetvercode.setText(String.format(getString(R.string.r_get_varcode), i + ""));
                }
                handler.postDelayed(this, 1000);
                i--;
            }
        }
    };

    /**
     * 重置密码成功
     */
    public void registSucccess(){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    public void randomCode(){
        code = new Random().nextInt(8999)+1000;
    }
}
