package com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi;

import android.text.TextUtils;
import android.util.Log;

import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi.databean.BimiPlayerJsConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.StringUtil;
import com.google.gson.Gson;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BimiNetApi {
    public static final String baseUrl = "http://www.bimiacg.net";
    public static final String baseVideoDir = baseUrl+"/static/danmu/";

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

    public static List<VideoListBean> updateVideoList(List<VideoListBean> videoListBeans, int selectSourceIndex) {
        VideoListBean orignal = videoListBeans.get(selectSourceIndex);
        VideoBean currentVideoBean = orignal.getCurrentVideoBean();
        String videoListExtra = orignal.getSourceExternalData();
        HashMap<String, String> videoListExtraData = new Gson().fromJson(videoListExtra, HashMap.class);
        if(videoListExtraData==null){
            videoListExtraData = new HashMap<>();
        }
        int selectVideoIndex = orignal.getSelectIndex()+1;
        Document pageDocument;
        String videoListId = videoListExtraData.get("videoListId");
        LogUtil.e("BimiParseVideo","getVideoListId:"+videoListId);
        String urlKey;
        String fromKey = "play";
        try {
            if(TextUtils.isEmpty(videoListId)){
                Connection pageConn = Jsoup.connect(baseUrl + "/bangumi/bi/" + currentVideoBean.getVideoKey());
                pageDocument = pageConn.get();
                Elements sourceElements = pageDocument.select("ul.player_list");
                Elements sourceTitleElements = pageDocument.select("div.play_source_tab a");
                if (sourceElements.size() == 0) {
                    return videoListBeans;
                }
                videoListBeans.clear();
                for(int i=0;i<sourceElements.size();i++){
                    VideoListBean videoListBean = new VideoListBean();
                    if(StringUtil.isEmpty(videoListBean.getVideoListDes())){
                        videoListBean.setVideoListDes(pageDocument.selectFirst("div.vod-jianjie p").text());
                    }
                    videoListBean.setSeasonTitle(sourceTitleElements.get(i).text());
                    String link = sourceElements.get(i).selectFirst("a").attr("href");
                    String playString = link.substring(link.indexOf("play")+5);
                    videoListExtraData.put("videoListId",playString.substring(0,playString.indexOf("/")));
                    LogUtil.e("BimiParseVideo:", "parse source"+ videoListBean.getSeasonTitle()+"  "+ videoListExtraData.get("videoListId"));
                    videoListBean.setSourceExternalData(new Gson().toJson(videoListExtraData));
                    videoListBean.setSourceName(Constants.Source.BIMI);
                    videoListBean.setTitle(orignal.getTitle());
                    videoListBean.setCoverPortrait(true);
                    videoListBean.setTag(orignal.getTag());
                    videoListBean.setCover(orignal.getCover());
                    Elements itemElements = sourceElements.get(i).select("a");
                    for(Element item:itemElements){
                        VideoBean videoBean = new VideoBean();
                        videoBean.setTitle(item.text());
                        videoBean.setCover(videoListBean.getCover());
                        videoBean.setVideoKey(currentVideoBean.getVideoKey());
                        videoBean.setUrl(baseUrl + item.attr("href"));
                        videoListBean.getVideoBeanList().add(videoBean);
                    }
                    videoListBeans.add(videoListBean);
                    if(i == selectSourceIndex){
                        videoListBean.setSelectIndex(orignal.getSelectIndex());
                        currentVideoBean = videoListBean.getCurrentVideoBean();
                        videoListId = videoListExtraData.get("videoListId");
                        LogUtil.e("BimiParseVideo","setVideoListId:"+videoListId);
                    }
                }
            }
            String playUrl = baseUrl + "/bangumi/" + currentVideoBean.getVideoKey() + "/play/"+videoListId+"/" + selectVideoIndex;
            LogUtil.e("BimiParseVideo","playUrl:"+playUrl);
            Connection playConn = Jsoup.connect(playUrl);
            Document playDocument = playConn.get();
            urlKey = playDocument.selectFirst("div.play-player script").html();
            LogUtil.e("BimiParseVideo:", "get orignal urlKey:" + urlKey);
            BimiPlayerJsConfig playerJsConfig = new Gson().fromJson(urlKey.substring(urlKey.indexOf("{")),BimiPlayerJsConfig.class);
            if (playerJsConfig.getFrom().contains("ksyun")) {
                fromKey = "ksyun";
            }else if(playerJsConfig.getFrom().contains("pic")){
                fromKey = "pic";
            }
            urlKey = playerJsConfig.getUrl();
            LogUtil.e("BimiParseVideo:", "get urlKey:" + playerJsConfig.getFrom());
        } catch (Exception e) {
            LogUtil.e("BimiParseVideo:", "connectEror" + e.getMessage());
            return videoListBeans;
        }
        String urlConnUri = baseVideoDir + fromKey + ".php?url=" + urlKey + "&myurl=" + baseUrl + "/bangumi/" + currentVideoBean.getVideoKey() + "/play/"+videoListId+"/"+selectVideoIndex;
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
//                currentVideoBean.setDanmakuUrl(response.body().getData().getPages().get(i).getDmlink());
//                currentVideoBean.setTitle(response.body().getData().getPages().get(i).getPart());
            currentVideoBean.setDash(false);
            VideoQualityBean qualityBean = new VideoQualityBean();
            LogUtil.e("BimiParseVideo", "get url Html:" + urlDocument.selectFirst("video#video").html());
            String realPlayUrl = urlDocument.selectFirst("video#video source").attr("src");
            if(!realPlayUrl.startsWith("http")&&realPlayUrl.contains(".m3u8")){
                realPlayUrl = baseVideoDir+"m3u8/play.php?url="+realPlayUrl;
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
}
