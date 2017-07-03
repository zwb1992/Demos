package com.example.strokecircle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by zwb
 * Description
 * Date 2017/5/2.
 */

public class StrokeCircle extends View {

    private float width;
    private int bgColor;
    private int shadowColor;
    private Paint mPaint;
    private Paint mShadowPaint;
    private float strokeWidth;

    public StrokeCircle(Context context) {
        super(context);
        initAttrs(context, null, 0);
    }

    public StrokeCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs, 0);
    }

    public StrokeCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        Log.e("info", "-----sizeWidth-------" + sizeWidth);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.e("info", "-----sizeHeight-------" + sizeHeight);
        int size = Math.min(sizeWidth, sizeHeight);
        Log.e("info", "-----size-------" + size);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        canvas.drawCircle(width / 2.0f, width / 2.0f, width / 2.0f - strokeWidth / 2, mShadowPaint);
        canvas.drawCircle(width / 2.0f, width / 2.0f, width / 2.0f - strokeWidth, mPaint);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StrokeCircle, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.StrokeCircle_sc_bg_color:
                    bgColor = array.getColor(attr, Color.GRAY);
                    break;
                case R.styleable.StrokeCircle_sc_shadow_color:
                    shadowColor = array.getColor(attr, Color.GRAY);
                    break;
                case R.styleable.StrokeCircle_sc_stroke_width:
                    strokeWidth = array.getDimension(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    break;
            }
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(bgColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
        PathEffect effects = new DashPathEffect(new float[]{20, 20, 20, 20}, 0);
        mPaint.setPathEffect(effects);

        mShadowPaint = new Paint();
        mShadowPaint.setAntiAlias(true);
        mShadowPaint.setColor(shadowColor);
        mShadowPaint.setStyle(Paint.Style.FILL);
        mShadowPaint.setStrokeWidth(strokeWidth);
        array.recycle();
    }
}
