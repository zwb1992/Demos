package com.zwb.thirdpartydemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zwb
 * Description
 * Date 17/4/19.
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("info", "--BaseFragment--onCreate--savedInstanceState-----" + savedInstanceState);
        mContext = getActivity();
        Log.e("info", "--BaseFragment--onCreate--mContext-----" + mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("info", "--BaseFragment--onCreateView--savedInstanceState-----" + savedInstanceState);
        return initView();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("info", "--BaseFragment--onActivityCreated--savedInstanceState-----" + savedInstanceState);
        initData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("info", "--BaseFragment--onViewCreated--savedInstanceState-----" + savedInstanceState);
    }

    protected abstract View initView();

    protected void initData() {

    }

}
