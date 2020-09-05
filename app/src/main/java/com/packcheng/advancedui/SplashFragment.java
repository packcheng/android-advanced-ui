package com.packcheng.advancedui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.packcheng.advancedui.databinding.FragmentSplashBinding;
import com.packcheng.base.component.BaseSupportFragment;
import com.packcheng.base.mvvm.DefaultViewModel;
import com.packcheng.base.mvvm.DefaultViewModelFactory;
import com.packcheng.base.provider.ARouterConstant;
import com.packcheng.base.provider.IMainProvider;
import com.packcheng.lib.common.util.LogUtils;

/**
 * 欢迎页
 *
 * @author packcheng
 * @date 2020/9/1 6:07 PM
 */
public class SplashFragment extends BaseSupportFragment<FragmentSplashBinding, DefaultViewModel> {

    /**
     * 欢迎页持续时间
     */
    private static final int SPLASH_TIME_MILLIS = 3 * 1000;

    @Autowired(name = ARouterConstant.ROUTER_PATH_PROVIDER_MAIN)
    IMainProvider mMainProvider;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void initData() {
        if (null == mMainProvider) {
            LogUtils.e(TAG + " - can not get MainProvider");
            return;
        }

        // 可以跳转主页
        mHandler.postDelayed(() -> {
            mMainProvider.startMainActivity(mHostActivity);
            mHostActivity.finish();
        }, SPLASH_TIME_MILLIS);
    }

    @Override
    public boolean onBackPressedSupport() {
        return null != mMainProvider;
    }

    @Override
    protected Class<DefaultViewModel> onBindViewModel() {
        return DefaultViewModel.class;
    }

    @Override
    protected ViewModelProvider.Factory onBindViewModelFactory() {
        return DefaultViewModelFactory.getInstance(mHostActivity.getApplication());
    }
}
