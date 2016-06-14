package com.zwb.talentteach.view.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.zwb.talentteach.R;
import com.zwb.talentteach.viewmodel.FindFragmentVM;
import com.zwb.talentteach.viewmodel.MineFragmentVM;
import com.zwb.zwbframe.mvvm.BaseFragment;

import butterknife.Bind;

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
