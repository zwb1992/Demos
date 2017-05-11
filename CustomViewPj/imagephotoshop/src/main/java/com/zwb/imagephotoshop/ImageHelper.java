package com.zwb.imagephotoshop;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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
     * @param bm         原图
     * @param hue        色相
     * @param saturation 饱和度
     * @param lum        亮度
     * @return
     */
    public static Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);//0:代表R
        hueMatrix.setRotate(1, hue);//1：代表G
        hueMatrix.setRotate(2, hue);//2：代表B

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix realMatrix = new ColorMatrix();
        realMatrix.postConcat(hueMatrix);
        realMatrix.postConcat(saturationMatrix);
        realMatrix.postConcat(lumMatrix);

        mPaint.setColorFilter(new ColorMatrixColorFilter(realMatrix));

        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bm, 0, 0, mPaint);
        return bmp;
    }


    public static Bitmap setColorMatrix(Bitmap bm, float[] matrixs) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        Canvas canvas = new Canvas(bmp);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(matrixs);

        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bm, 0, 0, mPaint);
        return bmp;

    }

    /**
     * 底片效果
     *
     * @param bm
     * @return
     */
    public static Bitmap pixelsEffect(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] pixels = new int[width * height];
        int[] realPixels = new int[width * height];
        bm.getPixels(pixels, 0, width, 0, 0, width, height);
        int r, g, b, a;
        int color;
        for (int i = 0; i < pixels.length; i++) {
            color = pixels[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            }
            if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            }
            if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            }
            if (b < 0) {
                b = 0;
            }

            int realColor = Color.argb(a, r, g, b);
            realPixels[i] = realColor;
        }
        bmp.setPixels(realPixels, 0, width, 0, 0, width, height);
        return bmp;
    }

    /**
     * 怀旧效果
     *
     * @param bm
     * @return
     */
    public static Bitmap pixelsEffect1(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] pixels = new int[width * height];
        int[] realPixels = new int[width * height];
        bm.getPixels(pixels, 0, width, 0, 0, width, height);
        int r, g, b, a;
        int color;
        for (int i = 0; i < pixels.length; i++) {
            color = pixels[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b = (int) (0.272 * r + 0.534 * g + 0.131 * b);

            if (r > 255) {
                r = 255;
            }
            if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            }
            if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            }
            if (b < 0) {
                b = 0;
            }

            int realColor = Color.argb(a, r, g, b);
            realPixels[i] = realColor;
        }
        bmp.setPixels(realPixels, 0, width, 0, 0, width, height);
        return bmp;
    }


    /**
     * 浮雕效果
     *
     * @param bm
     * @return
     */
    public static Bitmap pixelsEffect2(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] pixels = new int[width * height];
        int[] realPixels = new int[width * height];
        bm.getPixels(pixels, 0, width, 0, 0, width, height);
        int r, g, b, a;
        int r1, g1, b1;
        int color,colorBefore;
        for (int i = 1; i < pixels.length; i++) {
            colorBefore = pixels[i-1];
            r = Color.red(colorBefore);
            g = Color.green(colorBefore);
            b = Color.blue(colorBefore);
            a = Color.alpha(colorBefore);

            color = pixels[i];
            r1 = Color.red(color);
            g1 = Color.green(color);
            b1 = Color.blue(color);

            r = r - r1 + 127;
            g = g - g1 + 127;
            b = b - b1 + 127;

            if (r > 255) {
                r = 255;
            }
            if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            }
            if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            }
            if (b < 0) {
                b = 0;
            }

            int realColor = Color.argb(a, r, g, b);
            realPixels[i] = realColor;
        }
        bmp.setPixels(realPixels, 0, width, 0, 0, width, height);
        return bmp;
    }
}
