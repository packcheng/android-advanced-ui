package com.packcheng.base.component;

import com.alibaba.android.arouter.launcher.ARouter;
import com.packcheng.base.net.NetUtil;
import com.packcheng.lib.common.BuildConfig;
import com.packcheng.lib.common.component.CommonApplication;

/**
 * 项目Application的基类
 *
 * @author packcheng
 * @date 2020-02-03 13:51
 */
public class BaseApplication extends CommonApplication {
    private static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        // 初始化网络请求工具类
        NetUtil.getInstance().init();

        // 初始化ARouter
        if (BuildConfig.IS_DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // 释放ARouter资源
        ARouter.getInstance().destroy();
    }

    public static BaseApplication getInstance() {
        return mApplication;
    }

}
