package com.zwb.thirdpartydemo.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zwb.thirdpartydemo.base.BaseFragment;

/**
 * Created by zwb
 * Description 其他
 * Date 17/4/19.
 */

public class OtherFragment extends BaseFragment {

    private static final String TAG = OtherFragment.class.getName();

    private TextView textView;
    @Override
    protected View initView() {
        Log.e(TAG,"==initView==视图被初始化======");
        textView = new TextView(mContext);
        textView.setPadding(10,10,10,10);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        Log.e(TAG,"==initData==数据被初始化======");
        textView.setText("其他");
        super.initData();
    }
}
