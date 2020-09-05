package com.packcheng.base.recyclerview;

import android.view.View;

import androidx.databinding.ViewDataBinding;


/**
 * RecyclerView item数据封装接口
 *
 * @author packcheng
 * @date 2020-01-16$ 15:01$
 */
public interface IBindModel<V extends ViewDataBinding> {
    /**
     * 获取条目布局
     * 当是单类型item时，可以不传值，使用创建adapter设置的id
     *
     * @return
     */
    int getLayoutId();

    /**
     * 处理item的点击事件
     *
     * @param view
     * @return 点击事件是否被消费
     * true: 已消费，不会再调用{@link BaseBindAdapter#setOnBindItemClick(OnBindItemClick)}设置的监听
     * false: 未消费
     */
    boolean onClick(View view);

    /**
     * 处理item内部组件的点击事件
     *
     * @param view 被点击的组件
     * @return 事件是否被消费，
     * true: 已消费，不会再调用{@link BaseBindAdapter#setOnBindItemChildClick(OnBindItemChildClick)}设置的监听
     * false: 未消费
     */
    boolean onChildClick(View view);

    /**
     * 处理数据绑定
     *
     * @param helper
     * @param binding
     */
    void convert(BaseBindViewHolder helper, V binding);
}
