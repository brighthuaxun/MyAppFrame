package com.example.bowen.myappframe.protocol.network;


import com.example.bowen.myappframe.application.BaseApplication;
import com.example.bowen.myappframe.protocol.ApiClient;
import com.example.bowen.myappframe.util.ZaUtil;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dongbowen on 2016/6/26.
 * 使用retrofit创建ApiClient实例
 */
public class ApiFactory {

    private static ApiClient apiClient;

    public static ApiClient getApiClientInstance(){
        if(apiClient == null) {
            synchronized (ApiFactory.class) {
                if(apiClient == null) {
                    //添加body日志打印
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    //web缓存, 10M
                    Cache cache = new Cache(new File(ZaUtil.getSavePath(BaseApplication.getInstance(), "okHttpCache")), 1024 * 1024 * 10);
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            //设置一个自动管理cookies的管理器
                            .cookieJar(new CookiesManager())
                            .addInterceptor(httpLoggingInterceptor)
                            .addNetworkInterceptor(new HeaderInterceptor())
                            //设置请求读写的超时时间
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .cache(cache)
                            .build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ApiClient.HOST_HTTP)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(okHttpClient)
                            .build();

                    apiClient = retrofit.create(ApiClient.class);
                }
            }
        }
        return apiClient;
    }

    /**
     * 自动管理Cookies
     */
    private static class CookiesManager implements CookieJar {

        private final PersistentCookieStore cookieStore = new PersistentCookieStore(BaseApplication.getInstance());

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies;
        }
    }

}
