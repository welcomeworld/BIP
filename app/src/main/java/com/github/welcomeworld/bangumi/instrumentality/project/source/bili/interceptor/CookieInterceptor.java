package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor;

import android.webkit.CookieManager;

import com.github.welcomeworld.bangumi.instrumentality.project.utils.StringUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        String cookie = CookieManager.getInstance().getCookie(chain.request().url().toString());
        if(!StringUtil.isEmpty(cookie)){
            requestBuilder.addHeader("Cookie", cookie);
        }
        return chain.proceed(requestBuilder.build());
    }
}
