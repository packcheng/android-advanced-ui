package com.packcheng.base.mvvm;

import android.app.Application;

import com.packcheng.lib.common.mvvm.CommonModel;

/**
 * 项目默认Model实现类
 *
 * @author packcheng
 * @date 2020-02-03 14:02
 */
public abstract class BaseModel extends CommonModel implements IBaseModel {

    public BaseModel(Application application) {
        super(application);
    }
}
