package com.github.welcomeworld.bangumi.instrumentality.project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class VideoListBean implements Parcelable {
    private String title;
    private String cover;
    private int selectIndex;
    private String sourceName;
    private ArrayList<VideoBean> videoBeanList;
    private String sourceExternalData;

    public VideoListBean(){

    };


    protected VideoListBean(Parcel in) {
        title = in.readString();
        cover = in.readString();
        selectIndex = in.readInt();
        sourceName = in.readString();
        videoBeanList = in.createTypedArrayList(VideoBean.CREATOR);
        sourceExternalData = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(cover);
        dest.writeInt(selectIndex);
        dest.writeString(sourceName);
        dest.writeTypedList(videoBeanList);
        dest.writeString(sourceExternalData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VideoListBean> CREATOR = new Creator<VideoListBean>() {
        @Override
        public VideoListBean createFromParcel(Parcel in) {
            return new VideoListBean(in);
        }

        @Override
        public VideoListBean[] newArray(int size) {
            return new VideoListBean[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public ArrayList<VideoBean> getVideoBeanList() {
        return videoBeanList;
    }

    public void setVideoBeanList(ArrayList<VideoBean> videoBeanList) {
        this.videoBeanList = videoBeanList;
    }

    public VideoBean getCurrentVideoBean(){
        if(videoBeanList == null||videoBeanList.size()==0){
            return null;
        }
        return videoBeanList.get(selectIndex);
    }

    public String getSourceExternalData() {
        return sourceExternalData;
    }

    public void setSourceExternalData(String sourceExternalData) {
        this.sourceExternalData = sourceExternalData;
    }
}
