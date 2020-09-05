package com.packcheng.base.mvvm;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * 默认ViewModelFactory,不需要ViewModelFactory的页面泛型
 *
 * @author packcheng
 * @date 2020-02-06 13:32
 */
public class DefaultViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile DefaultViewModelFactory INSTANCE;
    private final Application mApplication;

    public static DefaultViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (DefaultViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DefaultViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private DefaultViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DefaultViewModel.class)) {
            return (T) new DefaultViewModel(mApplication, new DefaultModel(mApplication));
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
