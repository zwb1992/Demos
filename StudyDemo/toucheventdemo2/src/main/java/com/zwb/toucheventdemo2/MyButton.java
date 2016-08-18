package com.zwb.toucheventdemo2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zwb
 * Description
 * Date 16/7/29.
 */
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d("info","MyTextView----dispatchKeyEvent===="+super.dispatchKeyEvent(event));
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("info","MyTextView----onTouchEvent======ACTION_DOWN=");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("info","MyTextView----onTouchEvent======ACTION_MOVE=");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("info","MyTextView----onTouchEvent======ACTION_UP=");
                        break;
                }
        return false;
    }
}
