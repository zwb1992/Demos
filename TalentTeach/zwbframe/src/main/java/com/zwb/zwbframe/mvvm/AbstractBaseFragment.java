package com.zwb.zwbframe.mvvm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public abstract class AbstractBaseFragment<T extends IView, VM extends AbstractViewMode<T>> extends Fragment implements IView {
    private final ViewModelHelper<T, VM> mViewModeHelper = new ViewModelHelper<>();
    protected Context mContext;
    private View mRootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view;
        injectView(mRootView);
        //varyView
        initView(savedInstanceState);
        setModelView((T) this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.create(getVMClass());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (tellMeLayout() != 0) {
            return inflater.inflate(tellMeLayout(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    public abstract int tellMeLayout();

    public abstract boolean useButterknife();

    public abstract Class<VM> getVMClass();

    public final VM getViewModel() {
        return mViewModeHelper.getViewModel();
    }

    protected void injectView(View mRootView) {
        //ButterKnife
    }

    protected abstract void initView(Bundle savedInstanceState);

    public final void setModelView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModeHelper.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModeHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModeHelper.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModeHelper.onDestroy();
    }
}
