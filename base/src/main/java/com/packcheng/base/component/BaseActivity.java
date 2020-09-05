package com.packcheng.base.component;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.jaeger.library.StatusBarUtil;
import com.packcheng.base.R;
import com.packcheng.base.mvvm.BaseViewModel;
import com.packcheng.base.mvvm.IBaseView;
import com.packcheng.lib.common.component.CommonActivity;
import com.packcheng.lib.common.util.DensityUtil;
import com.packcheng.lib.common.util.LogUtils;
import com.packcheng.lib.common.util.ScreenUtil;
import com.packcheng.lib.common.widget.StatusBarView;

/**
 * 项目Activity的基类
 *
 * @author packcheng
 * @date 2020-02-03 13:50
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends CommonActivity implements IBaseView {
    /**
     * TopTip显示时长
     */
    private long mTopTipTimeMillis = 3 * 1000;

    private Handler mTopTipHandler = new Handler();

    protected V mBinding;
    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentView() != null) {
            mBinding = DataBindingUtil.bind(getContentView());
        } else if (getContentViewLayoutID() != 0) {
            mBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(this), getContentViewLayoutID(), null, false);
        } else {
            throw new IllegalStateException(getString(R.string.error_layout_not_found));
        }

        if (mBinding != null) {
            setContentView(mBinding.getRoot());
        }

        initView(savedInstanceState);
        initViewModel();
        initData();
    }

    /**
     * 初始化ViewModel
     */
    private void initViewModel() {
        mViewModel = createViewModel();
    }

    /**
     * 创建ViewModel
     *
     * @return
     */
    protected VM createViewModel() {
        return ViewModelProviders.of(this, onBindViewModelFactory()).get(onBindViewModel());
    }

    protected void overrideEnterAnim() {
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    protected void overrideExitAnim() {
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public void finish() {
        super.finish();
        overrideExitAnim();
    }

    @Override
    public void showTopTip(String msg) {
        LogUtils.d(TAG + " - showTopTip");

        Window window = getWindow();

        LinearLayout linearLayout = new LinearLayout(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.red));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setId(View.generateViewId());

        StatusBarView statusBarView = new StatusBarView(this);
        linearLayout.addView(statusBarView);
        LinearLayout.LayoutParams lpStatusBarView = (LinearLayout.LayoutParams) statusBarView.getLayoutParams();
        lpStatusBarView.width = LinearLayout.LayoutParams.MATCH_PARENT;
        lpStatusBarView.height = ScreenUtil.getStatusHeight(this);
        statusBarView.setLayoutParams(lpStatusBarView);

        TextView textView = new TextView(this);
        linearLayout.addView(textView);
        LinearLayout.LayoutParams lpTextView = (LinearLayout.LayoutParams) textView.getLayoutParams();
        lpTextView.width = LinearLayout.LayoutParams.MATCH_PARENT;
        lpTextView.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        textView.setPadding(DensityUtil.dp2px(this, 13), 0,
                0, DensityUtil.dp2px(this, 5));
        textView.setLayoutParams(lpTextView);
        textView.setMinHeight(DensityUtil.dp2px(this, 60) - ScreenUtil.getStatusHeight(this));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(msg);
        textView.setLines(2);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        ((FrameLayout) (window.getDecorView()).findViewById(android.R.id.content)).addView(linearLayout);

        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        ObjectAnimator objectAnimator = ObjectAnimator
                                .ofFloat(linearLayout, "translationY",
                                        -linearLayout.getMeasuredHeight(), 0);
                        objectAnimator.setDuration(300);
                        objectAnimator.setInterpolator(new LinearInterpolator());
                        objectAnimator.start();

                        mTopTipHandler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                ObjectAnimator objectAnimator = ObjectAnimator
                                        .ofFloat(linearLayout, "translationY",
                                                0, -linearLayout.getMeasuredHeight());
                                objectAnimator.setDuration(300);
                                objectAnimator.setInterpolator(new LinearInterpolator());
                                objectAnimator.addListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        ((FrameLayout) (window.getDecorView())
                                                .findViewById(android.R.id.content))
                                                .removeView(linearLayout);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {
                                        ((FrameLayout) (window.getDecorView())
                                                .findViewById(android.R.id.content))
                                                .removeView(linearLayout);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });
                                objectAnimator.start();
                            }
                        }, mTopTipTimeMillis);

                        linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }

    @Override
    public void showTopTip(int msgId) {
        LogUtils.d(TAG + " - showTopTip");
        showTopTip(getString(msgId));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mTopTipHandler) {
            mTopTipHandler.removeCallbacksAndMessages(null);
            mTopTipHandler = null;
        }

        if (null != mBinding) {
            mBinding.unbind();
        }
    }

    /**
     * 设置TopTip默认显示时长
     *
     * @param topTipTimeMillis TopTip显示时长，单位：ms
     */
    public void setTopTipTimeMillis(long topTipTimeMillis) {
        this.mTopTipTimeMillis = topTipTimeMillis;
    }

    /**
     * 设置状态栏字体颜色
     *
     * @param lightMode 浅色模式，状态栏字体为白色
     */
    public void setStatusBarLightMode(boolean lightMode) {
        if (lightMode) {
            StatusBarUtil.setLightMode(this);
        } else {
            StatusBarUtil.setDarkMode(this);
        }
    }

    /**
     * 设置页面视图
     *
     * @return 页面视图
     */
    protected View getContentView() {
        return null;
    }

    /**
     * 页面布局文件ID
     *
     * @return
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 初始化页面组件
     *
     * @param savedInstanceState
     */
    @CallSuper
    protected void initView(Bundle savedInstanceState) {
        overrideEnterAnim();
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

        setStatusBarLightMode(true);
    }

    /**
     * 初始化页面数据
     */
    protected abstract void initData();

    /**
     * 绑定ViewModel
     *
     * @return
     */
    protected abstract Class<VM> onBindViewModel();

    /**
     * 绑定ViewModelFactory
     *
     * @return
     */
    protected abstract ViewModelProvider.Factory onBindViewModelFactory();
}
