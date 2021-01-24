package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliOkHttpClientManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BiliRetrofitManager {

    public static Retrofit getRetrofit(String baseUrl){
       return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(BiliOkHttpClientManager.getInstance().getOkHttpClient())
                .build();
    }
}
