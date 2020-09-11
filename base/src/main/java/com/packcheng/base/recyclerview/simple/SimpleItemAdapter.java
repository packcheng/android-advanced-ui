package com.packcheng.base.recyclerview.simple;

import com.packcheng.base.R;
import com.packcheng.base.databinding.BaseItemSimpleBinding;
import com.packcheng.base.recyclerview.BaseBindAdapter;

/**
 * 简单类型列表数据适配器
 *
 * @author packcheng
 * @date 2020/9/11 5:03 PM
 */
public class SimpleItemAdapter extends BaseBindAdapter<SimpleBindModel, BaseItemSimpleBinding> {
    private SimpleItemAdapter(int layoutResId) {
        super(layoutResId);
    }

    public SimpleItemAdapter() {
        super(R.layout.base_item_simple);
    }
}
