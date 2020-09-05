package com.packcheng.base.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * The provider of module_main
 *
 * @author packcheng
 * @date 2020-02-04 16:39
 */
public interface IMainProvider extends IProvider {
    /**
     * get the Fragment of main's page
     *
     * @return
     */
    Fragment getMainFragment();

    /**
     * 打开MainActivity
     *
     * @param context
     */
    void startMainActivity(Context context);
}
