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

}
