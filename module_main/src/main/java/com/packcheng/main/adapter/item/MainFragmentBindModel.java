package com.packcheng.main.adapter.item;

import com.packcheng.base.recyclerview.BaseBindModel;
import com.packcheng.base.recyclerview.BaseBindViewHolder;
import com.packcheng.main.databinding.MainItemMainBinding;
import com.packcheng.main.domain.MainFragmentItemBean;

/**
 * 首页列表Item布局数据封装类
 *
 * @author packcheng
 * @date 2020/9/5 16:35
 */
public class MainFragmentBindModel extends BaseBindModel<MainItemMainBinding> {
    private MainFragmentItemBean mItemBean;

    public MainFragmentBindModel(MainFragmentItemBean itemBean) {
        this.mItemBean = itemBean;
    }

    public MainFragmentItemBean getItemBean() {
        return mItemBean;
    }

    @Override
    public void convert(BaseBindViewHolder helper, MainItemMainBinding binding) {
        binding.tvTitle.setText(mItemBean.getItemName());
    }
}
