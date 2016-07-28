package com.zwb.talentteach.view.fragment;

import android.os.Bundle;

import com.zwb.talentteach.R;
import com.zwb.talentteach.view.base.BaseFragment;
import com.zwb.talentteach.viewmodel.ActivityFragmentVM;

/***************************************
 * Author zhouweibin
 * Description .活动
 * Date:2016/6/13
 ***************************************/
public class ActivityFragment extends BaseFragment<ActivityFragment, ActivityFragmentVM> {
    @Override
    public int tellMeLayout() {
        return R.layout.fragment_activity;
    }

    @Override
    public Class<ActivityFragmentVM> getVMClass() {
        return ActivityFragmentVM.class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

}
