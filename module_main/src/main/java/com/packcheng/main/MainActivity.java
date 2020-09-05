package com.packcheng.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.packcheng.base.component.BaseSupportActivity;
import com.packcheng.base.mvvm.DefaultViewModel;
import com.packcheng.base.mvvm.DefaultViewModelFactory;
import com.packcheng.main.databinding.MainActivityMainBinding;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 应用首页
 *
 * @author packcheng
 * @date 2020-02-04 12:52
 */
public class MainActivity extends BaseSupportActivity<MainActivityMainBinding, DefaultViewModel> {

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if (null == findFragment(MainFragment.class)) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
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

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
