package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;


import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.SearchResultBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface SearchNetAPI {
    @GET("x/v2/search?duration=0&channel=bili&from_source=app_search&highlight=1&is_org_query=0&ps=20&recommend=1")
    public Call<SearchResultBean> getSearchResult(@QueryMap Map<String,String> parameters);
}
