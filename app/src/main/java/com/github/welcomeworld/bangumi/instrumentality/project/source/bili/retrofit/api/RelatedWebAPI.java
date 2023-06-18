package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiRelatedBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoRelatedBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RelatedWebAPI {
    /**
     * season_id
     */
    @GET("pgc/season/web/related/recommend")
    Call<BangumiRelatedBean> getRelatedBangumi(@QueryMap Map<String, String> parameters);

    /**
     * aid
     */
    @GET("x/web-interface/archive/related")
    Call<VideoRelatedBean> getRelatedVideo(@QueryMap Map<String, String> parameters);
}
