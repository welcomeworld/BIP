package com.github.welcomeworld.bangumi.instrumentality.project.source.bili;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.BiliFixedHeaderInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.BiliFixedParameterInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.BiliSortAndSignInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.CookieInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.UserAgentInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class BiliOkHttpClientManager {
    static BiliOkHttpClientManager mInstance;
    private OkHttpClient client;
    private OkHttpClient normalClient;
    public static BiliOkHttpClientManager getInstance(){
        if(mInstance == null){
            mInstance = new BiliOkHttpClientManager();
        }
        return mInstance;
    }

    public OkHttpClient getOkHttpClient(){
        if(client == null){
            client =new OkHttpClient.Builder()
                    .addInterceptor(new BiliFixedHeaderInterceptor())
                    .addInterceptor(new BiliFixedParameterInterceptor())
                    .addInterceptor(new BiliSortAndSignInterceptor())
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();
        }
        return client;
    }

    public OkHttpClient getNormalOkHttpClient(){
        if(normalClient == null){
            normalClient =new OkHttpClient.Builder()
                    .addInterceptor(new UserAgentInterceptor())
                    .addInterceptor(new CookieInterceptor())
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return normalClient;
    }

}
