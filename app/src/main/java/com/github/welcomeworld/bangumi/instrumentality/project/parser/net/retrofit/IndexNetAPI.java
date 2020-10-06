package com.github.welcomeworld.bangumi.instrumentality.project.parser.net.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;

public interface IndexNetAPI {
    @GET("x/feed/index")
    Call<IndexRecommendBean> getIndexRecommend();

}