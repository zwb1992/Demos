package com.zwb.talentteach.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwb.talentteach.R;
import com.zwb.talentteach.view.activity.PayActivity;
import com.zwb.talentteach.view.base.BaseFragment;
import com.zwb.talentteach.viewmodel.MineFragmentVM;

import butterknife.Bind;
import butterknife.OnClick;

/***************************************
 * Author zhouweibin
 * Description .我的
 * Date:2016/6/13
 ***************************************/
public class MineFragment extends BaseFragment<MineFragment, MineFragmentVM> {

    @Bind(R.id.iv_face)
    ImageView ivFace;
    @Bind(R.id.tv_tel)
    TextView tvTel;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_service_tel)
    TextView tvServiceTel;

    @Override
    public int tellMeLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public Class<MineFragmentVM> getVMClass() {
        return MineFragmentVM.class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @OnClick({R.id.ll_info, R.id.tv_collection, R.id.tv_entry, R.id.tv_join, R.id.tv_account, R.id.call})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_info:
                getViewModel().goPersonInfo();
                break;
            case R.id.tv_collection:
                break;
            case R.id.tv_entry:
                break;
            case R.id.tv_join:
                break;
            case R.id.tv_account:
                Intent intent = new Intent(mContext, PayActivity.class);
                startActivity(intent);
                break;
            case R.id.call:
                break;
        }
    }
}
