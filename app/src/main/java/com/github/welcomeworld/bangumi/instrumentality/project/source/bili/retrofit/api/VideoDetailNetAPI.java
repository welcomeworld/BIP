package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BvToAvBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BiliCommentBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoUrlBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

public interface VideoDetailNetAPI {
    @GET("x/v2/view?autoplay=0&fnval=16&fnver=0&force_host=0&plat=0&from=7")
    public Call<VideoDetailPageBean> getAvDetailPageInfo(@QueryMap Map<String, String> parameters);

    @GET("pgc/view/v2/app/season")
    public Call<BangumiDetailPageBean> getBangumiDetailPageInfo(@QueryMap Map<String, String> parameters);

    @Streaming
    @GET("x/playurl?fnval=16&fnver=0&force_host=0&expire=0&otype=json&npcybs=1")
    public Call<VideoUrlBean> getVideoUrl(@QueryMap Map<String, String> parameters);

    @Streaming
    @GET("pgc/player/api/playurl?module=bangumi&buvid=Yh4vH3pDckBzQQExADZRZlYqGytOd0Z0R3VCinfoc&fnval=0&fnver=0&force_host=0&expire=0&otype=json&npcybs=0")
    public Call<BangumiUrlBean> getBangumiUrl(@QueryMap Map<String, String> parameters);

    @GET("x/web-interface/view")
    public Call<BvToAvBean> getAvInfo(@Query("bvid") String bvid);

    @GET("x/v2/reply?type=1&ps=30&sort=1")
    public Call<BiliCommentBean> getComment(@Query("oid") long Avid, @Query("pn") int pageNumber);

    @GET("x/v2/reply/reply?&ps=20&type=1")
    public Call<BiliCommentBean> getSubComment(@Query("root") long parentId, @Query("oid") long Avid, @Query("pn") int pageNumber);
}
