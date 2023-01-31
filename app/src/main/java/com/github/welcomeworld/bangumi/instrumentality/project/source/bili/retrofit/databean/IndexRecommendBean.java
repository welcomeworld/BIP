package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import java.util.List;

@SuppressWarnings("unused")
public class IndexRecommendBean {
    private long code;
    private ConfigBean config;
    private String message;
    private List<IndexRecommendDataBean> data;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<IndexRecommendDataBean> getData() {
        return data;
    }

    public void setData(List<IndexRecommendDataBean> data) {
        this.data = data;
    }

    public static class ConfigBean {

        private long feed_clean_abtest;

        public long getFeed_clean_abtest() {
            return feed_clean_abtest;
        }

        public void setFeed_clean_abtest(long feed_clean_abtest) {
            this.feed_clean_abtest = feed_clean_abtest;
        }
    }
}
