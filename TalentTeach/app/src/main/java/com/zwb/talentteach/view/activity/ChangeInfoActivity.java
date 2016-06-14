package com.zwb.talentteach.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zwb.talentteach.R;
import com.zwb.talentteach.viewmodel.ChangeInfoVM;
import com.zwb.zwbframe.mvvm.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 修改个人信息
 */
public class ChangeInfoActivity extends BaseActivity<ChangeInfoActivity, ChangeInfoVM> {
    public static final int NICKNAME = 1;
    public static final int ADDRESS = 2;
    public static final int NAME = 3;
    public static final int SEX = 4;
    public static final int AGE = 5;
    public static final int INTRODUCE = 6;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.rl_top)
    RelativeLayout rlTop;
    @Bind(R.id.ed_name)
    EditText edName;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    @Bind(R.id.ll_name)
    LinearLayout llName;
    @Bind(R.id.tv_tip)
    TextView tvTip;
    @Bind(R.id.rb_male)
    RadioButton rbMale;
    @Bind(R.id.rb_female)
    RadioButton rbFemale;
    @Bind(R.id.rg_sex)
    RadioGroup rgSex;

    private int type;

    @Override
    public int tellMeLayout() {
        return R.layout.activity_change_info;
    }

    @Override
    public Class<ChangeInfoVM> getVMClass() {
        return ChangeInfoVM.class;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        showView(type);
        edName.addTextChangedListener(new MyTextWatcher());
    }

    class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int length = s.length();
            tvTip.setText((28 - length) + "");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private void showView(int type) {
        llName.setVisibility(View.GONE);
        tvTip.setVisibility(View.GONE);
        rgSex.setVisibility(View.GONE);
        switch (type) {
            case NICKNAME:
                llName.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.nickname));
                break;
            case ADDRESS:
                llName.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.address));
                break;
            case NAME:
                llName.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.name));
                break;
            case AGE:
                llName.setVisibility(View.VISIBLE);
                edName.setInputType(InputType.TYPE_CLASS_NUMBER);
                tvTitle.setText(getString(R.string.age));
                break;
            case INTRODUCE:
                llName.setVisibility(View.VISIBLE);
                edName.setMinLines(3);
                edName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(28)});
                tvTip.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.introduce));
                break;
            case SEX:
                rgSex.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.sex));
                break;
        }
    }


    @OnClick({R.id.tv_cancel, R.id.tv_save, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_save:
                break;
            case R.id.iv_delete:
                break;
        }
    }
}
