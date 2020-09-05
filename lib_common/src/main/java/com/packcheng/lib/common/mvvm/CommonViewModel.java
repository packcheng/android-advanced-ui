package com.packcheng.lib.common.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.packcheng.lib.common.util.LogUtils;

/**
 * 默认ViewModel实现类
 *
 * @author packcheng
 * @date 2020-02-03 12:49
 */
public abstract class CommonViewModel<M extends CommonModel> extends AndroidViewModel implements IViewModel {
    protected String TAG = getClass().getSimpleName();
    protected M mModel;

    public CommonViewModel(@NonNull Application application, M model) {
        super(application);
        this.mModel = model;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtils.i(TAG + "-onCleared");

        if (null != mModel) {
            mModel.onCleared();
        }
    }
}
