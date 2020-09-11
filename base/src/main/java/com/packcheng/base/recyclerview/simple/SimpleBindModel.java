package com.packcheng.base.recyclerview.simple;

import com.packcheng.base.databinding.BaseItemSimpleBinding;
import com.packcheng.base.recyclerview.BaseBindModel;
import com.packcheng.base.recyclerview.BaseBindViewHolder;

/**
 * 简单文本列表Item数据布局封装类
 *
 * @author packcheng
 * @date 2020/9/11 5:00 PM
 */
public class SimpleBindModel extends BaseBindModel<BaseItemSimpleBinding> {
    private SimpleItemBean mSimpleItemBean;

    @Override
    public void convert(BaseBindViewHolder helper, BaseItemSimpleBinding binding) {
        binding.tvTitle.setText(mSimpleItemBean.getTitle());
    }

    public SimpleBindModel(SimpleItemBean simpleItemBean) {
        this.mSimpleItemBean = simpleItemBean;
    }

    public SimpleItemBean getSimpleItemBean() {
        return mSimpleItemBean;
    }
}
