package com.zwb.talentteach.view.fragment;

import android.os.Bundle;

import com.zwb.talentteach.R;
import com.zwb.talentteach.view.base.BaseFragment;
import com.zwb.talentteach.viewmodel.FindFragmentVM;

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
