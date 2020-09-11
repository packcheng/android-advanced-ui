package com.packcheng.base.recyclerview.multiple;

import android.content.Context;
import android.view.View;

import com.packcheng.base.R;
import com.packcheng.base.databinding.BaseItemMultipleOneBinding;
import com.packcheng.base.recyclerview.BaseBindMultiModel;
import com.packcheng.base.recyclerview.BaseBindViewHolder;

/**
 * 多类型列表item数据布局数据封装类-Title+Content
 *
 * @author packcheng
 * @date 2020/9/11 5:50 PM
 */
public class MultipleOneBindModel extends BaseBindMultiModel<BaseItemMultipleOneBinding> {

    private Context mContext;
    private MultipleItemBean mItemBean;

    public MultipleOneBindModel(Context mContext, MultipleItemBean mItemBean) {
        this.mContext = mContext;
        this.mItemBean = mItemBean;
    }

    @Override
    public int getLayoutId() {
        return R.layout.base_item_multiple_one;
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
    public void convert(BaseBindViewHolder helper, BaseItemMultipleOneBinding binding) {
        binding.tvTitle.setText(mItemBean.getTitle());
        binding.tvContent.setText(mItemBean.getContent());
    }
}
