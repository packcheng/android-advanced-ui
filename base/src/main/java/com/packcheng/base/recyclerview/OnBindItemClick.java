package com.packcheng.base.recyclerview;

import android.view.View;

/**
 * RecyclerView item点击监听
 *
 * @author packcheng
 * @date 2020-01-16$ 17:37$
 */
public interface OnBindItemClick<M extends IBindModel> {
    /**
     * RecyclerView item 点击回调
     *
     * @param v        被点击的视图
     * @param position 被点击的视图在RecyclerView的position
     * @param model    item的model
     */
    void onBindItemClick(View v, int position, M model);
}
