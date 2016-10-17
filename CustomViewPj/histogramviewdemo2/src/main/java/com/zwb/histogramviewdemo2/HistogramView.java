package com.zwb.histogramviewdemo2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 16/8/25.
 */
public class HistogramView extends View {

    /**
     * 文字的大小
     */
    private int textSize;
    /**
     * 文字颜色
     */
    private int textColor;
    /**
     * 分割线的宽度
     */
    private int space;
    /**
     * 柱状颜色
     */
    private int histogram_color;

    private Paint mPaint;
    private List<String> yTextList = new ArrayList<>();//竖直方向显示的文字
    private String xStr[] = {"0","6","12","18","24"};//横向显示的文字
    private int w ;//控件的宽度
    private int h;//控件的高度
    private float averageH;
    private float averageW;
    private Rect rect;
    /**
     * 左边文字的宽度
     */
    private float leftTextW;
    /**
     * 左边文字的高度
     */
    private float leftTextH;

    /**
     * 底部文字的宽度
     */
    private float bottomTextW;
    /**
     * 底部文字的高度
     */
    private float bottomTextH;
    private float bottomAverageW;
    /**
     * 最大值
     */
    private float maxNum = 100;
    /**
     * 柱子的宽度
     */
    private float histogramW = 0.0f;
    /**
     * 默认柱子的高低
     */
    private int default_height;
    /**
     * 虚线的高度
     */
    private float strokeWidth = 5;
    private float den = 0;
    private String leftMaxLengthText = "";

    private List<Integer> list = new ArrayList<>();//柱状图的高度

    public HistogramView(Context context){
        super(context);
        init(context,null,0);
    }

    public HistogramView(Context context, AttributeSet set){
        super(context,set);
        init(context,set,0);
    }
    public HistogramView(Context context, AttributeSet set, int defStyle){
        super(context,set,defStyle);
        init(context,set,defStyle);
    }

    private void init(Context context, AttributeSet set,int defStyle){
        den = context.getResources().getDisplayMetrics().density;
        rect = new Rect();
        TypedArray array = context.getTheme().obtainStyledAttributes(set, R.styleable.HistogramView,defStyle,0);
        int n = array.getIndexCount();
        for (int i = 0;i<n;i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.HistogramView_textColor:
                    textColor = array.getColor(attr,Color.GRAY);
                    break;
                case R.styleable.HistogramView_histogram_color:
                    histogram_color = array.getColor(attr,Color.RED);
                    break;
                case R.styleable.HistogramView_space:
                    space = array.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,2
                            ,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.HistogramView_default_height:
                    default_height = array.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,2
                            ,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.HistogramView_textSize:
                    textSize = array.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,2
                            ,getResources().getDisplayMetrics()));
                    break;
            }
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        initData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        w = getWidth();
        h = getHeight();

        mPaint.setColor(textColor);
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(textSize);
        mPaint.getTextBounds(leftMaxLengthText, 0, leftMaxLengthText.length(), rect);
        leftTextW = rect.width()+5*den;
        leftTextH = rect.height();

        /**
         * 算出除顶部文字外的平均高度
         */
        mPaint.setTextSize(textSize);
        mPaint.getTextBounds(xStr[3], 0, xStr[3].length(), rect);
        bottomTextW = rect.width();
        bottomTextH = rect.height()+8*den;
        bottomAverageW = (w -leftTextW- bottomTextW * 5)/4.0f;
        averageH = (h - bottomTextH - default_height-10)/2.0f;

        histogramW = (w-leftTextW-99*space)/100.0f;
        /**
         * 画虚线
         */
        drawDashLine(canvas,leftTextW,leftTextH/2);
        drawDashLine(canvas,leftTextW,leftTextH/2+averageH);

        /**
         * 画左侧的文字
         */
        drawLeftText(canvas);
        /**
         * 画底部文字
         */
        drawBottomText(canvas);
        /**
         * 话柱线
         */
        histogram(canvas);
    }

    /**
     * 画虚线
     * @param canvas
     */
    private void drawDashLine(Canvas canvas,float x,float y){
//        Log.i("info","===x==="+x+"===y====="+y);
//        Log.i("info","===x==="+getWidth()+"===y====="+y);
        Paint paint = new Paint();
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(x,y);
        path.lineTo(getWidth(),y);
        PathEffect pathEffect = new DashPathEffect(new float[]{15,15,15,15},1);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path,paint);
    }

    /**
     * 左侧的文字
     * @param canvas
     */
    private void drawLeftText(Canvas canvas){
        mPaint.setTextSize(textSize);
        mPaint.setColor(histogram_color);
        /**
         * y轴上的文字
         */
        for (int i = 0;i<yTextList.size();i++){
            String str = yTextList.get(i);
            if(!"0".equals(str)) {
                mPaint.getTextBounds(str, 0, str.length(), rect);
                canvas.drawText(str, (leftTextW - rect.width()) / 2.0f, leftTextH + i * averageH, mPaint);
            }
        }
    }

    /**
     * 底部的文字
     * @param canvas
     */
    private void drawBottomText(Canvas canvas){
        mPaint.setTextSize(textSize);
        mPaint.setColor(textColor);
        /**
         * x轴上的文字
         */
        for (int i = 0;i<xStr.length;i++){
            String str = xStr[i];
            mPaint.getTextBounds(str, 0, str.length(), rect);
            canvas.drawText(str,i*(bottomAverageW+bottomTextW)+leftTextW,h,mPaint);
        }
    }

    /**
     * 画柱状图的位置
     * @param numbers 数值
     */
    public void setData(List<Integer> numbers){
        list.clear();
        list.addAll(numbers);
        if(list.size() == 0){
            return;
        }
        invalidate();//刷新界面
    }

    //画竖线
    private void histogram(Canvas canvas){
        mPaint.setColor(histogram_color);
        mPaint.setStrokeWidth(histogramW);
        for (int i = 0;i<list.size();i++){
            Integer integer = list.get(i);
            float tempH = 2*integer/maxNum * averageH;
            if(tempH > 2*averageH){
                tempH = 2*averageH;
            }
            float begin = i*(histogramW+space);
            canvas.drawLine(begin+leftTextW+histogramW/2.0f,h-bottomTextH,begin+leftTextW+histogramW/2.0f,h-bottomTextH-tempH-default_height+5/2.0f,mPaint);
        }
    }

    /**
     * 设置左侧显示的文字
     * @param yTextList
     */
    public void setyTextList(List<String> yTextList) {
        this.yTextList = yTextList;
        invalidate();
    }

    /**
     * 设置顶部的最大值
     * @param maxNum
     */
    public void setMaxNum(float maxNum) {
        this.maxNum = maxNum;
        invalidate();
    }

    /**
     * 初始化数据
     */
    private void initData(){
        for (int i = 0;i<100;i++){
            list.add(0);
        }
        for (int i = 0;i<3;i++){
            yTextList.add((100-50*i)+"");
        }
    }

    /**
     * 设置左侧最大长度文字
     * @param str
     */
    public void setLeftMaxLengthText(String str){
        leftMaxLengthText = str;
        invalidate();
    }
}
