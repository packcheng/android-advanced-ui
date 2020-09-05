package com.packcheng.main.domain;

import android.text.TextUtils;

import androidx.annotation.IntDef;

import com.packcheng.lib.common.util.LogUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 首页列表Item数据封装类
 *
 * @author packcheng
 * @date 2020/9/5 16:36
 */
public class MainFragmentItemBean {
    /**
     * 布局类型定义
     */
    public static final int ITEM_TYPE_UNDEFINE = -1;
    public static final int ITEM_TYPE_UI_LAYOUT = 0;
    public static final int ITEM_TYPE_UI_DRAW = 1;

    private MainFragmentItemBean(Builder builder) {
        setItemName(builder.itemName);
        setItemType(builder.itemType);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ITEM_TYPE_UI_LAYOUT, ITEM_TYPE_UI_DRAW})
    public @interface MainItemType {
    }

    private String itemName;
    private @MainItemType
    int itemType;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    /**
     * {@code MainFragmentItemBean} builder static inner class.
     */
    public static final class Builder {
        private String itemName;
        private int itemType;

        public Builder() {
        }

        /**
         * Sets the {@code itemName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param itemName the {@code itemName} to set
         * @return a reference to this Builder
         */
        public Builder withItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        /**
         * Sets the {@code itemType} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param itemType the {@code itemType} to set
         * @return a reference to this Builder
         */
        public Builder withItemType(@MainItemType int itemType) {
            this.itemType = itemType;
            return this;
        }

        /**
         * Returns a {@code MainFragmentItemBean} built from the parameters previously set.
         *
         * @return a {@code MainFragmentItemBean} built with parameters of this {@code MainFragmentItemBean.Builder}
         */
        public MainFragmentItemBean build() {
            return new MainFragmentItemBean(this);
        }
    }

    /**
     * 根据名字获取Item的类型
     *
     * @param itemName item的名字
     * @return item 的具体类型
     */
    public static int parseItemTypeByName(String itemName) {
        LogUtils.i("parseItemTypeByName, itemName: " + itemName);
        if (TextUtils.isEmpty(itemName)) {
            return ITEM_TYPE_UNDEFINE;
        }

        int itemType;
        switch (itemName) {
            case "UI布局测试":
                itemType = ITEM_TYPE_UI_LAYOUT;
                break;
            case "UI绘制流程测试":
                itemType = ITEM_TYPE_UI_DRAW;
                break;
            default:
                itemType = ITEM_TYPE_UNDEFINE;
                break;
        }
        return itemType;
    }
}
