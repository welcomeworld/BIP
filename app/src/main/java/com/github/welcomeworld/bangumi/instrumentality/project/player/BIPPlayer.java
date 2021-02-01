package com.github.welcomeworld.bangumi.instrumentality.project.player;

import android.view.Surface;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;

public interface BIPPlayer {
    void setVideoQualityBean(VideoQualityBean videoQualityBean);

    void prepareAsync();

    void setOnPreparedListener(OnPreparedListener listener);

    void setSurface(Surface surface);

    void setDataSource(String path);

    void setOnErrorListener(OnErrorListener listener);

    void release();

    void start();

    void reset();

    public interface OnPreparedListener {
        void onPrepared(BIPPlayer bp);
    }

    public interface OnErrorListener {
        boolean onError(BIPPlayer bp, int what, int extra);
    }

    void updatePlayer();

}
