package com.github.welcomeworld.bangumi.instrumentality.project.parser;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
}
