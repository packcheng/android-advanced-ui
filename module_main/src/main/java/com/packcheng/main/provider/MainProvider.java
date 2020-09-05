package com.packcheng.main.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.packcheng.base.component.BaseSupportFragment;
import com.packcheng.base.provider.ARouterConstant;
import com.packcheng.base.provider.IMainProvider;
import com.packcheng.main.MainActivity;
import com.packcheng.main.MainFragment;

/**
 * The provider of module_main
 *
 * @author packcheng
 * @date 2020-02-04 16:41
 */
@Route(path = ARouterConstant.ROUTER_PATH_PROVIDER_MAIN, name = "Main")
public class MainProvider implements IMainProvider {

    @Override
    public void init(Context context) {
    }


    @Override
    public BaseSupportFragment getMainFragment() {
        return MainFragment.newInstance();
    }

    @Override
    public void startMainActivity(Context context) {
        MainActivity.start(context);
    }
}
