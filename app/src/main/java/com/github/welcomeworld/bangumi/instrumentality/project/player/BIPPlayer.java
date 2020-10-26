package com.github.welcomeworld.bangumi.instrumentality.project.player;

import android.media.MediaPlayer;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;

import java.io.IOException;

public class BIPPlayer extends MediaPlayer {
    VideoQualityBean videoQualityBean;

    public void setVideoQualityBean(VideoQualityBean videoQualityBean){
        this.videoQualityBean = videoQualityBean;
        try {
            setDataSource(videoQualityBean.getRealVideoUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
