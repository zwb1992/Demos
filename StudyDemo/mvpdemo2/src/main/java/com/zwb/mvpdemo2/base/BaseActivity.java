package com.zwb.mvpdemo2.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/2
 ***************************************/
public abstract class BaseActivity<T extends IView,P extends AbstractBasePresenter<T>> extends AbstractBaseActitivty<T,P> {
    protected Context context = null;
    protected Activity activity = null;
    @Override
    public void initView(Bundle savedInstanceState) {
        context = this;
        activity = this;
    }

    @Override
    public boolean useButterknife() {
        return true;
    }
}
