package com.packcheng.lib.common.component;

import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import com.packcheng.lib.common.util.LogUtils;
import com.packcheng.lib.common.util.ToastUtil;

/**
 * 默认Application基类
 *
 * @author packcheng
 * @date 2020-02-03 13:14
 */
public class CommonApplication extends MultiDexApplication {
    protected String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG + " - onCreate");

        ToastUtil.init(this);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.d(TAG + " - onConfigurationChanged");
    }
}
