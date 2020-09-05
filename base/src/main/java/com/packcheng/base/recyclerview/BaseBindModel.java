package com.packcheng.base.recyclerview;

import android.view.View;

import androidx.databinding.ViewDataBinding;

/**
 * RecyclerView单类型item的model基类
 * 单类型item的监听建议通过设置adapter的监听实现
 *
 * @author packcheng
 * @date 2020-01-17 15:57
 */
public abstract class BaseBindModel<V extends ViewDataBinding> implements IBindModel<V> {

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public boolean onClick(View view) {
        return false;
    }

    @Override
    public boolean onChildClick(View view) {
        return false;
    }
}
