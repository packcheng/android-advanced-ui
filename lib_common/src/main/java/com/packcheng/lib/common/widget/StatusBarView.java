package com.packcheng.lib.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;

import com.packcheng.lib.common.util.ScreenUtil;

/**
 * Description:
 * Author: MiraclesHed
 * Date: 2019/7/9
 */
public class StatusBarView extends View {

    public StatusBarView(Context context) {
        super(context);
        initView();
    }


    public StatusBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.height = ScreenUtil.getStatusHeight(getContext());
                setLayoutParams(layoutParams);
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

}
