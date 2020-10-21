package com.github.welcomeworld.bangumi.instrumentality.project.parser;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseParser {
    public abstract String getTag();

    public abstract List<VideoListBean> refreshRecommend();

    public abstract List<VideoListBean> getMoreRecommend();

    public abstract VideoListBean parseVideoListRealInfo(VideoListBean videoListBean);

    public abstract boolean isMatchParser(String key);


}
