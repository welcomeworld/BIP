package com.github.welcomeworld.bangumi.instrumentality.project.model;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoQualityBean implements Parcelable {
    private String realVideoUrl;
    private String realAudioUrl;
    private String quality;

    public VideoQualityBean() {

    }


    protected VideoQualityBean(Parcel in) {
        realVideoUrl = in.readString();
        realAudioUrl = in.readString();
        quality = in.readString();
    }

    public static final Creator<VideoQualityBean> CREATOR = new Creator<VideoQualityBean>() {
        @Override
        public VideoQualityBean createFromParcel(Parcel in) {
            return new VideoQualityBean(in);
        }

        @Override
        public VideoQualityBean[] newArray(int size) {
            return new VideoQualityBean[size];
        }
    };

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

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(realVideoUrl);
        parcel.writeString(realAudioUrl);
        parcel.writeString(quality);
    }

    public boolean isAvailable() {
        return realVideoUrl != null || realAudioUrl != null;
    }
}
