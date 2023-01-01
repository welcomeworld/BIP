package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor;

import android.webkit.CookieManager;

import com.github.welcomeworld.devbase.utils.StringUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        String cookie = CookieManager.getInstance().getCookie(chain.request().url().toString());
        if (!StringUtil.isEmpty(cookie)) {
            requestBuilder.addHeader("Cookie", cookie);
        }
        Response response = chain.proceed(requestBuilder.build());
        List<String> cookies = response.headers().values("set-cookie");
        for (String webCookie : cookies) {
            CookieManager.getInstance().setCookie(chain.request().url().toString(), webCookie);
        }
        return response;
    }
}
