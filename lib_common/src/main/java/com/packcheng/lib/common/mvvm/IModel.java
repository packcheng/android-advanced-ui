package com.packcheng.lib.common.mvvm;

/**
 * 公共Model接口
 *
 * @author packcheng
 * @date 2020-02-03 12:36
 */
public interface IModel {
    /**
     * Model被销毁时调用
     * 用于释放资源
     */
    void onCleared();
}
