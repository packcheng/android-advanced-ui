package com.packcheng.base.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;

/**
 * 默认ViewModel, 不需要ViewModel的页面泛型
 *
 * @author packcheng
 * @date 2020-02-06 13:31
 */
public class DefaultViewModel extends BaseViewModel<DefaultModel> {
    public DefaultViewModel(@NonNull Application application, DefaultModel model) {
        super(application, model);
    }
}
