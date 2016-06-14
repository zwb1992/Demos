package com.zwb.zwbframe.mvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/3
 ***************************************/
public abstract class AbstractBaseActivity<T extends IView, VM extends AbstractViewMode<T>> extends AppCompatActivity implements IView {
    private final ViewModelHelper<T, VM> mViewModeHelper = new ViewModelHelper<>();
    public AbstractBaseActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.create(getVMClass());
        setContentView(tellMeLayout());
        activity = this;
        if (useButterknife()) {
            ButterKnife.bind(this);
        }
        initView(savedInstanceState);
        setModelView((T)this);
    }

    public abstract int tellMeLayout();

    public abstract boolean useButterknife();

    public abstract Class<VM> getVMClass();

    public final VM getViewModel() {
        return mViewModeHelper.getViewModel();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    public abstract void initView(Bundle savedInstanceState);

    public final void setModelView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModeHelper.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModeHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewModeHelper.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModeHelper.onDestroy();
    }
}
