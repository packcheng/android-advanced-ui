package com.packcheng.base.component;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.packcheng.base.mvvm.BaseViewModel;

import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackFragment;
import me.yokeyword.fragmentation_swipeback.core.SwipeBackFragmentDelegate;

/**
 * 项目可侧滑返回的Fragment基类
 * <p/>
 * 复制 {@link me.yokeyword.fragmentation_swipeback.SwipeBackFragment}
 * 修改其中的 {@link #onActivityCreated(Bundle)} 和 {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
 *
 * @author packcheng
 * @date 2020-02-05 15:37
 */
public abstract class BaseSwipeBackFragment<V extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseSupportFragment<V, VM>
        implements ISwipeBackFragment {
    final SwipeBackFragmentDelegate mDelegate = new SwipeBackFragmentDelegate(this);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setParallaxOffset(0.5f);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 需要手动attachToSwipeBack
        return attachToSwipeBack(super.onCreateView(inflater, container, savedInstanceState));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mDelegate.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mDelegate.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View attachToSwipeBack(View view) {
        return mDelegate.attachToSwipeBack(view);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mDelegate.onHiddenChanged(hidden);
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mDelegate.getSwipeBackLayout();
    }

    /**
     * 是否可滑动
     *
     * @param enable
     */
    @Override
    public void setSwipeBackEnable(boolean enable) {
        mDelegate.setSwipeBackEnable(enable);
    }

    @Override
    public void setEdgeLevel(SwipeBackLayout.EdgeLevel edgeLevel) {
        mDelegate.setEdgeLevel(edgeLevel);
    }

    @Override
    public void setEdgeLevel(int widthPixel) {
        mDelegate.setEdgeLevel(widthPixel);
    }

    /**
     * Set the offset of the parallax slip.
     */
    @Override
    public void setParallaxOffset(@FloatRange(from = 0.0f, to = 1.0f) float offset) {
        mDelegate.setParallaxOffset(offset);
    }

    @Override
    public void onDestroyView() {
        mDelegate.onDestroyView();
        super.onDestroyView();
    }
}
