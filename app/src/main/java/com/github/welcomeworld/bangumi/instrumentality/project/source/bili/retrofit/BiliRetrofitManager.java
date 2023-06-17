package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliOkHttpClientManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BiliRetrofitManager {
    public static Retrofit getNormalRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(BiliOkHttpClientManager.getInstance().getNormalOkHttpClient())
                .build();
    }

    public static Retrofit getWbiRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(BiliOkHttpClientManager.getInstance().getWbiOkHttpClient())
                .build();
    }
}
