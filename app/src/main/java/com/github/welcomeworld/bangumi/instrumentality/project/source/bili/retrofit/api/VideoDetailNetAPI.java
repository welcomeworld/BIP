package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.ReplyCursorBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoUrlBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

public interface VideoDetailNetAPI {
    @GET("x/v2/view?autoplay=0&fnval=16&fnver=0&force_host=0&plat=0&from=7&qn=32")
    public Call<VideoDetailPageBean> getAvDetailPageInfo(@QueryMap Map<String,String> parameters);

    @GET("pgc/view/app/season?qn=32")
    public Call<BangumiDetailPageBean> getBangumiDetailPageInfo(@QueryMap Map<String,String> parameters);

    @Streaming
    @GET("x/playurl?device=android&fnval=16&fnver=0&force_host=0&expire=0&otype=json&qn=32&npcybs=1")
    public Call<VideoUrlBean> getVideoUrl(@QueryMap Map<String,String> parameters);

    @Streaming
    @GET("pgc/player/api/playurl?module=bangumi&buvid=Yh4vH3pDckBzQQExADZRZlYqGytOd0Z0R3VCinfoc&qn=32&fnval=0&fnver=0&force_host=0&expire=0&otype=json&npcybs=0")
    public Call<BangumiUrlBean> getBangumiUrl(@QueryMap Map<String,String> parameters);

    @GET("x/v2/reply/cursor")
    public Call<ReplyCursorBean> getVideoReply();
}
