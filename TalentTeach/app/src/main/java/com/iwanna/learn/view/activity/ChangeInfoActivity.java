package com.iwanna.learn.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.R;
import com.iwanna.learn.viewmodel.ChangeInfoVM;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 修改个人信息
 */
public class ChangeInfoActivity extends BaseActivity<ChangeInfoActivity, ChangeInfoVM> {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ed_name)
    EditText edName;

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
        super.initView(savedInstanceState);
        tvTitle.setText(getString(R.string.nickname));
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.save);
    }




    @OnClick({R.id.img_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right:
                String name = edName.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(this,"昵称不为为空!",Toast.LENGTH_SHORT).show();
                    return;
                }
                showDialog();
                getViewModel().editName(name);
                break;
        }
    }

    public void editSuccess(String name){
        Toast.makeText(this,"您已修改昵称!",Toast.LENGTH_SHORT).show();
        finish();
        DataCache.getInstance().getmUserInfo().setNickName(name);
        DataCache.getInstance().saveUserToLocal();
        DataCache.getInstance().getUserFromLocal();
    }
}
