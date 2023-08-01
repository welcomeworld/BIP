package com.github.welcomeworld.bangumi.instrumentality.project.parser;

import android.net.Uri;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.Collections;
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

    private final LinkedHashMap<String, BaseParser> mParsers = new LinkedHashMap<>();

    public List<VideoListBean> refreshRecommend(int pageNumber) {
        List<VideoListBean> result = new ArrayList<>();
        for (BaseParser parser : mParsers.values()) {
            result.addAll(parser.getRecommend(pageNumber));
        }
        return result;
    }

    /**
     * @param searchKey searchWord
     * @param pn        start with 1
     */
    public void search(String searchKey, String pn, BaseParser.SearchCallback callback) {
        for (BaseParser parser : mParsers.values()) {
            parser.search(searchKey, pn, callback);
        }
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

    public Map<Integer, Map<String, String>> getPlayerOptions(VideoListBean videoListBean) {
        if (videoListBean != null) {
            for (BaseParser parser : mParsers.values()) {
                if (parser.isMatchParser(videoListBean.getSourceName())) {
                    return parser.getPlayerOptions();
                }
            }
        }
        return null;
    }

    public void clearCache(VideoListBean videoListBean) {
        if (videoListBean != null) {
            for (BaseParser parser : mParsers.values()) {
                if (parser.isMatchParser(videoListBean.getSourceName())) {
                    parser.clearCache(videoListBean);
                    return;
                }
            }
        }
    }

    public void initParsers() {
        for (BaseParser parser : mParsers.values()) {
            ThreadUtil.defer().when(parser::initParser);
        }
    }

    public BaseParser matchSource(Uri uri) {
        for (BaseParser parser : mParsers.values()) {
            if (parser.matchSource(uri)) {
                return parser;
            }
        }
        return null;
    }

    public List<String> getHotSearch() {
        for (BaseParser parser : mParsers.values()) {
            if (parser.canGetHotSearch()) {
                return parser.getHotSearch();
            }
        }
        return Collections.emptyList();
    }
}
