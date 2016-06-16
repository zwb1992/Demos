package com.zwb.talentteach.view.base;


import com.zwb.talentteach.http.Net;
import com.zwb.talentteach.http.NetApi;
import com.zwb.zwbframe.mvvm.AbstractBaseActivity;
import com.zwb.zwbframe.mvvm.AbstractViewMode;
import com.zwb.zwbframe.mvvm.IView;

import butterknife.ButterKnife;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/3
 ***************************************/
public abstract class BaseActivity<T extends IView, VM extends AbstractViewMode<T>> extends AbstractBaseActivity<T, VM> {

    @Override
    public boolean useButterknife() {
        return true;
    }

    public NetApi net(){
        return Net.get(this);
    }
}
