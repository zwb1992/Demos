package com.example.flowlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zwb
 * Description
 * Date 2017/5/12.
 */

public class FlowLayout extends ViewGroup {
    private static final String TAG = FlowLayout.class.getName();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = 0;//最终真实的宽度
        int realHeight = 0;//最终真实的高度

        int lineWidth = 0;//当前行的宽度
        int lineHeight = 0;//当前行的高度

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (childWidth + lineWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {//此时需要换行
                realWidth = Math.max(realWidth, lineWidth);
                lineWidth = childWidth;
                realHeight += lineHeight;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            //如果是最后一个，把最后一行的高加上，以及计算最终的宽度
            if (i == count - 1) {
                realWidth = Math.max(realWidth, lineWidth);
                realHeight += lineHeight;
            }
        }
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth
                : realWidth + getPaddingLeft() + getPaddingRight(), (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
                : realHeight + getPaddingBottom() + getPaddingTop());
    }

    /**
     * 存储所有的View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    //存储每一行的高度,便于排列child
    private List<Integer> lineHeights = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        lineHeights.clear();
        mAllViews.clear();

        int sizeWidth = getWidth();//真实的宽度
        int lineWidth = 0;//当前行的宽度
        int lineHeight = 0;//当前行的高度
        List<View> lineViews = new ArrayList<>();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (childWidth + lineWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {//此时需要换行
                // 记录这一行所有的View以及最大高度
                lineHeights.add(lineHeight);
                // 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView
                mAllViews.add(lineViews);
                lineWidth = 0;// 重置行宽
                lineViews = new ArrayList<View>();
            }

            /**
             * 如果不需要换行，则累加
             */
            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight, childHeight);
            lineViews.add(child);
        }

        // 记录最后一行
        lineHeights.add(lineHeight);
        mAllViews.add(lineViews);

        int left = getPaddingLeft();
        int top = getPaddingTop();
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++) {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = lineHeights.get(i);

            Log.e(TAG, "第" + i + "行 ：" + lineViews.size() + " , " + lineViews);
            Log.e(TAG, "第" + i + "行， ：" + lineHeight);
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();

                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                Log.e(TAG, child + " , l = " + lc + " , t = " + t + " , r ="
                        + rc + " , b = " + bc);

                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.rightMargin
                        + lp.leftMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
