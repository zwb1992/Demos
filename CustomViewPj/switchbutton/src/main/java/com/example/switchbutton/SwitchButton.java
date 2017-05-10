package com.example.switchbutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zwb
 * Description
 * Date 2017/5/8.
 */

public class SwitchButton extends View {

    private Bitmap bitmapFrame, bitmapBottom, bitmapThumb, bitmapMask;
    private Paint mPaint;

    private Rect mDest = null;//绘制的目标区域大小
    private Rect mSrc = null;//截取源图片的大小

    private PorterDuffXfermode pdf;
    private int saveFlags;
    private boolean isChecked = true;
    private GestureDetector gestureDetector;
    private float offset = 0;
    private boolean isGES = false;
    private OnCheckedListener onCheckedListener;

    public void setOnCheckedListener(SwitchButton.OnCheckedListener onCheckedListener) {
        this.onCheckedListener = onCheckedListener;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        invalidate();
    }

    public SwitchButton(Context context) {
        super(context);
        init(context);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化图片
     *
     * @param context
     */
    private void init(Context context) {
        bitmapFrame = BitmapFactory.decodeResource(context.getResources(), R.mipmap.switch_frame);
        bitmapBottom = BitmapFactory.decodeResource(context.getResources(), R.mipmap.switch_bottom);
        bitmapThumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.switch_btn_normal);
        bitmapMask = BitmapFactory.decodeResource(context.getResources(), R.mipmap.switch_mask);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setAlpha(255);
        pdf = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN); //2张重叠 取上面一张重叠部分

        saveFlags = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG |
                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG | Canvas.CLIP_TO_LAYER_SAVE_FLAG;

        gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override//按下
            public boolean onDown(MotionEvent motionEvent) {
                Log.e("info", "=======onDown=========" + motionEvent.getX());
                return false;
            }

            @Override//如果是按下的时间超过瞬间，而且在按下的时候没有松开或者是拖动的
            public void onShowPress(MotionEvent motionEvent) {
//                Log.e("info","=======onShowPress========="+motionEvent.getX());
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {//一次单独的轻击抬起操作
//                Log.e("info","=======onSingleTapUp========="+motionEvent.getX());
                isChecked = !isChecked();
                invalidate();
                return false;
            }

            @Override//在屏幕上拖动事件。无论是用手拖动view，或者是以抛的动作滚动
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
//                isGES = true;
//                offset += distanceX;
//                Log.e("info", "=======onScroll====distanceX=====" + distanceX);
////                Log.e("info","=======onScroll====distanceY====="+distanceY);
//                Log.e("info", "=======onScroll====offset=====" + offset);
//                invalidate();
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {//长按
//                Log.e("info","=======onLongPress=========");
            }

            @Override//滑屏，用户按下触摸屏、
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
//                Log.e("info","=======onFling====distanceX====="+distanceX);
//                Log.e("info","=======onFling====distanceY====="+distanceY);
                return false;
            }

        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(bitmapFrame.getWidth(), bitmapFrame.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isChecked()) {//如果是选择状态
            if (offset > 0) {
                offset = 0;
            }
            if (offset < bitmapFrame.getWidth() - bitmapThumb.getWidth()) {
                offset = bitmapFrame.getWidth() - bitmapThumb.getWidth();
            }
        }
        if (!isChecked()) {
            if (offset < 0) {
                offset = 0;
            }
            if (offset > bitmapThumb.getWidth() - bitmapFrame.getWidth()) {
                offset = bitmapThumb.getWidth() - bitmapFrame.getWidth();
            }
        }
        //开启一个新的图层，防止使用setXfermode 时受到原图层背景颜色的影响
        canvas.saveLayer(0, 0, bitmapFrame.getWidth(), bitmapFrame.getHeight(), null, saveFlags);
        canvas.drawBitmap(bitmapMask, 0, 0, mPaint);
        mPaint.setXfermode(pdf);
        if (isChecked()) {
            canvas.drawBitmap(bitmapBottom, bitmapFrame.getWidth() - bitmapBottom.getWidth() - offset, 0, mPaint);
        } else {
            canvas.drawBitmap(bitmapBottom, 0 - offset, 0, mPaint);
        }
        canvas.restore();
        mPaint.setXfermode(null);
        canvas.drawBitmap(bitmapFrame, 0, 0, mPaint);
        if (isChecked()) {
            canvas.drawBitmap(bitmapThumb, bitmapFrame.getWidth() - bitmapThumb.getWidth() - offset, 0, mPaint);
        } else {
            canvas.drawBitmap(bitmapThumb, 0 - offset, 0, mPaint);
        }
    }

    float x = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!isGES) {
                    Log.e("info", "========ACTION_MOVE===========" + event.getX());
                    offset = x - event.getX();
                    Log.e("info", "========ACTION_MOVE===========" + offset);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e("info", "========ACTION_UP===========");
                if (isChecked()) {//如果是选择状态
                    if (offset < (bitmapFrame.getWidth() - bitmapThumb.getWidth())/2) {
                        isChecked = !isChecked();
                    }
                }
                if (!isChecked()) {
                    if (offset > (bitmapThumb.getWidth() - bitmapFrame.getWidth())/2) {
                        isChecked = !isChecked();
                    }
                }
                isGES = false;
                offset = 0;
                invalidate();
                if(onCheckedListener != null){
                    onCheckedListener.onChecked(isChecked);
                }
                break;
        }
        return true;
    }
    interface OnCheckedListener{
        void onChecked(boolean isChecked);
    }
}
