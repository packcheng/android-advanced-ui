package com.packcheng.base.recyclerview;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import  com.packcheng.base.BR;
import java.util.ArrayList;

/**
 * RecyclerView单类型item的适配器基类
 *
 * @author packcheng
 * @date 2020-01-16 14:57
 */
public class BaseBindAdapter<M extends IBindModel, V extends ViewDataBinding>
        extends BaseQuickAdapter<M, BaseBindViewHolder> {
    private final String TAG = getClass().getSimpleName();

    /**
     * item点击监听
     */
    private OnBindItemClick<M> mOnBindItemClick;
    /**
     * item内组件点击监听
     */
    private OnBindItemChildClick<M> mOnBindItemChildClick;

    public BaseBindAdapter(int layoutResId) {
        super(layoutResId, new ArrayList<>());
    }

    /**
     * 设置item的点击监听
     *
     * @param onBindItemChildClick
     */
    public void setOnBindItemChildClick(OnBindItemChildClick<M> onBindItemChildClick) {
        this.mOnBindItemChildClick = onBindItemChildClick;
    }

    /**
     * 设置item内组件的点击监听
     *
     * @param onBindItemClick
     */
    public void setOnBindItemClick(OnBindItemClick<M> onBindItemClick) {
        this.mOnBindItemClick = onBindItemClick;
    }

    @Override
    protected void onItemViewHolderCreated(BaseBindViewHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(BaseBindViewHolder holder, M m) {
        V binding = holder.getBinding();
        if (null == binding) {
            return;
        }
        // 分发item初始化
        m.convert(holder, binding);
        // 分发item的点击事件
        binding.setVariable(BR.itemClick, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null == mOnBindItemClick) {
                            m.onClick(v);
                        } else if (!m.onClick(v)) {
                            mOnBindItemClick.onBindItemClick(v, holder.getAdapterPosition(), m);
                        }
                    }
                }
        );
        // 分发item内部组件的点击事件
        binding.setVariable(BR.itemChildClick, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null == mOnBindItemChildClick) {
                            m.onChildClick(v);
                        } else if (!m.onChildClick(v)) {
                            mOnBindItemChildClick.onBindItemChildClick(v, holder.getAdapterPosition(), m);
                        }
                    }
                }
        );
    }

}