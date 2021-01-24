package com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi;

import android.net.Uri;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BaseUrl;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BiliRetrofitManager;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.SearchNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.SearchResultBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Response;

public class BimiParser extends BaseParser {
    @Override
    public String getTag() {
        return "DiliParser";
    }

    @Override
    public List<VideoListBean> refreshRecommend() {
        return null;
    }

    @Override
    public List<VideoListBean> getMoreRecommend() {
        return null;
    }

    @Override
    public VideoListBean parseVideoListRealInfo(VideoListBean videoListBean) {
        return null;
    }

    @Override
    public List<VideoListBean> search(String key, String pn) {
        BimiNetApi
        List<VideoListBean> result = new ArrayList<>();
        Map<String,String> parameters=new HashMap<>();
        parameters.put("pn",pn);
        parameters.put("keyword",searchKey);
        parameters.put("ts",""+System.currentTimeMillis());
        SearchNetAPI searchNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(SearchNetAPI.class);
        try {
            Response<SearchResultBean> response = searchNetAPI.getSearchResult(parameters).execute();
            if (response.body().getData() == null) {
//                Toast.makeText(getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
                return result;
            }
            List<SearchResultBean.DataBean.ItemBean> moreData;
            moreData = response.body().getData().getItem();
            for (int i = 0; i < moreData.size(); i++) {
                if (!moreData.get(i).getGotoX().equalsIgnoreCase("av")) {
                    moreData.remove(i);
                    i--;
                } else {
                    LogUtil.e("BiliParser",moreData.get(i).toString());
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setSourceName(Constants.Source.BILI);
                    videoListBean.setTitle(moreData.get(i).getTitle());
                    videoListBean.setCover(moreData.get(i).getCover() + "@320w_200h_1e_1c.webp");
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
                    videoBean.setTitle(moreData.get(i).getTitle());
                    videoBean.setCover(videoListBean.getCover());
                    Uri currentUri = Uri.parse(moreData.get(i).getUri());
                    String currentAid = currentUri.getPath().substring(1);
                    currentAid = currentAid.substring(0,currentAid.contains("/")?currentAid.indexOf("/"):currentAid.length());
                    videoBean.setVideoKey(currentAid);
                    videoBean.setUrl(moreData.get(i).getUri());
                    videoBeans.add(videoBean);
                    videoListBean.setVideoBeanList(videoBeans);
                    result.add(videoListBean);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isMatchParser(String key) {
        return Constants.Source.BIMI.equals(key);
    }
}
