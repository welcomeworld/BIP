package com.github.welcomeworld.bangumi.instrumentality.project.model;

import java.util.List;

public class VideoListBean {
    private String title;
    private String cover;
    private int selectIndex;
    private List<VideoBean> videoBeanList;

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

    public List<VideoBean> getVideoBeanList() {
        return videoBeanList;
    }

    public void setVideoBeanList(List<VideoBean> videoBeanList) {
        this.videoBeanList = videoBeanList;
    }
}
