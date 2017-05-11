package com.zwb.imagephotoshop;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by zwb
 * Description 用来处理图片
 * Date 17/5/10.
 */

public class ImageHelper {

    /**
     *
     * @param bm 原图
     * @param hue 色相
     * @param saturation 饱和度
     * @param lum 亮度
     * @return
     */
    public static Bitmap handleImageEffect(Bitmap bm,float hue,float saturation,float lum){
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(), Bitmap.Config.ARGB_8888);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0,hue);//0:代表R
        hueMatrix.setRotate(1,hue);//1：代表G
        hueMatrix.setRotate(2,hue);//2：代表B

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum,lum,lum,1);

        ColorMatrix realMatrix = new ColorMatrix();
        realMatrix.postConcat(hueMatrix);
        realMatrix.postConcat(saturationMatrix);
        realMatrix.postConcat(lumMatrix);

        mPaint.setColorFilter(new ColorMatrixColorFilter(realMatrix));

        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bm,0,0,mPaint);
        return bmp;
    }

}
