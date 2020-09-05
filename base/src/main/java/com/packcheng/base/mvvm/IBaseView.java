package com.packcheng.base.mvvm;

/**
 * 项目View接口
 *
 * @author packcheng
 * @date 2020-02-03 13:53
 */
public interface IBaseView {

    /**
     * 顶部弹出提示
     *
     * @param msg 提示信息
     */
    void showTopTip(String msg);

    /**
     * 顶部弹出提示
     *
     * @param msgId 提示信息资源ID
     */
    void showTopTip(int msgId);
}
