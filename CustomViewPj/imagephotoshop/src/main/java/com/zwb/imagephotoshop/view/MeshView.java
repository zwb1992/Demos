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
 * Date 17/5/12.
 */

public class MeshView extends View {
    /**
     * 将一张图片以200*200的网格分割
     */
    private int WIDTH = 200;
    private int HEIGHT = 200;

    /**
     * 200*200的网格一共有多少个点
     */
    private int COUNT = (WIDTH + 1) * (HEIGHT + 1);

    /**
     * 用一个一维数组来代表一个二维数组，奇数代表x坐标，偶数代表y坐标，如：第一个点坐标x，y，分别为vets[0],vets[1]
     */
    private float[] verts = new float[COUNT * 2];
    private float[] orig = new float[COUNT * 2];

    private Bitmap mBitmap;
    private float K = 1;

    public MeshView(Context context) {
        super(context);
        initView();
    }

    public MeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        int index = 0;//从第1行开始
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg1);
        float mWidth = mBitmap.getWidth();
        float mHeight = mBitmap.getHeight();

        for (int i = 0; i < HEIGHT + 1; i++) {
            float fy = mHeight * i / HEIGHT;
            for (int j = 0; j < WIDTH + 1; j++) {
                float fx = j * mWidth / WIDTH;
                orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
                orig[index * 2 + 1] = verts[index * 2 + 1] = fy;
                index++;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //对图像进行变形
        for (int i = 0; i < HEIGHT + 1; i++) {
            for (int j = 0; j < WIDTH + 1; j++) {
                verts[(i * (WIDTH + 1) + j) * 2 + 0] += 0;
                float offsetY = (float) Math.sin((float) j / WIDTH * 2 * Math.PI + K * 2 * Math.PI);
                verts[(i * (WIDTH + 1) + j) * 2 + 1] =
                        orig[(i * (WIDTH + 1) + j) * 2 + 1] + offsetY * 50+100;
            }
        }
        K += 0.1f;
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
//        invalidate();
    }
}
