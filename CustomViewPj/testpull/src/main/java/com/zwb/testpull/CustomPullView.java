package com.zwb.testpull;

import android.content.Context;
import android.graphics.drawable.RotateDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by zwb
 * Description
 * Date 17/5/13.
 */

public class CustomPullView extends RelativeLayout {

    private int mHeight;
    private View mHeader;
    private float downY;
    private ImageView imgHeader;
    private RotateDrawable rotateDrawable;

    private int maxTopPadding;//最大的下拉padding
    private int minTopPadding;//最小的下拉padding

    public CustomPullView(Context context) {
        this(context, null);
    }

    public CustomPullView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mHeader = LayoutInflater.from(getContext()).inflate(R.layout.header, this, true);
        imgHeader = (ImageView) mHeader.findViewById(R.id.img_header);
        measureView(mHeader);
        rotateDrawable = (RotateDrawable) getResources().getDrawable(R.drawable.rotate);
        imgHeader.setImageDrawable(rotateDrawable);
        mHeight = mHeader.getMeasuredHeight();
        minTopPadding = -mHeight;
        maxTopPadding = (int) (1.4f * mHeight);
        setTopPadding(minTopPadding);
//        setLevel(70);
        Log.e("info", "====mHeight===" + mHeight);
//        addView(mHeader);//inflate(R.layout.header,this,true);这种不需要再次添加view
    }

    public void setTopPadding(int top) {
        if (top <= maxTopPadding && top >= minTopPadding) {
            setLevel(top);
            mHeader.setPadding(0, top, 0, 0);
            mHeader.invalidate();
        }
    }


    private void measureView(View child) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int widthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
        int heightSpec;
        int tempHeight = lp.height;
        if (tempHeight > 0) {
            heightSpec = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
        } else {
            heightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }

        child.measure(widthSpec, heightSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float fy = event.getY() - downY;
                int top = (int) (fy * 0.6f - mHeight);
                setTopPadding(top);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    /**
     * 设置图片的旋转角度
     *
     * @param progress
     */
    private void setLevel(int progress) {
        RotateDrawable rotateDrawable = (RotateDrawable) imgHeader.getDrawable();
        rotateDrawable.setLevel(progress);
    }
}
