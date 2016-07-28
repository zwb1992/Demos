package com.zwb.mvpdemo2.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/2
 ***************************************/
public abstract class AbstractBasePresenter<T extends IView> implements IPresenter{
    private T mView;
    private static final String TAG = AbstractBasePresenter.class.getName();
    public void bindView(@NonNull T view) {
        mView = view;
        Log.d(TAG, "==bindView===");
    }

    public T getView() {
        Log.d(TAG, "==getView===");
        return mView;
    }

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "==onCreate===");
    }

    public void onRestart() {
        Log.d(TAG, "==onRestart===");
    }

    public void onResume() {
        Log.d(TAG, "==onResume===");
    }

    public void onStart() {
        Log.d(TAG, "==onStart===");
    }

    public void onStop() {
        Log.d(TAG, "==onStop===");
    }

    public void onPause() {
        Log.i(TAG,"==onPause===");
    }

    public void onDestroy() {
        Log.i(TAG,"==onDestroy===");
    }
}
