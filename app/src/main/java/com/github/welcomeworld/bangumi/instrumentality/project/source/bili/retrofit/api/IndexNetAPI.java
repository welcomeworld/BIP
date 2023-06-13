package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;


import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebHomeRcmdData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface IndexNetAPI {
    @GET("x/web-interface/wbi/index/top/feed/rcmd?fresh_type=4&feed_version=V8&homepage_ver=1&ps=12")
    Call<WebHomeRcmdData> getRecommend(@QueryMap Map<String, String> parameters);

}