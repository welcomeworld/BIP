package com.github.welcomeworld.app_update;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface UpdateNetAPI {
    @GET
    Call<UpdateBean> checkUpdate(@Url String path);
}
