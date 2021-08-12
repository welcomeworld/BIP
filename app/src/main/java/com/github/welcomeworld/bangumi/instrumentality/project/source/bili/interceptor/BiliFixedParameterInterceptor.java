package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor;


import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api.BiliLocalStatus;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api.BiliParams;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BiliFixedParameterInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        boolean isPassport = request.url().encodedPath().contains("passport");
        if ("GET".equalsIgnoreCase(request.method())) {
            if (isPassport) {
                urlBuilder.addQueryParameter("local_id", BiliParams.getLocalId());
                urlBuilder.addQueryParameter("bili_local_id", BiliParams.getBiliLocalId());
                urlBuilder.addQueryParameter("device_id", BiliParams.getDeviceId());
                urlBuilder.addQueryParameter("buvid", BiliParams.getBuvid());
                urlBuilder.addQueryParameter("c_locale", BiliParams.getLocale());
                urlBuilder.addQueryParameter("s_locale", BiliParams.getLocale());
                urlBuilder.addQueryParameter("device_name", BiliParams.getDeviceName());
                urlBuilder.addQueryParameter("device_platform", BiliParams.getDevicePlatform());
            }
            urlBuilder.addQueryParameter("platform", "android");
            urlBuilder.addQueryParameter("mobi_app", "android");
            urlBuilder.addQueryParameter("device", "phone");
            urlBuilder.addQueryParameter("build", "" + BiliParams.getVersionCode());
            urlBuilder.addQueryParameter("appkey", BiliParams.getAppKey());
            urlBuilder.addQueryParameter("ts", "" + System.currentTimeMillis());
            if (BiliLocalStatus.isLogin()) {
                urlBuilder.addQueryParameter("access_key", BiliLocalStatus.getAccessKey());
            }
        } else if ("POST".equalsIgnoreCase(request.method())) {
            if (request.body() instanceof FormBody) {
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                FormBody formBody = (FormBody) request.body();
                // 先复制原来的参数
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }
                if(isPassport){
                    bodyBuilder.addEncoded("local_id", BiliParams.getLocalId());
                    bodyBuilder.addEncoded("bili_local_id", BiliParams.getBiliLocalId());
                    bodyBuilder.addEncoded("device_id", BiliParams.getDeviceId());
                    bodyBuilder.addEncoded("buvid", BiliParams.getBuvid());
                    bodyBuilder.addEncoded("c_locale", BiliParams.getLocale());
                    bodyBuilder.addEncoded("s_locale", BiliParams.getLocale());
                    bodyBuilder.addEncoded("device_name", BiliParams.getDeviceName());
                    bodyBuilder.addEncoded("device_platform", BiliParams.getDevicePlatform());
                }
                bodyBuilder.addEncoded("mobi_app", "android");
                bodyBuilder.addEncoded("platform", "android");
                bodyBuilder.addEncoded("device", "phone");
                bodyBuilder.addEncoded("build", "" + BiliParams.getVersionCode());
                bodyBuilder.addEncoded("appkey", BiliParams.getAppKey());
                bodyBuilder.addEncoded("ts", "" + System.currentTimeMillis());
                if (BiliLocalStatus.isLogin()) {
                    bodyBuilder.addEncoded("access_key", BiliLocalStatus.getAccessKey());
                }
                formBody = bodyBuilder
                        .build();
                request = request.newBuilder().post(formBody).build();
            }
        }
        return chain.proceed(request.newBuilder().url(urlBuilder.build()).build());
    }
}