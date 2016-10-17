package com.zwb.histogramviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
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
     * x轴上文字的大小
     */
    private int xTextSize;
    /**
     * y轴上文字的大小
     */
    private int yTextSize;
    /**
     * x轴颜色
     */
    private int xColor;
    /**
     * y轴颜色
     */
    private int yColor;
    /**
     * 文字颜色
     */
    private int textColor;
    /**
     * 分割线的高度
     */
    private int divider_height;
    /**
     * y轴柱状宽度
     */
    private int yWidth;

    private Paint mPaint;
    private String yStr[] = {"10000","50000","100000"};//竖直方向显示的文字
    private String xStr[] = {"6:00","12:00","18:00"};//横向显示的文字
    private int w ;
    private int h;
    private float averageH;
    private float averageW;
    private Rect rect;
    /**
     * 左边文字的宽度
     */
    private int textW;
    /**
     * 左边文字的宽度
     */
    private int textH;

    private List<Integer> list = new ArrayList<>();//柱状图的高度

    private int where = 1;//柱状图画在那个位置 1：6：00位置，2：12：00位置，3：18：00位置
    public HistogramView(Context context){
        super(context);
        init(context,null,0);
    }

    public HistogramView(Context context, AttributeSet set){
        super(context,set);
        init(context,set,0);
    }
    public HistogramView(Context context, AttributeSet set,int defStyle){
        super(context,set,defStyle);
        init(context,set,defStyle);
    }

    private void init(Context context, AttributeSet set,int defStyle){
        rect = new Rect();
        TypedArray array = context.getTheme().obtainStyledAttributes(set, R.styleable.HistogramView,defStyle,0);
        int n = array.getIndexCount();
        for (int i = 0;i<n;i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.HistogramView_xColor:
                    xColor = array.getColor(attr,Color.GRAY);
                    break;
                case R.styleable.HistogramView_yColor:
                    yColor = array.getColor(attr,Color.BLUE);
                    break;
                case R.styleable.HistogramView_textColor:
                    textColor = array.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.HistogramView_divider_height:
                    divider_height = array.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,2
                            ,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.HistogramView_yWidth:
                    yWidth = array.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,2
                            ,getResources().getDisplayMetrics()));
                    break;

                case R.styleable.HistogramView_xTextSize:
                    xTextSize = array.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,2
                            ,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.HistogramView_yTextSize:
                    yTextSize = array.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,2
                            ,getResources().getDisplayMetrics()));
                    break;
            }
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        w = getWidth();
        h = getHeight();
        mPaint.setTextSize(xTextSize);
        mPaint.getTextBounds(xStr[0], 0, xStr[0].length(), rect);
        averageH = (h - rect.height()-5*divider_height)/4.0f;
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(20);
        mPaint.getTextBounds(yStr[0], 0, yStr[0].length(), rect);

        drawText(canvas);
        drawLine(canvas);
        drawHostogram(canvas);
    }

    /**
     * 画横线
     */
    private void drawLine(Canvas canvas){
        mPaint.setColor(xColor);
        mPaint.setStrokeWidth(divider_height);
        canvas.drawLine(textW+15,averageH-textH/2,w,averageH-textH/2,mPaint);
        canvas.drawLine(textW+15,averageH*2-textH/2,w,averageH*2-textH/2,mPaint);
        canvas.drawLine(textW+15,averageH*3-textH/2,w,averageH*3-textH/2,mPaint);
        canvas.drawLine(textW+15,averageH*4-textH/2,w,averageH*4-textH/2,mPaint);

       /* Path path = new Path();
        path.moveTo(0,0);
        mPaint.setStyle(Paint.Style.STROKE);
        for (int i = 1;i<16;i++) {
            path.lineTo(60*i, (float)Math.random()*460);
        }
        path.lineTo(w,0);
        path.lineTo(w,0);
        mPaint.setPathEffect(new CornerPathEffect(10));
        canvas.drawPath(path,mPaint);*/
    }

    /**
     * 画文字
     */
    private void drawText(Canvas canvas){
        mPaint.setColor(textColor);
        mPaint.setTextSize(yTextSize);
        Rect rect = new Rect();
//        list.add(5000);
//        list.add(10000);
//        list.add(25000);
//        list.add(50000);
//        list.add(80000);
//        list.add(100000);
        /**
         * y轴上的文字
         */
        mPaint.getTextBounds(yStr[2], 0, yStr[2].length(), rect);
        textW = rect.width();
        textH = rect.height();
        averageW = (w - textW)/3.0f;
        canvas.drawText(yStr[2],0,averageH,mPaint);

        mPaint.getTextBounds(yStr[1], 0, yStr[1].length(), rect);
        int tempW = rect.width();
        canvas.drawText(yStr[1],textW - tempW,averageH*2,mPaint);

        mPaint.getTextBounds(yStr[0], 0, yStr[0].length(), rect);
        tempW = rect.width();
        canvas.drawText(yStr[0],textW - tempW,averageH*3,mPaint);

        /**
         * x轴上的文字
         */
        mPaint.setTextSize(xTextSize);
        mPaint.getTextBounds(xStr[0], 0, xStr[0].length(), rect);
        tempW = rect.width();
        canvas.drawText(xStr[0],averageW - tempW,getHeight()-rect.height()/2,mPaint);

        mPaint.getTextBounds(xStr[1], 0, xStr[1].length(), rect);
        tempW = rect.width();
        canvas.drawText(xStr[1],averageW*2 - tempW,getHeight()-rect.height()/2,mPaint);

        mPaint.getTextBounds(xStr[2], 0, xStr[2].length(), rect);
        tempW = rect.width();
        canvas.drawText(xStr[2],averageW*3 - tempW,getHeight()-rect.height()/2,mPaint);
    }

    /**
     * 画柱状图的位置
     * @param where //柱状图画在那个位置 1：6：00位置，2：12：00位置，3：18：00位置
     * @param numbers 数值
     */
    public void setData(int where,List<Integer> numbers){
        list.clear();
        list.addAll(numbers);
        this.where = where;
        if(list.size() == 0){
            return;
        }
        invalidate();//刷新界面
    }

    //画竖线
    private void drawHostogram(Canvas canvas){
        mPaint.setColor(yColor);
        mPaint.setStrokeWidth(yWidth);
        int num = list.size();
        float totalW = yWidth * num+(num -1)*5;
        switch (where){
            case 1:
                drawHostogram(canvas,averageW - totalW);
                break;
            case 2:
                drawHostogram(canvas,averageW*2 - totalW);
                break;
            case 3:
                drawHostogram(canvas,averageW*3 - totalW);
                break;
        }
    }
    //画竖线
    private void drawHostogram(Canvas canvas,float begin){
        for (int i = 0;i<list.size();i++){
            int num = list.get(i);
            float gaodu = 0;
            if(num<=10000){
                gaodu = averageH/10000.0f*num;
            }else if(num<=50000){
                gaodu = averageH+averageH/40000.0f*(num-10000);
            }else if(num<= 100000){
                gaodu = averageH*2 + averageH/50000.0f*(num-50000);
            }else {
                gaodu = averageH*3 + averageH/100000.0f*(num-100000);
                if(gaodu>=averageH*4){
                    gaodu = averageH*4;
                }
            }
            canvas.drawLine(begin+(yWidth+5)*i+yWidth,averageH*4-textH/2,begin+(yWidth+5)*i+yWidth,averageH*4-textH/2-gaodu,mPaint);
        }
    }
}
