package com.packcheng.lib.common.util;

import android.util.Log;

/**
 * 通用日志工具类
 *
 * @author packcheng
 * @date 2020-01-17 15:02
 */
public class LogUtils {
    /**
     * 日志级别定义
     */
    private static final int LEVEL_VERBOSE = 0;
    private static final int LEVEL_DEBUG = 1;
    private static final int LEVEL_INFO = 2;
    private static final int LEVEL_WARN = 3;
    private static final int LEVEL_ERROR = 4;
    private static final int LEVEL_CLOSED = 5;

    /**
     * 默认日志标记
     */
    private static final String TAG = "LogUtils";

    /**
     * 当前允许输出的日志级别
     * 只有大于当前级别的日志才能输出
     */
    private static int mLogLevel = LEVEL_VERBOSE;

    //************ 使用指定TAG输出日志 **************//

    public static void v(String tag, String msg) {
        if (LEVEL_VERBOSE >= mLogLevel) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL_DEBUG >= mLogLevel) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL_INFO >= mLogLevel) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL_WARN >= mLogLevel) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL_ERROR >= mLogLevel) {
            Log.e(tag, msg);
        }
    }


    //************ 使用默认TAG输出日志 **************//

    public static void v(String msg) {
        if (LEVEL_VERBOSE >= mLogLevel) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (LEVEL_DEBUG >= mLogLevel) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (LEVEL_INFO >= mLogLevel) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (LEVEL_WARN >= mLogLevel) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (LEVEL_ERROR >= mLogLevel) {
            Log.e(TAG, msg);
        }
    }
}
