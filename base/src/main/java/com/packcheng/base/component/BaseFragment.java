package com.packcheng.base.component;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.packcheng.base.R;
import com.packcheng.base.mvvm.BaseViewModel;
import com.packcheng.base.mvvm.IBaseView;
import com.packcheng.lib.common.component.CommonFragment;
import com.packcheng.lib.common.util.LogUtils;

/**
 * 项目Fragment的基类
 *
 * @author packcheng
 * @date 2020-02-03 13:51
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel>
        extends CommonFragment implements IBaseView {

    /**
     * 异常关闭时，保存当前Fragment隐藏状态的KEY
     */
    private final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected V mBinding;
    protected VM mViewModel;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentView() != null) {
            mBinding = DataBindingUtil.bind(getContentView());
        } else if (getContentViewLayoutID() != 0) {
            mBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(mHostActivity), getContentViewLayoutID(), null, false);
        } else {
            throw new IllegalStateException(getString(R.string.error_layout_not_found));
        }

        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }

        LogUtils.d(TAG + " - onCreateView");

        initView(savedInstanceState);

        initViewModel();

        if (mBinding != null) {
            return mBinding.getRoot();
        } else {
            return null;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    /**
     * 初始化ViewModel
     */
    private void initViewModel() {
        mViewModel = createViewModel();
    }

    /**
     * 创建ViewModel
     *
     * @return
     */
    protected VM createViewModel() {
        return ViewModelProviders.of(this, onBindViewModelFactory()).get(onBindViewModel());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mBinding) {
            mBinding.unbind();
        }
    }

    @Override
    public void showTopTip(String msg) {
        LogUtils.d(TAG + " - showTopTip");
        if (this.isAdded() && mHostActivity instanceof BaseActivity) {
            ((BaseActivity) mHostActivity).showTopTip(msg);
        }
    }

    @Override
    public void showTopTip(int msgId) {
        LogUtils.d(TAG + " - showTopTip");
        showTopTip(getString(msgId));
    }


    /**
     * 设置状态栏字体颜色
     *
     * @param lightMode 浅色模式，状态栏字体为白色
     */
    public void setStatusBarLightMode(boolean lightMode) {
        if (mHostActivity instanceof BaseActivity) {
            ((BaseActivity) mHostActivity).setStatusBarLightMode(lightMode);
        }
    }

    /**
     * 设置页面视图
     *
     * @return 页面视图
     */
    protected View getContentView() {
        return null;
    }

    /**
     * 页面布局文件ID
     *
     * @return
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 初始化页面组件
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化页面数据
     */
    protected abstract void initData();

    /**
     * 绑定ViewModel
     *
     * @return
     */
    protected abstract Class<VM> onBindViewModel();

    /**
     * 绑定ViewModelFactory
     *
     * @return
     */
    protected abstract ViewModelProvider.Factory onBindViewModelFactory();
}
