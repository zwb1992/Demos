package com.zwb.talentteach.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zwb.talentteach.R;
import com.zwb.talentteach.view.base.BaseActivity;
import com.zwb.talentteach.viewmodel.PayVM;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 支付页面
 */
public class PayActivity extends BaseActivity<PayActivity, PayVM> {
    @Bind(R.id.iv_face)
    ImageView ivFace;
    @Bind(R.id.tv_tel)
    TextView tvTel;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_need_pay)
    TextView tvNeedPay;
    @Bind(R.id.RadioButtonWeChat)
    RadioButton RadioButtonWeChat;
    @Bind(R.id.RadioButtonAlipay)
    RadioButton RadioButtonAlipay;
    @Bind(R.id.ButtonPay)
    Button ButtonPay;

    @Override
    public int tellMeLayout() {
        return R.layout.activity_pay;
    }

    @Override
    public Class<PayVM> getVMClass() {
        return PayVM.class;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @OnClick(R.id.ButtonPay)
    public void onClick() {
        if (RadioButtonAlipay.isChecked()) {
            getViewModel().aliPay();
        } else if (RadioButtonWeChat.isChecked()) {
            getViewModel().wxPay();
        }
    }
}
