package com.github.welcomeworld.bangumi.instrumentality.project.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "download")
public class DownloadInfoBean {
    @Ignore
    public static final int PREPARED = 0;
    @Ignore
    public static final int DOWNLOADING = 1;
    @Ignore
    public static final int PAUSE = 2;
    @Ignore
    public static final int ERROR = 3;
    @Ignore
    public static final int COMPLETE = 4;

    @NonNull
    @PrimaryKey
    private String mediaId = "";

    private String title;

    private String sourceUrl;

    private int downloadState = -1;

    private String localPath;

    private long contentLength = -1;

    private String cover;

    private String downloadUrl;

    private boolean coverPortrait;

    private long duration;

    private long downloadTime;

    private String sourceName;

    private List<VideoListBean> videoData;

    private int selectSourceIndex;

    private int downloadTargetPosition;

    private int downloadingProgress;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getDownloadState() {
        return downloadState;
    }

    public void setDownloadState(int downloadState) {
        this.downloadState = downloadState;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public boolean isCoverPortrait() {
        return coverPortrait;
    }

    public void setCoverPortrait(boolean coverPortrait) {
        this.coverPortrait = coverPortrait;
    }

    @NonNull
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(@NonNull String mediaId) {
        this.mediaId = mediaId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(long downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public List<VideoListBean> getVideoData() {
        return videoData;
    }

    public void setVideoData(List<VideoListBean> videoData) {
        this.videoData = videoData;
    }

    public int getSelectSourceIndex() {
        return selectSourceIndex;
    }

    public void setSelectSourceIndex(int selectSourceIndex) {
        this.selectSourceIndex = selectSourceIndex;
    }

    public int getDownloadTargetPosition() {
        return downloadTargetPosition;
    }

    public void setDownloadTargetPosition(int downloadTargetPosition) {
        this.downloadTargetPosition = downloadTargetPosition;
    }

    public int getDownloadingProgress() {
        return downloadingProgress;
    }

    public void setDownloadingProgress(int downloadingProgress) {
        this.downloadingProgress = downloadingProgress;
    }
}
