package com.github.welcomeworld.bangumi.instrumentality.project.source.bili;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;

import androidx.fragment.app.Fragment;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.SettingConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api.BiliLocalStatus;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.fragment.LoginFragment;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BaseUrl;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BiliRetrofitManager;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.IndexNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.SearchNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.UserNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.VideoDetailNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BangumiUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.BvToAvBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.IndexRecommendBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.IndexRecommendDataBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.LoginKeyBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.LoginResultBean;
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
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Response;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class BiliParser extends BaseParser {
    private static BiliParser instance;

    public static BiliParser getInstance() {
        if (instance == null) {
            instance = new BiliParser();
        }
        return instance;
    }

    @Override
    public String getTag() {
        return "BiliParser";
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
        result.put(IjkMediaPlayer.OPT_CATEGORY_PLAYER, playerOptions);

        Map<String, String> formatOptions = new HashMap<>();
//        formatOptions.put("reconnect","1");
//        formatOptions.put("safe","0");
//        formatOptions.put("dns_cache_clear","1");
//        formatOptions.put("protocol_whitelist","rtmp,concat,ffconcat,file,subfile,http,https,tls,rtp,tcp,udp,crypto");
        formatOptions.put("user_agent", "Bilibili Freedoooooom/MarkII");
        result.put(IjkMediaPlayer.OPT_CATEGORY_FORMAT, formatOptions);
        return result;
    }

    @Override
    public List<VideoListBean> refreshRecommend() {
        List<VideoListBean> result = new ArrayList<>();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("pull", "true");
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

    private void queryBangumiItemDetail(VideoBean currentVideoBean, String aid, String cid) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("aid", aid);
        parameters.put("cid", cid);
        parameters.put("qn", getUserQuality());
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APIURL).create(VideoDetailNetAPI.class);
        try {
            Response<BangumiUrlBean> urlResponse = videoDetailNetAPI.getBangumiUrl(parameters).execute();

            if (urlResponse.body() == null || urlResponse.body().getCode() != 0) {
                return;
            }
            String description;
            if (currentVideoBean.getQualityBeans().size() <= 1) {
                currentVideoBean.getQualityBeans().clear();
                List<Integer> qualityList = urlResponse.body().getAcceptQuality();
                TreeMap<Integer, String> qualityMap = new TreeMap<>();
                for (Integer quality : qualityList) {
                    switch (quality) {
                        case 16:
                            qualityMap.put(16, "360P");
                            break;
                        case 32:
                            qualityMap.put(32, "480P");
                            break;
                        case 64:
                            qualityMap.put(64, "720P");
                            break;
                        case 80:
                            qualityMap.put(80, "1080P");
                            break;
                        case 112:
                        default:
                            qualityMap.put(112, "1080P+");
                            break;
                    }
                }
                int selectIndex = 0;
                for (String quality : qualityMap.values()) {
                    VideoQualityBean videoQualityBean = new VideoQualityBean();
                    videoQualityBean.setQuality(quality);
                    currentVideoBean.getQualityBeans().add(videoQualityBean);
                    if (quality.equals(SettingConfig.getCurrentQuality())) {
                        currentVideoBean.setCurrentQualityIndex(selectIndex);
                    }
                    selectIndex++;
                }
            }
            if (urlResponse.body().getDash() != null) {
                currentVideoBean.setDash(true);
                for (int j = 0; j < urlResponse.body().getDash().getVideo().size(); j++) {
                    VideoQualityBean qualityBean = new VideoQualityBean();
                    qualityBean.setRealVideoUrl(urlResponse.body().getDash().getVideo().get(j).getBaseUrl());
                    int quality = urlResponse.body().getDash().getVideo().get(j).getId();
                    switch (quality) {
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
                        case 112:
                        default:
                            description = "1080P+";
                            break;
                    }
                    qualityBean.setQuality(description);
                    qualityBean.setRealAudioUrl(urlResponse.body().getDash().getAudio().get(0).getBaseUrl());
                    currentVideoBean.getQualityBeans().add(qualityBean);
                    if (quality == urlResponse.body().getQuality()) {
                        currentVideoBean.setCurrentQualityIndex(currentVideoBean.getQualityBeans().size() - 1);
                    }
                }
            } else {
                currentVideoBean.setDash(false);
                VideoQualityBean qualityBean = new VideoQualityBean();
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
                    case 112:
                    default:
                        description = "1080P+";
                        break;
                }
                boolean qualityMatch = false;
                for (int innerIndex = 0; innerIndex < currentVideoBean.getQualityBeans().size(); innerIndex++) {
                    VideoQualityBean innerBean = currentVideoBean.getQualityBeans().get(innerIndex);
                    if (description.equals(innerBean.getQuality())) {
                        qualityBean = innerBean;
                        qualityMatch = true;
                        currentVideoBean.setCurrentQualityIndex(innerIndex);
                        break;
                    }
                }
                qualityBean.setRealVideoUrl(urlResponse.body().getDurl().get(0).getUrl());
                qualityBean.setQuality(description);
                LogUtil.e("DataLog", qualityBean.getRealVideoUrl());
                if (!qualityMatch) {
                    currentVideoBean.getQualityBeans().add(qualityBean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("BiliNextError", "error:" + e.getMessage());
        }
    }

    private void queryAVItemDetail(VideoBean currentVideoBean, String aid, String cid) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("aid", aid);
        parameters.put("cid", cid + "");
        parameters.put("qn", getUserQuality());
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(VideoDetailNetAPI.class);
        try {
            Response<VideoUrlBean> urlResponse = videoDetailNetAPI.getVideoUrl(parameters).execute();
            if (urlResponse.body() == null || urlResponse.body().getCode() != 0) {
                return;
            }
            String description;
            if (currentVideoBean.getQualityBeans().size() <= 1) {
                currentVideoBean.getQualityBeans().clear();
                List<Integer> qualityList = urlResponse.body().getData().getAccept_quality();
                TreeMap<Integer, String> qualityMap = new TreeMap<>();
                for (Integer quality : qualityList) {
                    switch (quality) {
                        case 16:
                            qualityMap.put(16, "360P");
                            break;
                        case 32:
                            qualityMap.put(32, "480P");
                            break;
                        case 64:
                            qualityMap.put(64, "720P");
                            break;
                        case 80:
                            qualityMap.put(80, "1080P");
                            break;
                        case 112:
                        default:
                            qualityMap.put(112, "1080P+");
                            break;
                    }
                }
                int selectIndex = 0;
                for (String quality : qualityMap.values()) {
                    VideoQualityBean videoQualityBean = new VideoQualityBean();
                    videoQualityBean.setQuality(quality);
                    currentVideoBean.getQualityBeans().add(videoQualityBean);
                    if (quality.equals(SettingConfig.getCurrentQuality())) {
                        currentVideoBean.setCurrentQualityIndex(selectIndex);
                    }
                    selectIndex++;
                }
            }
            if (urlResponse.body().getData().getDash() != null) {
                currentVideoBean.setDash(true);
                for (int j = 0; j < urlResponse.body().getData().getDash().getVideo().size(); j++) {
                    VideoQualityBean qualityBean = new VideoQualityBean();
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
                        case 112:
                        default:
                            description = "1080P+";
                            break;
                    }
                    boolean qualityMatch = false;
                    for (int innerIndex = 0; innerIndex < currentVideoBean.getQualityBeans().size(); innerIndex++) {
                        VideoQualityBean innerBean = currentVideoBean.getQualityBeans().get(innerIndex);
                        if (description.equals(innerBean.getQuality())) {
                            qualityBean = innerBean;
                            qualityMatch = true;
                            currentVideoBean.setCurrentQualityIndex(innerIndex);
                            break;
                        }
                    }
                    qualityBean.setRealVideoUrl(urlResponse.body().getData().getDash().getVideo().get(j).getBase_url());
                    qualityBean.setQuality(description);
                    qualityBean.setRealAudioUrl(urlResponse.body().getData().getDash().getAudio().get(0).getBase_url());
                    LogUtil.e("DataLog", qualityBean.getRealVideoUrl());
                    if (!qualityMatch) {
                        currentVideoBean.getQualityBeans().add(qualityBean);
                    }
                }
            } else {
                currentVideoBean.setDash(false);
                VideoQualityBean qualityBean = new VideoQualityBean();
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
                    case 112:
                    default:
                        description = "1080P+";
                        break;
                }
                boolean qualityMatch = false;
                for (int innerIndex = 0; innerIndex < currentVideoBean.getQualityBeans().size(); innerIndex++) {
                    VideoQualityBean innerBean = currentVideoBean.getQualityBeans().get(innerIndex);
                    if (description.equals(innerBean.getQuality())) {
                        qualityBean = innerBean;
                        qualityMatch = true;
                        currentVideoBean.setCurrentQualityIndex(innerIndex);
                        break;
                    }
                }
                qualityBean.setRealVideoUrl(urlResponse.body().getData().getDurl().get(0).getUrl());
                qualityBean.setQuality(description);
                LogUtil.e("DataLog", qualityBean.getRealVideoUrl());
                if (!qualityMatch) {
                    currentVideoBean.getQualityBeans().add(qualityBean);
                }
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

    public static String login(String userName, String rawPassword) {
        if (BiliLocalStatus.isLogin()) {
            Log.e("jsTest", "user already login");
            return "success";
        }
        UserNetAPI userNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.PASSPORTURL).create(UserNetAPI.class);
        try {
            Response<LoginKeyBean> response = userNetAPI.getKey().execute();
            if (response.body() == null || response.body().getData() == null) {
                Log.e("jsTest", "key response is null");
                return "";
            }
            Log.e("jsTest", "key response normal");
            LoginKeyBean.AuthKey authKey = response.body().getData();
            String password = authKey.encypt(authKey.getHash() + rawPassword, authKey.getKey());
            Response<LoginResultBean> loginResponse = userNetAPI.login(userName, password, "device_meta", "dt", "main.homepage.avatar-nologin.all.click", "bilibili://live/home").execute();
            return parseLoginResponse(loginResponse);
        } catch (Exception e) {
            e.printStackTrace();
            //
        }
        return "";
    }

    public static String acquireAccessTokenV2(String code) {
        Log.e("JsBridgeDispatcher", "acquireAccess by code" + code);
        UserNetAPI userNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.PASSPORTURL).create(UserNetAPI.class);
        try {
            Response<LoginResultBean> loginResponse = userNetAPI.acquireAccessTokenV2(code, "authorization_code").execute();
            return parseLoginResponse(loginResponse);
        } catch (Exception e) {
            e.printStackTrace();
            //
        }
        return "";
    }

    private static String parseLoginResponse(Response<LoginResultBean> loginResponse) {
        if (loginResponse.body() == null) {
            Log.e("jsTest", "login response is null");
            return "";
        }
        if (loginResponse.body().getCode() == 0) {
            String redirectUrl = loginResponse.body().getData().getUrl();
            if (redirectUrl != null && !redirectUrl.isEmpty()) {
                return redirectUrl;
            }
            Log.e("LoginActivity", "登录成功" + loginResponse.body().getCode());
            LoginResultBean.TokenInfoBean token = loginResponse.body().getData().getTokenInfo();
            BiliLocalStatus.setLogin(true);
            BiliLocalStatus.setAccessKey(token.getAccessToken());
            BiliLocalStatus.setMid(token.getMid());
            BiliLocalStatus.setToken(token);
            CookieManager cookieManager = CookieManager.getInstance();
            LoginResultBean.CookieInfoBean cookieInfoBean = loginResponse.body().getData().getCookieInfo();
            for (String domain : cookieInfoBean.getDomains()) {
                for (int i = 0; i < cookieInfoBean.getCookies().size(); i++) {
                    cookieManager.setCookie(domain, cookieInfoBean.getCookies().get(i).getName() + "=" + cookieInfoBean.getCookies().get(i).getValue());
                }
            }
            return "success";
        } else if (loginResponse.body().getCode() == -105) {
            Uri uri = Uri.parse(loginResponse.body().getData().getUrl());
            Log.e("jsTest", "url:" + uri);
            return loginResponse.body().getData().getUrl();
        } else {
            Log.e("LoginActivity", "fail:" + loginResponse.body().getCode());
        }
        return "";
    }

    @Override
    public Fragment getLoginFragment() {
        return new LoginFragment();
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
            queryBangumiItemDetail(currentVideoBean, currentAid, currentCid);
            return videoListBeans;
        }
        Map<String, VideoBean> cacheVideoBeans = new HashMap<>();
        List<Integer> selectIndexs = new ArrayList<>();
        for (VideoListBean indexBean : videoListBeans) {
            selectIndexs.add(indexBean.getSelectIndex());
            for (VideoBean cacheBean : indexBean.getVideoBeanList()) {
                cacheVideoBeans.put(cacheBean.getVideoKey(), cacheBean);
            }
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
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APIURL).create(VideoDetailNetAPI.class);
        try {
            Response<BangumiDetailPageBean> response = videoDetailNetAPI.getBangumiDetailPageInfo(parameters).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                LogUtil.e("BiliParser", "response error:" + response.body().getCode());
                return videoListBeans;
            }
            BangumiDetailPageBean.Result detailResult = response.body().getResult();
            videoListBeans.clear();
            int loopIndex = 0;
            for (BangumiDetailPageBean.Result.Modules module : detailResult.getModules()) {
                boolean matchCid = false;
                LogUtil.e("BiliParser", "parse Module:" + module.getTitle());
                VideoListBean videoListBean = new VideoListBean();
                videoListBean.setSourceName(Constants.Source.BILI);
                videoListBean.setTag(orignal.getTag());
                videoListBean.setCoverPortrait(orignal.isCoverPortrait());
                videoListBean.setCover(orignal.getCover());
                videoListBean.setTitle(detailResult.getTitle());
                videoListBean.setSeasonTitle(module.getTitle());
                videoListBean.setVideoListDes(detailResult.getEvaluate());
                if (selectIndexs.size() > loopIndex) {
                    videoListBean.setSelectIndex(selectIndexs.get(loopIndex));
                }
                List<BangumiDetailPageBean.Result.Modules.Data.Episodes> episodes = module.getData().getEpisodes();
                for (int i = 0; i < episodes.size(); i++) {
                    String title = episodes.get(i).getTitle();
                    if (!TextUtils.isEmpty(episodes.get(i).getLongTitle())) {
                        title = title + " " + episodes.get(i).getLongTitle();
                    }
                    LogUtil.e("BiliParser", "parse Data:" + title);
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
                    queryBangumiItemDetail(currentVideoBean, extraData.get("aid"), extraData.get("cid"));
                }
                if (!matchCid && videoListBean.getVideoBeanList().size() > 0 && loopIndex == selectSourceIndex) {
                    currentVideoBean = videoListBean.getCurrentVideoBean();
                    extraData = new Gson().fromJson(currentVideoBean.getSourceExternalData(), HashMap.class);
                    currentAid = extraData.get("aid");
                    currentCid = extraData.get("cid");
                    queryBangumiItemDetail(currentVideoBean, currentAid, currentCid);
                }
                loopIndex++;
                videoListBeans.add(videoListBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("BiliNextError", "error:" + e.getMessage());
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
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getRetrofit(BaseUrl.APPURL).create(VideoDetailNetAPI.class);
        try {
            Response<VideoDetailPageBean> response = videoDetailNetAPI.getAvDetailPageInfo(parameters).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                LogUtil.e("BiliParser", "response error:" + response.body().getCode());
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
                currentVideoBean.setDanmakuUrl(response.body().getData().getPages().get(i).getDmlink());
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
                LogUtil.e("BiliParser", "not here error");
                queryAVItemDetail(currentVideoBean, currentAid, currentCid);
            }
            videoListBeans.clear();
            videoListBeans.add(videoListBean);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("BiliNextError", "error:" + e.getMessage());
        }
        return videoListBeans;
    }

    private List<String> getAvInfo(String bvid) {
        List<String> result = new ArrayList<>();
        VideoDetailNetAPI videoDetailNetAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.APIURL).create(VideoDetailNetAPI.class);
        try {
            Response<BvToAvBean> response = videoDetailNetAPI.getAvInfo(bvid).execute();
            if (response.body() == null || response.body().getCode() != 0) {
                LogUtil.e("BiliParser", "response error:" + response.body().getCode());
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
            case "360P":
                return "16";
            case "480P":
                return "32";
            case "720P":
                return "64";
            case "1080P":
                return "80";
            case "1080P+":
            default:
                return "112";
        }
    }
}
