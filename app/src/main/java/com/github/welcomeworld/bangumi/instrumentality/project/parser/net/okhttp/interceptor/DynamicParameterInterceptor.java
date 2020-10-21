package com.github.welcomeworld.bangumi.instrumentality.project.parser.net.okhttp.interceptor;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Response;

public class DynamicParameterInterceptor implements Interceptor {
    private Map<String, String> parameters;

    public DynamicParameterInterceptor(Map<String, String> parameters){
        this.parameters=parameters;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpUrl.Builder urlBuilder=chain.request().url().newBuilder();
        if(parameters!=null){
            for(String key:parameters.keySet()){
                urlBuilder.addQueryParameter(key, URLEncoder.encode(parameters.get(key),"utf-8"));
            }
        }
        return chain.proceed(chain.request().newBuilder().url(urlBuilder.build()).build());
    }
}
