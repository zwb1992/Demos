package com.example.guaguaka.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.guaguaka.R;

/**
 * Created by zwb
 * Description 刮刮乐效果
 * Date 2017/5/10.
 */

public class GuaGuaKa extends View {
    private Paint mOutterPaint;
    private Paint mBgPaint;
    private Bitmap mOutterBitmap;//把涂搽的部分先画到bitmap上，然后再画到画版上
    private Canvas mOutterCanvas;
    private Path mPath;

    //---------------------------------------------------------------
    private Bitmap bitmap;

    private String mText = "谢谢惠顾";
    private Paint mTextPaint;
    private Rect mBounds;
    private int textSize = 40;
    private boolean isComplete = false;
    private OnComplteListenner onComplteListenner;

    public GuaGuaKa(Context context) {
        this(context, null);
    }

    public GuaGuaKa(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuaGuaKa(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnComplteListenner(OnComplteListenner onComplteListenner) {
        this.onComplteListenner = onComplteListenner;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        mOutterBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mOutterCanvas = new Canvas(mOutterBitmap);
//        mOutterCanvas.drawColor(Color.parseColor("#c0c0c0"));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.s_title);
        mOutterCanvas.drawRoundRect(0, 0, w, h, 30, 30, mBgPaint);
        mOutterCanvas.drawBitmap(bitmap, null, new Rect(0, 0, w, h), null);
    }

    private void init() {
        mOutterPaint = new Paint();
        mOutterPaint.setStyle(Paint.Style.STROKE);
        mOutterPaint.setStrokeWidth(15);
        mOutterPaint.setAntiAlias(true);
        mOutterPaint.setDither(true);
        mOutterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOutterPaint.setStrokeJoin(Paint.Join.ROUND);
        mOutterPaint.setColor(Color.parseColor("#c0c0c0"));
        mPath = new Path();

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
        setUpBackPaint();

        //绘制背圆角矩形景
        mBgPaint = new Paint();
        mBgPaint.setColor(Color.parseColor("#c0c0c0"));
        mBgPaint.setAntiAlias(true);
        mBgPaint.setDither(true);
        mBgPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 设置文本属性
     */
    private void setUpBackPaint() {
        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(Color.BLACK);
        mBounds = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), mBounds);
    }

    private int mLastX, mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                //移动大于3个像素才画rf
                if (dx > 3 || dy > 3) {
                    mPath.lineTo(x, y);
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                new Thread(runnable).start();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //底部为一张图片--刮完显示的内容
//        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        Rect dstRect = new Rect(0, 0, getWidth(), getHeight());
//        canvas.drawBitmap(bitmap, rect, dstRect, null);//把完整图片绘制到指定区域,图片会拉伸

        //底部画中奖文本
        canvas.drawText(mText, (getWidth() - mBounds.width()) / 2, (getHeight() + mBounds.height()) / 2, mTextPaint);

        if (isComplete) {
            if (onComplteListenner != null) {
                onComplteListenner.onComplete();
            }
        }
        if (!isComplete) {
            mOutterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            mOutterCanvas.drawPath(mPath, mOutterPaint);
            canvas.drawBitmap(mOutterBitmap, 0, 0, null);
        }
    }

    public interface OnComplteListenner {
        void onComplete();
    }


    /**
     * 用来计算被擦除的像素所占的百分比
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int w = getWidth();
            int h = getHeight();
            Bitmap bitmap = mOutterBitmap;
            int[] pixels = new int[w * h];
            int totalPixels = w * h;
            bitmap.getPixels(pixels, 0, w, 0, 0, w, h);
            float percent = 0;
            for (int i = 0; i < pixels.length; i++) {
                if (pixels[i] == 0) {
                    percent++;
                }
            }

            if (percent > 0 && totalPixels > 0) {
                if (percent * 100 / totalPixels > 60) {
                    isComplete = true;
                    postInvalidate();
                }
            }
        }
    };
}
