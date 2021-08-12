package com.github.welcomeworld.bangumi.instrumentality.project.source.agefans.retrofit.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.agefans.retrofit.databean.AgeVideoUrlBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface VideoNetAPI {
    @POST("/Api.php")
    @FormUrlEncoded
    public Call<AgeVideoUrlBean> getRealUrl(@HeaderMap Map<String,String>headers, @Field("url") String url, @Field("wap") String wap, @Field("ios") String ios, @Field("host") String host, @Field("key")String key, @Field("sign")String sign, @Field("token")String token, @Field("type")String type, @Field("referer")String referer, @Field("time")String time);
}
