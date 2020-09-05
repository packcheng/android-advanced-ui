package com.packcheng.base.recyclerview;

import androidx.databinding.ViewDataBinding;

/**
 * RecyclerView多类型item的model基类
 * 多类型item的监听建议model内部实现实现
 *
 * @author packcheng
 * @date 2020-01-17$ 16:00$
 */
public abstract class BaseBindMultiModel<V extends ViewDataBinding>
        implements IBindModel<V> {
}
