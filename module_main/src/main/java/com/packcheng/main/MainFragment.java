package com.packcheng.main;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.packcheng.base.component.BaseSupportFragment;
import com.packcheng.base.mvvm.DefaultViewModel;
import com.packcheng.base.mvvm.DefaultViewModelFactory;
import com.packcheng.base.provider.ARouterConstant;
import com.packcheng.base.provider.ILayoutProvider;
import com.packcheng.base.recyclerview.OnBindItemClick;
import com.packcheng.lib.common.util.LogUtils;
import com.packcheng.main.adapter.MainFragmentAdapter;
import com.packcheng.main.adapter.item.MainFragmentBindModel;
import com.packcheng.main.databinding.MainFragmentMainBinding;
import com.packcheng.main.domain.MainFragmentItemBean;

import java.util.ArrayList;

/**
 * 主Fragment
 *
 * @author packcheng
 * @date 2020-02-04 13:42
 */
public class MainFragment extends BaseSupportFragment<MainFragmentMainBinding, DefaultViewModel>
        implements OnBindItemClick<MainFragmentBindModel> {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Autowired(name = ARouterConstant.ROUTER_PATH_PROVIDER_LAYOUT)
    ILayoutProvider mLayoutProvider;

    private MainFragmentAdapter mAdapter;
    private ArrayList<MainFragmentBindModel> mDataList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.main_fragment_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);

        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        LogUtils.i(TAG, "initRecyclerView");
        mBinding.rvMainContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MainFragmentAdapter(R.layout.main_item_main);
        mBinding.rvMainContent.setAdapter(mAdapter);
        mAdapter.setOnBindItemClick(this);
    }

    @Override
    protected void initData() {
        mDataList = new ArrayList<>();
        String[] functionListArr = getResources().getStringArray(R.array.main_function_list);
        MainFragmentBindModel itemModel;
        for (String function : functionListArr) {
            itemModel = new MainFragmentBindModel(
                    new MainFragmentItemBean
                            .Builder()
                            .withItemName(function)
                            .withItemType(MainFragmentItemBean.parseItemTypeByName(function))
                            .build());
            mDataList.add(itemModel);
        }
        mAdapter.setNewData(mDataList);
    }

    @Override
    protected Class<DefaultViewModel> onBindViewModel() {
        return DefaultViewModel.class;
    }

    @Override
    protected ViewModelProvider.Factory onBindViewModelFactory() {
        return DefaultViewModelFactory.getInstance(mHostActivity.getApplication());
    }

    @Override
    public void onBindItemClick(View v, int position, MainFragmentBindModel model) {
        LogUtils.i(TAG, "setOnBindItemClick, position: " + position);
        int itemType = model.getItemBean().getItemType();
        switch (itemType) {
            case MainFragmentItemBean.ITEM_TYPE_UI_LAYOUT:
                if (isLayoutModuleReady()) {
                    start(mLayoutProvider.getLayoutFragment());
                }
                break;
            case MainFragmentItemBean.ITEM_TYPE_UI_DRAW:
            default:
                showTopTip(R.string.main_item_type_undefine);
                break;
        }
    }

    /**
     * 是否已成功加载module_layout模块
     *
     * @return true: 已加载
     */
    private boolean isLayoutModuleReady() {
        if (null == mLayoutProvider) {
            LogUtils.e(TAG, "Unable to load module_layout yet.");
            return false;
        }
        LogUtils.d(TAG, "Loaded module_layout success.");
        return true;
    }
}
