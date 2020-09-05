package com.packcheng.base.recyclerview;

import android.view.View;

/**
 * RecyclerView item内部组件点击监听
 *
 * @author packcheng
 * @date 2020-01-16$ 17:37$
 */
public interface OnBindItemChildClick<M extends IBindModel> {
    /**
     * RecyclerView item 内部组件点击回调
     *
     * @param v        被点击内部组件的视图
     * @param position 被点击的视图在RecyclerView的position
     * @param model    item的model
     */
    void onBindItemChildClick(View v, int position, M model);
}
