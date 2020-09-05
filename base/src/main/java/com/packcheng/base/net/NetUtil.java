package com.packcheng.base.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类
 *
 * @author packcheng
 * @date 2020-01-15$ 18:05$
 */
public class NetUtil {

    /**
     * 连接超时时长ms
     */
    private final long TIMEOUT_CONNECT = 20 * 1000;
    /**
     * 读取时长ms
     */
    private final long TIMEOUT_READ = 30 * 1000;

    private static String mBaseUrl = "http://www.baidu.com";

    private static Retrofit mRetrofit;
    private static OkHttpClient mClient;

    private static volatile NetUtil instance;

    private NetUtil() {
    }

    public static NetUtil getInstance() {
        if (null == instance) {
            synchronized (NetUtil.class) {
                if (null == instance) {
                    instance = new NetUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化Retrofit和OkHttpClient
     */
    public void init() {
        initOkHttpClient();
        initRetrofit();
    }

    private void initOkHttpClient() {
        mClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.MILLISECONDS)
                .build();
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 创建网络请求接口
     *
     * @param clazz
     * @param <T>
     * @return 网络请求接口
     */
    public  <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
