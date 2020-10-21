package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BiliFixedParameterInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        HttpUrl.Builder urlBuilder=request.url().newBuilder();
        urlBuilder.addQueryParameter("build","5360000");
        urlBuilder.addQueryParameter("mobi_app","android_b");
        urlBuilder.addQueryParameter("platform","android");
        urlBuilder.addQueryParameter("appkey","1d8b6e7d45233436");
        urlBuilder.addQueryParameter("device","android");
//        if(BiliLocalStatus.isLogin()){
//            urlBuilder.addQueryParameter("access_key",BiliLocalStatus.getAccessKey());
//        }
        return chain.proceed(request.newBuilder().url(urlBuilder.build()).build());
    }
}