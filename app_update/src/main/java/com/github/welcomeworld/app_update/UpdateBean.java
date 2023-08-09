package com.github.welcomeworld.app_update;

import com.google.gson.annotations.SerializedName;

public class UpdateBean {
    @SerializedName("versionCode")
    public long versionCode;
    @SerializedName("versionName")
    public String versionName;
    @SerializedName("url")
    public String url;
    @SerializedName("versionInfo")
    public String versionInfo;
}
