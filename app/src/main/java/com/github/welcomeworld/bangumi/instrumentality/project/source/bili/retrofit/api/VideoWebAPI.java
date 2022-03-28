package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoUrlBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

public interface VideoWebAPI {
    @Streaming
    @GET("x/player/playurl?fnval=0&fnver=0&otype=json")
    public Call<VideoUrlBean> getVideoUrl(@QueryMap Map<String,String> parameters);
}
