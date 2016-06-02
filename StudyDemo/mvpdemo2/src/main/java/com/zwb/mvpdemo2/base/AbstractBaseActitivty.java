package com.zwb.mvpdemo2.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/2
 ***************************************/
public abstract class AbstractBaseActitivty<View extends IView, Presenter extends AbstractBasePresenter<View>> extends AppCompatActivity implements IView {
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(tellMeLayout());
        if(useButterknife()){
            ButterKnife.bind(this);
        }
        try {
            presenter = (Presenter) getPresenterClass().newInstance();
            presenter.onCreate(savedInstanceState);
            presenter.bindView((View) this);
            initView(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract int tellMeLayout();
    public abstract boolean useButterknife();

    public final void setPresenter(@NonNull Presenter presenter) {
        this.presenter = presenter;
    }
    public final Presenter getPresenter(){
        return presenter;
    }

    public abstract Class<Presenter> getPresenterClass();

    public abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onRestart() {
        super.onRestart();
        if (presenter != null) {
            presenter.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
