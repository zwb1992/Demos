package com.zwb.linechartdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zwb
 * Description
 * Date 16/9/22.
 */
public class LineChart extends View {

    private Paint mPaint;
    public LineChart(Context context){
        super(context);
        init(context,null,0);
    }

    public LineChart(Context context, AttributeSet set){
        super(context,set);
        init(context,set,0);
    }
    public LineChart(Context context, AttributeSet set,int defStyle){
        super(context,set,defStyle);
        init(context,set,defStyle);
    }

    private void init(Context context, AttributeSet set,int defStyle){
        mPaint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        Path path = new Path();
        path.moveTo(0,getHeight()/2);
        path.lineTo(getWidth(),getHeight()/2);
        PathEffect pathEffect = new DashPathEffect(new float[]{15,15,15,15},1);
        mPaint.setPathEffect(pathEffect);
        canvas.drawPath(path,mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.check_blue));
        path = new Path();
        Path path1 = new Path();

        float pinjueW = (getWidth() - 20)/6.0f;
        float radius = 10.0f;
        path.moveTo(radius,getHeight()-10);
        path1.moveTo(radius,getHeight()-10);
        drawPoint(canvas,radius, getHeight()-10,10);


        path.lineTo(pinjueW+radius, getHeight()-10);
        path1.lineTo(pinjueW+radius, getHeight()-10);
        drawPoint(canvas,pinjueW+radius, getHeight()-10,10);

        path.lineTo(pinjueW*2+radius, getHeight()-10);
        path1.lineTo(pinjueW*2+radius, getHeight()-10);
        drawPoint(canvas,pinjueW*2+radius, getHeight()-10,10);

        path.lineTo(pinjueW*3+radius, getHeight()/2-10);
        path1.lineTo(pinjueW*3+radius, getHeight()/2-10);
        drawPoint(canvas,pinjueW*3+radius, getHeight()/2-10,10);

        path.lineTo(pinjueW*4+radius, getHeight()-10);
        path1.lineTo(pinjueW*4+radius, getHeight()-10);
        drawPoint(canvas,pinjueW*4+radius, getHeight()-10,10);

        path.lineTo(pinjueW*5+radius, getHeight()/5*4-10);
        path1.lineTo(pinjueW*5+radius, getHeight()/5*4-10);
        drawPoint(canvas,pinjueW*5+radius, getHeight()/5*4-10,10);

        path.lineTo(pinjueW*6+radius, getHeight()/2-210);
        path1.lineTo(pinjueW*6+radius, getHeight()/2-210);
        drawPoint(canvas,pinjueW*6+radius, getHeight()/2-210,10);

        path.lineTo(pinjueW*6+radius,getHeight()-10);
        path1.lineTo(pinjueW*6+radius,getHeight()-10);
        mPaint.setPathEffect(new CornerPathEffect(10));
        canvas.drawPath(path,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.check_blue_50));
        canvas.drawPath(path1,mPaint);
    }

    private void drawPoint(Canvas canvas,float x,float y,float radius){
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.check_blue));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x,y,radius,paint);
    }
}
