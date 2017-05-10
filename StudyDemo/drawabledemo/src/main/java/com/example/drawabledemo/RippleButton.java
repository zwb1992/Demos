package com.example.drawabledemo;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by zwb
 * Description
 * Date 2017/5/10.
 */

public class RippleButton extends Button {
    private RippleDrawable rippleDrawable;

    public RippleButton(Context context) {
        this(context, null);
    }

    public RippleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rippleDrawable = new RippleDrawable();
        rippleDrawable.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.round1));
        //设置drawable刷新的接口
        rippleDrawable.setCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制他自己--这个是最后绘制上去的，可能会导致把文字给覆盖了，所以要放在super.onDraw(canvas)之前调用
        rippleDrawable.draw(canvas);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //设置绘制drawable的区域
        rippleDrawable.setBounds(0, 0, getWidth(), getHeight());
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        //返回验证是否ok
        return who == rippleDrawable || super.verifyDrawable(who);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        rippleDrawable.onTouchEvent(event);
//        invalidate();
        return true;
    }
}
