package com.packcheng.base.mvvm;

import com.packcheng.lib.common.mvvm.CommonView;
import com.packcheng.lib.common.util.LogUtils;

/**
 * 项目默认View接口实现
 *
 * @author packcheng
 * @date 2020-02-03 13:59
 */
public abstract class BaseView extends CommonView implements IBaseView {
    @Override
    public void showTopTip(String msg) {
        LogUtils.d(TAG + " - showTopTip");
    }

    @Override
    public void showTopTip(int msgId) {
        LogUtils.d(TAG + " - showTopTip");
    }
}
