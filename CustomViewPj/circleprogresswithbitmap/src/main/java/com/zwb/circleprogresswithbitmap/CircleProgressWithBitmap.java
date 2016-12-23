package com.zwb.circleprogresswithbitmap;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

/**
 * Created by zwb
 * Description
 * Date 16/10/25.
 */
public class CircleProgressWithBitmap extends View {
    private Paint mPaint;
    private int centre;//圆心的x坐标
    private float radius;//半径
    private float c_width = 20;//环的宽度
    private float progress = 0;
    private int shadowColor;
    private int shoadowEndColor;
    private float den;
    private Bitmap bitmap;
    private ValueAnimator valueAnimator;
    private boolean isStart = false;
    public CircleProgressWithBitmap(Context context){
        super(context);
        init(context);
    }
    public CircleProgressWithBitmap(Context context, AttributeSet set){
        super(context,set);
        init(context);
    }
    public CircleProgressWithBitmap(Context context, AttributeSet set, int defStyle){
        super(context,set,defStyle);
        init(context);
    }
    private void init(Context context){
        den = context.getResources().getDisplayMetrics().density;
        mPaint = new Paint();
        mPaint.setAntiAlias(true); // 消除锯齿
        shadowColor = context.getResources().getColor(R.color.health_red);
        shoadowEndColor = context.getResources().getColor(R.color.health_orange);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPaint.setStrokeCap(Paint.Cap.ROUND); // 定义线段断头形状为圆弧形
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        centre = getWidth() / 2; // 获取圆心的x坐标
        radius = centre - 10.0f*den;// 半径

        drawCicle(canvas);
        drawArc(canvas);

        if(bitmap != null){
            if(isStart) {
                float angle = (float) (3.6 * progress);
                drawBitmap(canvas, angle);
            }else {
                drawBitmap(canvas, 0);
            }
        }
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    private void drawCicle(Canvas canvas){
        mPaint.setColor(getResources().getColor(R.color.health_grey));
        mPaint.setStrokeWidth(20*den);
        mPaint.setShader(null);
        canvas.drawCircle(centre,centre,radius,mPaint);
    }

    private void drawArc(Canvas canvas){
//        mPaint.setColor(getResources().getColor(R.color.health_red));
        mPaint.setShader(new SweepGradient(getMeasuredWidth() / 2, getMeasuredHeight() / 2, new int[]{shoadowEndColor,shadowColor}, null));
        mPaint.setStrokeWidth(20*den);
        RectF oval = new RectF(centre - radius,centre - radius,centre+radius,centre+radius);
        float angle = (float) (3.6 * progress);
        canvas.save();
        canvas.rotate(-90,getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        canvas.drawArc(oval,0,angle,false,mPaint);
        canvas.restore();
    }

    /**
     * 根据角度计算x，y坐标
     * @param canvas
     * @param cirAngle
     */
    private void drawBitmap(Canvas canvas,float cirAngle){
        cirAngle -= 90;
        Log.e("bitmap","===cirAngle===="+cirAngle);
        //将角度转换为弧度
        float arcAngle = (float) (Math.PI * (cirAngle) / 180.0);
        float cirX  =getWidth()/2.0f;
        float cirY  =getHeight()/2.0f;
        float posX = 0.0f;
        float posY = 0.0f;
        if (cirAngle < 90)
        {
            posX = cirX + (float)(Math.cos(arcAngle)) * radius;
            posY = cirY + (float)(Math.sin(arcAngle)) * radius;
        }
        else if (cirAngle == 90)
        {
            posX = cirX;
            posY = cirY + radius;
        }
        else if (cirAngle > 90 && cirAngle < 180)
        {
            arcAngle = (float) (Math.PI * (180 - cirAngle) / 180.0);
            posX = cirX - (float)(Math.cos(arcAngle)) * radius;
            posY = cirY + (float)(Math.sin(arcAngle)) * radius;
        }
        else if (cirAngle == 180)
        {
            posX = cirX - radius;
            posY = cirY;
        }
        else if (cirAngle > 180 && cirAngle < 270)
        {
            arcAngle = (float) (Math.PI * (cirAngle - 180) / 180.0);
            posX = cirX - (float)(Math.cos(arcAngle)) * radius;
            posY = cirY - (float)(Math.sin(arcAngle)) * radius;
        }
        else if (cirAngle == 270)
        {
            posX = cirX;
            posY = cirY - radius;
        }
        else
        {
            arcAngle = (float) (Math.PI * (360 - cirAngle) / 180.0);
            posX = cirX + (float)(Math.cos(arcAngle)) * radius;
            posY = cirY - (float)(Math.sin(arcAngle)) * radius;
        }

//        Log.e("bitmap","===x===="+posX);
//        Log.e("bitmap","===y===="+posY);
//        Log.e("bitmap","===radius===="+radius);
//        Log.e("bitmap","===getWidth()/2===="+getWidth()/2);
//        Log.e("bitmap","===getHeight()/2===="+getHeight()/2);
//        Log.e("bitmap","===bitmap.getHeight()/2===="+bitmap.getHeight()/2);
//        Log.e("bitmap","===bitmap.getHeight()/2===="+bitmap.getHeight()/2);
        bitmap = zoomBitmapDeng(bitmap,(int)(c_width * den),(int)(c_width * den));
        canvas.drawBitmap(bitmap,posX-bitmap.getWidth()/2,posY-bitmap.getHeight()/2,mPaint);

    }

    /**
     * 开始动画
     */
    public void start(){
        isStart = true;
        progress = 100;
        valueAnimator = ValueAnimator.ofFloat(0,progress);
        valueAnimator.setDuration(1500);//设置动画一次执行的时间
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircleProgressWithBitmap.this.progress = (float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        //无限循环
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.start();
    }

    /**
     * 结束动画
     */
    public void stop(){
        if(valueAnimator != null){
            valueAnimator.end();
        }
        isStart = false;
        progress = 0;
        invalidate();
    }

    /**
     * 设置显示的图片
     * @param bitmap
     */
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
        invalidate();
    }

    /**
     * 等比图片缩放
     *
     * @param bitmap 对象
     * @param w      要缩放的宽度
     * @param h      要缩放的高度
     * @return newBmp 新 Bitmap对象
     */
    public static Bitmap zoomBitmapDeng(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        float scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
        matrix.postScale(scale, scale);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        bitmap.recycle();
        return newBmp;
    }
}
