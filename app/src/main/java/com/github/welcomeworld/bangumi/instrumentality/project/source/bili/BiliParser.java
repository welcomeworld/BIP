package com.github.welcomeworld.bangumi.instrumentality.project.source.bili;

import android.net.Uri;
import android.util.Log;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.BiliSortAndSignInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.BiliFixedParameterInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BaseUrl;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.IndexNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.IndexRecommendBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.IndexRecommendDataBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.VideoDetailNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.VideoDetailPageBean;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.VideoUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ToastUtils;

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
        Map<String,String> parameters=new HashMap<>();
        parameters.put("ad_extra","6BCCA2213B3B094292DFF9454EB02128A5CD624F226D40F0B86CA8263CE1D50C927F2F8DF2FD134C361A06FAA537402E66E53155D1C1F218BE42D2AFE4A6A701C496E5196401D81A1E390498A1CA24C20C25C97600C552962682D90C9D9DF8B9EDAD866490BE972EA37F92AA7A1040F2BEA5122D039B942437307F298336AAF27EFB5AF87961F6F852401DD8BBD0BFB92309D3B60C12E307ECD02D9BCBB19725E2964F77CDE07BFAC45A34884CE0167EEDBB0EBC8926A3CC9CB9B27536BF9C0DF87AB6DABAE86D1E6D4E714BC140A1D500E27446265DC85C226B381E10AF2299D961E06FA60A84EE34DFCB65E1253339112FD0D5ECE9C9C58C084D028DD7E26A70DC806C36C46E9D5C08169A2571B8BAC2BC0AE91AE8D36F3CBDCD2768950CD1CE9C3A3F53B5FE145A20B020435E79CA");
        parameters.put("banner_hash","5370060068233862947");
        parameters.put("idx","1547815474");
        parameters.put("login_event","0");
        parameters.put("build","591204");
        parameters.put("network","wifi");
        parameters.put("pull","true");
        parameters.put("style","2");
        parameters.put("ts",""+System.currentTimeMillis());
        parameters.put("open_event","");
        OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new BiliFixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
                .addInterceptor(new BiliSortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.APPURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        IndexNetAPI indexNetAPI=retrofit.create(IndexNetAPI.class);
        Call<IndexRecommendBean> indexBeanCall=indexNetAPI.getIndexRecommend();
        try {
            Response<IndexRecommendBean> response = indexBeanCall.execute();
            if(response.body().getData()==null){
//                Toast.makeText(getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
                return result;
            }
            List<IndexRecommendDataBean> moreData;
            moreData=response.body().getData();
            for(int i=0;i<moreData.size();i++){
                if(!moreData.get(i).getGotoX().equalsIgnoreCase("av")){
                    moreData.remove(i);
                    i--;
                }else {
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setSourceName(Constants.Source.BILI);
                    videoListBean.setTitle(moreData.get(i).getTitle());
                    videoListBean.setCover(moreData.get(i).getCover()+"@320w_200h_1e_1c.webp");
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
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
    public List<VideoListBean> getMoreRecommend() {
        List<VideoListBean> result = new ArrayList<>();
        Map<String,String> parameters=new HashMap<>();
        parameters.put("ad_extra","6BCCA2213B3B094292DFF9454EB02128A5CD624F226D40F0B86CA8263CE1D50C927F2F8DF2FD134C361A06FAA537402E66E53155D1C1F218BE42D2AFE4A6A701C496E5196401D81A1E390498A1CA24C20C25C97600C552962682D90C9D9DF8B9EDAD866490BE972EA37F92AA7A1040F2BEA5122D039B942437307F298336AAF27EFB5AF87961F6F852401DD8BBD0BFB92309D3B60C12E307ECD02D9BCBB19725E2964F77CDE07BFAC45A34884CE0167EEDBB0EBC8926A3CC9CB9B27536BF9C0DF87AB6DABAE86D1E6D4E714BC140A1D500E27446265DC85C226B381E10AF2299D961E06FA60A84EE34DFCB65E1253339112FD0D5ECE9C9C58C084D028DD7E26A70DC806C36C46E9D5C08169A2571B8BAC2BC0AE91AE8D36F3CBDCD2768950CD1CE9C3A3F53B5FE145A20B020435E79CA");
        parameters.put("banner_hash","5370060068233862947");
        parameters.put("idx","1547815474");
        parameters.put("login_event","0");
        parameters.put("build","591204");
        parameters.put("network","wifi");
        parameters.put("pull","false");
        parameters.put("style","2");
        parameters.put("ts",""+System.currentTimeMillis());
        parameters.put("open_event","");
        OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new BiliFixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
                .addInterceptor(new BiliSortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.APPURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        IndexNetAPI indexNetAPI=retrofit.create(IndexNetAPI.class);
        Call<IndexRecommendBean> indexBeanCall=indexNetAPI.getIndexRecommend();
        try {
            Response<IndexRecommendBean> response = indexBeanCall.execute();
            if(response.body().getData()==null){
//                Toast.makeText(getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
                return result;
            }
            List<IndexRecommendDataBean> moreData;
            moreData=response.body().getData();
            for(int i=0;i<moreData.size();i++){
                if(!moreData.get(i).getGotoX().equalsIgnoreCase("av")){
                    moreData.remove(i);
                    i--;
                }else {
                    VideoListBean videoListBean = new VideoListBean();
                    videoListBean.setTitle(moreData.get(i).getTitle());
                    videoListBean.setSourceName(Constants.Source.BILI);
                    videoListBean.setCover(moreData.get(i).getCover()+"@320w_200h_1e_1c.webp");
                    ArrayList<VideoBean> videoBeans = new ArrayList<>();
                    VideoBean videoBean = new VideoBean();
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
       Uri currentUri = Uri.parse(videoListBean.getCurrentVideoBean().getUrl());
        String currentAid = currentUri.getPath().substring(1);
        currentAid = currentAid.substring(0,currentAid.contains("/")?currentAid.indexOf("/"):currentAid.length());
        Map<String,String> parameters=new HashMap<>();
        parameters.put("ad_extra","6BCCA2213B3B094292DFF9454EB02128A5CD624F226D40F0B86CA8263CE1D50C927F2F8DF2FD134C361A06FAA537402E66E53155D1C1F218BE42D2AFE4A6A701C496E5196401D81A1E390498A1CA24C20C25C97600C552962682D90C9D9DF8B9EDAD866490BE972EA37F92AA7A1040F2BEA5122D039B942437307F298336AAF27EFB5AF87961F6F852401DD8BBD0BFB92309D3B60C12E307ECD02D9BCBB19725E2964F77CDE07BFAC45A34884CE0167EEDBB0EBC8926A3CC9CB9B27536BF9C0DF87AB6DABAE86D1E6D4E714BC140A1D500E27446265DC85C226B381E10AF2299D961E06FA60A84EE34DFCB65E1253339112FD0D5ECE9C9C58C084D028DD7E26A70DC806C36C46E9D5C08169A2571B8BAC2BC0AE91AE8D36F3CBDCD2768950CD1CE9C3A3F53B5FE145A20B020435E79CA");
        parameters.put("aid",currentAid);
        parameters.put("autoplay","0");
        parameters.put("fnval","16");
        parameters.put("fnver","0");
        parameters.put("from","7");
        parameters.put("force_host","0");
        parameters.put("plat","0");
        parameters.put("trackid","all_16.shylf-ai-recsys-120.1548139653021.82");
        parameters.put("ts",""+System.currentTimeMillis());
        parameters.put("qn","32");
        OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new BiliFixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
                .addInterceptor(new BiliSortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.APPURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        VideoDetailNetAPI videoDetailNetAPI=retrofit.create(VideoDetailNetAPI.class);
        try {
            Response<VideoDetailPageBean> response = videoDetailNetAPI.getVideoDetailPageInfo().execute();
            if(response.body() ==null||response.body().getCode() !=0){
                return videoListBean;
            }
            for(int i=0;i<response.body().getData().getPages().size();i++){
                int cid = response.body().getData().getPages().get(i).getCid();
                VideoDataSource videoDataSource=new VideoDataSource();
                videoDataSource.setDanmakuSource(response.body().getData().getPages().get(i).getDmlink());
                videoDataSource.setTitle(response.body().getData().getPages().get(i).getPart());
                parameters.clear();
                parameters.put("device","android");
                parameters.put("aid",currentAid);
                parameters.put("expire","0");
                parameters.put("fnval","0");
                parameters.put("fnver","0");
                parameters.put("otype","json");
                parameters.put("force_host","0");
                parameters.put("cid",response.body().getData().getPages().get(i).getCid()+"");
                parameters.put("npcybs","1");
                parameters.put("ts",""+System.currentTimeMillis());
                parameters.put("qn","32");
                OkHttpClient.Builder urlOkHttpClientBuilder=new OkHttpClient.Builder()
                        .addInterceptor(new FixedHeaderInterceptor())
                        .addInterceptor(new DynamicHeaderInterceptor(null))
                        .addInterceptor(new BiliFixedParameterInterceptor())
                        .addInterceptor(new DynamicParameterInterceptor(parameters))
                        .addInterceptor(new BiliSortAndSignInterceptor())
                        .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

                        retrofit.newBuilder().client(urlOkHttpClientBuilder.build())
                        .build();
                        videoDetailNetAPI = retrofit.create(VideoDetailNetAPI.class);
                Response<VideoUrlBean> urlResponse = videoDetailNetAPI.getVideoUrl().execute();

                videoDetailNetAPI.getVideoUrl().enqueue(new Callback<VideoUrlBean>() {
                    @Override
                    public void onResponse(Call<VideoUrlBean> call, Response<VideoUrlBean> response) {
                        String urlString;
                        if(response.body().getData().getDash()!=null){
                            if(cid == currentCid){
                                currentUrl = response.body().getData().getDash().getVideo().get(0).getBase_url();
                            }
                            videoDataSource.setDash(true);
                            ArrayList<String> videoSources=new ArrayList<>();
                            ArrayList<String> descriptions=new ArrayList<>();
                            ArrayList<String> audioSources=new ArrayList<>();
                            for(int j=0;j<response.body().getData().getDash().getVideo().size();j++){
                                videoSources.add(response.body().getData().getDash().getVideo().get(j).getBase_url());
                                String description;
                                switch (response.body().getData().getDash().getVideo().get(j).getId()){
                                    case 16:
                                        description="360P";
                                        break;
                                    case 32:
                                        description="480P";
                                        break;
                                    case 64:
                                        description="720P";
                                        break;
                                    default:
                                        description="1080P";
                                        break;
                                }
                                descriptions.add(description);
                                audioSources.add(response.body().getData().getDash().getAudio().get(0).getBase_url());
                            }
                            videoDataSource.setVideoSources(videoSources);
                            videoDataSource.setDescriptions(descriptions);
                            videoDataSource.setAudioSources(audioSources);
                        }else {
                            if(cid == currentCid){
                                currentUrl = response.body().getData().getDurl().get(0).getUrl();
                            }
                            videoDataSource.setDash(false);
                            ArrayList<String> videoSources=new ArrayList<>();
                            ArrayList<String> descriptions=new ArrayList<>();
                            videoSources.add(response.body().getData().getDurl().get(0).getUrl());
                            descriptions.add(response.body().getData().getFormat());
                            videoDataSource.setVideoSources(videoSources);
                            videoDataSource.setDescriptions(descriptions);
                        }
                        ijkMediaView.addVideoDataSource(videoDataSource);
                    }

                    @Override
                    public void onFailure(Call<VideoUrlBean> call, Throwable t) {

                    }
                });
            }
        } catch (Exception e) {
            Log.e("BiliNextError","error:"+e.getMessage());
        }
        videoDetailNetAPI.getVideoDetailPageInfo().enqueue(new Callback<VideoDetailPageBean>() {
            @Override
            public void onResponse(Call<VideoDetailPageBean> call, Response<VideoDetailPageBean> response) {


            }

            @Override
            public void onFailure(Call<VideoDetailPageBean> call, Throwable t) {
                ToastUtils.showToast(R.string.error_toast);
            }
        });
        return videoListBean;
    }

    @Override
    public boolean isMatchParser(String key) {
        if(Constants.Source.BILI.equals(key)){
            return true;
        }
        return false;
    }
}
