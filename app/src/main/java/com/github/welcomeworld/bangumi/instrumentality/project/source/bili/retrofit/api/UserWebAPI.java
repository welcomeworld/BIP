package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebLoginInfoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebLoginUrlBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserWebAPI {
    @GET("qrcode/getLoginUrl")
    public Call<WebLoginUrlBean> getLoginUrl();

    @POST("qrcode/getLoginInfo")
    @FormUrlEncoded
    public Call<WebLoginInfoBean> getLoginInfo(@Field("oauthKey") String oauthKey);
}
