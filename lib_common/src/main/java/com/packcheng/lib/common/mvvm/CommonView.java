package com.packcheng.lib.common.mvvm;

import android.app.Dialog;
import android.content.Context;

import com.packcheng.lib.common.util.LogUtils;

/**
 * 默认View实现类
 *
 * @author packcheng
 * @date 2020-02-03 12:48
 */
public abstract class CommonView implements IView {
    protected String TAG = getClass().getSimpleName();


    @Override
    public void showLoading(boolean isCancelable) {
        LogUtils.d(TAG + " - showLoading");
    }

    @Override
    public void hideLoading() {
        LogUtils.d(TAG + " - hideLoading");
    }

    @Override
    public Dialog provideProgressDialog() {
        LogUtils.d(TAG + " - provideProgressDialog");
        return null;
    }

    @Override
    public void showTip(String msg) {
        LogUtils.d(TAG + " - showTip");
    }

    @Override
    public void showTip(int msgId) {
        LogUtils.d(TAG + " - showTip");
    }

    @Override
    public void showTip(String msg, int imageUrl) {
        LogUtils.d(TAG + " - showTip");
    }

    @Override
    public Context getContext() {
        LogUtils.d(TAG + " - getContext");
        return null;
    }
}
