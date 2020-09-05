package com.packcheng.lib.common.component;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.packcheng.lib.common.component.callback.OnLoadingTimeOut;
import com.packcheng.lib.common.mvvm.IView;
import com.packcheng.lib.common.util.ActivityController;
import com.packcheng.lib.common.util.LogUtils;

/**
 * 默认Fragment基类
 *
 * @author packcheng
 * @date 2020-02-03 12:59
 */
public class CommonFragment extends Fragment implements IView {
    protected String TAG = getClass().getSimpleName();

    /**
     * Fragment的宿主Activity
     */
    protected CommonActivity mHostActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityController.getInstance().addFragment(this);
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG + " - onCreate");
        LogUtils.i("Location", "Fragment Location: " + TAG);
    }

    @Override
    public void onDestroyView() {
        ActivityController.getInstance().removeFragment(this);
        super.onDestroyView();
        LogUtils.d(TAG + " - onDestroyView");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LogUtils.d(TAG + " - onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG + " - onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG + " - onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG + " - onDestroy");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.d(TAG + " - onHiddenChanged = " + hidden);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d(TAG + " - onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.d(TAG + " - onStop");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d(TAG + " - onActivityCreated");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        LogUtils.d(TAG + " - onAttach");
        Activity hostActivity = getActivity();
        if (hostActivity instanceof CommonActivity) {
            mHostActivity = (CommonActivity) hostActivity;
        } else {
            throw new IllegalArgumentException("The host Activity must be CommonActivity");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.d(TAG + " - onDetach");
    }


    @Override
    public void showLoading(boolean isCancelable) {
        LogUtils.d(TAG + " - showLoading");

        if (this.isAdded()) {
            mHostActivity.showLoading(isCancelable);
        }
    }

    @Override
    public void hideLoading() {
        LogUtils.d(TAG + " - hideLoading");
        mHostActivity.hideLoading();
    }

    @Override
    public Dialog provideProgressDialog() {
        LogUtils.d(TAG + " - provideProgressDialog");
        return mHostActivity.provideProgressDialog();
    }

    @Override
    public void showTimeOutLoading(long milliTime, OnLoadingTimeOut callback) {
        LogUtils.d(TAG + " - showTimeOutLoading");
        if (isAdded()) {
            mHostActivity.showTimeOutLoading(milliTime, callback);
        }
    }

    @Override
    public void hindTimeOutLoading() {
        LogUtils.d(TAG + " - hindTimeOutLoading");
        mHostActivity.hindTimeOutLoading();
    }

    @Override
    public void showTip(String msg) {
        LogUtils.d(TAG + " - showTip");
        if (this.isAdded()) {
            mHostActivity.showTip(msg);
        }
    }

    @Override
    public void showTip(int msgId) {
        LogUtils.d(TAG + " - showTip");
        if (this.isAdded()) {
            mHostActivity.showTip(msgId);
        }
    }

    @Override
    public void showTip(String msg, int imageUrl) {
        LogUtils.d(TAG + " - showTip");
        if (this.isAdded()) {
            mHostActivity.showTip(msg, imageUrl);
        }
    }

    @Override
    public Context getContext() {
        LogUtils.d(TAG + " - getContext");
        return mHostActivity;
    }
}
