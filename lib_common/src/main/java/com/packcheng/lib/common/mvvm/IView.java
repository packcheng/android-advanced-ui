package com.packcheng.lib.common.mvvm;

import android.app.Dialog;
import android.content.Context;

import com.packcheng.lib.common.component.callback.OnLoadingTimeOut;

/**
 * 公共View接口
 *
 * @author packcheng
 * @date 2020-02-03 12:34
 */
public interface IView {
    /**
     * 显示加载中对话框
     *
     * @param isCancelable 是否可点击对话框外部取消
     */
    void showLoading(boolean isCancelable);

    /**
     * 隐藏加载中对话框
     */
    void hideLoading();

    /**
     * 设置加载中对话框
     *
     * @return 加载中对话框
     */
    Dialog provideProgressDialog();

    /**
     * 显示有超时回调的加载中对话框
     *
     * @param milliTime 超时时间，ms
     * @param callback  超时回调
     */
    void showTimeOutLoading(long milliTime, OnLoadingTimeOut callback);

    /**
     * 隐藏有超时回调的对话框
     */
    void hindTimeOutLoading();

    /**
     * 显示文字提示
     *
     * @param msg
     */
    void showTip(String msg);

    /**
     * 显示字符资源提示
     *
     * @param msgId 提示字符资源id
     */
    void showTip(int msgId);

    /**
     * 显示文本+图片提示
     *
     * @param msg
     * @param imageUrl
     */
    void showTip(String msg, int imageUrl);

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    Context getContext();
}
