package com.github.welcomeworld.bangumi.instrumentality.project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class VideoListBean implements Parcelable {
    private String title;
    private String cover;
    private int selectIndex;
    private String sourceName;
    private ArrayList<VideoBean> videoBeanList = new ArrayList<>();
    private String sourceExternalData;
    private boolean coverPortrait;
    private String tag;
    private String VideoListDes;
    private String seasonTitle;

    public VideoListBean() {

    }


    protected VideoListBean(Parcel in) {
        title = in.readString();
        cover = in.readString();
        selectIndex = in.readInt();
        sourceName = in.readString();
        videoBeanList = in.createTypedArrayList(VideoBean.CREATOR);
        sourceExternalData = in.readString();
        coverPortrait = in.readByte() == 1;
        tag = in.readString();
        VideoListDes = in.readString();
        seasonTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(cover);
        dest.writeInt(selectIndex);
        dest.writeString(sourceName);
        dest.writeTypedList(videoBeanList);
        dest.writeString(sourceExternalData);
        dest.writeByte(coverPortrait ? (byte) 1 : 0);
        dest.writeString(tag);
        dest.writeString(VideoListDes);
        dest.writeString(seasonTitle);
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
        if (videoBeanList == null) {
            videoBeanList = new ArrayList<>();
        }
        return videoBeanList;
    }

    public void setVideoBeanList(ArrayList<VideoBean> videoBeanList) {
        this.videoBeanList = videoBeanList;
    }

    public VideoBean getCurrentVideoBean() {
        if (videoBeanList == null || videoBeanList.size() == 0) {
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

    public boolean isCoverPortrait() {
        return coverPortrait;
    }

    public void setCoverPortrait(boolean coverPortrait) {
        this.coverPortrait = coverPortrait;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getVideoListDes() {
        return VideoListDes;
    }

    public void setVideoListDes(String videoListDes) {
        VideoListDes = videoListDes;
    }

    public String getSeasonTitle() {
        return seasonTitle;
    }

    public void setSeasonTitle(String seasonTitle) {
        this.seasonTitle = seasonTitle;
    }

    @Override
    public String toString() {
        return "VideoListBean{" +
                "title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", selectIndex=" + selectIndex +
                ", sourceName='" + sourceName + '\'' +
                ", videoBeanList size=" + videoBeanList.size() +
                ", sourceExternalData='" + sourceExternalData + '\'' +
                ", coverPortrait=" + coverPortrait +
                ", tag='" + tag + '\'' +
                ", VideoListDes='" + VideoListDes + '\'' +
                ", seasonTitle='" + seasonTitle + '\'' +
                '}';
    }
}
