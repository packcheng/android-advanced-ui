package com.packcheng.layout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.packcheng.lib.common.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义流式布局
 *
 * @author packcheng
 * @date 2020/9/10 4:39 PM
 */
public class WaterFlowLayout extends ViewGroup {
    private static final String TAG = WaterFlowLayout.class.getName();

    private List<List<View>> mAllLineViews;
    private List<Integer> mLineHeight;

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
        mAllLineViews = new ArrayList<>();
        mLineHeight = new ArrayList<>();
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

        // 当前控件设置的左右边距
        int currentPaddingWidth = getPaddingStart() + getPaddingEnd();
        int currentPaddingHeight = getPaddingBottom() + getPaddingTop();

        View child;
        MarginLayoutParams childParams;
        int rowWith = 0, rowHeight = 0;
        int childWidth, childHeight;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            childParams = (MarginLayoutParams) child.getLayoutParams();
            childWidth = child.getMeasuredWidth() + childParams.getMarginStart() + childParams.getMarginEnd();
            childHeight = child.getMeasuredHeight() + childParams.topMargin + childParams.bottomMargin;

            if (childWidth + currentPaddingWidth >= widthSize) {
                LogUtils.w(TAG, "You child view's width of WaterFlowLayout " +
                        "should not larger than parent's width.");
                // 单个子View超过父控件宽度，直接换行
                if (0 == rowWith) {
                    // 某一行不存在其他控件
                    realHeight += childHeight;
                } else {
                    // 某一行已存在其他控件
                    realHeight += rowHeight;
                    realHeight += childHeight;
                    rowWith = 0;
                    rowHeight = 0;
                }
                realWidth = widthSize;// 高度设置为父布局给定的最大值
            } else if (rowWith + childWidth + currentPaddingWidth > widthSize) {
                // 换行
                realHeight += rowHeight;
                realWidth = Math.max(realWidth, rowWith);
                rowHeight = childHeight;
                rowWith = childWidth;
            } else {
                // 同行追加
                rowWith += childWidth;
                rowHeight = Math.max(rowHeight, childHeight);
            }

            // 最后一行
            if (getChildCount() - 1 == i) {
                realHeight += rowHeight;
                realWidth = Math.max(realWidth, rowHeight);
            }
        }

        // 真实宽高需要添加设置的内边距
        realHeight += currentPaddingHeight;
        realWidth += currentPaddingWidth;

        // 确定当前控件大小
        setMeasuredDimension(
                MeasureSpec.EXACTLY == widthMode ? widthSize : realWidth,
                MeasureSpec.EXACTLY == heightMode ? heightSize : realHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LogUtils.d(TAG, "onLayout");
        sortChildrenByLine();
        layoutChildrenByLine(l, t, r, b);
    }

    /**
     * 将子view按行分组
     */
    private void sortChildrenByLine() {
        LogUtils.i(TAG, "sortChildrenOnLine");
        mAllLineViews.clear();
        mLineHeight.clear();

        View child;
        MarginLayoutParams childParams;
        int rowHeight = 0, usedRowWidth = 0;
        List<View> rowViews = new ArrayList<>();
        for (int index = 0; index < getChildCount(); index++) {
            child = getChildAt(index);
            childParams = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + childParams.getMarginStart() + childParams.getMarginEnd();
            int childHeight = child.getMeasuredHeight() + childParams.topMargin + childParams.bottomMargin;

            if (usedRowWidth + childWidth > getMeasuredWidth()) {
                if (0 == rowViews.size()) {
                    LogUtils.d(TAG, "换行，当前View加入当前行");
                    // 当前行为空，将当前view加到该行
                    // 保存当前行
                    rowViews.add(child);
                    mAllLineViews.add(rowViews);
                    mLineHeight.add(childHeight);
                    // 初始化下一行
                    rowViews = new ArrayList<>();
                    rowHeight = 0;
                    usedRowWidth = 0;
                } else {
                    LogUtils.d(TAG, "换行，当前View加入下一行");
                    // 当前行已经有view了，将当前View加到下一行
                    // 保存当前行数据信息
                    mAllLineViews.add(rowViews);
                    mLineHeight.add(rowHeight);
                    // 初始化下一行
                    rowViews = new ArrayList<>();
                    rowViews.add(child);
                    rowHeight = childHeight;
                    usedRowWidth = childWidth;
                }
            } else {
                LogUtils.d(TAG, "同行追加");
                rowViews.add(child);
                usedRowWidth += childWidth;
                rowHeight = Math.max(rowHeight, childHeight);
            }

            // 最后一个View
            if (index == getChildCount() - 1
                    && 0 < rowViews.size()) {
                LogUtils.d(TAG, "最后一行不满，主动追加");
                mAllLineViews.add(rowViews);
                mLineHeight.add(rowHeight);
            }
            LogUtils.d(TAG, "usedWith: " + usedRowWidth);
        }
    }

    /**
     * 将子View按行摆放
     */
    private void layoutChildrenByLine(int l, int t, int r, int b) {
        LogUtils.i(TAG, "layoutChildrenByLine");
        int childTop = getPaddingTop();
        int childLeft = getPaddingLeft();
        int childBottom = 0, childRight = 0;
        List<View> rowViews;
        View child;
        MarginLayoutParams childParams;
        for (int rowIndex = 0; rowIndex < mAllLineViews.size(); rowIndex++) {
            rowViews = mAllLineViews.get(rowIndex);
            // 摆放一行的View
            for (int childIndex = 0; childIndex < rowViews.size(); childIndex++) {
                child = rowViews.get(childIndex);
                childParams = (MarginLayoutParams) child.getLayoutParams();

                childLeft += childParams.getMarginStart();
                childRight = childLeft + child.getMeasuredWidth();
                childTop += childParams.topMargin;
                childBottom = childTop + child.getMeasuredHeight();

                child.layout(childLeft, childTop, childRight, childBottom);

                childLeft = childRight + childParams.getMarginEnd();
            }

            // 定义下一行的左上角坐标
            childLeft = getPaddingLeft();
            childTop += mLineHeight.get(rowIndex);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
