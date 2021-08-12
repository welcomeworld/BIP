package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.LoginKeyBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.LoginResultBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserNetAPI {
    @POST("x/passport-login/oauth2/login")
    @FormUrlEncoded
    public Call<LoginResultBean> login(@Field("username") String username, @Field("password") String password, @Field("device_meta") String device_meta, @Field("dt") String dt, @Field("from_pv")String fromPv, @Field("from_url")String fromUrl);

    @GET("x/passport-login/web/key")
    public Call<LoginKeyBean> getKey();

    @POST("/x/passport-login/oauth2/access_token")
    @FormUrlEncoded
    Call<LoginResultBean> acquireAccessTokenV2(@Field("code") String code,@Field("grant_type") String grantType);

//    @GET("x/v2/account/mine")
//    public Call<UserInfoMineBean> getMine();

}
