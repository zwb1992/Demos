package com.zwb.talentteach.viewmodel;

import android.content.Intent;

import com.zwb.talentteach.view.activity.ChangeInfoActivity;
import com.zwb.talentteach.view.activity.PersonInfoActivity;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/14
 ***************************************/
public class PersonInfoVM extends AbstractViewMode<PersonInfoActivity> {
    public void goChange(int type){
        Intent intent = new Intent(getView(), ChangeInfoActivity.class);
        intent.putExtra("type",type);
        getView().startActivity(intent);
    }
}
