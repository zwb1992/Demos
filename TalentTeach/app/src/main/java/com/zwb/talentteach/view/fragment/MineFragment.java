package com.zwb.talentteach.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwb.talentteach.R;
import com.zwb.talentteach.viewmodel.MineFragmentVM;
import com.zwb.zwbframe.mvvm.BaseFragment;

import butterknife.Bind;

/***************************************
 * Author zhouweibin
 * Description .我的
 * Date:2016/6/13
 ***************************************/
public class MineFragment extends BaseFragment<MineFragment, MineFragmentVM> {
    @Bind(R.id.tv)
    TextView tv;

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
        tv.setText("=========MineFragment==============");
    }

}
