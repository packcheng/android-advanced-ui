package com.packcheng.layout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.packcheng.lib.common.util.LogUtils;

import java.util.ArrayList;

/**
 * 自定义流式布局
 *
 * @author packcheng
 * @date 2020/9/10 4:39 PM
 */
public class WaterFlowLayout extends ViewGroup {
    private static final String TAG = WaterFlowLayout.class.getName();
    private ArrayList<Integer> mRowHeight; // 行高
    private ArrayList<Integer> mRowIndex; // child的行号

    public WaterFlowLayout(Context context) {
        this(context, null);
    }

    public WaterFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public WaterFlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LogUtils.i(TAG, "init");
        mRowHeight = new ArrayList<>();
        mRowIndex = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtils.d(TAG, "onMeasure");
        // 父布局指定的测量方式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 父布局指定的宽高
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 当前控件的宽高
        int realWidth = 0;
        int realHeight = 0;

        mRowIndex.clear();
        mRowHeight.clear();

        if (MeasureSpec.EXACTLY == widthMode
                && MeasureSpec.EXACTLY == heightMode) {
            LogUtils.d(TAG, "Both measure mode of width and height are MeasureSpec.EXACTLY.");

            View child;
            MarginLayoutParams childParams;
            int rowWith = 0, rowHeight = 0;
            int childWidth = 0, childHeight;
            int row = 0; // 当前行
            for (int i = 0; i < getChildCount(); i++) {
                child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                childParams = (MarginLayoutParams) child.getLayoutParams();
                childWidth = child.getMeasuredWidth() + childParams.getMarginStart() + childParams.getMarginEnd();
                childHeight = child.getMeasuredHeight() + childParams.topMargin + childParams.bottomMargin;
                rowHeight = Math.max(rowHeight, childHeight);

                if (childWidth >= widthSize) {
                    LogUtils.w(TAG, "You child view's width of WaterFlowLayout " +
                            "should not larger than parent's width.");
                    // 单个子View超过父控件宽度，直接换行
                    realHeight += rowHeight;
                    mRowHeight.add(rowHeight);
                    if (rowWith > 0) {
                        // 当前行前面有控件
                        mRowIndex.add(++row);
                    } else {
                        mRowIndex.add(row);
                    }
                    rowWith = 0;
                    rowHeight = 0;
                } else if (rowWith + childWidth > widthSize) {
                    // 换行
                    realHeight += rowHeight;
                    mRowHeight.add(rowHeight);
                    rowHeight = 0;
                    mRowIndex.add(row);
                    rowWith = childWidth;
                } else {
                    // 同行追加
                    rowWith += childWidth;
                    mRowIndex.add(row);

                    // 最后一行
                    if (getChildCount() - 1 == i) {
                        realHeight += rowHeight;
                        mRowHeight.add(rowHeight);
                    }
                }
            }
        } else {
            LogUtils.e(TAG, "UnSupport measure mode.");
        }

        setMeasuredDimension(
                MeasureSpec.EXACTLY == widthMode ? widthSize : realWidth,
                MeasureSpec.EXACTLY == heightMode ? heightSize : realHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LogUtils.d(TAG, "onLayout");
        int childTop = 0, childBottom = 0, childLeft = 0, childRight = 0;
        int rowIndex, rowHeight;
        View child;
        MarginLayoutParams childParams;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            childParams = (MarginLayoutParams) child.getLayoutParams();
            rowIndex = mRowIndex.get(i);
            rowHeight = mRowHeight.get(rowIndex);

            childTop = rowIndex * rowHeight;
            childLeft += childParams.leftMargin;
            childBottom = childTop + child.getMeasuredHeight();
            childRight = childLeft + child.getMeasuredWidth();

            child.layout(childLeft, childTop, childRight, childBottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
