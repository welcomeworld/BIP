package com.github.welcomeworld.bangumi.instrumentality.project.player;

import android.media.MediaPlayer;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bipplayer.BIPPlayer;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkBIPPlayerImpl implements BIPPlayer {
    VideoQualityBean videoQualityBean;
    IjkMediaPlayer ijkMediaPlayer;
    Surface surface;
    SurfaceHolder surfaceHolder;
    private OnSeekCompleteListener mSeeCompleteListener;

    static {
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            //
        }
    }

    public IjkBIPPlayerImpl() {
        updatePlayer();
    }

    public void updatePlayer() {
        MediaPlayer j = new MediaPlayer();
        j.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.stop();
            ijkMediaPlayer.reset();
        } else {
            ijkMediaPlayer = new IjkMediaPlayer();
            ijkMediaPlayer.setOnSeekCompleteListener(new IMediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                    if (mSeeCompleteListener != null) {
                        mSeeCompleteListener.onSeekComplete(IjkBIPPlayerImpl.this);
                    }
                }
            });
        }
        IjkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
        ijkMediaPlayer.setScreenOnWhilePlaying(true);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-buffer-size", 500 * 1024);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 100);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 1);   //需要准备好后自动播放
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 5);

        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "reconnect", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "safe", 0);

        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "dns_cache_clear", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "protocol_whitelist", "rtmp,concat,ffconcat,file,subfile,http,https,tls,rtp,tcp,udp,crypto");
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "Bilibili Freedoooooom/MarkII");

        //ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"http_proxy","192.168.0.107");
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36");
        if (this.surface != null) {
            ijkMediaPlayer.setSurface(surface);
        }
        if (this.surfaceHolder != null) {
            ijkMediaPlayer.setDisplay(this.surfaceHolder);
        }
    }

    @Override
    public void stop() {
        ijkMediaPlayer.stop();
    }

    public void setVideoQualityBean(VideoQualityBean videoQualityBean) {
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
    public void setOnCompletionListener(OnCompletionListener listener) {
        ijkMediaPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                listener.onCompletion(IjkBIPPlayerImpl.this);
            }
        });
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
                listener.onError(IjkBIPPlayerImpl.this, i, i1);
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

    @Override
    public boolean isPlaying() {
        return ijkMediaPlayer.isPlaying();
    }

    @Override
    public void pause() {
        ijkMediaPlayer.pause();
    }

    @Override
    public long getDuration() {
        return ijkMediaPlayer.getDuration();
    }

    @Override
    public long getCurrentPosition() {
        return ijkMediaPlayer.getCurrentPosition();
    }

    @Override
    public int getVideoHeight() {
        return ijkMediaPlayer.getVideoHeight();
    }

    @Override
    public int getVideoWidth() {
        return ijkMediaPlayer.getVideoWidth();
    }

    @Override
    public void setDisplay(SurfaceHolder sh) {
        surfaceHolder = sh;
        ijkMediaPlayer.setDisplay(sh);
    }

    @Override
    public void setScreenOnWhilePlaying(boolean screenOn) {
        ijkMediaPlayer.setScreenOnWhilePlaying(screenOn);
    }

    @Override
    public void seekTo(long time) {
        ijkMediaPlayer.seekTo(time);
    }

    IjkMediaPlayer tempMediaPlayer;

    @Override
    public void prepareQualityAsync(String path) {
        if (tempMediaPlayer != null) {
            tempMediaPlayer.stop();
            tempMediaPlayer.reset();
        }
        tempMediaPlayer = new IjkMediaPlayer();
        IjkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
        tempMediaPlayer.setScreenOnWhilePlaying(true);
//        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-buffer-size", 500 * 1024);
        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 100);
        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "reconnect", 1);
        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "safe", 0);

        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 1);   //需要准备好后自动播放
        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "dns_cache_clear", 1);
        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "protocol_whitelist", "rtmp,concat,ffconcat,file,subfile,http,https,tls,rtp,tcp,udp,crypto");
        //tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"http_proxy","192.168.0.107");

//        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36");
        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "Bilibili Freedoooooom/MarkII");
        tempMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 5);
        tempMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                long currentPosition = ijkMediaPlayer.getCurrentPosition();
                long duration = ijkMediaPlayer.getDuration();
                if (duration > currentPosition + 500) {
                    iMediaPlayer.seekTo(currentPosition + 500);
                }
                iMediaPlayer.setOnSeekCompleteListener(new IMediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                        if (tempMediaPlayer != null) {
                            ijkMediaPlayer.stop();
                            ijkMediaPlayer.reset();
                            ijkMediaPlayer = tempMediaPlayer;
                            if (surface != null) {
                                ijkMediaPlayer.setSurface(surface);
                            }
                            if (surfaceHolder != null) {
                                ijkMediaPlayer.setDisplay(surfaceHolder);
                            }
                            tempMediaPlayer = null;
                        } else {
                            iMediaPlayer.start();
                        }
                    }
                });
            }
        });
        try {
            tempMediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempMediaPlayer.prepareAsync();
    }

    @Override
    public void setOnSeekCompleteListener(OnSeekCompleteListener listener) {
        this.mSeeCompleteListener = listener;
    }

    @Override
    public void setOnBufferingUpdateListener(OnBufferingUpdateListener listener) {
        ijkMediaPlayer.setOnBufferingUpdateListener((iMediaPlayer, i) -> listener.onBufferingUpdate(IjkBIPPlayerImpl.this, i));
    }

    @Override
    public void setOption(int category, String name, String value) {
        ijkMediaPlayer.setOption(category,name,value);
    }

    @Override
    public void setOnInfoListener(OnInfoListener listener) {
        ijkMediaPlayer.setOnInfoListener((iMediaPlayer, i, i1) -> {
            listener.onInfo(IjkBIPPlayerImpl.this,i,i1);
            return false;
        });
    }
}
