package com.zwb.talentteach.viewmodel;


import android.content.Intent;

import com.zwb.talentteach.view.activity.PersonInfoActivity;
import com.zwb.talentteach.view.fragment.MineFragment;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class MineFragmentVM extends AbstractViewMode<MineFragment> {
    public void goPersonInfo(){
        Intent intent = new Intent(getView().getActivity(), PersonInfoActivity.class);
        getView().startActivity(intent);
    }
}
