package com.packcheng.main.adapter;

import com.packcheng.base.recyclerview.BaseBindAdapter;
import com.packcheng.main.adapter.item.MainFragmentBindModel;
import com.packcheng.main.databinding.MainItemMainBinding;

/**
 * 主模块首页列表数据适配器
 *
 * @author packcheng
 * @date 2020/9/5 16:33
 */
public class MainFragmentAdapter extends BaseBindAdapter<MainFragmentBindModel, MainItemMainBinding> {

    public MainFragmentAdapter(int layoutResId) {
        super(layoutResId);
    }
}
