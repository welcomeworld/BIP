package com.github.welcomeworld.bangumi.instrumentality.project.parser.net.okhttp.interceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class DynamicHeaderInterceptor implements Interceptor {
    Map<String, String> headers;

    public DynamicHeaderInterceptor(Map<String, String> headers){
        this.headers=headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder =chain.request().newBuilder();
        if(headers!=null&&headers.size()>0){
            for(String key:headers.keySet()){
                requestBuilder.addHeader(key,headers.get(key));
            }
        }
        return chain.proceed(requestBuilder.build());
    }
}
