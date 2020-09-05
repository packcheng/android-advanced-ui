package com.packcheng.layout.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.packcheng.base.component.BaseSupportFragment;
import com.packcheng.base.provider.ARouterConstant;
import com.packcheng.base.provider.ILayoutProvider;
import com.packcheng.layout.LayoutActivity;
import com.packcheng.layout.LayoutFragment;

/**
 * The provider of module_layout
 *
 * @author packcheng
 * @date 2020-02-04 16:41
 */
@Route(path = ARouterConstant.ROUTER_PATH_PROVIDER_LAYOUT, name = "Layout")
public class LayoutProvider implements ILayoutProvider {

    @Override
    public void init(Context context) {
    }

    @Override
    public BaseSupportFragment getLayoutFragment() {
        return LayoutFragment.newInstance();
    }

    @Override
    public void startLayoutActivity(Context context) {
        LayoutActivity.start(context);
    }
}
