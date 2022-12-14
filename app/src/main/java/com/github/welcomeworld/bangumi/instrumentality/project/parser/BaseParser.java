package com.github.welcomeworld.bangumi.instrumentality.project.parser;

import android.net.Uri;

import androidx.fragment.app.Fragment;

import com.github.welcomeworld.bangumi.instrumentality.project.model.CommentBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bipplayer.DefaultBIPPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseParser {
    public abstract String getTag();

    public abstract List<VideoListBean> refreshRecommend();

    public abstract List<VideoListBean> getMoreRecommend();

    /**
     * @param key searchWord
     * @param pn  start with 1
     */
    public abstract List<VideoListBean> search(String key, String pn);

    public abstract boolean isMatchParser(String key);

    public Fragment getLoginFragment() {
        return null;
    }

    public List<VideoListBean> updateVideoList(List<VideoListBean> videoListBeans, int selectSourceIndex) {
        return videoListBeans;
    }

    public Map<Integer, Map<String, String>> getPlayerOptions() {
        Map<Integer, Map<String, String>> result = new HashMap<>();
        Map<String, String> formatOptions = new HashMap<>();
        formatOptions.put("allowed_extensions", "ALL");
        formatOptions.put("user_agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        result.put(DefaultBIPPlayer.OPT_CATEGORY_FORMAT, formatOptions);
        Map<String, String> playerOptions = new HashMap<>();
        playerOptions.put("start-on-prepared", "1");
        result.put(DefaultBIPPlayer.OPT_CATEGORY_PLAYER, playerOptions);
        return result;
    }

    public void clearCache(VideoListBean videoListBean) {
        videoListBean.setSourceExternalData(null);
    }

    public void initParser() {

    }

    public boolean matchSource(Uri uri) {
        return false;
    }


    public List<VideoListBean> createVideoListBeans(Uri uri) {
        return null;
    }

    public Map<String, String> getDownloadHeaders(VideoListBean videoListBean) {
        return null;
    }

    public boolean hasComment() {
        return false;
    }

    public CommentBean getComment(VideoBean videoBean, int page) {
        return new CommentBean();
    }

    public List<CommentBean.CommentDataBean> getSubComment(CommentBean.CommentDataBean parent) {
        return new ArrayList<>();
    }


}
