package com.packcheng.base.recyclerview.multiple;

import android.content.Context;
import android.view.View;

import com.packcheng.base.R;
import com.packcheng.base.databinding.BaseItemMultipleTwoBinding;
import com.packcheng.base.recyclerview.BaseBindMultiModel;
import com.packcheng.base.recyclerview.BaseBindViewHolder;

/**
 * 多类型列表item数据布局数据封装类-单Title
 *
 * @author packcheng
 * @date 2020/9/11 5:50 PM
 */
public class MultipleTwoBindModel extends BaseBindMultiModel<BaseItemMultipleTwoBinding> {

    private Context mContext;
    private MultipleItemBean mItemBean;

    public MultipleTwoBindModel(Context mContext, MultipleItemBean mItemBean) {
        this.mContext = mContext;
        this.mItemBean = mItemBean;
    }

    @Override
    public int getLayoutId() {
        return R.layout.base_item_multiple_two;
    }

    @Override
    public boolean onClick(View view) {
        return false;
    }

    @Override
    public boolean onChildClick(View view) {
        return false;
    }

    @Override
    public void convert(BaseBindViewHolder helper, BaseItemMultipleTwoBinding binding) {
        binding.tvTitle.setText(mItemBean.getTitle());
    }
}
