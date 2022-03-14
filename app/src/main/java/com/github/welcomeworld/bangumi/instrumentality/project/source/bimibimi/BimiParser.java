package com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BimiParser extends BaseParser {
    private static HashMap<String,Integer> searchMaxPageCache = new HashMap<>();

    @Override
    public String getTag() {
        return "BimiParser";
    }

    @Override
    public List<VideoListBean> refreshRecommend() {
        return Collections.emptyList();
    }

    @Override
    public List<VideoListBean> getMoreRecommend() {
        return Collections.emptyList();
    }

    @Override
    public List<VideoListBean> updateVideoList(List<VideoListBean> videoListBeans, int selectSourceIndex) {
        return BimiNetApi.updateVideoList(videoListBeans,selectSourceIndex);
    }

    @Override
    public List<VideoListBean> search(String key, String pn) {
        Integer currentPn = Integer.parseInt(pn);
        Integer cachePn = searchMaxPageCache.get(key);
        if(cachePn !=null &&currentPn>=cachePn){
            LogUtil.e("BimiSearch:", "skip search because out of max page max:"+cachePn+" current:"+currentPn);
            return Collections.emptyList();
        }
        List<VideoListBean> result = BimiNetApi.search(key,pn);
        if(result == null){
            searchMaxPageCache.put(key,currentPn);
            return Collections.emptyList();
        }
        return result;
    }

    @Override
    public boolean isMatchParser(String key) {
        return Constants.Source.BIMI.equals(key);
    }

    @Override
    public void initParser() {
        BimiNetApi.initBaseUrl();
    }
}
