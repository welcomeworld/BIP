package com.github.welcomeworld.bangumi.instrumentality.project.player;

import android.view.Surface;
import android.view.SurfaceHolder;

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

    interface OnPreparedListener {
        void onPrepared(BIPPlayer bp);
    }

    interface OnErrorListener {
        boolean onError(BIPPlayer bp, int what, int extra);
    }

    interface OnCompletionListener {
        void onCompletion(BIPPlayer bp);
    }

    interface OnSeekCompleteListener {
        void onSeekComplete(BIPPlayer bp);
    }

    void updatePlayer();

    boolean isPlaying();

    void pause();

    long getDuration();

    long getCurrentPosition();

    int getVideoWidth();

    int getVideoHeight();

    void setDisplay(SurfaceHolder sh);

    void setScreenOnWhilePlaying(boolean screenOn);

    void seekTo(long time);

    void setOnCompletionListener(OnCompletionListener listener);

    void prepareQualityAsync(String path);

    void setOnSeekCompleteListener(OnSeekCompleteListener listener);
}
