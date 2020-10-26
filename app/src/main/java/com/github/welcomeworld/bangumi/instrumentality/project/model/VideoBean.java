package com.github.welcomeworld.bangumi.instrumentality.project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class VideoBean implements Parcelable {
    private String title;
    private String url;
    private String cover;
    private boolean dash;
    private String videoKey;
    private String sourceExternalData;
    private String danmakuUrl;
    private ArrayList<VideoQualityBean> qualityBeans = new ArrayList<>();
    private int currentQualityIndex;

    public VideoBean(){

    }


    protected VideoBean(Parcel in) {
        title = in.readString();
        url = in.readString();
        cover = in.readString();
        dash = in.readByte() != 0;
        videoKey = in.readString();
        sourceExternalData = in.readString();
        danmakuUrl = in.readString();
        qualityBeans = in.createTypedArrayList(VideoQualityBean.CREATOR);
        currentQualityIndex = in.readInt();
    }

    public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel in) {
            return new VideoBean(in);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isDash() {
        return dash;
    }

    public void setDash(boolean dash) {
        this.dash = dash;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(url);
        parcel.writeString(cover);
        parcel.writeByte((byte) (dash ? 1 : 0));
        parcel.writeString(videoKey);
        parcel.writeString(sourceExternalData);
        parcel.writeString(danmakuUrl);
        parcel.writeTypedList(qualityBeans);
        parcel.writeInt(currentQualityIndex);
    }

    public String getVideoKey() {
        return videoKey;
    }

    public void setVideoKey(String videoKey) {
        this.videoKey = videoKey;
    }

    public String getSourceExternalData() {
        return sourceExternalData;
    }

    public void setSourceExternalData(String sourceExternalData) {
        this.sourceExternalData = sourceExternalData;
    }

    public String getDanmakuUrl() {
        return danmakuUrl;
    }

    public void setDanmakuUrl(String danmakuUrl) {
        this.danmakuUrl = danmakuUrl;
    }

    public ArrayList<VideoQualityBean> getQualityBeans() {
        return qualityBeans;
    }

    public void setQualityBeans(ArrayList<VideoQualityBean> qualityBeans) {
        this.qualityBeans = qualityBeans;
    }

    public int getCurrentQualityIndex() {
        return currentQualityIndex;
    }

    public void setCurrentQualityIndex(int currentQualityIndex) {
        this.currentQualityIndex = currentQualityIndex;
    }

    public VideoQualityBean getCurrentQualityBean(){
        if(qualityBeans == null||qualityBeans.size()<=currentQualityIndex){
            return null;
        }
        return qualityBeans.get(currentQualityIndex);
    }
}
