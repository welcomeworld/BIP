package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api;


import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebHotSearchBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebSearchBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface SearchNetAPI {
    /**
     * search_type:video,media_bangumi,media_ft
     * keyword
     * page:start from 1
     * dynamic_offset: previous search result size,maybe=(page-1)*24
     * page_size:video=42 other=12
     */
    @GET("x/web-interface/wbi/search/type?_refresh_=true&platform=pc&highlight=0&web_location=1430654")
    Call<WebSearchBean> getSearchResult(@QueryMap Map<String, String> parameters);

    @GET("x/web-interface/wbi/search/square?limit=10&platform=web")
    Call<WebHotSearchBean> getHotSearch();
}
