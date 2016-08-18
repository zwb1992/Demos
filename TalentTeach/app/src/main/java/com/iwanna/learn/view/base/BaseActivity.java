package com.iwanna.learn.view.base;


import com.iwanna.learn.http.Net;
import com.iwanna.learn.http.NetApi;
import com.zwb.zwbframe.mvvm.AbstractBaseActivity;
import com.zwb.zwbframe.mvvm.AbstractViewMode;
import com.zwb.zwbframe.mvvm.IView;

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
