package com.github.welcomeworld.bangumi.instrumentality.project.source.bili;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.CookieInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.UserAgentInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.WbiSortAndSignInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class BiliOkHttpClientManager {
    static BiliOkHttpClientManager mInstance;
    private OkHttpClient normalClient;

    private OkHttpClient wbiClient;

    public static BiliOkHttpClientManager getInstance() {
        if (mInstance == null) {
            mInstance = new BiliOkHttpClientManager();
        }
        return mInstance;
    }

    public OkHttpClient getNormalOkHttpClient() {
        if (normalClient == null) {
            normalClient = new OkHttpClient.Builder()
                    .addInterceptor(new UserAgentInterceptor())
                    .addInterceptor(new CookieInterceptor())
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return normalClient;
    }

    public OkHttpClient getWbiOkHttpClient() {
        if (wbiClient == null) {
            wbiClient = new OkHttpClient.Builder()
                    .addInterceptor(new UserAgentInterceptor())
                    .addInterceptor(new CookieInterceptor())
                    .addInterceptor(new WbiSortAndSignInterceptor())
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return wbiClient;
    }

}
