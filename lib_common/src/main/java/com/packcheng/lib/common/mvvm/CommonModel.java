package com.packcheng.lib.common.mvvm;

import android.app.Application;

import com.packcheng.lib.common.util.LogUtils;

/**
 * 默认Model实现类
 *
 * @author packcheng
 * @date 2020-02-03 12:47
 */
public abstract class CommonModel implements IModel {
    protected String TAG = getClass().getSimpleName();

    protected Application mApplication;

    public CommonModel(Application application) {
        this.mApplication = application;
    }

    @Override
    public void onCleared() {
        LogUtils.i(TAG + "-onCleared");
    }
}
