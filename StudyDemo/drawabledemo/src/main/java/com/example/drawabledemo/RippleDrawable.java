package com.example.drawabledemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by zwb
 * Description
 * Date 2017/5/10.
 */

public class RippleDrawable extends Drawable {

    private int mAlpha = 255;
    private Paint mPaint;
    private int color;
    private float mDownX, mDownY;
    private float mCenterX, mCenterY;//中心点的位置
    private float mPonitX, mPonitY;//移动的时候圆点的位置
    private float radius = 0;
    private float progress = 0;
    private float mStartRadius, mEndRadius;

    public void setColor(int color) {
        this.color = color;
        setColorAndAlpha();
    }

    public RippleDrawable() {
        mPaint = new Paint();
        //开启抗锯齿，在画圆的时候，使边缘更加圆滑
        mPaint.setAntiAlias(true);
        //开启防抖动，在绘制渐变色的时候，使颜色过度更加自然
        mPaint.setDither(true);
        mPaint.setColor(Color.BLUE);
    }

    /**
     * 设置颜色以及真正的透明度
     */
    private void setColorAndAlpha() {
        mPaint.setColor(color);
        int alpha = mPaint.getAlpha();
//        alpha  = Color.alpha(color);
        if (alpha != 255) {//如果颜色中带有透明度，则重新计算透明度
            int realAlpha = (int) (alpha * mAlpha / 255f);
            mPaint.setAlpha(realAlpha);
            invalidateSelf();//刷新自己
        }
    }

    private Bitmap bitmap;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void draw(Canvas canvas) {
        //绘制一个圆
        canvas.saveLayer(mCenterX - radius, mCenterY - radius, mCenterX + radius, mCenterY + radius, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(mPonitX, mPonitY, radius, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.restore();
        mPaint.setXfermode(null);
    }

    public void onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                onDown(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                onMove(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                onUp(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_CANCEL:
                onCancel(event.getX(), event.getY());
                break;
        }
    }

    private Interpolator interpolator = new DecelerateInterpolator();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            progress += 16 / 3600f;
            if (progress >= 1) {
                return;
            }
            float realProgress = interpolator.getInterpolation(progress);
            onEnterProgress(realProgress);
            scheduleSelf(this, SystemClock.uptimeMillis() + 16);
        }
    };

    private void onEnterProgress(float progress) {
        radius = cucalatePoint(mStartRadius, mEndRadius, progress);
        mPonitX = cucalatePoint(mDownX, mCenterX, progress);
        mPonitY = cucalatePoint(mDownY, mCenterY, progress);
        invalidateSelf();
    }

    private float cucalatePoint(float start, float end, float progress) {
        float result = start + (end - start) * progress;
        return result;
    }


    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mCenterX = bounds.centerX();
        mCenterY = bounds.centerY();
        Log.e("info", "===mCenterX===" + mCenterX + "====mCenterY=====" + mCenterY);
        float mMaxRdius = Math.max(mCenterX, mCenterY);
        mStartRadius = mMaxRdius * 0;
        mEndRadius = mMaxRdius * 0.8f;
    }

    private void onDown(float x, float y) {
        mDownX = x;
        mDownY = y;
        start();
    }

    private void start() {
        radius = 0;
        progress = 0;
        unscheduleSelf(runnable);
        scheduleSelf(runnable, SystemClock.uptimeMillis() + 16);
    }


    private void onMove(float x, float y) {

    }

    private void onUp(float x, float y) {
        radius = 0;
        progress = 0;
        unscheduleSelf(runnable);
        invalidateSelf();
    }

    private void onCancel(float x, float y) {
        radius = 0;
        progress = 0;
        unscheduleSelf(runnable);
        invalidateSelf();
    }

    @Override
    public void setAlpha(int i) {
        //设置透明度
        mAlpha = i;
    }

    @Override//主要是绘制图片的时间设置滤镜，会保留一种颜色，然后把其他的颜色替换掉
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        if (mAlpha == 255) {
            return PixelFormat.OPAQUE;//完全不透明的
        } else if (mAlpha == 0) {
            return PixelFormat.TRANSPARENT;//完全透明的
        } else {
            return PixelFormat.TRANSLUCENT;//半透明的
        }
    }
}
