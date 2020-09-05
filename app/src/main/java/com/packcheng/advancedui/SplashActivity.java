package com.packcheng.advancedui;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.packcheng.advancedui.databinding.ActivitySplashBinding;
import com.packcheng.base.component.BaseSupportActivity;
import com.packcheng.base.mvvm.DefaultViewModel;
import com.packcheng.base.mvvm.DefaultViewModelFactory;

/**
 * 欢迎页
 *
 * @author packcheng
 * @date 2020/9/1 6:06 PM
 */
public class SplashActivity extends BaseSupportActivity<ActivitySplashBinding, DefaultViewModel> {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if (null == findFragment(SplashFragment.class)) {
            loadRootFragment(R.id.fl_container, SplashFragment.newInstance());
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected Class<DefaultViewModel> onBindViewModel() {
        return DefaultViewModel.class;
    }

    @Override
    protected ViewModelProvider.Factory onBindViewModelFactory() {
        return DefaultViewModelFactory.getInstance(getApplication());
    }
}
