package com.zwb.zwbframe.mvvm;

import android.os.Bundle;

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
