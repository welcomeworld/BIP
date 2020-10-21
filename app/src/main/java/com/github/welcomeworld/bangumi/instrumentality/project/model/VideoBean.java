package com.github.welcomeworld.bangumi.instrumentality.project.model;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoBean implements Parcelable {
    private String title;
    private String url;
    private String cover;
    private String realVideoUrl;
    private String realAudioUrl;
    private boolean dash;
    private String videoKey;
    private String sourceExternalData;

    public VideoBean(){

    }

    protected VideoBean(Parcel in) {
        title = in.readString();
        url = in.readString();
        cover = in.readString();
        realVideoUrl = in.readString();
        realAudioUrl = in.readString();
        dash = in.readByte() != 0;
        videoKey = in.readString();
        sourceExternalData = in.readString();
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

    public String getRealVideoUrl() {
        return realVideoUrl;
    }

    public void setRealVideoUrl(String realVideoUrl) {
        this.realVideoUrl = realVideoUrl;
    }

    public String getRealAudioUrl() {
        return realAudioUrl;
    }

    public void setRealAudioUrl(String realAudioUrl) {
        this.realAudioUrl = realAudioUrl;
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
        parcel.writeString(realVideoUrl);
        parcel.writeString(realAudioUrl);
        parcel.writeByte((byte) (dash ? 1 : 0));
        parcel.writeString(videoKey);
        parcel.writeString(sourceExternalData);
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
}
