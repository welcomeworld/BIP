package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResultBean {

    @SerializedName("ts")
    private int ts;
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("message")
    private String message;

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class DataBean {
        @SerializedName("status")
        private int status;
        @SerializedName("token_info")
        private TokenInfoBean tokenInfo;
        @SerializedName("cookie_info")
        private CookieInfoBean cookieInfo;
        @SerializedName("sso")
        private List<String> sso;
        @SerializedName("url")
        private String url;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public TokenInfoBean getTokenInfo() {
            return tokenInfo;
        }

        public void setTokenInfo(TokenInfoBean tokenInfo) {
            this.tokenInfo = tokenInfo;
        }

        public CookieInfoBean getCookieInfo() {
            return cookieInfo;
        }

        public void setCookieInfo(CookieInfoBean cookieInfo) {
            this.cookieInfo = cookieInfo;
        }

        public List<String> getSso() {
            return sso;
        }

        public void setSso(List<String> sso) {
            this.sso = sso;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class CookieInfoBean {

        @SerializedName("cookies")
        private List<CookiesBean> cookies;
        @SerializedName("domains")
        private List<String> domains;

        public List<CookiesBean> getCookies() {
            return cookies;
        }

        public void setCookies(List<CookiesBean> cookies) {
            this.cookies = cookies;
        }

        public List<String> getDomains() {
            return domains;
        }

        public void setDomains(List<String> domains) {
            this.domains = domains;
        }

        public static class CookiesBean {
            @SerializedName("name")
            private String name;
            @SerializedName("value")
            private String value;
            @SerializedName("http_only")
            private int httpOnly;
            @SerializedName("expires")
            private int expires;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public int getHttpOnly() {
                return httpOnly;
            }

            public void setHttpOnly(int httpOnly) {
                this.httpOnly = httpOnly;
            }

            public int getExpires() {
                return expires;
            }

            public void setExpires(int expires) {
                this.expires = expires;
            }
        }
    }



    @Entity(tableName = "token")
    public static class TokenInfoBean {
        @PrimaryKey(autoGenerate = true)
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        @SerializedName("mid")
        private int mid;
        @SerializedName("access_token")
        private String accessToken;
        @SerializedName("refresh_token")
        private String refreshToken;
        @SerializedName("expires_in")
        private int expiresIn;


        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

    }

}

