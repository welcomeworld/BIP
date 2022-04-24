package com.github.welcomeworld.bangumi.instrumentality.project.source.agefans;

import android.net.Uri;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AgeFansParser extends BaseParser {
    private static final HashMap<String, Integer> searchMaxPageCache = new HashMap<>();

    @Override
    public String getTag() {
        return Constants.Source.AGEFANS;
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
        if (cachePn != null && currentPn >= cachePn) {
            LogUtil.e("AgeFansParser:", "skip search because out of max page max:" + cachePn + " current:" + currentPn);
            return Collections.emptyList();
        }
        List<VideoListBean> result = AgeFansNetApi.search(key, pn);
        if (result == null) {
            searchMaxPageCache.put(key, currentPn);
            return Collections.emptyList();
        }
        return result;
    }

    @Override
    public boolean isMatchParser(String key) {
        return Constants.Source.AGEFANS.equals(key);
    }

    @Override
    public boolean matchSource(Uri uri) {
        return uri.toString().startsWith(AgeFansNetApi.baseUrl);
    }

    @Override
    public List<VideoListBean> createVideoListBeans(Uri uri) {
        String path = uri.getPath();
        VideoListBean videoListBean = new VideoListBean();
        videoListBean.setSourceName(Constants.Source.AGEFANS);
        videoListBean.setCoverPortrait(true);
        String tagName = "age番剧";
        videoListBean.setTag(tagName);
        ArrayList<VideoBean> videoBeans = new ArrayList<>();
        VideoBean videoBean = new VideoBean();
        videoBean.setCover(videoListBean.getCover());
        String videoKey = path.replace("/detail/", "");
        videoKey = videoKey.replace("/play/", "");
        LogUtil.e("AgeFansParser", "getVideoKey:" + videoKey);
        videoBean.setVideoKey(videoKey);
        videoBean.setUrl(uri.toString());
        videoBeans.add(videoBean);
        videoListBean.setVideoBeanList(videoBeans);
        ArrayList<VideoListBean> videoListBeans = new ArrayList<>();
        videoListBeans.add(videoListBean);
        return videoListBeans;
    }
}
