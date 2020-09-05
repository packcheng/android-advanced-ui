package com.packcheng.base.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.packcheng.lib.common.mvvm.CommonViewModel;

/**
 * 项目默认ViewModel实现类
 *
 * @author packcheng
 * @date 2020-02-03 14:01
 */
public abstract class BaseViewModel<M extends BaseModel> extends CommonViewModel<M>
        implements IBaseViewModel {
    public BaseViewModel(@NonNull Application application, M model) {
        super(application, model);
    }
}
