package com.zwb.toucheventdemo2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zwb
 * Description
 * Date 16/7/29.
 */
public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context){
        super(context);
    }
    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d("info","MyLinearLayout-------dispatchKeyEvent===");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("info","MyLinearLayout-------onInterceptTouchEvent==="+false);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("info","MyLinearLayout-------onTouchEvent==ACTION_DOWN="+true);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("info","MyLinearLayout-------onTouchEvent==ACTION_MOVE="+true);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("info","MyLinearLayout-------onTouchEvent==ACTION_UP="+true);
                break;
        }
        return false;
    }
}

