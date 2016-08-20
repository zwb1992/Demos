package com.zwb.circleprogressdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by zwb
 * Description 带刻度的进度控件
 * Date 16/8/18.
 */
public class CircleProgressBar extends View{

    /**
     * 背景颜色
     */
    private int bgColor;
    /**
     * 第一圈的颜色
     */
    private int firstColor;
    /**
     * 进度的颜色
     */
    private int secondColor;

    /**
     * 圆环的宽度
     */
    private int mCircleWidth;
    /**
     * 刻度的宽度
     */
    private int keduWidth;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 个数
     */
    private int mCount;

    /**
     * 当前刻度值值
     */
    private int currentKd;

    private Rect mRect;
    private int centre;//圆心的x坐标
    private int radius;//半径
    private float itemSize;
    private static final float TOTAL_ANGLE = 300.0F;
    public CircleProgressBar(Context context){
        super(context);
        init(context,null,0);
    }
    public CircleProgressBar(Context context, AttributeSet set){
        super(context,set);
        init(context,set,0);
    }
    public CircleProgressBar(Context context, AttributeSet set,int defStyle){
        super(context,set,defStyle);
        init(context,set,defStyle);
    }

    private void init(Context context, AttributeSet set,int defStyle){
        TypedArray a = context.getTheme().obtainStyledAttributes(set,R.styleable.CircleProgressBar,defStyle,0);
        int n = a.getIndexCount();

        for (int i = 0;i<n;i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.CircleProgressBar_firstColor:
                    firstColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.CircleProgressBar_bgColor:
                    bgColor = a.getColor(attr,Color.GRAY);
                    break;
                case R.styleable.CircleProgressBar_secondColor:
                    secondColor = a.getColor(attr,Color.BLUE);
                    break;
                case R.styleable.CircleProgressBar_mCircleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,20
                    ,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CircleProgressBar_mCount:
                    mCount = a.getInt(attr,50);
                    break;
                case R.styleable.CircleProgressBar_keduWidth:
                    keduWidth = a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,2
                            ,getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStrokeCap(Paint.Cap.BUTT); // 定义线段断头形状为圆弧形
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        centre = getWidth() / 2; // 获取圆心的x坐标
        radius = centre - mCircleWidth / 2;// 半径
        //每个刻度旋转的角度
        itemSize = TOTAL_ANGLE/mCount;
        drawCircle(canvas,mPaint);
        drawPicture(canvas);
        drawKeDu(canvas,mPaint);
        currentKd = 100;
        drawCurrentKeDu(canvas,mPaint);
    }

    /**
     * 画圆弧 总共300°
     * @param canvas
     * @param paint
     */
    private void drawCircle(Canvas canvas,Paint paint){
        paint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setColor(bgColor);

        RectF oval = new RectF(centre - radius,centre - radius,centre+radius,centre+radius);
        canvas.drawArc(oval,120,TOTAL_ANGLE,false,paint);
    }

    private void drawPicture(Canvas canvas){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap
        ,centre-bitmap.getWidth()/2,mCircleWidth,mPaint);
    }
    /**
     * 画白色的刻度
     * @param canvas
     * @param paint
     */
    private void drawKeDu(Canvas canvas,Paint paint){
        paint.setStrokeWidth(keduWidth);
        paint.setColor(firstColor);
        canvas.save();
        canvas.rotate(210,centre,centre);
        canvas.rotate(itemSize,centre,centre);
        for (int i = 0;i<mCount-1;i++){
            canvas.drawLine(centre,0,centre,mCircleWidth,paint);
            canvas.rotate(itemSize,centre,centre);
        }
        canvas.restore();
    }

    /**
     * 画当前刻度
     * @param canvas
     * @param paint
     */
    private void drawCurrentKeDu(Canvas canvas,Paint paint){
        paint.setStrokeWidth(keduWidth);
        paint.setColor(secondColor);

        int count = (mCount-1)*currentKd/100;//当前需要画几格
        canvas.save();
        canvas.rotate(210,centre,centre);
        canvas.rotate((mCount-count)*itemSize,centre,centre);
        for (int i = 0;i<count;i++){
            canvas.drawLine(centre,0,centre,mCircleWidth,paint);
            canvas.rotate(itemSize,centre,centre);
        }
        canvas.restore();
    }

    /**
     * 设置当前进度
     * @param ke
     */
    public void setCurrentKd(int ke){
        currentKd = ke;
        invalidate();
    }
}
