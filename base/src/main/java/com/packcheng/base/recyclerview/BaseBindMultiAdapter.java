package com.packcheng.base.recyclerview;

import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;

/**
 * RecyclerView多类型item的适配器基类
 * 创建时无需传布局id,通过{@link IBindModel#getLayoutId()}设置
 *
 * @author packcheng
 * @date 2020-01-17 14:53
 */
public class BaseBindMultiAdapter<M extends IBindModel, V extends ViewDataBinding>
        extends BaseBindAdapter<M, V> {
    private final String TAG = getClass().getSimpleName();

    public BaseBindMultiAdapter() {
        super(0);
    }

    @Override
    public int getItemViewType(int position) {
        int dataSize = getData().size();
        int headers = getHeaderLayoutCount();
        if (position >= headers && position < headers + dataSize) {
            // 数据区域，修改viewType
            return position;
        }
        return super.getItemViewType(position);
    }

    @Override
    protected BaseBindViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        int dataSize = getData().size();
        int headers = getHeaderLayoutCount();
        if (viewType >= headers && viewType < headers + dataSize) {
            // 数据区域，根据Model的布局ID创建ViewHolder
            return createBaseViewHolder(parent, getData().get(viewType - headers).getLayoutId());
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

}
