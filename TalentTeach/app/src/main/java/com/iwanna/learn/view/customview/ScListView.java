package com.iwanna.learn.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Author: zwb
 * Description:ScrollView中嵌套的ListView，重写测量方法
 * Date: 2016/5/3
 */
public class ScListView extends ListView{
    public ScListView(Context context) {
        super(context);
    }

    public ScListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
