package com.zwb.canvaslayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zwb
 * Description
 * Date 17/5/8.
 */

public class CanvasLayer extends View {
    private Paint mPaint;
    private PorterDuffXfermode pdf;
    public CanvasLayer(Context context) {
        super(context);
        init(context);
    }

    public CanvasLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CanvasLayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        pdf = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("info","==getWidth=="+getWidth());
        canvas.saveLayerAlpha(0,0,getWidth(),getHeight(),120,Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        mPaint.setXfermode(pdf);
        mPaint.setColor(Color.RED);
        canvas.drawRect(100,100,300,300,mPaint);
        canvas.restore();
        mPaint.setXfermode(null);
    }
}
