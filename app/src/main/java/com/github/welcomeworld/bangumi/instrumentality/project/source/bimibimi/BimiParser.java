package com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

import java.util.HashMap;
import java.util.List;

public class BimiParser extends BaseParser {
    private static final HashMap<String, Integer> searchMaxPageCache = new HashMap<>();

    @Override
    public String getTag() {
        return Constants.Source.BIMI;
    }

    @Override
    public List<VideoListBean> updateVideoList(List<VideoListBean> videoListBeans, int selectSourceIndex) {
        return BimiNetApi.updateVideoList(videoListBeans, selectSourceIndex);
    }

    @Override
    public void search(String key, String pn, SearchCallback searchCallback) {
        Integer currentPn = Integer.parseInt(pn);
        Integer cachePn = searchMaxPageCache.get(key);
        if (cachePn != null && currentPn >= cachePn) {
            LogUtil.e("BimiSearch:", "skip search because out of max page max:" + cachePn + " current:" + currentPn);
            return;
        }
        ThreadUtil.defer().when(() -> BimiNetApi.search(key, pn)).done((result) -> {
            if (result == null) {
                searchMaxPageCache.put(key, currentPn);
            } else {
                searchCallback.onSearchResult(result);
            }
        });
    }

    @Override
    public boolean isMatchParser(String key) {
        return Constants.Source.BIMI.equals(key);
    }
}
