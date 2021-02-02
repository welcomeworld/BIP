package com.github.welcomeworld.bangumi.instrumentality.project.player;

import android.view.Surface;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkBIPPlayerImpl implements BIPPlayer{
    VideoQualityBean videoQualityBean;
    IjkMediaPlayer ijkMediaPlayer;
    Surface surface;

    public IjkBIPPlayerImpl(){
        updatePlayer();
    }

    public void updatePlayer() {
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.stop();
            ijkMediaPlayer.reset();
        }else {
            ijkMediaPlayer = new IjkMediaPlayer();
        }
        IjkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"max-buffer-size",500*1024);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 100);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"enable-accurate-seek",1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"reconnect",1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"safe",0);

        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 1);   //需要准备好后自动播放
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "dns_cache_clear", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "protocol_whitelist", "rtmp,concat,ffconcat,file,subfile,http,https,tls,rtp,tcp,udp,crypto");
        //ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"http_proxy","192.168.0.107");

        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36");
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "Bilibili Freedoooooom/MarkII");
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"framedrop",5);
        if(this.surface!=null){
            ijkMediaPlayer.setSurface(surface);
        }
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
        this.surface = surface;
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

    @Override
    public void reset() {
        ijkMediaPlayer.reset();
    }
}
