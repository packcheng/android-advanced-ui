package com.packcheng.base.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.packcheng.base.component.BaseSupportFragment;

/**
 * The provider of module_layout
 *
 * @author packcheng
 * @date 2020-02-04 16:39
 */
public interface ILayoutProvider extends IProvider {
    /**
     * get the Fragment of layout's page
     *
     * @return
     */
    BaseSupportFragment getLayoutFragment();

    /**
     * 打开LayoutActivity
     *
     * @param context
     */
    void startLayoutActivity(Context context);
}
