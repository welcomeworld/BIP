package com.github.welcomeworld.bangumi.instrumentality.project.source.agefans;

import android.net.Uri;
import android.text.TextUtils;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliOkHttpClientManager;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.devbase.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public class AgeFansNetApi {
    public static final String baseUrl = "https://www.agemys.org";

    public static List<VideoListBean> search(String key, String pn) {
        List<VideoListBean> result = new ArrayList<>();
        String searchUrl = baseUrl + "/search?query=" + key + "&page=" + pn;
        LogUtil.i("AgeParserSearch:", "get:" + searchUrl);
        Document searchDocument;
        try {
            searchDocument = Jsoup.parse(httpGet(searchUrl));
        } catch (Exception e) {
            LogUtil.e("AgeParserSearch:", "connectEror" + e.getMessage());
            return result;
        }
        try {
            Elements searchItemElements = searchDocument.select("div.video_cover div.video_cover_wrapper a");
            if (searchItemElements.size() == 0) {
                LogUtil.i("AgeParserSearch:", "search item empty");
                return null;
            }
            for (int i = 0; i < searchItemElements.size(); i++) {
                try {
                    Element searchItem = searchItemElements.get(i);
                    LogUtil.i("AgeParserSearch:", searchItem.html());
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setSourceName(Constants.Source.AGEFANS);
                    String title = searchItem.selectFirst("img").attr("alt");
                    videoListBean.setTitle(title);
                    videoListBean.setCoverPortrait(true);
                    String tagName = "age番剧";
                    videoListBean.setTag(tagName);
                    String cover = searchItem.selectFirst("img").attr("data-original");
                    if (cover != null && cover.startsWith("//")) {
                        cover = "https:" + cover;
                    }
                    videoListBean.setCover(cover);
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
                    videoBean.setTitle(title);
                    videoBean.setCover(videoListBean.getCover());
                    String path = searchItem.attr("href");
                    if (!path.startsWith("http")) {
                        path = baseUrl + path;
                    }
                    String videoKey = path.substring(path.lastIndexOf("detail/") + 7);
                    LogUtil.e("AgeParserSearch getVideoKey:", videoKey);
                    videoBean.setVideoKey(videoKey + "/2/1");
                    videoBean.setUrl(path);
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
        if (wrapVideoKey.contains("/")) {
            originalVideoKey = wrapVideoKey.substring(0, wrapVideoKey.indexOf("/"));
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
        LogUtil.e("AgeParseVideo", "getVideoListId:" + videoListId);
        try {
            if (TextUtils.isEmpty(videoListId)) {
                pageDocument = Jsoup.parse(httpGet(baseUrl + "/detail/" + originalVideoKey));
                Elements sourceElements = pageDocument.select("div.tab-content div.tab-pane ul");
                Elements sourceTitleElements = pageDocument.select("ul.nav li.nav-item");
                if (sourceElements.size() == 0) {
                    return videoListBeans;
                }
                List<Long> positionList = new ArrayList<>();
                for (VideoBean cacheBean : orignal.getVideoBeanList()) {
                    positionList.add(cacheBean.getPlayPosition());
                }
                videoListBeans.clear();
                String title = orignal.getTitle();
                if (StringUtil.isEmpty(title)) {
                    title = pageDocument.selectFirst("div.video_detail_right h2.video_detail_title").text();
                }
                String cover = orignal.getCover();
                if (StringUtil.isEmpty(cover)) {
                    cover = pageDocument.selectFirst("div.video_detail_cover img.lazyload").attr("data-original");
                    if (cover != null && cover.startsWith("//")) {
                        cover = "https:" + cover;
                    }
                }
                String listDesc = pageDocument.selectFirst("div.video_detail_right div.video_detail_desc").text();
                int skipSource = 0;
                for (int i = 0; i < sourceElements.size(); i++) {
                    Elements itemElements = sourceElements.get(i).select("li a");
                    if (itemElements.size() <= 0) {
                        skipSource++;
                        continue;
                    }
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setVideoListDes(listDesc);
                    videoListBean.setSeasonTitle(sourceTitleElements.get(i).text());
                    videoListExtraData.put("videoListId", String.valueOf(i + 1));
                    LogUtil.e("AgeParseVideo:", "parse source" + videoListBean.getSeasonTitle() + "  " + videoListExtraData.get("videoListId"));
                    videoListBean.setSourceExternalData(new Gson().toJson(videoListExtraData));
                    videoListBean.setSourceName(Constants.Source.AGEFANS);
                    videoListBean.setTitle(title);
                    videoListBean.setCoverPortrait(true);
                    videoListBean.setTag(orignal.getTag());
                    videoListBean.setCover(cover);
                    videoListBean.setSelectIndex(orignal.getSelectIndex());
                    int positionIndex = 0;
                    int keyPosition = 1;
                    for (Element item : itemElements) {
                        VideoBean videoBean = new VideoBean();
                        videoBean.setTitle(item.text());
                        videoBean.setCover(videoListBean.getCover());
                        videoBean.setVideoKey(originalVideoKey + "/" + (i + 1) + "/" + keyPosition++);
                        if (positionIndex < positionList.size()) {
                            videoBean.setPlayPosition(positionList.get(positionIndex++));
                        }
                        videoBean.setUrl(baseUrl + item.attr("href"));
                        videoListBean.getVideoBeanList().add(videoBean);
                    }
                    videoListBeans.add(videoListBean);
                    if (i - skipSource == selectSourceIndex) {
                        currentVideoBean = videoListBean.getCurrentVideoBean();
                        videoListId = videoListExtraData.get("videoListId");
                        LogUtil.e("AgeParseVideo", "setVideoListId:" + videoListId);
                    }
                }
            }
            String playUrl = baseUrl + "/play/" + originalVideoKey + "/" + videoListId + "/" + selectVideoIndex;
            Map<String, String> headers = new HashMap<>();
            headers.put("referer", baseUrl + "/");
            Document playDocument = Jsoup.parse(httpGet(playUrl, headers));
            String realPlayUrl = playDocument.selectFirst("div.video_play_wrapper iframe").attr("src");
            if (!realPlayUrl.startsWith("http") || realPlayUrl.contains("sp-flv.com")) {
                if (realPlayUrl.contains("vip.sp-flv.com")) {
                    LogUtil.e("AgeParseVideo:", "Vip加密了，懒得解析");
                    return videoListBeans;
                }
                LogUtil.e("AgeParseVideo", "云解析:" + realPlayUrl);
                realPlayUrl = parseshankuwang(realPlayUrl);
            }
            currentVideoBean.setDash(false);
            VideoQualityBean qualityBean = new VideoQualityBean();
            qualityBean.setRealVideoUrl(realPlayUrl);
            LogUtil.e("AgeParseVideo:", "final get url:" + qualityBean.getRealVideoUrl());
            qualityBean.setQuality("高清");
            currentVideoBean.getQualityBeans().add(qualityBean);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("AgeParseVideo:", "connectEror" + e.getMessage());
            return videoListBeans;
        }
        return videoListBeans;
    }

    private static String parseshankuwang(String url) {
        String finalResult = "";
        try {
            Document document = Jsoup.parse(httpGet(url));
            Elements jsElements = document.select("script");
            for (Element jsElement : jsElements) {
                String tempJsString = jsElement.html();
                if (tempJsString.contains("var video_url")) {
                    int start = tempJsString.indexOf("video_url = ");
                    int end = tempJsString.indexOf("var video_key");
                    String tempUrl = tempJsString.substring(start + 13, end - 8);
                    LogUtil.d("AgeParseVideo:", "云解析-js tempUrl:" + tempUrl);
                    int finalEnd = tempUrl.indexOf("';");
                    String videoUrl = tempUrl.substring(0, finalEnd);
                    if (videoUrl.startsWith("https")) {
                        finalResult = videoUrl;
                    } else {
                        Uri baseUri = Uri.parse(url);
                        finalResult = baseUri.getScheme() + "://" + baseUri.getAuthority() + videoUrl;
                    }
                    break;
                }
            }
            return finalResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalResult;
    }

    private static String httpGet(String url) {
        return httpGet(url, null);
    }

    private static String httpGet(String url, Map<String, String> headers) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        try {
            Response response = BiliOkHttpClientManager.getInstance().getNormalOkHttpClient().newCall(requestBuilder.build()).execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }

}
