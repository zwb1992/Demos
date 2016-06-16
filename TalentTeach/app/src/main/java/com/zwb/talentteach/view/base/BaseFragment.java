package com.zwb.talentteach.view.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.zwb.talentteach.http.Net;
import com.zwb.talentteach.http.NetApi;
import com.zwb.zwbframe.mvvm.AbstractBaseFragment;
import com.zwb.zwbframe.mvvm.AbstractViewMode;
import com.zwb.zwbframe.mvvm.IView;

import butterknife.ButterKnife;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public abstract class BaseFragment<T extends IView, VM extends AbstractViewMode<T>> extends AbstractBaseFragment<T, VM> {
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
    public NetApi net(){
        return Net.get(this);
    }
}
