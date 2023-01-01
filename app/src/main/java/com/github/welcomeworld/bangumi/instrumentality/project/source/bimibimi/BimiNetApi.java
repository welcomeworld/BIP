package com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi;

import android.text.TextUtils;
import android.util.Log;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi.databean.BimiPlayerJsConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.devbase.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BimiNetApi {
    public static String baseUrl = "http://www.bimiacg4.net";

    private static String getBaseUrl() {
        return baseUrl;
    }

    public static List<VideoListBean> search(String key, String pn) {
        List<VideoListBean> result = new ArrayList<>();
        Connection searchConn = Jsoup.connect(getBaseUrl() + "/vod/search/wd/" + key + "/page/" + pn);
        Document searchDocument;
        try {
            searchDocument = searchConn.get();
        } catch (Exception e) {
            LogUtil.e("BimiSearch:", "connectEror" + e.getMessage());
            return result;
        }
        try {
            Elements searchItemElements = searchDocument.select("div.v_tb ul.tab-cont li");
            if (searchItemElements.size() == 0) {
                LogUtil.e("BimiSearch:", "search return nothing");
                return null;
            }
            for (int i = 0; i < searchItemElements.size(); i++) {
                try {
                    Element searchItem = searchItemElements.get(i);
                    LogUtil.e("BimiSearch:", searchItem.html());
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setSourceName(Constants.Source.BIMI);
                    Element linkItem = searchItem.selectFirst("a");
                    videoListBean.setTitle(linkItem.attr("title"));
                    videoListBean.setCoverPortrait(true);
                    String tagName = "番剧";
                    Element tagBean = searchItem.selectFirst("div p");
                    if (tagBean != null) {
                        tagName = tagBean.attr("title");
                    }
                    videoListBean.setTag(tagName);
                    videoListBean.setCover(linkItem.selectFirst("img").attr("src"));
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
                    videoBean.setTitle(linkItem.attr("title"));
                    videoBean.setCover(videoListBean.getCover());
                    String path = linkItem.attr("href");
                    String videoKey = path.substring(path.indexOf("bi/") + 3, path.lastIndexOf('/'));
                    LogUtil.e("BimiSearch getVideoKey:", videoKey);
                    videoBean.setVideoKey(videoKey + "_1_1");
                    videoBean.setUrl(getBaseUrl() + path);
                    videoBeans.add(videoBean);
                    videoListBean.setVideoBeanList(videoBeans);
                    result.add(videoListBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<VideoListBean> updateVideoList(List<VideoListBean> videoListBeans, int selectSourceIndex) {
        VideoListBean orignal = videoListBeans.get(selectSourceIndex);
        VideoBean currentVideoBean = orignal.getCurrentVideoBean();
        String wrapVideoKey = currentVideoBean.getVideoKey();
        String originalVideoKey;
        if (wrapVideoKey.contains("_")) {
            originalVideoKey = wrapVideoKey.substring(0, wrapVideoKey.indexOf("_"));
        } else {
            originalVideoKey = wrapVideoKey;
        }
        String videoListExtra = orignal.getSourceExternalData();
        HashMap<String, String> videoListExtraData = new Gson().fromJson(
                videoListExtra,
                new TypeToken<HashMap<String, String>>() {
                }.getType());
        if (videoListExtraData == null) {
            videoListExtraData = new HashMap<>();
        }
        int selectVideoIndex = orignal.getSelectIndex() + 1;
        Document pageDocument;
        String videoListId = videoListExtraData.get("videoListId");
        LogUtil.e("BimiParseVideo", "getVideoListId:" + videoListId);
        String urlKey = "";
        String fromKey;
        String playUrl;
        try {
            if (TextUtils.isEmpty(videoListId)) {
                String pageUrl = getBaseUrl() + "/bangumi/bi/" + originalVideoKey;
                Connection pageConn = Jsoup.connect(pageUrl);
                pageDocument = pageConn.get();
                Elements sourceElements = pageDocument.select("ul.player_list");
                Elements sourceTitleElements = pageDocument.select("div.play_source_tab a");
                String tag = pageDocument.selectFirst("div.txt_intro_con div.tit p.p_txt em").text();
                if (StringUtil.isEmpty(tag)) {
                    tag = orignal.getTag();
                }
                if (sourceElements.size() == 0) {
                    LogUtil.e("BimiParseVideo", "parse empty with:" + pageUrl);
                    return videoListBeans;
                }
                List<Long> positionList = new ArrayList<>();
                for (VideoBean cacheBean : orignal.getVideoBeanList()) {
                    positionList.add(cacheBean.getPlayPosition());
                }
                videoListBeans.clear();
                for (int i = 0; i < sourceElements.size(); i++) {
                    VideoListBean videoListBean = new VideoListBean();
                    if (StringUtil.isEmpty(videoListBean.getVideoListDes())) {
                        videoListBean.setVideoListDes(pageDocument.selectFirst("div.vod-jianjie p").text());
                    }
                    videoListBean.setSeasonTitle(sourceTitleElements.get(i).text());
                    String link = sourceElements.get(i).selectFirst("a").attr("href");
                    String playString = link.substring(link.indexOf("play") + 5);
                    String tempVideoListId = playString.substring(0, playString.indexOf("/"));
                    videoListExtraData.put("videoListId", tempVideoListId);
                    LogUtil.e("BimiParseVideo:", "parse source" + videoListBean.getSeasonTitle() + "  " + videoListExtraData.get("videoListId"));
                    videoListBean.setSourceExternalData(new Gson().toJson(videoListExtraData));
                    videoListBean.setSourceName(Constants.Source.BIMI);
                    videoListBean.setTitle(orignal.getTitle());
                    videoListBean.setCoverPortrait(true);
                    videoListBean.setTag(tag);
                    videoListBean.setCover(orignal.getCover());
                    Elements itemElements = sourceElements.get(i).select("a");
                    int positionIndex = 0;
                    int keyPosition = 1;
                    for (Element item : itemElements) {
                        VideoBean videoBean = new VideoBean();
                        videoBean.setTitle(item.text());
                        videoBean.setCover(videoListBean.getCover());
                        videoBean.setVideoKey(originalVideoKey + "_" + tempVideoListId + "_" + keyPosition++);
                        if (positionIndex < positionList.size()) {
                            videoBean.setPlayPosition(positionList.get(positionIndex++));
                        }
                        videoBean.setUrl(getBaseUrl() + item.attr("href"));
                        videoListBean.getVideoBeanList().add(videoBean);
                    }
                    videoListBeans.add(videoListBean);
                    if (i == selectSourceIndex) {
                        videoListBean.setSelectIndex(orignal.getSelectIndex());
                        currentVideoBean = videoListBean.getCurrentVideoBean();
                        videoListId = videoListExtraData.get("videoListId");
                        LogUtil.e("BimiParseVideo", "setVideoListId:" + videoListId);
                    }
                }
            }
            playUrl = getBaseUrl() + "/bangumi/" + originalVideoKey + "/play/" + videoListId + "/" + selectVideoIndex;
            LogUtil.e("BimiParseVideo", "playUrl:" + playUrl);
            Connection playConn = Jsoup.connect(playUrl);
            Document playDocument = playConn.get();
            Elements playerJss = playDocument.select("div.play-player script");
            for (Element playerJs : playerJss) {
                String js = playerJs.html();
                if (js.contains("var player_aaaa")) {
                    urlKey = js;
                    break;
                }
            }
            LogUtil.e("BimiParseVideo:", "get orignal urlKey:" + urlKey);
            BimiPlayerJsConfig playerJsConfig = new Gson().fromJson(urlKey.substring(urlKey.indexOf("{")), BimiPlayerJsConfig.class);
            fromKey = playerJsConfig.getFrom();
            urlKey = playerJsConfig.getUrl();
        } catch (Exception e) {
            LogUtil.e("BimiParseVideo:", "connectEror" + e.getMessage());
            return videoListBeans;
        }
        String urlConnUri = getBaseUrl() + genPath(fromKey, urlKey) + "&myurl=" + playUrl;
        LogUtil.e("BimiParseVideo:", "log url:" + urlConnUri);
        Connection urlConn = Jsoup.connect(urlConnUri);

        Document urlDocument;
        try {
            urlDocument = urlConn.get();
        } catch (Exception e) {
            LogUtil.e("BimiParseVideo:", "url connectEror" + e.getMessage());
            return videoListBeans;
        }
        try {
            currentVideoBean.setDash(false);
            VideoQualityBean qualityBean = new VideoQualityBean();
            LogUtil.e("BimiParseVideo", "get url Html:" + urlDocument.selectFirst("video#video").html());
            String realPlayUrl = urlDocument.selectFirst("video#video source").attr("src");
            if (!realPlayUrl.startsWith("http") && realPlayUrl.contains("m3u8")) {
                realPlayUrl = realPlayUrl.replace("./m3u8", getBaseUrl() + "/static/danmu/m3u8");
            }
            qualityBean.setRealVideoUrl(realPlayUrl);
            LogUtil.e("BimiParseVideo:", "final get url:" + qualityBean.getRealVideoUrl());
            qualityBean.setQuality("高清");
            currentVideoBean.getQualityBeans().add(qualityBean);
        } catch (Exception e) {
            Log.e("BimiParseVideo", "final error:" + e.getMessage());
        }
        return videoListBeans;
    }

    private static String genPath(String fromKey, String urlKey) {
        String fromResult = "play";
        switch (fromKey) {
            case "pic":
            case "danmakk":
                fromResult = "pic";
                break;
            case "alos":
            case "bimi":
                if (urlKey.contains(".m3u8")) {
                    fromResult = "pic";
                }
                break;
            case "special":
            case "zhilian":
            case "miui":
            case "ckplayer":
                if (urlKey.contains(".m3u8")) {
                    fromResult = "m3u8";
                }
                break;
            case "aliplay":
            case "kzyun":
                fromResult = "kzyun";
                break;
            case "renrenmi":
                fromResult = "rrm";
                break;
            case "dplayer":
            case "zkm3u8":
            case "qq":
            case "qiyi":
                if (urlKey.contains(".mp4")) {
                    fromResult = "dm";
                } else if (urlKey.contains(".m3u8")) {
                    fromResult = "m3u8";
                } else {
                    fromResult = "dm";
                }
                break;
            case "cqyunm3u8":
                if (urlKey.contains(".m3u8")) {
                    fromResult = "m3u8";
                } else {
                    fromResult = "dm";
                }
                break;
            case "youku":
                fromResult = "qy";
                break;
            case "jdym3u8":
                if (urlKey.contains(".mp4")) {
                    fromResult = "dm";
                } else {
                    fromResult = "m3u8";
                }
                break;
            case "piaoquan":
                fromResult = "piaoquan";
                break;
        }
        return "/static/danmu/" + fromResult + ".php?url=" + urlKey;
    }
}
