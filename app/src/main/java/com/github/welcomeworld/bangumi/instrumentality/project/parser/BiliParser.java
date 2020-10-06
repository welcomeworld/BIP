package com.github.welcomeworld.bangumi.instrumentality.project.parser;

import android.content.ContentProviderOperation;
import android.widget.Toast;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.retrofit.BaseUrl;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.retrofit.IndexNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.retrofit.IndexRecommendBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.retrofit.IndexRecommendDataBean;

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
                .addInterceptor(new FixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
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
                    videoListBean.setCover(moreData.get(i).getCover()+"@320w_200h_1e_1c.webp");
                    List<VideoBean> videoBeans = new ArrayList<>();
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
                .addInterceptor(new FixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
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
                    videoListBean.setCover(moreData.get(i).getCover()+"@320w_200h_1e_1c.webp");
                    List<VideoBean> videoBeans = new ArrayList<>();
                    videoListBean.setVideoBeanList(videoBeans);
                    result.add(videoListBean);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
