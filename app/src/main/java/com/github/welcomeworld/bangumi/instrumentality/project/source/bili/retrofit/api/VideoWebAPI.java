package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BvToAvBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoUrlBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

public interface VideoWebAPI {
    @GET("x/web-interface/view")
    public Call<BvToAvBean> getAvInfo(@Query("bvid") String bvid);

    @GET("x/web-interface/view")
    public Call<VideoDetailPageBean> getAvDetailPageInfo(@QueryMap Map<String, String> parameters);

    @Streaming
    @GET("x/player/playurl?fnval=4048&fnver=0&otype=json&fourk=1")
    public Call<VideoUrlBean> getVideoUrl(@QueryMap Map<String, String> parameters);
}
