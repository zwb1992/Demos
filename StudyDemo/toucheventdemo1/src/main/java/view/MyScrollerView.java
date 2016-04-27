package view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by zwb
 * Description
 * Date 16/4/27.
 */
public class MyScrollerView extends ScrollView {
    public MyScrollerView(Context context){
        super(context);
    }
    public MyScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MyScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);

    }

    //事件分发
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

}
