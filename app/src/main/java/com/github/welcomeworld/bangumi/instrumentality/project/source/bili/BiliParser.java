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
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.VideoDetailNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.IndexRecommendBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.IndexRecommendDataBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.SearchResultBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

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
                    LogUtil.e("DataLog", "RecommendData:" + moreData.get(i).toString());
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setSourceName(Constants.Source.BILI);
                    videoListBean.setTitle(moreData.get(i).getTitle());
                    String tagName = moreData.get(i).getTname();
                    IndexRecommendDataBean.TagBean tagBean = moreData.get(i).getTag();
                    if (tagBean != null) {
                        tagName += "·" + tagBean.getTag_name();
                    }
                    videoListBean.setTag(tagName);
                    videoListBean.setCoverPortrait(false);
                    videoListBean.setCover(moreData.get(i).getCover() + "@320w_200h_1e_1c.webp");
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
                    HashMap<String, String> extraMap = new HashMap<>();
                    extraMap.put("videoType", "av");
                    videoBean.setSourceExternalData(new Gson().toJson(extraMap));
                    videoBean.setTitle(moreData.get(i).getTitle());
                    videoBean.setCover(videoListBean.getCover());
                    videoBean.setDuration(moreData.get(i).getDuration());
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
                    String tagName = moreData.get(i).getTname();
                    IndexRecommendDataBean.TagBean tagBean = moreData.get(i).getTag();
                    if (tagBean != null) {
                        tagName += "·" + tagBean.getTag_name();
                    }
                    videoListBean.setTag(tagName);
                    videoListBean.setCoverPortrait(false);
                    videoListBean.setCover(moreData.get(i).getCover() + "@320w_200h_1e_1c.webp");
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
                    HashMap<String, String> extraMap = new HashMap<>();
                    extraMap.put("videoType", "av");
                    videoBean.setSourceExternalData(new Gson().toJson(extraMap));
                    videoBean.setVideoKey(String.valueOf(moreData.get(i).getCid()));
                    videoBean.setTitle(moreData.get(i).getTitle());
                    videoBean.setDuration(moreData.get(i).getDuration());
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
        HashMap<String, String> extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
        if ("bangumi".equals(extraData.get("videoType"))) {
            return parseVideoListRealInfoInnerBangumi(videoListBean);
        } else {
            return parseVideoListRealInfoInnerAv(videoListBean);
        }
    }

    public VideoListBean parseVideoListRealInfoInnerBangumi(VideoListBean videoListBean) {
        VideoBean currentVideoBean = videoListBean.getCurrentVideoBean();
        HashMap<String, String> extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
        String currentAid = extraData.get("aid");
        String currentCid = extraData.get("cid");
        if (currentAid != null && currentCid != null) {
            queryBangumiItemDetail(currentVideoBean, currentAid, currentCid);
            return videoListBean;
        }
        Uri currentUri = Uri.parse(currentVideoBean.getUrl());
        LogUtil.e("BiliParser", "uri:" + currentUri);
        Map<String, String> parameters = new HashMap<>();
        if (currentUri.getPath() != null && currentUri.getPath().contains("ss")) {
            LogUtil.e("BiliParser", "sid:" + currentVideoBean.getVideoKey());
            parameters.put("season_id", currentVideoBean.getVideoKey());
        } else if (currentUri.getPath() != null && currentUri.getPath().contains("ep")) {
            LogUtil.e("BiliParser", "epid:" + currentVideoBean.getVideoKey());
            parameters.put("ep_id", currentVideoBean.getVideoKey());
        }
        parameters.put("ts", "" + System.currentTimeMillis());
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APIURL).create(VideoDetailNetAPI.class);
        try {
            Response<BangumiDetailPageBean> response = videoDetailNetAPI.getBangumiDetailPageInfo(parameters).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                LogUtil.e("BiliParser", "response error:" + response.body().getCode());
                return videoListBean;
            }
            boolean matchCid = false;
            BangumiDetailPageBean.ResultBean detailResult = response.body().getResult();
            videoListBean.getVideoBeanList().clear();
            videoListBean.setTitle(detailResult.getSeasonTitle());
            videoListBean.setVideoListDes(detailResult.getEvaluate());
            for (int i = 0; i < detailResult.getEpisodes().size(); i++) {
                LogUtil.e("BiliParser", "parse Data:" + detailResult.getEpisodes().get(i));
                long cid = detailResult.getEpisodes().get(i).getCid();
                if (currentCid == null || !currentCid.equals("" + cid)) {
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
                    videoBean.setUrl(currentVideoBean.getUrl());
                    extraData.put("aid", String.valueOf(detailResult.getEpisodes().get(i).getAid()));
                    extraData.put("cid", String.valueOf(cid));
                    videoBean.setSourceExternalData(new Gson().toJson(extraData));
                    videoBean.setDanmakuUrl("http://comment.bilibili.com/" + cid + ".xml");
                    videoBean.setTitle(detailResult.getEpisodes().get(i).getLongTitle());
                    if (!haveData) {
                        videoListBean.getVideoBeanList().add(videoBean);
                    }
                    continue;
                }
                matchCid = true;
                videoListBean.setSelectIndex(i);
                extraData.put("aid", String.valueOf(detailResult.getEpisodes().get(i).getAid()));
                extraData.put("cid", String.valueOf(cid));
                currentVideoBean.setSourceExternalData(new Gson().toJson(extraData));
                currentVideoBean.setDanmakuUrl("http://comment.bilibili.com/" + cid + ".xml");
                currentVideoBean.setTitle(detailResult.getEpisodes().get(i).getLongTitle());
                videoListBean.getVideoBeanList().add(currentVideoBean);
                queryBangumiItemDetail(currentVideoBean, extraData.get("aid"), extraData.get("cid"));
            }
            if (!matchCid && videoListBean.getVideoBeanList().size() > 0) {
                videoListBean.setSelectIndex(0);
                currentVideoBean = videoListBean.getCurrentVideoBean();
                extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
                currentAid = extraData.get("aid");
                currentCid = extraData.get("cid");
                queryBangumiItemDetail(currentVideoBean, currentAid, currentCid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("BiliNextError", "error:" + e.getMessage());
        }
        return videoListBean;
    }

    private void queryBangumiItemDetail(VideoBean currentVideoBean, String aid, String cid) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("aid", aid);
        parameters.put("cid", cid);
        parameters.put("ts", "" + System.currentTimeMillis());
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APIURL).create(VideoDetailNetAPI.class);
        try {
            Response<BangumiUrlBean> urlResponse = videoDetailNetAPI.getBangumiUrl(parameters).execute();

            if (urlResponse.body() == null || urlResponse.body().getCode() != 0) {
                return;
            }
            String description;
            if (urlResponse.body().getDash() != null) {
                currentVideoBean.setDash(true);
                for (int j = 0; j < urlResponse.body().getDash().getVideo().size(); j++) {
                    VideoQualityBean qualityBean = new VideoQualityBean();
                    qualityBean.setRealVideoUrl(urlResponse.body().getDash().getVideo().get(j).getBaseUrl());
                    switch (urlResponse.body().getDash().getVideo().get(j).getId()) {
                        case 16:
                            description = "360P";
                            break;
                        case 32:
                            description = "480P";
                            break;
                        case 64:
                            description = "720P";
                            break;
                        case 80:
                            description = "1080P";
                            break;
                        default:
                            description = "1080P+";
                            break;
                    }
                    qualityBean.setQuality(description);
                    qualityBean.setRealAudioUrl(urlResponse.body().getDash().getAudio().get(0).getBaseUrl());
                    currentVideoBean.getQualityBeans().add(qualityBean);
                }
            } else {
                currentVideoBean.setDash(false);
                VideoQualityBean qualityBean = new VideoQualityBean();
                qualityBean.setRealVideoUrl(urlResponse.body().getDurl().get(0).getUrl());
                switch (urlResponse.body().getQuality()) {
                    case 16:
                        description = "360P";
                        break;
                    case 32:
                        description = "480P";
                        break;
                    case 64:
                        description = "720P";
                        break;
                    case 80:
                        description = "1080P";
                        break;
                    default:
                        description = "1080P+";
                        break;
                }
                qualityBean.setQuality(description);
                currentVideoBean.getQualityBeans().add(qualityBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("BiliNextError", "error:" + e.getMessage());
        }
    }

    public VideoListBean parseVideoListRealInfoInnerAv(VideoListBean videoListBean) {
        VideoBean currentVideoBean = videoListBean.getCurrentVideoBean();
        HashMap<String, String> extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
        String currentAid = extraData.get("aid");
        String currentCid = extraData.get("cid");
        if (currentAid != null && currentCid != null) {
            queryAVItemDetail(currentVideoBean, currentAid, currentCid);
            return videoListBean;
        }
        Uri currentUri = Uri.parse(currentVideoBean.getUrl());
        LogUtil.e("BiliParser", "uri:" + currentUri);
        currentAid = currentUri.getPath().substring(1);
        try {
            currentCid = currentVideoBean.getVideoKey();
        } catch (Exception e) {
            //ignore
        }
        LogUtil.e("BiliParser", "cid:" + currentCid);
        currentAid = currentAid.substring(0, currentAid.contains("/") ? currentAid.indexOf("/") : currentAid.length());
        LogUtil.e("BiliParser", "final aid:" + currentAid);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("aid", currentAid);
        parameters.put("ts", "" + System.currentTimeMillis());
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(VideoDetailNetAPI.class);
        try {
            Response<VideoDetailPageBean> response = videoDetailNetAPI.getAvDetailPageInfo(parameters).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                LogUtil.e("BiliParser", "response error:" + response.body().getCode());
                return videoListBean;
            }
            boolean matchCid = false;
            videoListBean.setVideoListDes(response.body().getData().getDesc());
            videoListBean.getVideoBeanList().clear();
            for (int i = 0; i < response.body().getData().getPages().size(); i++) {
                LogUtil.e("BiliParser", "parse Data:" + response.body().getData().getPages().get(i));
                int cid = response.body().getData().getPages().get(i).getCid();
                if (currentCid == null || !currentCid.equals("" + cid)) {
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
                    videoBean.setUrl(currentVideoBean.getUrl());
                    extraData.put("aid", currentAid);
                    extraData.put("cid", String.valueOf(cid));
                    videoBean.setSourceExternalData(new Gson().toJson(extraData));
                    videoBean.setDanmakuUrl(response.body().getData().getPages().get(i).getDmlink());
                    videoBean.setTitle(response.body().getData().getPages().get(i).getPart());
                    if (!haveData) {
                        videoListBean.getVideoBeanList().add(videoBean);
                    }
                    continue;
                }
                matchCid = true;
                videoListBean.setSelectIndex(i);
                extraData.put("aid", currentAid);
                extraData.put("cid", String.valueOf(cid));
                currentVideoBean.setSourceExternalData(new Gson().toJson(extraData));
                currentVideoBean.setDanmakuUrl(response.body().getData().getPages().get(i).getDmlink());
                currentVideoBean.setTitle(response.body().getData().getPages().get(i).getPart());
                videoListBean.getVideoBeanList().add(currentVideoBean);
                queryAVItemDetail(currentVideoBean, extraData.get("aid"), extraData.get("cid"));
            }
            if (!matchCid && videoListBean.getVideoBeanList().size() > 0) {
                videoListBean.setSelectIndex(0);
                currentVideoBean = videoListBean.getCurrentVideoBean();
                currentCid = currentVideoBean.getVideoKey();
                LogUtil.e("BiliParser", "not here error");
                queryAVItemDetail(currentVideoBean,currentAid,currentCid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("BiliNextError", "error:" + e.getMessage());
        }
        return videoListBean;
    }

    private void queryAVItemDetail(VideoBean currentVideoBean, String aid, String cid) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("aid", aid);
        parameters.put("cid", cid + "");
        parameters.put("ts", "" + System.currentTimeMillis());
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(VideoDetailNetAPI.class);
        try {
            Response<VideoUrlBean> urlResponse = videoDetailNetAPI.getVideoUrl(parameters).execute();
            if (urlResponse.body() == null || urlResponse.body().getCode() != 0) {
                return;
            }
            String description;
            if (urlResponse.body().getData().getDash() != null) {
                currentVideoBean.setDash(true);
                for (int j = 0; j < urlResponse.body().getData().getDash().getVideo().size(); j++) {
                    VideoQualityBean qualityBean = new VideoQualityBean();
                    qualityBean.setRealVideoUrl(urlResponse.body().getData().getDash().getVideo().get(j).getBase_url());
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
                        case 80:
                            description = "1080P";
                            break;
                        default:
                            description = "1080P+";
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
                switch (urlResponse.body().getData().getQuality()) {
                    case 16:
                        description = "360P";
                        break;
                    case 32:
                        description = "480P";
                        break;
                    case 64:
                        description = "720P";
                        break;
                    case 80:
                        description = "1080P";
                        break;
                    default:
                        description = "1080P+";
                        break;
                }
                qualityBean.setQuality(description);
                currentVideoBean.getQualityBeans().add(qualityBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("BiliNextError", "error:" + e.getMessage());
        }
    }

    @Override
    public List<VideoListBean> search(String searchKey, String pn) {
        List<VideoListBean> result = new ArrayList<>();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("pn", pn);
        parameters.put("keyword", searchKey);
        parameters.put("ts", "" + System.currentTimeMillis());
        SearchNetAPI searchNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(SearchNetAPI.class);
        try {
            Response<SearchResultBean> response = searchNetAPI.getSearchResult(parameters).execute();
            if (response.body().getData() == null) {
//                Toast.makeText(getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
                return result;
            }
            List<SearchResultBean.DataBean.ItemBean> moreData;
            moreData = response.body().getData().getItem();
            if (moreData == null || moreData.size() == 0) {
                return result;
            }
            for (int i = 0; i < moreData.size(); i++) {
                if (!moreData.get(i).getGotoX().equalsIgnoreCase("av") && !moreData.get(i).getGotoX().equalsIgnoreCase("bangumi")) {
                    moreData.remove(i);
                    i--;
                } else {
                    boolean isBangumi = moreData.get(i).getGotoX().equalsIgnoreCase("bangumi");
                    LogUtil.e("DataLog", "SearchData:" + moreData.get(i).toString());
//                    LogUtil.e("BiliParser", moreData.get(i).toString());
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setSourceName(Constants.Source.BILI);
                    videoListBean.setTitle(moreData.get(i).getTitle());
                    videoListBean.setTag(isBangumi ? "bili番剧" : moreData.get(i).getAuthor());
                    videoListBean.setCoverPortrait(isBangumi);
                    videoListBean.setCover(moreData.get(i).getCover() + (isBangumi ? "@240w_320h_1e_1c.webp" : "@320w_200h_1e_1c.webp"));
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
                    videoBean.setTitle(moreData.get(i).getTitle());
                    videoBean.setCover(videoListBean.getCover());
                    HashMap<String, String> extraMap = new HashMap<>();
                    extraMap.put("videoType", isBangumi ? "bangumi" : "av");
                    videoBean.setSourceExternalData(new Gson().toJson(extraMap));
                    String uriString = moreData.get(i).getUri();
                    if (uriString.endsWith("/")) {
                        uriString = uriString.substring(0, uriString.length() - 1);
                    }
                    Uri currentUri = Uri.parse(uriString);
                    if (isBangumi) {
                        videoBean.setVideoKey(currentUri.getPath().substring(currentUri.getPath().lastIndexOf('/') + 3));
                    } else {
                        String currentAid = currentUri.getPath().substring(1);
                        currentAid = currentAid.substring(0, currentAid.contains("/") ? currentAid.indexOf("/") : currentAid.length());
                        videoBean.setVideoKey(currentAid);
                    }
                    videoBean.setUrl(moreData.get(i).getUri());
                    videoBean.setDuration(parseDuration(moreData.get(i).getDuration()));
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

    private int parseDuration(String duration) {
        int result = 0;
        if (duration == null) {
            return result;
        }
        if (duration.contains(":")) {
            try {
                String min = duration.substring(0, duration.indexOf(':'));
                result += Integer.parseInt(min) * 60;
                result += Integer.parseInt(duration.substring(duration.indexOf(':') + 1));
            } catch (Exception e) {
                //ignore
            }
        } else {
            try {
                result += Integer.parseInt(duration);
            } catch (Exception e) {
                //ignore
            }
        }
        return result;
    }
}
