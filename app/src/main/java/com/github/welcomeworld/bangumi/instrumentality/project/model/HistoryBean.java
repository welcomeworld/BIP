package com.github.welcomeworld.bangumi.instrumentality.project.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.github.welcomeworld.bangumi.instrumentality.project.persistence.AppBaseConverter;

import java.util.List;

@Entity(tableName = "history",primaryKeys = {"uid","vid","isFav"})
@TypeConverters({AppBaseConverter.class})
public class HistoryBean {
    private int uid;
    @NonNull
    private String vid = "";
    private String title;
    private String cover;
    private boolean coverPortrait;
    private List<VideoListBean> videoData;
    private int selectSourceIndex;
    private long viewTime;
    private boolean isActive = true;
    private boolean isFav;
    private long favTime;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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

    public boolean isCoverPortrait() {
        return coverPortrait;
    }

    public void setCoverPortrait(boolean coverPortrait) {
        this.coverPortrait = coverPortrait;
    }

    public int getSelectSourceIndex() {
        return selectSourceIndex;
    }

    public void setSelectSourceIndex(int selectSourceIndex) {
        this.selectSourceIndex = selectSourceIndex;
    }

    public long getViewTime() {
        return viewTime;
    }

    public void setViewTime(long viewTime) {
        this.viewTime = viewTime;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getFavTime() {
        return favTime;
    }

    public void setFavTime(long favTime) {
        this.favTime = favTime;
    }

    public String getVid() {
        if(vid.isEmpty()){
            vid = videoData.get(0).getSourceName()+":"+videoData.get(0).getVideoBeanList().get(0).getVideoKey();
        }
        return vid;
    }

    public void setVid(String vId) {
        this.vid = vId;
    }

    public List<VideoListBean> getVideoData() {
        return videoData;
    }

    public void setVideoData(List<VideoListBean> videoData) {
        this.videoData = videoData;
    }
}
