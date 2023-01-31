package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class LoginResultBean {

    @SerializedName("ts")
    private long ts;
    @SerializedName("code")
    private long code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("message")
    private String message;

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
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
        private long status;
        @SerializedName("token_info")
        private TokenInfoBean tokenInfo;
        @SerializedName("cookie_info")
        private CookieInfoBean cookieInfo;
        @SerializedName("sso")
        private List<String> sso;
        @SerializedName("url")
        private String url;

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
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
            private long httpOnly;
            @SerializedName("expires")
            private long expires;

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

            public long getHttpOnly() {
                return httpOnly;
            }

            public void setHttpOnly(long httpOnly) {
                this.httpOnly = httpOnly;
            }

            public long getExpires() {
                return expires;
            }

            public void setExpires(long expires) {
                this.expires = expires;
            }
        }
    }


    @Entity(tableName = "token")
    public static class TokenInfoBean {
        @PrimaryKey(autoGenerate = true)
        private long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        @SerializedName("mid")
        private long mid;
        @SerializedName("access_token")
        private String accessToken;
        @SerializedName("refresh_token")
        private String refreshToken;
        @SerializedName("expires_in")
        private long expiresIn;


        public long getMid() {
            return mid;
        }

        public void setMid(long mid) {
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

        public long getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
        }

    }

}

