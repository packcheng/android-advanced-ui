package com.packcheng.layout.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.packcheng.base.component.BaseSwipeBackFragment;
import com.packcheng.base.mvvm.DefaultViewModel;
import com.packcheng.base.mvvm.DefaultViewModelFactory;
import com.packcheng.layout.R;
import com.packcheng.layout.databinding.LayoutFragmentWaterfallBinding;
import com.packcheng.layout.widget.WaterFlowLayout;
import com.packcheng.lib.common.util.LogUtils;

import java.util.Random;

/**
 * 自定义流布局测试页
 *
 * @author packcheng
 * @date 2020/9/11 5:10 PM
 */
public class WaterfallFragment extends BaseSwipeBackFragment<LayoutFragmentWaterfallBinding, DefaultViewModel> {

    public static WaterfallFragment newInstance() {
        return new WaterfallFragment();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.layout_fragment_waterfall;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        addWaterFlowLayout();
    }

    /**
     * 通过代码的方式添加自定义流式布局
     */
    private void addWaterFlowLayout() {
        LogUtils.i(TAG, "addWaterFlowLayout");
        WaterFlowLayout waterFlowLayout = new WaterFlowLayout(getContext());
        TextView child;
        StringBuilder sb = new StringBuilder();
        Random colorRandom = new Random(255);
        for (int i = 0; i < 20; i++) {
            child = new TextView(getContext());
            sb.append("child: ");
            sb.append(i);
            child.setText(sb.toString());
            sb.delete(0, sb.length());

            child.setBackgroundColor(Color.rgb(colorRandom.nextInt(), colorRandom.nextInt(), colorRandom.nextInt()));
            child.setTextColor(Color.WHITE);
            child.setPadding(20, 10, 20, 10);
            waterFlowLayout.addView(child);
        }
        mBinding.llRoot.addView(waterFlowLayout);
    }

    @Override
    protected void initData() {

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
