package com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi;

import android.util.Log;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.IndexRecommendDataBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class BimiNetApi {
    public static final String baseUrl = "http://www.bimiacg.com";

    public static List<VideoListBean> search(String key, String pn) {
        List<VideoListBean> result = new ArrayList<>();
        Connection searchConn = Jsoup.connect(baseUrl + "/vod/search/wd/" + key + "/page/" + pn);
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
                    if (tagBean!=null) {
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
                    videoBean.setVideoKey(videoKey);
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

    public static VideoListBean parseVideoListRealInfo(VideoListBean videoListBean) {
        VideoBean currentVideoBean = videoListBean.getCurrentVideoBean();
        Connection pageConn = Jsoup.connect(baseUrl + "/bangumi/" + currentVideoBean.getVideoKey() + "/play/1/1");
        Document pageDocument;
        String urlKey;
        String fromKey = "play";
        try {
            pageDocument = pageConn.get();
            urlKey = pageDocument.selectFirst("div.play-player script").html();
            if(urlKey.contains("\"ksyun\",")){
                fromKey = "ksyun";
            }
            LogUtil.e("BimiParseVideo:", "get orignal urlKey:" + urlKey);
            urlKey = urlKey.substring(urlKey.indexOf("\"url\":") + 6, urlKey.indexOf("\"url_next\""));
            urlKey = urlKey.substring(urlKey.indexOf("\"") + 1, urlKey.lastIndexOf("\""));
            LogUtil.e("BimiParseVideo:", "get urlKey:" + urlKey);
        } catch (Exception e) {
            LogUtil.e("BimiParseVideo:", "connectEror" + e.getMessage());
            return videoListBean;
        }
        String urlConnUri = "http://49.234.56.246/danmu/"+fromKey+".php?url=" + urlKey + "&myurl=" + baseUrl + "/bangumi/" + currentVideoBean.getVideoKey() + "/play/1/1";
        LogUtil.e("BimiParseVideo:", "log url:" + urlConnUri);
        Connection urlConn = Jsoup.connect(urlConnUri);

        Document urlDocument;
        try {
            urlDocument = urlConn.get();
        } catch (Exception e) {
            LogUtil.e("BimiParseVideo:", "url connectEror" + e.getMessage());
            return videoListBean;
        }

        //http://49.234.56.246/danmu/ksyun.php?url=95416mG-jn-aUJ7dHUxp7K9BN46c8kZagrjkwTDshPRNBSFIk-ju6jbTql2d12NpBr_JxAo051JcYvAs3tZs5qLS_JB2Z658LMI&myurl=http://www.bimiacg.com/bangumi/551/play/1/1
        //http://49.234.56.246/danmu/ksyun.php?url=8a0a9JCA9gZMgFLo6XKIEa41zFLzqaCZjWNhxCzlH1jEnioReL3eCIp2bzGgxRwW_Woh1Qt4Xg&myurl=http://www.bimiacg.com/bangumi/551/play/3/2/
        try {
//                currentVideoBean.setDanmakuUrl(response.body().getData().getPages().get(i).getDmlink());
//                currentVideoBean.setTitle(response.body().getData().getPages().get(i).getPart());
            currentVideoBean.setDash(false);
            VideoQualityBean qualityBean = new VideoQualityBean();
            LogUtil.e("BimiParseVideo","get url Html:"+urlDocument.selectFirst("video#video").html());
            qualityBean.setRealVideoUrl(urlDocument.selectFirst("video#video source").attr("src"));
            LogUtil.e("BimiParseVideo:", "final get url:" + qualityBean.getRealVideoUrl());
            qualityBean.setQuality("高清");
            currentVideoBean.getQualityBeans().add(qualityBean);
        } catch (Exception e) {
            Log.e("BimiParseVideo", "final error:" + e.getMessage());
        }
        return videoListBean;
    }
}
