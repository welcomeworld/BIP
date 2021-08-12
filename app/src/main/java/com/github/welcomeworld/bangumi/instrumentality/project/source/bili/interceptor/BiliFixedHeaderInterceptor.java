package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api.BiliParams;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BiliFixedHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder =chain.request().newBuilder();
        requestBuilder.addHeader("Buvid",BiliParams.getBuvid());
        requestBuilder.addHeader("User-Agent", BiliParams.getUserAgent());
        return chain.proceed(requestBuilder.build());
    }
}
