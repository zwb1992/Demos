package com.zwb.xfermodedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zwb
 * Description
 * Date 17/7/3.
 */

public class ClipView2 extends View {
    private Paint mPaintCircle;
    private Paint mPaintRect;
    private Bitmap mBgBitmap;
    private Canvas mCanvas;
    public ClipView2(Context context) {
        this(context, null);
    }

    public ClipView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setDither(true);
        mPaintCircle.setStyle(Paint.Style.FILL);
        mPaintCircle.setColor(Color.YELLOW);

        mPaintRect = new Paint();
        mPaintRect.setAntiAlias(true);
        mPaintRect.setDither(true);
        mPaintRect.setStyle(Paint.Style.FILL);
        mPaintRect.setColor(Color.BLUE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawShape();
        canvas.drawBitmap(mBgBitmap,0,0,null);
    }

    private void drawShape() {
        mBgBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBgBitmap);

        mCanvas.drawCircle(200,200,100,mPaintCircle);
//        mPaintRect.setColor(Color.BLUE);
        mPaintRect.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mCanvas.drawRect(200,200,350,350,mPaintRect);
    }
}
