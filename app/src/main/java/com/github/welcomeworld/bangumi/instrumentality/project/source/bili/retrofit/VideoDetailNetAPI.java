package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface VideoDetailNetAPI {
    @GET("x/v2/view")
    public Call<VideoDetailPageBean> getVideoDetailPageInfo();

    @Streaming
    @GET("x/playurl")
    public Call<VideoUrlBean> getVideoUrl();

    @GET("x/v2/reply/cursor")
    public Call<ReplyCursorBean> getVideoReply();
}
