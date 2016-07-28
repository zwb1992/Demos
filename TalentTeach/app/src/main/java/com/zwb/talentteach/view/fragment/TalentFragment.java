package com.zwb.talentteach.view.fragment;

import android.os.Bundle;

import com.zwb.talentteach.R;
import com.zwb.talentteach.view.base.BaseFragment;
import com.zwb.talentteach.viewmodel.TalentFragmentVM;

/***************************************
 * Author zhouweibin
 * Description .才艺
 * Date:2016/6/13
 ***************************************/
public class TalentFragment extends BaseFragment<TalentFragment, TalentFragmentVM> {
    @Override
    public int tellMeLayout() {
        return R.layout.fragment_talent;
    }

    @Override
    public Class<TalentFragmentVM> getVMClass() {
        return TalentFragmentVM.class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

}
