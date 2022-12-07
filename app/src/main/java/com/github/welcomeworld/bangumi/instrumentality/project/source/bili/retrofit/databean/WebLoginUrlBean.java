package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

public class WebLoginUrlBean {

    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private Data data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("url")
        private String url;
        @SerializedName("qrcode_key")
        private String oauthKey;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getOauthKey() {
            return oauthKey;
        }

        public void setOauthKey(String oauthKey) {
            this.oauthKey = oauthKey;
        }
    }
}
