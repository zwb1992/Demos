package com.iwanna.learn.view.fragment;

import android.os.Bundle;

import com.iwanna.learn.R;
import com.iwanna.learn.view.base.BaseFragment;
import com.iwanna.learn.viewmodel.FindFragmentVM;

/***************************************
 * Author zhouweibin
 * Description .发现
 * Date:2016/6/13
 ***************************************/
public class FindFragment extends BaseFragment<FindFragment, FindFragmentVM> {

    @Override
    public int tellMeLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public Class<FindFragmentVM> getVMClass() {
        return FindFragmentVM.class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

}
