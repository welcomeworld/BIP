package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

public class WebHotSearchBean {

    @SerializedName("code")
    public long code;
    @SerializedName("message")
    public String message;
    @SerializedName("ttl")
    public long ttl;
    @SerializedName("data")
    public Data data;

    public static class Data {
        @SerializedName("trending")
        public Trending trending;

        public static class Trending {
            @SerializedName("title")
            public String title;
            @SerializedName("trackid")
            public String trackid;
            @SerializedName("list")
            public java.util.List<HotSearch> hotSearch;
            @SerializedName("top_list")
            public Object topList;

            public static class HotSearch {
                @SerializedName("keyword")
                public String keyword;
                @SerializedName("show_name")
                public String showName;
                @SerializedName("icon")
                public String icon;
                @SerializedName("uri")
                public String uri;
                @SerializedName("goto")
                public String gotoX;
            }
        }
    }
}
