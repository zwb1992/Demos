package com.zwb.talentteach.view.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.zwb.talentteach.R;
import com.zwb.talentteach.viewmodel.ActivityFragmentVM;
import com.zwb.zwbframe.mvvm.BaseFragment;

import butterknife.Bind;

/***************************************
 * Author zhouweibin
 * Description .活动
 * Date:2016/6/13
 ***************************************/
public class ActivityFragment extends BaseFragment<ActivityFragment, ActivityFragmentVM> {
    @Bind(R.id.tv)
    TextView tv;

    @Override
    public int tellMeLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public Class<ActivityFragmentVM> getVMClass() {
        return ActivityFragmentVM.class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tv.setText("=========ActivityFragment==============");
    }

}
