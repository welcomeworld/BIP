package com.github.welcomeworld.bangumi.instrumentality.project.parser;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.nisigada.common.devbase.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParserManager {

    private static ParserManager instance;

    public static ParserManager getInstance() {
        if (instance == null) {
            instance = new ParserManager();
        }
        return instance;
    }

    private LinkedHashMap<String, BaseParser> mParsers = new LinkedHashMap<>();

    public List<VideoListBean> refreshRecommend() {
        List<VideoListBean> result = new ArrayList<>();
        for (BaseParser parser : mParsers.values()) {
            result.addAll(parser.refreshRecommend());
        }
        return result;
    }

    public List<VideoListBean> getMoreRecommend() {
        List<VideoListBean> result = new ArrayList<>();
        for (BaseParser parser : mParsers.values()) {
            result.addAll(parser.getMoreRecommend());
        }
        return result;
    }

    /**
     *
     * @param searchKey searchWord
     * @param pn start with 1
     */
    public List<VideoListBean> search(String searchKey, String pn) {
        List<VideoListBean> result = new ArrayList<>();
        for (BaseParser parser : mParsers.values()) {
            result.addAll(parser.search(searchKey, pn));
        }
        return result;
    }

    public void addParser(BaseParser parser) {
        mParsers.put(parser.getTag(), parser);
    }

    public BaseParser getParser(String tag) {
        return mParsers.get(tag);
    }

    public List<VideoListBean> updateVideoList(List<VideoListBean> videoListBeans, int selectSourceIndex) {
        VideoListBean videoListBean = videoListBeans.get(selectSourceIndex);
        if (videoListBean != null) {
            for (BaseParser parser : mParsers.values()) {
                if (parser.isMatchParser(videoListBean.getSourceName())) {
                    return parser.updateVideoList(videoListBeans, selectSourceIndex);
                }
            }
        }
        return videoListBeans;
    }

    public Map<Integer, Map<String,String>> getPlayerOptions(VideoListBean videoListBean){
        if (videoListBean != null) {
            for (BaseParser parser : mParsers.values()) {
                if (parser.isMatchParser(videoListBean.getSourceName())) {
                    return parser.getPlayerOptions();
                }
            }
        }
        return null;
    }

    public void clearCache(VideoListBean videoListBean){
        if (videoListBean != null) {
            for (BaseParser parser : mParsers.values()) {
                if (parser.isMatchParser(videoListBean.getSourceName())) {
                    parser.clearCache(videoListBean);
                    return;
                }
            }
        }
    }

    public void initParsers(){
        for (BaseParser parser : mParsers.values()) {
            ThreadUtil.defer().when(parser::initParser);
        }
    }
}
