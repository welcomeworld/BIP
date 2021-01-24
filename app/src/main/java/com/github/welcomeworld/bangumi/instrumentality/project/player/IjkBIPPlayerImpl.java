package com.github.welcomeworld.bangumi.instrumentality.project.player;

import android.view.Surface;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkBIPPlayerImpl implements BIPPlayer{
    VideoQualityBean videoQualityBean;
    IjkMediaPlayer ijkMediaPlayer;

    public IjkBIPPlayerImpl(){
        createPlayer();
    }

    private void createPlayer() {
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.stop();
            ijkMediaPlayer.setDisplay(null);
            ijkMediaPlayer.release();
        }
        ijkMediaPlayer = new IjkMediaPlayer();
        IjkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
        //ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"max-buffer-size",500*1024);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 100);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"enable-accurate-seek",1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"reconnect",1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"safe",0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "protocol_whitelist", "rtmp,concat,ffconcat,file,subfile,http,https,tls,rtp,tcp,udp,crypto");
        //ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"http_proxy","192.168.0.107");
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "Bilibili Freedoooooom/MarkII");
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"framedrop",5);
    }


    public void setVideoQualityBean(VideoQualityBean videoQualityBean){
        this.videoQualityBean = videoQualityBean;
        setDataSource(videoQualityBean.getRealVideoUrl());
    }

    @Override
    public void prepareAsync() throws IllegalStateException {
        ijkMediaPlayer.prepareAsync();
    }

    @Override
    public void setOnPreparedListener(OnPreparedListener listener) {
        ijkMediaPlayer.setOnPreparedListener(iMediaPlayer -> listener.onPrepared(IjkBIPPlayerImpl.this));
    }

    @Override
    public void setSurface(Surface surface) {
        ijkMediaPlayer.setSurface(surface);
    }

    @Override
    public void setDataSource(String path) {
        try {
            ijkMediaPlayer.setDataSource(path);
        } catch (IOException e) {
            //ignore
        }
    }

    @Override
    public void setOnErrorListener(OnErrorListener listener) {
        ijkMediaPlayer.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                listener.onError(IjkBIPPlayerImpl.this,i,i1);
                return false;
            }
        });
    }

    @Override
    public void release() {
        ijkMediaPlayer.release();
    }

    @Override
    public void start() {
        ijkMediaPlayer.start();
    }
}
