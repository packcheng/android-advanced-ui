package com.packcheng.base.recyclerview.multiple;

/**
 * @author packcheng
 * @date 2020/9/11 5:49 PM
 */
public class MultipleItemBean {
    private String title;
    private String content;

    public MultipleItemBean() {
    }

    public MultipleItemBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MultipleItemBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
