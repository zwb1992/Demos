package com.iwanna.learn.view.fragment;

import android.os.Bundle;

import com.iwanna.learn.viewmodel.ActivityFragmentVM;
import com.iwanna.learn.R;
import com.iwanna.learn.view.base.BaseFragment;

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
