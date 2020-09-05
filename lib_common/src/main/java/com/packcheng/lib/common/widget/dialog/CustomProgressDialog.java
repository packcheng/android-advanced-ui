package com.packcheng.lib.common.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.packcheng.lib.common.R;


/**
 * 默认加载中对话框
 *
 * @author packcheng
 * @date 2020-02-03 18:24
 */
public class CustomProgressDialog extends ProgressDialog {
    private boolean canCancel;


    public CustomProgressDialog(Context context, boolean canCancel, int style) {
        super(context, style);
        this.canCancel = canCancel;
    }

    public CustomProgressDialog(Context context, boolean canCancel) {
        super(context, R.style.CustomDialog);
        this.canCancel = canCancel;
    }

    public CustomProgressDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.canCancel = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(true);
        setCanceledOnTouchOutside(canCancel);
        setContentView(R.layout.dialog_custom_progress);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

}
