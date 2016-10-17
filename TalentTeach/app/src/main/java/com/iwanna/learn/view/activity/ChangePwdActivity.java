package com.iwanna.learn.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iwanna.learn.R;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.ChangePwdVM;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePwdActivity extends BaseActivity<ChangePwdActivity, ChangePwdVM> {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_password)
    EditText tvPassword;
    @Bind(R.id.tv_password2)
    EditText tvPassword2;
    @Bind(R.id.tv_password3)
    EditText tvPassword3;

    @Override
    public Class<ChangePwdVM> getVMClass() {
        return ChangePwdVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_change_pwd;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.change_pwd);
    }


    @OnClick({R.id.img_back, R.id.bt_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.bt_change:
                String pwd1 = tvPassword.getText().toString();
                if(TextUtils.isEmpty(pwd1)){
                    Toast.makeText(activity,R.string.password_cannot_be_null,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd1.length()< 6 || pwd1.length()>32){
                    Toast.makeText(activity,R.string.pwd_tip,Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd2 = tvPassword2.getText().toString();
                if(TextUtils.isEmpty(pwd2)){
                    Toast.makeText(activity,R.string.password_cannot_be_null,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd2.length()< 6 || pwd2.length()>32){
                    Toast.makeText(activity,R.string.pwd_tip,Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd3 = tvPassword3.getText().toString();
                if(!pwd2.equals(pwd3)){
                    Toast.makeText(activity,R.string.pwd_not_be_same,Toast.LENGTH_SHORT).show();
                    return;
                }
                showDialog();
                getViewModel().changePwd(pwd1,pwd2,pwd3);
                break;
        }
    }
}
