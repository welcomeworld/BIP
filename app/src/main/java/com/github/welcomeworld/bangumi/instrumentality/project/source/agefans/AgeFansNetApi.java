package com.github.welcomeworld.bangumi.instrumentality.project.source.agefans;

import android.net.Uri;
import android.text.TextUtils;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.devbase.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AgeFansNetApi {
    public static final String baseUrl = "https://www.agedm.tv";

    public static List<VideoListBean> search(String key, String pn) {
        List<VideoListBean> result = new ArrayList<>();
        String searchUrl = baseUrl + "/search?query=" + key + "&page=" + pn;
        LogUtil.i("AgeParserSearch:", "get:" + searchUrl);
        Connection searchConn = Jsoup.connect(searchUrl);
        Document searchDocument;
        try {
            searchDocument = searchConn.get();
        } catch (Exception e) {
            LogUtil.e("AgeParserSearch:", "connectEror" + e.getMessage());
            return result;
        }
        try {
            Elements searchItemElements = searchDocument.select("div.blockcontent1 div.cell a.cell_poster");
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
                    String cover = searchItem.selectFirst("img").attr("src");
                    if (cover != null && cover.startsWith("//")) {
                        cover = "https:" + cover;
                    }
                    videoListBean.setCover(cover);
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
                    videoBean.setTitle(title);
                    videoBean.setCover(videoListBean.getCover());
                    String path = searchItem.attr("href");
                    String videoKey = path.replace("/detail/", "");
                    LogUtil.e("AgeParserSearch getVideoKey:", videoKey);
                    videoBean.setVideoKey(videoKey + "_1_1");
                    videoBean.setUrl(baseUrl + path);
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
        LogUtil.e("AgeParseVideo", "getVideoListId:" + videoListId);
        try {
            if (TextUtils.isEmpty(videoListId)) {
                Connection pageConn = Jsoup.connect(baseUrl + "/detail/" + originalVideoKey);
                pageDocument = pageConn.get();
                Elements sourceElements = pageDocument.select("div#main0 div.movurl ul");
                Elements sourceTitleElements = pageDocument.select("ul#menu0 li");
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
                    title = pageDocument.selectFirst("div.baseblock div.blockcontent h4.detail_imform_name").text();
                }
                String cover = orignal.getCover();
                if (StringUtil.isEmpty(cover)) {
                    cover = pageDocument.selectFirst("div.baseblock div.blockcontent img.poster").attr("src");
                    if (cover != null && cover.startsWith("//")) {
                        cover = "https:" + cover;
                    }
                }
                String listDesc = pageDocument.selectFirst("div.baseblock div.blockcontent div.detail_imform_desc_pre p").text();
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
                        videoBean.setVideoKey(originalVideoKey + "_" + (i + 1) + "_" + keyPosition++);
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
            String referrerUrl = baseUrl + "/play/" + originalVideoKey + "?playid=" + videoListId + "_" + selectVideoIndex;
            Connection cookieConn = Jsoup.connect(referrerUrl);
            Connection.Response cookieResponse = cookieConn.execute();
            List<String> cookies = cookieResponse.headers("set-cookie");
            HashMap<String, String> requestCookie = new HashMap<>();
            for (String cookie : cookies) {
                requestCookie.put(cookie.substring(0, cookie.indexOf("=")), cookie.substring(cookie.indexOf("=") + 1, cookie.indexOf(";")));
            }
            String playUrl = baseUrl + "/_getplay?aid=" + originalVideoKey + "&playindex=" + videoListId + "&epindex=" + selectVideoIndex + "&r=" + Math.random();
            LogUtil.e("AgeParseVideo", " playUrl:" + playUrl);
            Connection playConn = Jsoup.connect(playUrl);
            playConn.cookie("username", "admin");
            playConn.referrer(referrerUrl);
            long cookieT1 = Long.parseLong(Objects.requireNonNull(requestCookie.get("t1")));
            String k1 = requestCookie.get("k1");
            long t1 = Math.round(cookieT1 / 1000.0) >> 5;
            long k2 = ((t1 * (t1 % 4096) * 0x3 + 83215) * (t1 % 4096) + t1);
            long t2 = System.currentTimeMillis();
            LogUtil.e("AgeParseVideo", ":cookie:start t2=" + t2);
            long k2End = k2 % 10;
            long t2End = t2 % 1000;
            if (!String.valueOf(t2End).contains("" + k2End)) {
                t2End = t2End % 10;
                if (t2End > k2End) {
                    t2 = t2 - (t2End - k2End);
                } else {
                    t2 = t2 + (k2End - t2End);
                }
            }
            LogUtil.e("AgeParseVideo", ":cookie:k1=" + k1);
            LogUtil.e("AgeParseVideo", ":cookie:t1=" + cookieT1);
            LogUtil.e("AgeParseVideo", ":cookie:k2=" + k2);
            LogUtil.e("AgeParseVideo", ":cookie:t2=" + t2);
            playConn.cookie("t1", "" + cookieT1);
            playConn.cookie("k1", k1);
            playConn.cookie("k2", "" + k2);
            playConn.cookie("t2", "" + t2);
            long fa_t = System.currentTimeMillis();
            playConn.cookie("fa_t", "" + fa_t);
            playConn.cookie("fa_c", "1");
            Connection.Response playResponse = playConn.execute();
            if (playResponse.statusCode() != 200) {
                LogUtil.e("AgeParseVideo:", "play response code:" + playResponse.statusCode());
                return videoListBeans;
            }
            String responseBody = playResponse.body();
            LogUtil.e("AgeParseVideo:", "play response:" + responseBody);
            PlayUrlBean playUrlBean = new Gson().fromJson(responseBody, PlayUrlBean.class);
            String realPlayUrl = URLDecoder.decode(playUrlBean.getVurl(), "UTF-8");
            if (!realPlayUrl.startsWith("http") || playUrlBean.getPurlf().contains("shankuwang.com")) {
                realPlayUrl = playUrlBean.getPurlf() + URLEncoder.encode(realPlayUrl, "UTF-8");
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
            Document document = Jsoup.parse(new URL(url), 3000);
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

}
