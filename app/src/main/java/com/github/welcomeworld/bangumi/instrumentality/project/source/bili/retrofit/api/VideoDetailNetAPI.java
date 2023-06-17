package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BiliCommentBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface VideoDetailNetAPI {

    @GET("pgc/view/web/season")
    Call<BangumiDetailPageBean> getBangumiDetailPageInfo(@QueryMap Map<String, String> parameters);

    @GET("x/v2/reply?type=1&ps=30&sort=1")
    Call<BiliCommentBean> getComment(@Query("oid") long Avid, @Query("pn") int pageNumber);

    @GET("x/v2/reply/reply?&ps=20&type=1")
    Call<BiliCommentBean> getSubComment(@Query("root") long parentId, @Query("oid") long Avid, @Query("pn") int pageNumber);
}
