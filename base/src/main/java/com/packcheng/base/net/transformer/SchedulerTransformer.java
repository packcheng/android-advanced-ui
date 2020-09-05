package com.packcheng.base.net.transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava线程切换转换器
 *
 * @author packcheng
 * @date 2020-01-17$ 17:15$
 */
public class SchedulerTransformer {

    /**
     * 订阅在io线程，响应在主线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> applyIo2Main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
