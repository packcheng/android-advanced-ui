package com.packcheng.lib.common.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * 常用单位转换的辅助类
 */
public class DensityUtil {
    private DensityUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        //这里为什么要加0.5？
        // 为了四舍五入
        // 在Java中，float转int是直接把小数位砍掉的(如4.1>4;4.9>4)，所以如果要做到四舍五入就要加0.5(即4.1+0.5=4.6>4;4.9+0.5=5.4>5),保证了结果相对准确。
        return (pxVal / scale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (pxVal / fontScale + 0.5f);
    }

}
