package com.zwb.imagephotoshop.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.zwb.imagephotoshop.R;

/**
 * Created by zwb
 * Description
 * Date 17/5/11.
 */

public class ImageMatrixView extends View {
    private Matrix mMatrix;
    private Bitmap mBitmap;
    public ImageMatrixView(Context context) {
        this(context,null);
    }

    public ImageMatrixView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mMatrix = new Matrix();
    }

    public void setmMatrix(Matrix mMatrix) {
        this.mMatrix = mMatrix;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,0,0,null);
        canvas.drawBitmap(mBitmap,mMatrix,null);
    }
}
