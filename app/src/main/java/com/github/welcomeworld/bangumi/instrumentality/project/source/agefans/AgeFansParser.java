package com.github.welcomeworld.bangumi.instrumentality.project.source.agefans;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AgeFansParser extends BaseParser {
    private static final HashMap<String,Integer> searchMaxPageCache = new HashMap<>();
    @Override
    public String getTag() {
        return "AgeFansParser";
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
        return AgeFansNetApi.updateVideoList(videoListBeans, selectSourceIndex);
    }

    @Override
    public List<VideoListBean> search(String key, String pn) {
        Integer currentPn = Integer.parseInt(pn);
        Integer cachePn = searchMaxPageCache.get(key);
        if(cachePn !=null &&currentPn>=cachePn){
            LogUtil.e("AgeFansParser:", "skip search because out of max page max:"+cachePn+" current:"+currentPn);
            return Collections.emptyList();
        }
        List<VideoListBean> result = AgeFansNetApi.search(key,pn);
        if(result == null){
            searchMaxPageCache.put(key,currentPn);
            return Collections.emptyList();
        }
        return result;
    }

    @Override
    public boolean isMatchParser(String key) {
        return Constants.Source.AGEFANS.equals(key);
    }
}
