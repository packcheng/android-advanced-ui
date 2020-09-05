package com.packcheng.lib.common.component;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.packcheng.lib.common.component.callback.OnLoadingTimeOut;
import com.packcheng.lib.common.mvvm.IView;
import com.packcheng.lib.common.util.ActivityController;
import com.packcheng.lib.common.util.LogUtils;
import com.packcheng.lib.common.util.ToastUtil;
import com.packcheng.lib.common.widget.dialog.CustomProgressDialog;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 默认Activity基类
 *
 * @author packcheng
 * @date 2020-02-03 12:53
 */
public class CommonActivity extends AppCompatActivity implements IView {
    protected String TAG = getClass().getSimpleName();

    private Dialog mProgressDialog;

    private Runnable mLoadingTimeOutRunnable;
    private Handler mLoadingTimeOutHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityController.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG + " - onCreate");
        LogUtils.i("Location", "Activity Location: " + TAG);
    }

    @Override
    protected void onDestroy() {
        ActivityController.getInstance().removeActivity(this);
        super.onDestroy();
        LogUtils.d(TAG + " - onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d(TAG + " - onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG + " - onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d(TAG + " - onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d(TAG + " - onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d(TAG + " - onRestart");
    }


    @Override
    public void showLoading(boolean isCancelable) {
        LogUtils.d(TAG + " - showLoading");
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }

        mProgressDialog = provideProgressDialog();
        if (!this.isFinishing() && !this.isDestroyed()
                && mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.setCancelable(isCancelable);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        LogUtils.d(TAG + " - hideLoading");
        if (!this.isFinishing() && !this.isDestroyed()
                && mProgressDialog != null && mProgressDialog.isShowing()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        if (!CommonActivity.this.isFinishing() && !CommonActivity.this.isDestroyed()
                                && mProgressDialog != null && mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    });
                }
            }, 0);

        }
    }

    @Override
    public Dialog provideProgressDialog() {
        LogUtils.d(TAG + " - provideProgressDialog");
        return new CustomProgressDialog(this);
    }

    @Override
    public void showTimeOutLoading(long milliTime, OnLoadingTimeOut callback) {
        LogUtils.d(TAG + " - showTimeOutLoading");
        hindTimeOutLoading();
        mLoadingTimeOutRunnable = new Runnable() {
            @Override
            public void run() {
                hindTimeOutLoading();
                LogUtils.d(TAG + " - showTimeOutLoading - TimeOut");
                if (callback != null) {
                    callback.onLoadingTimeOut();
                }
            }
        };
        showLoading(false);
        mLoadingTimeOutHandler.postDelayed(mLoadingTimeOutRunnable, milliTime);
    }

    @Override
    public void hindTimeOutLoading() {
        LogUtils.d(TAG + " - hindTimeOutLoading");
        mLoadingTimeOutHandler.removeCallbacksAndMessages(null);
        mLoadingTimeOutRunnable = null;
        hideLoading();
    }

    @Override
    public void showTip(String msg) {
        LogUtils.d(TAG + " - showTip");
        ToastUtil.toast(msg);
    }

    @Override
    public void showTip(int msgId) {
        LogUtils.d(TAG + " - showTip");
        ToastUtil.toast(msgId);
    }

    @Override
    public void showTip(String msg, int imageUrl) {
        LogUtils.d(TAG + " - showTip");
        ToastUtil.toast(msg, imageUrl);
    }

    @Override
    public Context getContext() {
        LogUtils.d(TAG + " - getContext");
        return this;
    }
}
