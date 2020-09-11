package com.packcheng.layout;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.packcheng.base.component.BaseSupportFragment;
import com.packcheng.base.mvvm.DefaultViewModel;
import com.packcheng.base.mvvm.DefaultViewModelFactory;
import com.packcheng.base.recyclerview.simple.SimpleBindModel;
import com.packcheng.base.recyclerview.simple.SimpleItemAdapter;
import com.packcheng.base.recyclerview.simple.SimpleItemBean;
import com.packcheng.layout.databinding.LayoutFragmentMainBinding;
import com.packcheng.layout.ui.WaterfallFragment;
import com.packcheng.lib.common.util.LogUtils;

import java.util.ArrayList;

/**
 * 布局模块Fragment
 *
 * @author packcheng
 * @date 2020-02-04 13:42
 */
public class LayoutFragment extends BaseSupportFragment<LayoutFragmentMainBinding, DefaultViewModel> {

    private SimpleItemAdapter mAdapter;
    private ArrayList<SimpleBindModel> mDataList;

    public static LayoutFragment newInstance() {
        return new LayoutFragment();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.layout_fragment_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    /**
     * 初始化RecyclerView相关数据
     */
    private void initRecyclerView() {
        LogUtils.i(TAG, "initRecyclerView");
        mAdapter = new SimpleItemAdapter();
        mBinding.rvLayoutMain.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvLayoutMain.setAdapter(mAdapter);
        mAdapter.setOnBindItemClick((View v, int position, SimpleBindModel model) -> {
            LogUtils.i(TAG, "OnBindItemClick, position: " + position);
            switch (position) {
                case 0:
                    start(WaterfallFragment.newInstance());
                    break;
                default:
                    showTopTip(com.packcheng.lib.resource.R.string.function_unfinished);
                    break;
            }

        });
    }

    /**
     * 初始化列表数据
     */
    private void initRecyclerData() {
        LogUtils.i(TAG, "initRecyclerData");
        mDataList = new ArrayList<>();
        mDataList.add(new SimpleBindModel(new SimpleItemBean(getString(R.string.layout_function_waterfall))));
        mAdapter.setNewData(mDataList);
    }

    @Override
    protected void initData() {
        initRecyclerData();
    }

    @Override
    protected Class<DefaultViewModel> onBindViewModel() {
        return DefaultViewModel.class;
    }

    @Override
    protected ViewModelProvider.Factory onBindViewModelFactory() {
        return DefaultViewModelFactory.getInstance(mHostActivity.getApplication());
    }
}
