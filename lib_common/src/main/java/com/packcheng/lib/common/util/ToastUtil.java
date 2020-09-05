package com.packcheng.lib.common.util;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.packcheng.lib.common.R;


/**
 * Toast工具类
 * Created by Administrator on 2017/5/16 0016.
 */

public class ToastUtil {

    protected static Toast mToast = null;
    protected static Toast mImageToast = null;

    private static Context mContext;
    private static String oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void init(Context context) {
        mContext = context;
    }

//    public static void toast(View view) {
//        Toast toast = new Toast(mContext);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(view);
//        toast.show();
//    }

    public static void toast(String s) {
        if (mToast == null) {
            mToast = getToast(s);
            mToast.show();
        } else {
            twoTime = System.currentTimeMillis();
            if (!TextUtils.isEmpty(s) && s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    mToast.show();
                }
            } else {
                oldMsg = s;
                mToast.setText(s);
                mToast.show();
            }
        }
        oneTime = twoTime;
    }

    public static void toast(String s, int imageResource) {
        if (mImageToast == null) {
            mImageToast = getToast(s, imageResource);
            mImageToast.show();
        } else {
            twoTime = System.currentTimeMillis();
            if (!TextUtils.isEmpty(s) && s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    mImageToast.show();
                }
            } else {
                oldMsg = s;
                mImageToast.setText(s);
                mImageToast.show();
            }
        }
        oneTime = twoTime;
    }

    public static void toast(int resId) {
        toast(mContext.getString(resId));
    }


    private static Toast getToast(String message) {
        Toast t = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);

//        Toast默认的底边距 <dimen name="toast_y_offset">64dip</dimen>
        t.setGravity(Gravity.BOTTOM, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, mContext.getResources().getDisplayMetrics()));
        LinearLayout view = (LinearLayout) t.getView();
        view.setBackgroundResource(R.drawable.shape_toast_bg);
        TextView tv = (TextView) view.findViewById(android.R.id.message);
        tv.setTextSize(18);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundColor(mContext.getResources().getColor(R.color.lucency));
        int hdp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, mContext.getResources().getDisplayMetrics());
        int vdp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, mContext.getResources().getDisplayMetrics());
        view.setPadding(hdp, vdp, hdp, vdp);
        view.setGravity(Gravity.CENTER);
        return t;
    }

    private static Toast getToast(String message, int imageResource) {
        Toast t = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout view = (LinearLayout) t.getView();
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        view.setBackgroundResource(R.drawable.shape_toast_bg);
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageResource);
        view.addView(imageView, 0);
        TextView tv = (TextView) view.findViewById(android.R.id.message);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundColor(mContext.getResources().getColor(R.color.lucency));
        tv.setTextSize(18);
        tv.setPadding(0, DensityUtil.dp2px(mContext, 20), 0, 0);
        tv.setGravity(Gravity.CENTER);
        int hdp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, mContext.getResources().getDisplayMetrics());
        int vdp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, mContext.getResources().getDisplayMetrics());
        view.setPadding(hdp, vdp, hdp, vdp);
        view.setGravity(Gravity.CENTER);
        return t;
    }

//    public static void showCustomToastCenter(Context context, String message, int drawableTop) {
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_custom_toast, null);
//        TextView tv = (TextView) view.findViewById(R.id.tv_integral);
//        tv.setText(message);
//        tv.setCompoundDrawablesWithIntrinsicBounds(0, drawableTop, 0, 0);
//        tv.setCompoundDrawablePadding(UIUtils.dpToPixel(context, 10));
//        if (customToast == null) {
//            customToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
//            customToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//        }
//        customToast.setView(view);
//        customToast.show();
//    }

}
