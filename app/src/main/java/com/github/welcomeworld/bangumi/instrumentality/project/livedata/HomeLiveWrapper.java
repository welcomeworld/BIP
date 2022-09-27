package com.github.welcomeworld.bangumi.instrumentality.project.livedata;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;

import java.util.List;

public class HomeLiveWrapper {
    public static final int REFRESH = 0;
    public static final int MORE = 1;
    private final List<VideoListBean> data;
    private final int action;
    
    public HomeLiveWrapper(int action, List<VideoListBean> data) {
        this.action = action;
        this.data = data;
    }

    public List<VideoListBean> getData() {
        return data;
    }

    public int getAction() {
        return action;
    }
}
