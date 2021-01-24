package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;


import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.IndexRecommendBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface IndexNetAPI {
    @GET("x/feed/index?banner_hash=5370060068233862947&idx=1547815474&login_event=0&network=wifi&style=2&open_event=")
    Call<IndexRecommendBean> getIndexRecommend(@QueryMap Map<String,String> parameters);

}