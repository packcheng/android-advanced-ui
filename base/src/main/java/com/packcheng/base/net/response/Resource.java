package com.packcheng.base.net.response;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 网络请求结果封装类
 *
 * @author packcheng
 * @date 2020-01-15$ 16:06$
 */
public class Resource<T> {

    /**
     * 网络请求状态，可用于控制界面的loading状态
     */
    private final @Status
    int status;
    private final T data;
    private final int code;
    private final String message;

    private Resource(@NonNull @Status int status, @Nullable T data,
                     int code,
                     @Nullable String message) {
        this.status = status;
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, 1, null);
    }

    public static <T> Resource<T> error(T data, int code, String msg) {
        return new Resource<>(ERROR, data, code, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, -1, null);
    }

    public static <T> Resource<T> completed() {
        return new Resource<>(COMPLETED, null, 0, null);
    }

    public boolean isSuccess() {

        return SUCCESS == status;
    }

    public boolean isError() {

        return ERROR == status;
    }

    public boolean isLoading() {

        return LOADING == status;
    }

    public @Status
    int getStatus() {

        return status;
    }

    public T getData() {

        return data;
    }

    public int getCode() {

        return code;
    }

    public String getMessage() {

        return message;
    }

    /**
     * 加载中
     */
    public static final int LOADING = 0;
    /**
     * 加载成功
     */
    public static final int SUCCESS = 1;
    /**
     * 加载失败
     */
    public static final int ERROR = 2;
    /**
     * 加载完成
     */
    public static final int COMPLETED = 3;

    @IntDef({LOADING, SUCCESS, ERROR, COMPLETED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }
}
