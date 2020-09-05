package com.packcheng.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.packcheng.base.component.BaseSupportFragment;
import com.packcheng.base.mvvm.DefaultViewModel;
import com.packcheng.base.mvvm.DefaultViewModelFactory;
import com.packcheng.main.databinding.MainFragmentMainBinding;

/**
 * 主Fragment
 *
 * @author packcheng
 * @date 2020-02-04 13:42
 */
public class MainFragment extends BaseSupportFragment<MainFragmentMainBinding, DefaultViewModel> {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.main_fragment_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
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
        return DefaultViewModelFactory.getInstance(mHostActivity.getApplication());
    }
}
