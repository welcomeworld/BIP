package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebLoginInfoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebLoginUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebNavBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserWebAPI {
    @GET("x/passport-login/web/qrcode/generate")
    Call<WebLoginUrlBean> getLoginUrl();

    @GET("x/passport-login/web/qrcode/poll")
    Call<WebLoginInfoBean> getLoginInfo(@Query("qrcode_key") String oauthKey);

    @GET("x/web-interface/nav")
    Call<WebNavBean> getNav();
}
