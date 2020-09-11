package com.packcheng.base.recyclerview.simple;

/**
 * 单标题列表Item数据封装类
 * @author packcheng
 * @date 2020/9/11 4:54 PM
 */
public class SimpleItemBean {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SimpleItemBean{" +
                "title='" + title + '\'' +
                '}';
    }


}
