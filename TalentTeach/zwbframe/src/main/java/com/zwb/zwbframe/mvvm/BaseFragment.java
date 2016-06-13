package com.zwb.zwbframe.mvvm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public abstract class BaseFragment<T extends IView, VM extends AbstractViewMode<T>> extends AbstractBaseFragment<T,VM> {
    private Context context;
    private Activity activity;
    @Override
    public boolean useButterknife() {
        return true;
    }
    @Override
    protected void injectView(View mRootView) {
        ButterKnife.bind(this, mRootView);
        context = getActivity();
        activity = getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
