package com.zwb.imagephotoshop.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zwb.imagephotoshop.R;

/**
 * Created by zwb
 * Description 倒影效果
 * Date 17/5/11.
 */

public class ReflectView extends View {
    private Bitmap mSrcBitmap;
    private Bitmap mReflectBitmap;
    private Paint mPaint;
    public ReflectView(Context context) {
        super(context);
        initView();
    }

    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg1);
        Matrix matrix = new Matrix();
        matrix.postScale(1,-1);//x轴对称
        mReflectBitmap = Bitmap.createBitmap(mSrcBitmap,0,0,mSrcBitmap.getWidth(),mSrcBitmap.getHeight(),matrix,true);

//        matrix.setScale(1,2);
//        Bitmap bitmap = Bitmap.createBitmap(mSrcBitmap,0,0,mSrcBitmap.getWidth(),mSrcBitmap.getHeight(),matrix,true);
//        Log.e("info","=====bitmap.getWidth()====="+bitmap.getWidth());
//        Log.e("info","=====bitmap.getHeight()====="+bitmap.getHeight());
//        Log.e("info","=====mSrcBitmap.getWidth()====="+mSrcBitmap.getWidth());
//        Log.e("info","=====bimSrcBitmaptmap.getHeight()====="+mSrcBitmap.getHeight());
//        mReflectBitmap = Bitmap.createScaledBitmap(mSrcBitmap,400,400,true);
//        mReflectBitmap = Bitmap.createBitmap(mSrcBitmap,0,0,400,400);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setShader(new LinearGradient(0,mSrcBitmap.getHeight(),0,mSrcBitmap.getHeight() * 2,0xdd000000,0x10000000, Shader.TileMode.CLAMP));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mSrcBitmap,0,0,null);
        canvas.drawBitmap(mReflectBitmap,0,mSrcBitmap.getHeight(),null);
        canvas.drawRect(0,mSrcBitmap.getHeight(),mSrcBitmap.getWidth(),mSrcBitmap.getHeight() * 2,mPaint);
    }
}
