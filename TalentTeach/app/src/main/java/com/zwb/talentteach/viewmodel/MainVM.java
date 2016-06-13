package com.zwb.talentteach.viewmodel;

import com.zwb.talentteach.http.Net;
import com.zwb.talentteach.view.activity.MainActivity;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class MainVM extends AbstractViewMode<MainActivity> {
    public void getData(){
        Net.get(getView()).getData();
    }
}
