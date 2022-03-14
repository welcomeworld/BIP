package com.github.welcomeworld.bangumi.instrumentality.project.parser;

import androidx.fragment.app.Fragment;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bipplayer.DefaultBIPPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseParser {
    public abstract String getTag();

    public abstract List<VideoListBean> refreshRecommend();

    public abstract List<VideoListBean> getMoreRecommend();

    /**
     *
     * @param key searchWord
     * @param pn start with 1
     */
    public  abstract List<VideoListBean> search(String key,String pn);

    public abstract boolean isMatchParser(String key);

    public Fragment getLoginFragment(){
        return null;
    }

    public List<VideoListBean> updateVideoList(List<VideoListBean> videoListBeans,int selectSourceIndex){
        return videoListBeans;
    }

    public Map<Integer, Map<String,String>> getPlayerOptions(){
        Map<Integer, Map<String, String>> result = new HashMap<>();
        Map<String,String> formatOptions = new HashMap<>();
        formatOptions.put("allowed_extensions","ALL");
        result.put(DefaultBIPPlayer.OPT_CATEGORY_FORMAT,formatOptions);
        Map<String,String> playerOptions = new HashMap<>();
        playerOptions.put("start-on-prepared","1");
        result.put(DefaultBIPPlayer.OPT_CATEGORY_PLAYER,playerOptions);
        return result;
    }

    public void clearCache(VideoListBean videoListBean){
        videoListBean.setSourceExternalData(null);
    }

    public void initParser(){

    }


}
