package com.github.welcomeworld.bangumi.instrumentality.project.source.bili;

import android.net.Uri;
import android.util.Log;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BaseUrl;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BiliRetrofitManager;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.IndexNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.SearchNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.IndexRecommendBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.IndexRecommendDataBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.VideoDetailNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.SearchResultBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BiliParser extends BaseParser {
    @Override
    public String getTag() {
        return "BiliParser";
    }

    @Override
    public List<VideoListBean> refreshRecommend() {
        List<VideoListBean> result = new ArrayList<>();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("pull", "true");
        parameters.put("ts", "" + System.currentTimeMillis());
        IndexNetAPI indexNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(IndexNetAPI.class);
        Call<IndexRecommendBean> indexBeanCall = indexNetAPI.getIndexRecommend(parameters);
        try {
            Response<IndexRecommendBean> response = indexBeanCall.execute();
            if (response.body().getData() == null) {
//                Toast.makeText(getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
                return result;
            }
            List<IndexRecommendDataBean> moreData;
            moreData = response.body().getData();
            for (int i = 0; i < moreData.size(); i++) {
                if (!moreData.get(i).getGotoX().equalsIgnoreCase("av")) {
                    moreData.remove(i);
                    i--;
                } else {
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setSourceName(Constants.Source.BILI);
                    videoListBean.setTitle(moreData.get(i).getTitle());
                    videoListBean.setCover(moreData.get(i).getCover() + "@320w_200h_1e_1c.webp");
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
                    videoBean.setTitle(moreData.get(i).getTitle());
                    videoBean.setCover(videoListBean.getCover());
                    videoBean.setVideoKey(String.valueOf(moreData.get(i).getCid()));
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
    public List<VideoListBean> getMoreRecommend() {
        List<VideoListBean> result = new ArrayList<>();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("pull", "false");
        parameters.put("ts", "" + System.currentTimeMillis());
        IndexNetAPI indexNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(IndexNetAPI.class);
        Call<IndexRecommendBean> indexBeanCall = indexNetAPI.getIndexRecommend(parameters);
        try {
            Response<IndexRecommendBean> response = indexBeanCall.execute();
            if (response.body().getData() == null) {
//                Toast.makeText(getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
                return result;
            }
            List<IndexRecommendDataBean> moreData;
            moreData = response.body().getData();
            for (int i = 0; i < moreData.size(); i++) {
                if (!moreData.get(i).getGotoX().equalsIgnoreCase("av")) {
                    moreData.remove(i);
                    i--;
                } else {
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setTitle(moreData.get(i).getTitle());
                    videoListBean.setSourceName(Constants.Source.BILI);
                    videoListBean.setCover(moreData.get(i).getCover() + "@320w_200h_1e_1c.webp");
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
                    videoBean.setVideoKey(String.valueOf(moreData.get(i).getCid()));
                    videoBean.setTitle(moreData.get(i).getTitle());
                    videoBean.setCover(videoListBean.getCover());
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
    public VideoListBean parseVideoListRealInfo(VideoListBean videoListBean) {
        VideoBean currentVideoBean = videoListBean.getCurrentVideoBean();
        Uri currentUri = Uri.parse(currentVideoBean.getUrl());
        LogUtil.e("BiliParser","uri:"+currentUri);
        String currentAid = currentUri.getPath().substring(1);
        LogUtil.e("BiliParser","aid:"+currentAid);
        int currentCid = Integer.parseInt(currentVideoBean.getVideoKey());
        LogUtil.e("BiliParser","cid:"+currentCid);
        currentAid = currentAid.substring(0, currentAid.contains("/") ? currentAid.indexOf("/") : currentAid.length());
        LogUtil.e("BiliParser","final aid:"+currentAid);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("aid", currentAid);
        parameters.put("ts", "" + System.currentTimeMillis());
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(VideoDetailNetAPI.class);
        try {
            Response<VideoDetailPageBean> response = videoDetailNetAPI.getVideoDetailPageInfo(parameters).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                LogUtil.e("BiliParser","response error:"+response.body().getCode());
                return videoListBean;
            }
            boolean matchCid = false;
            videoListBean.getVideoBeanList().clear();
            for (int i = 0; i < response.body().getData().getPages().size(); i++) {
                int cid = response.body().getData().getPages().get(i).getCid();
                if (cid != currentCid) {
                    VideoBean videoBean = new VideoBean();
                    boolean haveData = false;
                    for (VideoBean cacheBean : videoListBean.getVideoBeanList()) {
                        if (cacheBean.getVideoKey().equals(String.valueOf(cid))) {
                            videoBean = cacheBean;
                            haveData = true;
                            break;
                        }
                    }
                    videoBean.setVideoKey(String.valueOf(cid));
                    videoBean.setDanmakuUrl(response.body().getData().getPages().get(i).getDmlink());
                    videoBean.setTitle(response.body().getData().getPages().get(i).getPart());
                    if (!haveData) {
                        videoListBean.getVideoBeanList().add(videoBean);
                    }
                    continue;
                }
                matchCid = true;
                videoListBean.setSelectIndex(i);
                currentVideoBean.setDanmakuUrl(response.body().getData().getPages().get(i).getDmlink());
                currentVideoBean.setTitle(response.body().getData().getPages().get(i).getPart());
                parameters.clear();
                parameters.put("aid", currentAid);
                parameters.put("cid", currentCid + "");
                parameters.put("ts", "" + System.currentTimeMillis());
                videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(VideoDetailNetAPI.class);
                Response<VideoUrlBean> urlResponse = videoDetailNetAPI.getVideoUrl(parameters).execute();
                String urlString;
                if (urlResponse.body() == null || urlResponse.body().getCode() != 0) {
                    return videoListBean;
                }
                if (urlResponse.body().getData().getDash() != null) {
                    currentVideoBean.setDash(true);
                    for (int j = 0; j < urlResponse.body().getData().getDash().getVideo().size(); j++) {
                        VideoQualityBean qualityBean = new VideoQualityBean();
                        qualityBean.setRealVideoUrl(urlResponse.body().getData().getDash().getVideo().get(j).getBase_url());
                        String description;
                        switch (urlResponse.body().getData().getDash().getVideo().get(j).getId()) {
                            case 16:
                                description = "360P";
                                break;
                            case 32:
                                description = "480P";
                                break;
                            case 64:
                                description = "720P";
                                break;
                            default:
                                description = "1080P";
                                break;
                        }
                        qualityBean.setQuality(description);
                        qualityBean.setRealAudioUrl(urlResponse.body().getData().getDash().getAudio().get(0).getBase_url());
                        currentVideoBean.getQualityBeans().add(qualityBean);
                    }
                } else {
                    currentVideoBean.setDash(false);
                    VideoQualityBean qualityBean = new VideoQualityBean();
                    qualityBean.setRealVideoUrl(urlResponse.body().getData().getDurl().get(0).getUrl());
                    qualityBean.setQuality(urlResponse.body().getData().getFormat());
                    currentVideoBean.getQualityBeans().add(qualityBean);
                }
                videoListBean.getVideoBeanList().add(currentVideoBean);
            }
            if(!matchCid&&videoListBean.getVideoBeanList().size()>0){
                videoListBean.setSelectIndex(0);
                currentVideoBean = videoListBean.getCurrentVideoBean();
                currentCid = Integer.parseInt(currentVideoBean.getVideoKey());
                parameters.clear();
                parameters.put("aid", currentAid);
                parameters.put("cid", currentCid + "");
                parameters.put("ts", "" + System.currentTimeMillis());
                videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(VideoDetailNetAPI.class);
                Response<VideoUrlBean> urlResponse = videoDetailNetAPI.getVideoUrl(parameters).execute();
                String urlString;
                if (urlResponse.body() == null || urlResponse.body().getCode() != 0) {
                    return videoListBean;
                }
                if (urlResponse.body().getData().getDash() != null) {
                    currentVideoBean.setDash(true);
                    for (int j = 0; j < urlResponse.body().getData().getDash().getVideo().size(); j++) {
                        VideoQualityBean qualityBean = new VideoQualityBean();
                        qualityBean.setRealVideoUrl(urlResponse.body().getData().getDash().getVideo().get(j).getBase_url());
                        String description;
                        switch (urlResponse.body().getData().getDash().getVideo().get(j).getId()) {
                            case 16:
                                description = "360P";
                                break;
                            case 32:
                                description = "480P";
                                break;
                            case 64:
                                description = "720P";
                                break;
                            default:
                                description = "1080P";
                                break;
                        }
                        qualityBean.setQuality(description);
                        qualityBean.setRealAudioUrl(urlResponse.body().getData().getDash().getAudio().get(0).getBase_url());
                        currentVideoBean.getQualityBeans().add(qualityBean);
                    }
                } else {
                    currentVideoBean.setDash(false);
                    VideoQualityBean qualityBean = new VideoQualityBean();
                    qualityBean.setRealVideoUrl(urlResponse.body().getData().getDurl().get(0).getUrl());
                    qualityBean.setQuality(urlResponse.body().getData().getFormat());
                    currentVideoBean.getQualityBeans().add(qualityBean);
                }
            }
        } catch (Exception e) {
            Log.e("BiliNextError", "error:" + e.getMessage());
        }
        return videoListBean;
    }

    @Override
    public List<VideoListBean> search(String searchKey,String pn) {
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
        return Constants.Source.BILI.equals(key);
    }
}
