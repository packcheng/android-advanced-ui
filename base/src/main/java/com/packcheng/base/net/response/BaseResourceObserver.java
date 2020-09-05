package com.packcheng.base.net.response;

import com.packcheng.base.net.response.Resource;

/**
 * LiveData网络请求结果处理类
 *
 * @author packcheng
 * @date 2020-01-15$ 16:58$
 */
public abstract class BaseResourceObserver<T> implements androidx.lifecycle.Observer<Resource<T>> {

    @Override
    public void onChanged(Resource<T> resource) {
        dispatchResult(resource);
    }

    /**
     * 分发网络请求结果
     *
     * @param resource 网络请求结果
     */
    private void dispatchResult(Resource<T> resource) {
        switch (resource.getStatus()) {
            case Resource.LOADING:
                onLoading(resource.getData(), resource.getCode(), resource.getMessage());
                break;
            case Resource.SUCCESS:
                onSuccess(resource.getData(), resource.getCode(), resource.getMessage());
                break;
            case Resource.ERROR:
                onError(resource.getData(), resource.getCode(), resource.getMessage());
                break;
            case Resource.COMPLETED:
                onCompleted(resource.getData(), resource.getCode(), resource.getMessage());
                break;
            default:
                throw new IllegalArgumentException("resource's status is illegal");
        }
    }

    /**
     * 网络请求前回调
     * 通常用于显示加载中对话框
     *
     * @param data
     * @param code
     * @param message
     */
    public abstract void onLoading(T data, int code, String message);

    /**
     * 网络请求成功回调
     *
     * @param data
     * @param code
     * @param message
     */
    public abstract void onSuccess(T data, int code, String message);

    /**
     * 网络请求失败回调
     *
     * @param data
     * @param code
     * @param message
     */
    public abstract void onError(T data, int code, String message);

    /**
     * 网络请求结束后回调
     * 通常用于隐藏加载中对话框
     *
     * @param data
     * @param code
     * @param message
     */
    public abstract void onCompleted(T data, int code, String message);
}
