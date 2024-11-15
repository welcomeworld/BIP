package com.github.welcomeworld.bangumi.instrumentality.project.source.bili;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.CommentBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.SettingConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.fragment.WebLoginFragment;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BaseUrl;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BiliRetrofitManager;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.IndexNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.RelatedWebAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.SearchNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.UserWebAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.VideoDetailNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.VideoWebAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiRelatedBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BiliCommentBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BvToAvBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoRelatedBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.VideoUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebHomeRcmdData;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebHotSearchBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebLoginInfoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebLoginUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebSearchBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.bipplayer.DefaultBIPPlayer;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiliParser extends BaseParser {
    private static BiliParser instance;
    private static final List<String> typeWhiteList = Arrays.asList("av", "ogv", "bangumi");
    private static final Deque<List<String>> lastShowListDeque = new ArrayDeque<>();

    public static BiliParser getInstance() {
        if (instance == null) {
            instance = new BiliParser();
        }
        return instance;
    }

    private BiliParser() {
        initCookie();
    }

    @Override
    public String getTag() {
        return Constants.Source.BILI;
    }

    @Override
    public Map<Integer, Map<String, String>> getPlayerOptions() {
        Map<Integer, Map<String, String>> result = new HashMap<>();
        Map<String, String> playerOptions = new HashMap<>();
        playerOptions.put("max-buffer-size", (500 * 1024) + "");
        playerOptions.put("min-frames", "100");
        playerOptions.put("enable-accurate-seek", "1");
        playerOptions.put("start-on-prepared", "1");
        playerOptions.put("framedrop", "5");
        result.put(DefaultBIPPlayer.OPT_CATEGORY_PLAYER, playerOptions);

        Map<String, String> formatOptions = new HashMap<>();
        formatOptions.put("user_agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        formatOptions.put("headers", "referer:https://www.bilibili.com\r\n");
        result.put(DefaultBIPPlayer.OPT_CATEGORY_FORMAT, formatOptions);
        return result;
    }

    @Override
    public Map<String, String> getDownloadHeaders(VideoListBean videoListBean) {
        Map<String, String> headers = new HashMap<>();
        headers.put("referer", "https://www.bilibili.com");
        return headers;
    }

    @Override
    public List<VideoListBean> getRecommend(int pageNumber) {
        List<VideoListBean> result = new ArrayList<>();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("fresh_idx_1h", "" + pageNumber);
        parameters.put("fetch_row", (pageNumber * 3 + 1) + "");
        parameters.put("fresh_idx", pageNumber + "");
        parameters.put("brush", pageNumber + "");
        if (pageNumber == 1) {
            lastShowListDeque.clear();
        } else {
            String lastShowList = getLastShowList();
            if (!lastShowList.isEmpty()) {
                parameters.put("last_showlist", lastShowList);
            }
        }
        IndexNetAPI indexNetAPI = BiliRetrofitManager.getWbiRetrofit(BaseUrl.APIURL).create(IndexNetAPI.class);
        Call<WebHomeRcmdData> indexBeanCall = indexNetAPI.getRecommend(parameters);
        try {
            Response<WebHomeRcmdData> response = indexBeanCall.execute();
            if (response.body() == null || response.body().getData() == null) {
                LogUtil.w("DataLog", "获取不到数据:" + response.code());
                return result;
            }
            WebHomeRcmdData.Data rcmdData = response.body().getData();
            List<WebHomeRcmdData.Data.Item> moreData = rcmdData.getItem();
            List<String> lastShowList = new ArrayList<>();
            for (int i = 0; i < moreData.size(); i++) {
                WebHomeRcmdData.Data.Item item = moreData.get(i);
                if (item.getId() == 0) {
                    lastShowList.add(item.getGotoX() + "_n_" + item.getBusinessInfo().getSrcId());
                } else {
                    lastShowList.add(item.getGotoX() + "_n_" + item.getId());
                }
                if (!typeWhiteList.contains(item.getGotoX())) {
                    LogUtil.w("DataLog", "跳过非av:" + moreData.get(i).getGotoX());
                    moreData.remove(i);
                    i--;
                } else {
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setSourceName(Constants.Source.BILI);
                    videoListBean.setTitle(item.getTitle());
                    String tagName = item.getOwner().getName();
                    videoListBean.setTag(tagName);
                    videoListBean.setCoverPortrait(false);
                    videoListBean.setCover(item.getPic() + "@320w_200h_1e_1c.webp");
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
                    HashMap<String, String> extraMap = new HashMap<>();
                    extraMap.put("videoType", "av");
                    extraMap.put("bvid", item.getBvid());
                    videoBean.setSourceExternalData(new Gson().toJson(extraMap));
                    videoBean.setTitle(item.getTitle());
                    videoBean.setCover(videoListBean.getCover());
                    videoBean.setDuration(item.getDuration() * 1000L);
                    videoBean.setVideoKey(String.valueOf(item.getCid()));
                    videoBean.setUrl(item.getUri());
                    videoBeans.add(videoBean);
                    videoListBean.setVideoBeanList(videoBeans);
                    result.add(videoListBean);
                }
            }
            pushLastShowList(lastShowList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getLastShowList() {
        StringBuilder lastShowListBuilder = new StringBuilder();
        for (List<String> lastShowList : lastShowListDeque) {
            for (String showId : lastShowList) {
                lastShowListBuilder.append(showId);
                lastShowListBuilder.append(",");
            }
        }
        if (lastShowListBuilder.length() > 1) {
            lastShowListBuilder.deleteCharAt(lastShowListBuilder.length() - 1);
        }
        return lastShowListBuilder.toString();
    }

    private void pushLastShowList(List<String> showList) {
        lastShowListDeque.addLast(showList);
        if (lastShowListDeque.size() > 6) {
            lastShowListDeque.removeFirst();
        }
    }

    private void queryAVItemDetail(VideoBean currentVideoBean, String aid, String cid) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("avid", aid);
        parameters.put("cid", cid + "");
        String qn = getUserQuality();
        parameters.put("qn", qn);
        VideoWebAPI videoDetailNetAPI = BiliRetrofitManager.getWbiRetrofit(BaseUrl.APIURL).create(VideoWebAPI.class);
        try {
            Response<VideoUrlBean> urlResponse = videoDetailNetAPI.getVideoUrl(parameters).execute();
            if (urlResponse.body() == null || urlResponse.body().getCode() != 0) {
                return;
            }
            String description;
            //排序分辨率和确定当前选择的分辨率
            if (currentVideoBean.getQualityBeans().size() <= 1) {
                currentVideoBean.getQualityBeans().clear();
                List<Integer> qualityList = urlResponse.body().getData().getAccept_quality();
                TreeMap<Integer, String> qualityMap = new TreeMap<>();
                for (Integer quality : qualityList) {
                    qualityMap.put(quality, getVideoQuality(quality));
                }
                int selectIndex = 0;
                boolean matchQuality = false;
                for (String quality : qualityMap.values()) {
                    VideoQualityBean videoQualityBean = new VideoQualityBean();
                    videoQualityBean.setQuality(quality);
                    currentVideoBean.getQualityBeans().add(videoQualityBean);
                    if (!matchQuality) {
                        matchQuality = quality.equals(SettingConfig.getCurrentQuality());
                        currentVideoBean.setCurrentQualityIndex(selectIndex);
                    }
                    selectIndex++;
                }
            }
            currentVideoBean.setDash(true);
            VideoUrlBean.DataBean.DashBean dashData = urlResponse.body().getData().getDash();
            //select the best audio
            VideoUrlBean.DataBean.DashBean.AudioBean bestAudioBean = dashData.getAudio().get(0);
            boolean useLowAudio = Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
            for (VideoUrlBean.DataBean.DashBean.AudioBean audioBean : dashData.getAudio()) {
                if ((useLowAudio && audioBean.getId() < bestAudioBean.getId() ||
                        (!useLowAudio) && audioBean.getId() > bestAudioBean.getId())) {
                    bestAudioBean = audioBean;
                }
            }
            //update qualityBean's source url
            for (int j = 0; j < dashData.getVideo().size(); j++) {
                VideoUrlBean.DataBean.DashBean.VideoBean videoBean = dashData.getVideo().get(j);
                if (videoBean.getCodecid() != 7) {
                    //ignore codec without avc
                    continue;
                }
                VideoQualityBean qualityBean = new VideoQualityBean();
                description = getVideoQuality(videoBean.getId());
                boolean qualityMatch = false;
                for (int innerIndex = 0; innerIndex < currentVideoBean.getQualityBeans().size(); innerIndex++) {
                    VideoQualityBean innerBean = currentVideoBean.getQualityBeans().get(innerIndex);
                    if (description.equals(innerBean.getQuality())) {
                        qualityBean = innerBean;
                        qualityMatch = true;
                        break;
                    }
                }
                qualityBean.setRealVideoUrl(videoBean.getBase_url());
                qualityBean.setQuality(description);
                qualityBean.setRealAudioUrl(bestAudioBean.getBase_url());
                qualityBean.videoWidth = videoBean.width;
                qualityBean.videoHeight = videoBean.height;
                LogUtil.d("DataLog", qualityBean.getRealVideoUrl());
                //加入没有匹配到即原列表没有的分辨率
                if (!qualityMatch) {
                    currentVideoBean.getQualityBeans().add(qualityBean);
                }
            }
            //fix if the select quality source is null
            if (!currentVideoBean.getCurrentQualityBean().isAvailable()) {
                for (int select = currentVideoBean.getQualityBeans().size() - 1; select >= 0; select--) {
                    if (currentVideoBean.getQualityBeans().get(select).isAvailable()) {
                        currentVideoBean.setCurrentQualityIndex(select);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("BiliNextError", "error:" + e.getMessage());
        }
    }

    @Override
    public void search(String searchKey, String pn, SearchCallback searchCallback) {
        int pageNumber = Integer.parseInt(pn);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("page", pn);
        parameters.put("page_size", "42");
        int offset = (pageNumber - 1) * 24;
        parameters.put("dynamic_offset", "" + offset);
        parameters.put("keyword", searchKey);
        SearchNetAPI searchNetAPI = BiliRetrofitManager.getWbiRetrofit(BaseUrl.APIURL).create(SearchNetAPI.class);
        if (pageNumber == 1) {
            parameters.put("search_type", "media_bangumi");
            searchNetAPI.getSearchResult(parameters).enqueue(new SearchResultCallback(searchCallback, true));
            parameters.put("search_type", "media_ft");
            searchNetAPI.getSearchResult(parameters).enqueue(new SearchResultCallback(searchCallback, true));
        }
        parameters.put("search_type", "video");
        searchNetAPI.getSearchResult(parameters).enqueue(new SearchResultCallback(searchCallback, false));
    }

    private static class SearchResultCallback implements Callback<WebSearchBean> {
        private final SearchCallback searchCallback;
        boolean isBangumi;

        SearchResultCallback(SearchCallback searchCallback, boolean isBangumi) {
            this.searchCallback = searchCallback;
            this.isBangumi = isBangumi;
        }

        @Override
        public void onResponse(Call<WebSearchBean> call, Response<WebSearchBean> response) {
            if (response.body() == null || response.body().getData() == null) {
                return;
            }
            List<WebSearchBean.Data.Result> moreData;
            moreData = response.body().getData().getResult();
            if (moreData == null || moreData.size() == 0) {
                return;
            }
            List<VideoListBean> result = new ArrayList<>();
            for (int i = 0; i < moreData.size(); i++) {
                WebSearchBean.Data.Result searchData = moreData.get(i);
                bindSearchData(result, searchData, isBangumi);
            }
            searchCallback.onSearchResult(result);
        }

        @Override
        public void onFailure(Call<WebSearchBean> call, Throwable t) {

        }
    }

    private static void bindSearchData(List<VideoListBean> result, WebSearchBean.Data.Result searchData, boolean isBangumi) {
        VideoListBean videoListBean = new VideoListBean();
        videoListBean.setSourceName(Constants.Source.BILI);
        String searchTitle = searchData.getTitle();
        searchTitle = searchTitle.replaceAll("<em class=\"keyword\">", "").replaceAll("</em>", "");
        videoListBean.setTitle(searchTitle);
        videoListBean.setTag(isBangumi ? "bili番剧" : searchData.getAuthor());
        videoListBean.setCoverPortrait(isBangumi);
        String coverUrl = isBangumi ? searchData.getCover() : "https:" + searchData.getPic();
        videoListBean.setCover(coverUrl + (isBangumi ? "@240w_320h_1e_1c.webp" : "@320w_200h_1e_1c.webp"));
        ArrayList<VideoBean> videoBeans = new ArrayList<>();
        VideoBean videoBean = new VideoBean();
        videoBean.setTitle(searchTitle);
        videoBean.setCover(videoListBean.getCover());
        HashMap<String, String> extraMap = new HashMap<>();
        extraMap.put("videoType", isBangumi ? "bangumi" : "av");
        extraMap.put("bvid", searchData.getBvid());
        videoBean.setSourceExternalData(new Gson().toJson(extraMap));
        String uriString = isBangumi ? searchData.getUrl() : searchData.getArcurl();
        if (uriString.endsWith("/")) {
            uriString = uriString.substring(0, uriString.length() - 1);
        }
        Uri currentUri = Uri.parse(uriString);
        if (isBangumi) {
            videoBean.setVideoKey(currentUri.getPath().substring(currentUri.getPath().lastIndexOf('/') + 3));
        }
        videoBean.setUrl(uriString);
        videoBean.setDuration(parseDuration(searchData.getDuration()) * 1000L);
        videoBeans.add(videoBean);
        videoListBean.setVideoBeanList(videoBeans);
        result.add(videoListBean);
    }

    @Override
    public boolean isMatchParser(String key) {
        return Constants.Source.BILI.equals(key);
    }

    private static int parseDuration(String duration) {
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

    @Override
    public Fragment getLoginFragment() {
        return new WebLoginFragment();
    }

    @Override
    public List<VideoListBean> updateVideoList(List<VideoListBean> videoListBeans, int selectSourceIndex) {
        VideoBean currentVideoBean = videoListBeans.get(selectSourceIndex).getCurrentVideoBean();
        HashMap<String, String> extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
        if ("bangumi".equals(extraData.get("videoType"))) {
            return updateBangumiList(videoListBeans, selectSourceIndex);
        } else {
            return updateAvVideoList(videoListBeans, selectSourceIndex);
        }
    }

    private List<VideoListBean> updateBangumiList(List<VideoListBean> videoListBeans, int selectSourceIndex) {
        VideoListBean orignal = videoListBeans.get(selectSourceIndex);
        VideoBean currentVideoBean = videoListBeans.get(selectSourceIndex).getCurrentVideoBean();
        HashMap<String, String> extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
        String currentAid = extraData.get("aid");
        String currentCid = extraData.get("cid");
        if (currentAid != null && currentCid != null) {
            queryAVItemDetail(currentVideoBean, currentAid, currentCid);
            return videoListBeans;
        }
        Map<String, VideoBean> cacheVideoBeans = new HashMap<>();
        for (VideoBean cacheBean : orignal.getVideoBeanList()) {
            cacheVideoBeans.put(cacheBean.getVideoKey(), cacheBean);
        }
        Uri currentUri = Uri.parse(currentVideoBean.getUrl());
        LogUtil.d("BiliParser", "uri:" + currentUri);
        Map<String, String> parameters = new HashMap<>();
        if (currentUri.getPath() != null && currentUri.getPath().contains("ss")) {
            LogUtil.d("BiliParser", "sid:" + currentVideoBean.getVideoKey());
            parameters.put("season_id", currentVideoBean.getVideoKey());
        } else if (currentUri.getPath() != null && currentUri.getPath().contains("ep")) {
            LogUtil.d("BiliParser", "epid:" + currentVideoBean.getVideoKey());
            parameters.put("ep_id", currentVideoBean.getVideoKey());
        }
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.APIURL).create(VideoDetailNetAPI.class);
        try {
            Response<BangumiDetailPageBean> response = videoDetailNetAPI.getBangumiDetailPageInfo(parameters).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                LogUtil.w("BiliParser", "response error:" + response.body().getCode());
                return videoListBeans;
            }
            BangumiDetailPageBean.Result detailResult = response.body().getResult();
            videoListBeans.clear();
            boolean matchCid = false;
            VideoListBean videoListBean = new VideoListBean();
            videoListBean.setSourceName(Constants.Source.BILI);
            videoListBean.setTag(orignal.getTag());
            videoListBean.setCoverPortrait(orignal.isCoverPortrait());
            videoListBean.setCover(orignal.getCover());
            videoListBean.setTitle(detailResult.getTitle());
            videoListBean.setSeasonTitle(detailResult.getTitle());
            videoListBean.setVideoListDes(detailResult.getEvaluate());
            videoListBean.setSelectIndex(orignal.getSelectIndex());
            List<BangumiDetailPageBean.Result.Episodes> episodes = detailResult.getEpisodes();
            extraData.put("season_id", "" + detailResult.getSeasonId());
            for (int i = 0; i < episodes.size(); i++) {
                String title = episodes.get(i).getTitle();
                if (!TextUtils.isEmpty(episodes.get(i).getLongTitle())) {
                    title = title + " " + episodes.get(i).getLongTitle();
                }
                LogUtil.d("BiliParser", "parse Data:" + title);
                long cid = episodes.get(i).getCid();
                long aid = episodes.get(i).getAid();
                if (currentCid == null || !currentCid.equals("" + cid) || cid == 0) {
                    String link = episodes.get(i).getLink();
                    if (cid == 0 && link.contains("/BV")) {
                        List<String> ids = getAvInfo(link.substring(link.lastIndexOf("/") + 1));
                        if (ids.size() >= 2) {
                            extraData.put("aid", ids.get(0));
                            extraData.put("cid", ids.get(1));
                            extraData.put("videoType", "av");
                        }
                    } else {
                        extraData.put("aid", String.valueOf(aid));
                        extraData.put("cid", String.valueOf(cid));
                        extraData.put("videoType", "bangumi");
                    }
                    String finalCid = extraData.get("cid");
                    VideoBean videoBean = new VideoBean();
                    boolean haveData = false;
                    for (VideoBean cacheBean : videoListBean.getVideoBeanList()) {
                        if (cacheBean.getVideoKey().equals(finalCid)) {
                            videoBean = cacheBean;
                            haveData = true;
                            break;
                        }
                    }
                    videoBean.setVideoKey(finalCid);
                    videoBean.setUrl(currentVideoBean.getUrl());
                    videoBean.setSourceExternalData(new Gson().toJson(extraData));
                    videoBean.setDanmakuUrl("http://comment.bilibili.com/" + cid + ".xml");
                    videoBean.setTitle(title);
                    if (!haveData) {
                        VideoBean cacheBean = cacheVideoBeans.get(videoBean.getVideoKey());
                        if (cacheBean != null) {
                            videoBean.setPlayPosition(cacheBean.getPlayPosition());
                            videoBean.setCurrentQualityIndex(cacheBean.getCurrentQualityIndex());
                        }
                        videoListBean.getVideoBeanList().add(videoBean);
                    }
                    continue;
                }
                matchCid = true;
                videoListBean.setSelectIndex(i);
                extraData.put("aid", String.valueOf(episodes.get(i).getAid()));
                extraData.put("cid", String.valueOf(cid));
                extraData.put("videoType", "bangumi");
                currentVideoBean.setSourceExternalData(new Gson().toJson(extraData));
                currentVideoBean.setDanmakuUrl("http://comment.bilibili.com/" + cid + ".xml");
                currentVideoBean.setTitle(title);
                videoListBean.getVideoBeanList().add(currentVideoBean);
                queryAVItemDetail(currentVideoBean, extraData.get("aid"), extraData.get("cid"));
            }
            if (!matchCid && videoListBean.getVideoBeanList().size() > 0) {
                currentVideoBean = videoListBean.getCurrentVideoBean();
                extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
                currentAid = extraData.get("aid");
                currentCid = extraData.get("cid");
                queryAVItemDetail(currentVideoBean, currentAid, currentCid);
            }
            videoListBeans.add(videoListBean);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("BiliNextError", "error:" + e.getMessage());
        }
        return videoListBeans;
    }

    private List<VideoListBean> updateAvVideoList(List<VideoListBean> videoListBeans, int selectSourceIndex) {
        VideoListBean orignal = videoListBeans.get(selectSourceIndex);
        VideoBean currentVideoBean = orignal.getCurrentVideoBean();
        HashMap<String, String> extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
        String currentAid = extraData.get("aid");
        String currentCid = extraData.get("cid");
        if (currentAid != null && currentCid != null) {
            queryAVItemDetail(currentVideoBean, currentAid, currentCid);
            return videoListBeans;
        }
        Map<String, VideoBean> cacheVideoBeans = new HashMap<>();
        for (VideoBean cacheBean : orignal.getVideoBeanList()) {
            cacheVideoBeans.put(cacheBean.getVideoKey(), cacheBean);
        }
        String bvid = extraData.get("bvid");
        if (bvid != null) {
            List<String> ids = getAvInfo(bvid);
            if (ids.size() >= 2) {
                currentAid = ids.get(0);
            }
        } else {
            Uri currentUri = Uri.parse(currentVideoBean.getUrl());
            LogUtil.d("BiliParser", "uri:" + currentUri);
            currentAid = currentUri.getPath().substring(1);
        }
        try {
            currentCid = currentVideoBean.getVideoKey();
        } catch (Exception e) {
            //ignore
        }
        LogUtil.d("BiliParser", "cid:" + currentCid);
        currentAid = currentAid.substring(0, currentAid.contains("/") ? currentAid.indexOf("/") : currentAid.length());
        LogUtil.d("BiliParser", "final aid:" + currentAid);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("aid", currentAid);
        VideoWebAPI videoDetailNetAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.APIURL).create(VideoWebAPI.class);
        try {
            Response<VideoDetailPageBean> response = videoDetailNetAPI.getAvDetailPageInfo(parameters).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                LogUtil.w("BiliParser", "response error:" + response.body().getCode());
                return videoListBeans;
            }
            boolean matchCid = false;
            VideoListBean videoListBean = new VideoListBean();
            videoListBean.setSourceName(Constants.Source.BILI);
            videoListBean.setTag(orignal.getTag());
            videoListBean.setCoverPortrait(orignal.isCoverPortrait());
            videoListBean.setCover(orignal.getCover());
            videoListBean.setTitle(response.body().getData().getTitle());
            videoListBean.setSeasonTitle("选集");
            videoListBean.setVideoListDes(response.body().getData().getDesc());
            videoListBean.setSelectIndex(orignal.getSelectIndex());
            videoListBean.getVideoBeanList().clear();
            boolean useListTitle = response.body().getData().getPages().size() <= 1;
            for (int i = 0; i < response.body().getData().getPages().size(); i++) {
                long cid = response.body().getData().getPages().get(i).getCid();
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
                    videoBean.setDanmakuUrl("http://comment.bilibili.com/" + cid + ".xml");
                    if (useListTitle) {
                        videoBean.setTitle(videoListBean.getTitle());
                    } else {
                        videoBean.setTitle(response.body().getData().getPages().get(i).getPart());
                    }
                    if (!haveData) {
                        VideoBean cacheBean = cacheVideoBeans.get(videoBean.getVideoKey());
                        if (cacheBean != null) {
                            videoBean.setPlayPosition(cacheBean.getPlayPosition());
                            videoBean.setCurrentQualityIndex(cacheBean.getCurrentQualityIndex());
                        }
                        videoListBean.getVideoBeanList().add(videoBean);
                    }
                    continue;
                }
                matchCid = true;
                videoListBean.setSelectIndex(i);
                extraData.put("aid", currentAid);
                extraData.put("cid", String.valueOf(cid));
                currentVideoBean.setSourceExternalData(new Gson().toJson(extraData));
                currentVideoBean.setDanmakuUrl("http://comment.bilibili.com/" + cid + ".xml");
                if (useListTitle) {
                    currentVideoBean.setTitle(videoListBean.getTitle());
                } else {
                    currentVideoBean.setTitle(response.body().getData().getPages().get(i).getPart());
                }
                videoListBean.getVideoBeanList().add(currentVideoBean);
                queryAVItemDetail(currentVideoBean, extraData.get("aid"), extraData.get("cid"));
            }
            if (!matchCid && videoListBean.getVideoBeanList().size() > 0) {
                currentVideoBean = videoListBean.getCurrentVideoBean();
                currentCid = currentVideoBean.getVideoKey();
                LogUtil.d("BiliParser", "not here error");
                queryAVItemDetail(currentVideoBean, currentAid, currentCid);
            }
            videoListBeans.clear();
            videoListBeans.add(videoListBean);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("BiliNextError", "error:" + e.getMessage());
        }
        return videoListBeans;
    }

    private List<String> getAvInfo(String bvid) {
        List<String> result = new ArrayList<>();
        VideoWebAPI videoDetailNetAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.APIURL).create(VideoWebAPI.class);
        try {
            Response<BvToAvBean> response = videoDetailNetAPI.getAvInfo(bvid).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                return result;
            }
            result.add("" + response.body().getData().getAid());
            result.add("" + response.body().getData().getCid());
        } catch (Exception e) {

        }
        return result;
    }

    private String getUserQuality() {
        switch (SettingConfig.getCurrentQuality()) {
            case "240P":
                return "6";
            case "360P":
                return "16";
            case "480P":
                return "32";
            case "720P":
                return "64";
            case "720P60":
                return "74";
            case "1080P":
                return "80";
            case "1080P60":
                return "116";
            case "4K":
                return "120";
            case "HDR":
                return "125";
            case "杜比视界":
                return "126";
            case "8K":
                return "127";
            case "1080P+":
            default:
                return "112";
        }
    }

    private static String getVideoQuality(int id) {
        switch (id) {
            case 6:
                return "240P";
            case 16:
                return "360P";
            case 32:
                return "480P";
            case 74:
                return "720P60";
            case 80:
                return "1080P";
            case 112:
                return "1080P+";
            case 116:
                return "1080P60";
            case 120:
                return "4K";
            case 125:
                return "HDR";
            case 126:
                return "杜比视界";
            case 127:
                return "8K";
            default:
            case 64:
                return "720P";
        }
    }

    public static WebLoginUrlBean.Data getWebLoginUrl() {
        UserWebAPI userWebAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.PASSPORTURL).create(UserWebAPI.class);
        try {
            Response<WebLoginUrlBean> response = userWebAPI.getLoginUrl().execute();
            if (response.body() == null || response.body().getData() == null) {
                LogUtil.w("jsTest", "key response is null");
                return null;
            }
            return response.body().getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getWebLoginInfo(String oauthKey) {
        UserWebAPI userWebAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.PASSPORTURL).create(UserWebAPI.class);
        try {
            Response<WebLoginInfoBean> response = userWebAPI.getLoginInfo(oauthKey).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                return false;
            }
            List<String> cookies = response.headers().values("set-cookie");
            for (String cookie : cookies) {
                if (cookie.contains("Secure")) {
                    CookieManager.getInstance().setCookie("https://bilibili.com", cookie);
                } else {
                    CookieManager.getInstance().setCookie("bilibili.com", cookie);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkLogin() {
        String cookie = CookieManager.getInstance().getCookie("bilibili.com");
        return cookie != null && cookie.contains("DedeUserID");
    }

    public static void clearLoginStatus() {
        //todo 只清除bilibili的cookie
        CookieManager.getInstance().removeAllCookies(null);
    }

    @Override
    public boolean hasComment() {
        return true;
    }

    @Override
    public CommentBean getComment(VideoBean currentVideoBean, int page) {
        HashMap<String, String> extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
        String currentAid = extraData.get("aid");
        if (currentAid == null) {
            return super.getComment(currentVideoBean, page);
        }
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.APIURL).create(VideoDetailNetAPI.class);
        try {
            Response<BiliCommentBean> response = videoDetailNetAPI.getComment(Long.parseLong(currentAid), page).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                return super.getComment(currentVideoBean, page);
            }
            CommentBean result = new CommentBean();
            result.commentId = getTag() + "_" + currentAid;
            for (BiliCommentBean.DataBean.RepliesBean reply : response.body().getData().getReplies()) {
                CommentBean.CommentDataBean comment = new CommentBean.CommentDataBean();
                comment.comment = reply.getContent().getMessage();
                comment.upperName = reply.getMember().getUname();
                comment.avatar = reply.getMember().getAvatar();
                comment.upTime = reply.getCtime() * 1000;
                comment.commentDataLongId = reply.getRpid();
                comment.commentDataId = getTag() + "_" + currentAid + "_" + comment.commentDataLongId;
                List<BiliCommentBean.DataBean.RepliesBean> subReplys = reply.getReplies();
                if (subReplys != null && subReplys.size() > 0) {
                    comment.subCommentCount = reply.getRcount();
                    comment.subComment = new ArrayList<>();
                    for (BiliCommentBean.DataBean.RepliesBean subReply : subReplys) {
                        CommentBean.CommentDataBean subComment = new CommentBean.CommentDataBean();
                        String subContent = subReply.getContent().getMessage();
                        if (subContent.startsWith("回复")) {
                            subComment.comment = " " + subContent;
                        } else {
                            subComment.comment = ": " + subContent;
                        }
                        subComment.upperName = subReply.getMember().getUname();
                        subComment.avatar = subReply.getMember().getAvatar();
                        subComment.upTime = subReply.getCtime() * 1000;
                        subComment.commentDataLongId = subReply.getRpid();
                        subComment.commentDataId = getTag() + "_" + currentAid + "_" + subComment.commentDataLongId;
                        comment.subComment.add(subComment);
                    }
                }
                result.comments.add(comment);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.getComment(currentVideoBean, page);
    }

    @Override
    public List<CommentBean.CommentDataBean> getSubComment(CommentBean.CommentDataBean parent) {

        int page = Math.round(parent.subComment.size() * 1.0f / 20 + 0.5f);
        String currentAid = parent.commentDataId.replace(getTag() + "_", "").replace("_" + parent.commentDataLongId, "");
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.APIURL).create(VideoDetailNetAPI.class);
        try {
            Response<BiliCommentBean> response = videoDetailNetAPI.getSubComment(parent.commentDataLongId, Long.parseLong(currentAid), page).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                return super.getSubComment(parent);
            }
            List<CommentBean.CommentDataBean> result = new ArrayList<>();
            for (BiliCommentBean.DataBean.RepliesBean reply : response.body().getData().getReplies()) {
                CommentBean.CommentDataBean comment = new CommentBean.CommentDataBean();
                String subCommentContent = reply.getContent().getMessage();
                if (subCommentContent.startsWith("回复")) {
                    comment.comment = " " + subCommentContent;
                } else {
                    comment.comment = ": " + subCommentContent;
                }
                comment.upperName = reply.getMember().getUname();
                comment.avatar = reply.getMember().getAvatar();
                comment.upTime = reply.getCtime() * 1000;
                comment.commentDataLongId = reply.getRpid();
                comment.commentDataLongId = reply.getRpid();
                comment.commentDataId = getTag() + "_" + currentAid + "_" + comment.commentDataLongId;
                result.add(comment);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.getSubComment(parent);
    }

    @Override
    public boolean canGetRelated() {
        return true;
    }

    @Override
    public List<VideoListBean> getRelated(VideoListBean videoListBean) {
        HashMap<String, String> extraData = new Gson().fromJson(videoListBean.getCurrentVideoBean().getSourceExternalData(), HashMap.class);
        if ("bangumi".equals(extraData.get("videoType"))) {
            return getBangumiRelated(videoListBean);
        } else {
            return getVideoRelated(videoListBean);
        }
    }

    private List<VideoListBean> getBangumiRelated(VideoListBean videoListBean) {
        List<VideoListBean> result = new ArrayList<>();
        VideoBean currentVideoBean = videoListBean.getCurrentVideoBean();
        HashMap<String, String> extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("season_id", extraData.get("season_id"));
        RelatedWebAPI relatedWebAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.APIURL).create(RelatedWebAPI.class);
        Call<BangumiRelatedBean> bangumiRelatedBeanCall = relatedWebAPI.getRelatedBangumi(parameters);
        try {
            Response<BangumiRelatedBean> response = bangumiRelatedBeanCall.execute();
            if (response.body() == null || response.body().getData() == null) {
                LogUtil.w("DataLog", "获取不到数据" +
                        "");
                return result;
            }
            BangumiRelatedBean.Data rcmdData = response.body().getData();
            List<BangumiRelatedBean.Data.Season> moreData = rcmdData.getSeason();
            for (int i = 0; i < moreData.size(); i++) {
                BangumiRelatedBean.Data.Season item = moreData.get(i);
                VideoListBean relatedVideoListBean = new VideoListBean();
                relatedVideoListBean.setSourceName(Constants.Source.BILI);
                relatedVideoListBean.setTitle(item.getTitle());
                relatedVideoListBean.setTag("bili番剧");
                relatedVideoListBean.setCoverPortrait(false);
                relatedVideoListBean.setCover(item.getCover() + "@320w_200h_1e_1c.webp");
                ArrayList<VideoBean> videoBeans = new ArrayList<>();
                VideoBean videoBean = new VideoBean();
                HashMap<String, String> extraMap = new HashMap<>();
                extraMap.put("videoType", "bangumi");
                videoBean.setSourceExternalData(new Gson().toJson(extraMap));
                videoBean.setTitle(item.getTitle());
                videoBean.setCover(relatedVideoListBean.getCover());
                videoBean.setVideoKey("" + item.getSeasonId());
                videoBean.setUrl(item.getUrl());
                videoBeans.add(videoBean);
                relatedVideoListBean.setVideoBeanList(videoBeans);
                result.add(relatedVideoListBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<VideoListBean> getVideoRelated(VideoListBean videoListBean) {
        List<VideoListBean> result = new ArrayList<>();
        VideoBean currentVideoBean = videoListBean.getCurrentVideoBean();
        HashMap<String, String> extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("aid", extraData.get("aid"));
        RelatedWebAPI relatedWebAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.APIURL).create(RelatedWebAPI.class);
        Call<VideoRelatedBean> indexBeanCall = relatedWebAPI.getRelatedVideo(parameters);
        try {
            Response<VideoRelatedBean> response = indexBeanCall.execute();
            if (response.body() == null || response.body().getData() == null) {
                LogUtil.w("DataLog", "获取不到数据" +
                        "");
                return result;
            }
            List<VideoRelatedBean.Data> moreData = response.body().getData();
            for (int i = 0; i < moreData.size(); i++) {
                VideoRelatedBean.Data item = moreData.get(i);
                VideoListBean relatedVideoListBean = new VideoListBean();
                relatedVideoListBean.setSourceName(Constants.Source.BILI);
                relatedVideoListBean.setTitle(item.getTitle());
                String tagName = item.getOwner().getName();
                relatedVideoListBean.setTag(tagName);
                relatedVideoListBean.setCoverPortrait(false);
                relatedVideoListBean.setCover(item.getPic() + "@320w_200h_1e_1c.webp");
                ArrayList<VideoBean> videoBeans = new ArrayList<>();
                VideoBean videoBean = new VideoBean();
                HashMap<String, String> extraMap = new HashMap<>();
                extraMap.put("videoType", "av");
                extraMap.put("bvid", item.getBvid());
                videoBean.setSourceExternalData(new Gson().toJson(extraMap));
                videoBean.setTitle(item.getTitle());
                videoBean.setCover(relatedVideoListBean.getCover());
                videoBean.setDuration(item.getDuration() * 1000L);
                videoBean.setVideoKey(String.valueOf(item.getCid()));
                videoBean.setUrl("https://www.bilibili.com/video/" + item.getBvid());
                videoBeans.add(videoBean);
                relatedVideoListBean.setVideoBeanList(videoBeans);
                result.add(relatedVideoListBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void initCookie() {
        Request.Builder requestBuilder = new Request.Builder().url("https://www.bilibili.com/");
        BiliOkHttpClientManager.getInstance().getNormalOkHttpClient().newCall(requestBuilder.build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
            }
        });
    }

    @Override
    public boolean canGetHotSearch() {
        return true;
    }

    @Override
    public List<String> getHotSearch() {
        List<String> result = new ArrayList<>();
        SearchNetAPI searchNetAPI = BiliRetrofitManager.getWbiRetrofit(BaseUrl.APIURL).create(SearchNetAPI.class);
        Call<WebHotSearchBean> hotSearchBeanCall = searchNetAPI.getHotSearch();
        try {
            Response<WebHotSearchBean> response = hotSearchBeanCall.execute();
            if (response.body() == null || response.body().data == null) {
                LogUtil.w("DataLog", "获取不到数据");
                return result;
            }
            for (WebHotSearchBean.Data.Trending.HotSearch hotSearch : response.body().data.trending.hotSearch) {
                result.add(hotSearch.keyword);
            }
        } catch (Exception e) {

        }
        return result;
    }
}
