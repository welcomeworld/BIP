package com.github.welcomeworld.bangumi.instrumentality.project.parser;

import androidx.fragment.app.Fragment;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;

import java.util.List;

public abstract class BaseParser {
    public abstract String getTag();

    public abstract List<VideoListBean> refreshRecommend();

    public abstract List<VideoListBean> getMoreRecommend();

    public  abstract List<VideoListBean> search(String key,String pn);

    public abstract boolean isMatchParser(String key);

    public Fragment getLoginFragment(){
        return null;
    }

    public List<VideoListBean> updateVideoList(List<VideoListBean> videoListBeans,int selectSourceIndex){
        return videoListBeans;
    };


}
