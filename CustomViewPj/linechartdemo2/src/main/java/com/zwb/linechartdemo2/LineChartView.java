package com.zwb.linechartdemo2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwb
 * Description 折线图
 * Date 16/8/25.
 */
public class LineChartView extends View {

    /**
     * 文字的大小
     */
    private int textSize;
    /**
     * 文字颜色
     */
    private int textColor;
    /**
     * 折线颜色
     */
    private int lineChart_color;
    /**
     * 阴影颜色
     */
    private int shadowColor;

    private Paint mPaint;
    private List<String> yTextList = new ArrayList<>();//竖直方向显示的文字
    private List<String> xTextList = new ArrayList<>();//横向显示的文字
    private int w ;//控件的宽度
    private int h;//控件的高度
    private float averageH;
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
     * 点的半径
     */
    private int point_redius = 8;
    /**
     * 虚线的高度
     */
    private float strokeWidth = 5;
    /**
     * 折线的宽度
     */
    private float lineChartWidth = 3;
    private float den = 0;
    private String leftMaxLengthText = "";
    private String bottomLengthText = "";
    /**
     * 底部最左侧的文字
     */
    private String bottomLeftText = "";

    private List<Float> list = new ArrayList<>();//折线图的高度

    public LineChartView(Context context){
        super(context);
        init(context,null,0);
    }

    public LineChartView(Context context, AttributeSet set){
        super(context,set);
        init(context,set,0);
    }
    public LineChartView(Context context, AttributeSet set, int defStyle){
        super(context,set,defStyle);
        init(context,set,defStyle);
    }

    private void init(Context context, AttributeSet set,int defStyle){
        den = context.getResources().getDisplayMetrics().density;
        rect = new Rect();
        TypedArray array = context.getTheme().obtainStyledAttributes(set, R.styleable.LineChartView,defStyle,0);
        int n = array.getIndexCount();
        for (int i = 0;i<n;i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.LineChartView_textColor:
                    textColor = array.getColor(attr,Color.GRAY);
                    break;
                case R.styleable.LineChartView_shadow_color:
                    shadowColor = array.getColor(attr,Color.RED);
                    break;
                case R.styleable.LineChartView_lineChartView_color:
                    lineChart_color = array.getColor(attr,Color.RED);
                    break;
                case R.styleable.LineChartView_textSize:
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
        mPaint.getTextBounds(bottomLengthText, 0, bottomLengthText.length(), rect);
        bottomTextW = rect.width();
        bottomTextH = rect.height()+8*den;
        if(!xTextList.isEmpty()){
            float ave = xTextList.size();
            bottomAverageW = (w -leftTextW- bottomTextW * (ave -1))/ave;
        }
        averageH = (h - bottomTextH -10)/2.0f;


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
         * 画折线
         */
        drawLineChart(canvas);
    }

    /**
     * 画虚线
     * @param canvas
     */
    private void drawDashLine(Canvas canvas,float x,float y){
        Log.i("info","===x==="+x+"===y====="+y);
        Log.i("info","===x==="+getWidth()+"===y====="+y);
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
        mPaint.setColor(lineChart_color);
        mPaint.setShader(null);
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
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(null);
        /**
         * x轴上的文字
         */
        for (int i = 0;i<xTextList.size();i++){
            String str = xTextList.get(i);
            canvas.drawText(str,i*(bottomAverageW+bottomTextW)+leftTextW,h,mPaint);
        }
        if(!TextUtils.isEmpty(bottomLeftText)){
            mPaint.getTextBounds(bottomLeftText, 0, bottomLeftText.length(), rect);
            canvas.drawText(bottomLeftText,(leftTextW-rect.width())/2.0f,h,mPaint);
        }
    }

    //画折线
    private void drawLineChart(Canvas canvas){
        mPaint.setColor(lineChart_color);
        mPaint.setStrokeWidth(lineChartWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        Path showdowPath = new Path();
        float begin = 0.0f;
        for (int i = 0;i<list.size();i++){
            Float integer = list.get(i);
            float tempH = integer * 2*averageH;
            if(tempH > 2*averageH){
                tempH = 2*averageH;
            }
            begin = i*(bottomAverageW+bottomTextW)+leftTextW+(bottomTextW-point_redius);
            float height = h-bottomTextH-tempH+point_redius/2.0f;
            drawPoint(canvas,begin,height);
            if(i == 0){
                path.moveTo(begin,height);
                showdowPath.moveTo(begin,h-bottomTextH+point_redius/2.0f);
            }else {
                path.lineTo(begin,height);
            }
            showdowPath.lineTo(begin,height);
        }
        /**
         * 画折线
         */
        canvas.drawPath(path,mPaint);

        /**
         * 画阴影
         */
        showdowPath.lineTo(begin,h-bottomTextH+point_redius/2.0f);
        mPaint.setColor(shadowColor);
        mPaint.setStyle(Paint.Style.FILL);
        Shader shader = new LinearGradient(0,0,0,2*averageH,new int[]{shadowColor,Color.WHITE},null, Shader.TileMode.MIRROR);
        mPaint.setShader(shader);
        canvas.drawPath(showdowPath,mPaint);
    }
    private void drawPoint(Canvas canvas,float x,float y){
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.check_blue));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x,y,point_redius,paint);
    }
    /**
     * 设置左侧显示的文字
     * @param yTextList
     */
    public void setYTextList(List<String> yTextList) {
        this.yTextList = yTextList;
        invalidate();
    }

    /**
     * 设置底部显示的文字
     * @param xTextList
     */
    public void setXTextList(List<String> xTextList) {
        this.xTextList = xTextList;
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
            list.add(0.0f);
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

    /**
     * 设置底部最大长度文字
     * @param str
     */
    public void setBottomMaxLengthText(String str){
        bottomLengthText = str;
        invalidate();
    }

    public void setBottomLeftText(String bottomLeftText) {
        this.bottomLeftText = bottomLeftText;
        invalidate();
    }
    /**
     * 画折线图的高度
     * @param numbers 数值
     */
    public void setData(List<Float> numbers){
        list.clear();
        list.addAll(numbers);
        if(list.size() == 0){
            return;
        }
        invalidate();//刷新界面
    }
}
