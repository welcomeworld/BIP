package com.github.welcomeworld.bangumi.instrumentality.project.player;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;

import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class BIPPlayer extends MediaPlayer {
    VideoQualityBean videoQualityBean;
    IjkMediaPlayer ijkMediaPlayer;

    public BIPPlayer(){
        setScreenOnWhilePlaying(true);
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
        try {
//            ijkMediaPlayer.setDataSource(videoQualityBean.getRealVideoUrl());
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.P){
                try {
                    Method forName = Class.class.getDeclaredMethod("forName", String.class);
                    Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
                    Class<?> vmRuntimeClass = (Class<?>) forName.invoke(null, "dalvik.system.VMRuntime");
                    Method getRuntime = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "getRuntime", null);
                    Method setHiddenApiExemptions = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
                    Object sVmRuntime = getRuntime.invoke(null);
                    setHiddenApiExemptions.invoke(sVmRuntime, new Object[]{new String[]{"L"}});
                } catch (Throwable e) {
                    LogUtil.e("[error]", "reflect bootstrap failed:");
                }
            }
            LogUtil.e("BIPPlayer","setDataSource:"+videoQualityBean.getRealVideoUrl());
            Map<String,String> headers = new HashMap<>();
            headers.put("user_agent","Bilibili Freedoooooom/MarkII");
            Class<MediaPlayer> mediaPlayerClass = MediaPlayer.class;
            Method setSourceMethod = mediaPlayerClass.getDeclaredMethod("setDataSource",String.class,Map.class);
            setSourceMethod.setAccessible(true);
            setSourceMethod.invoke(this,videoQualityBean.getRealVideoUrl(),headers);
//            setDataSource(BIPApp.getInstance(),Uri.parse(videoQualityBean.getRealVideoUrl()),headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prepareAsync() throws IllegalStateException {
//        ijkMediaPlayer.prepareAsync();
        super.prepareAsync();
    }

    @Override
    public void setOnPreparedListener(OnPreparedListener listener) {
        super.setOnPreparedListener(listener);
        ijkMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener(){

            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                listener.onPrepared(null);
            }
        });
    }

    @Override
    public void setSurface(Surface surface) {
        super.setSurface(surface);
        setScreenOnWhilePlaying(true);
//        ijkMediaPlayer.setSurface(surface);
    }

    @Override
    public void setDataSource(String path) throws IOException, IllegalArgumentException, IllegalStateException, SecurityException {
        LogUtil.e("BIPPlayer","setDataSource:"+path);
        super.setDataSource(path);
    }

}
