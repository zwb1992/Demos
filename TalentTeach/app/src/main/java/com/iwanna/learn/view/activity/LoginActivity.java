package com.iwanna.learn.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.LoginVM;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginActivity, LoginVM> {

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.ed_password)
    EditText edPassword;

    private boolean isFirst;
    @Override
    public Class<LoginVM> getVMClass() {
        return LoginVM.class;
    }

    @Override
    public int tellMeLayout() {
        /**
         * 如果用户已经注册了，直接跳转到首页
         */
        if(DataCache.getInstance().getUserID() != -1){
            loginSuccess();
        }
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.account_login);
        isFirst = getIntent().getBooleanExtra("isFirst",false);
        if(isFirst){
            imgBack.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.img_back, R.id.tv_forgot, R.id.bt_login, R.id.tv_regist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_forgot:
                readyGo(ForgetPwdActivity.class);
                break;
            case R.id.bt_login:
                String phone = edPhone.getText().toString();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(activity,R.string.phone_cannot_be_null,Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = edPassword.getText().toString();
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(activity,R.string.password_cannot_be_null,Toast.LENGTH_SHORT).show();
                    return;
                }
                showDialog();
                getViewModel().login(phone,pwd);
                break;
            case R.id.tv_regist:
                readyGoForResult(RegistActivity.class,101);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK){
            String phone = data.getStringExtra("phone");
            String pwd = data.getStringExtra("pwd");
            edPhone.setText(phone);
            edPassword.setTag(pwd);
        }
    }
    public void loginSuccess(){
        readyGoThenKill(MainActivity.class);
    }
}
