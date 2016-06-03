package com.zwb.mvvm;

import android.os.Bundle;

import butterknife.ButterKnife;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/3
 ***************************************/
public abstract class BaseActivity<T extends IView, VM extends AbstractViewMode<T>> extends AbstractBaseActitivty<T, VM> {

    @Override
    public boolean useButterknife() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(tellMeLayout());
        if (useButterknife()) {
            ButterKnife.bind(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
