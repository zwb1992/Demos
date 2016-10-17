package com.iwanna.learn.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.AccountSafeVM;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSafeActivity extends BaseActivity<AccountSafeActivity, AccountSafeVM> {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_phone)
    TextView tvPhone;

    @Override
    public Class<AccountSafeVM> getVMClass() {
        return AccountSafeVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_account_safe;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.accountSafe);
        setPhone();
    }

    @OnClick({R.id.img_back, R.id.tv_cg_phone, R.id.tv_cg_pwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.tv_cg_phone:
                readyGoForResult(ChangePhoneActivity.class,101);
                break;
            case R.id.tv_cg_pwd:
                readyGo(ChangePwdActivity.class);
                break;
        }
    }
    private void setPhone(){
        String phone = DataCache.getInstance().getLoginPhone();
        if(phone != null){
            phone = phone.substring(0,4)+"****"+phone.substring(8,11);
            tvPhone.setText(phone);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK){
            setPhone();
        }
    }
}
