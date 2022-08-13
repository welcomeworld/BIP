package com.github.welcomeworld.bangumi.instrumentality.project.player;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;
import com.github.welcomeworld.bipplayer.BIPPlayer;
import com.github.welcomeworld.bipplayer.DefaultBIPPlayer;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.EventLogger;

import java.io.FileDescriptor;
import java.util.HashMap;
import java.util.Map;

public class BipExoPlayer implements BIPPlayer {
    private final String TAG = "BipExoPlayer";
    private final ExoPlayer exoPlayer;
    DefaultHttpDataSource.Factory httpFactory;
    DefaultMediaSourceFactory mediaSourceFactory;
    private OnPreparedListener mOnPreparedListener;
    private OnCompletionListener mOnCompletionListener;
    private OnBufferingUpdateListener mOnBufferingUpdateListener;
    private OnSeekCompleteListener mOnSeekCompleteListener;
    private OnErrorListener mOnErrorListener;
    private OnInfoListener mOnInfoListener;
    long tempQualityPosition = 0;
    Map<String, String> headers = new HashMap<>();
    boolean tempPrepare = false;
    boolean tempSeek = false;
    private SurfaceHolder mSurfaceHolder;
    private boolean mScreenOnWhilePlaying = true;

    public BipExoPlayer() {
        Context appContext = BIPApp.getInstance().getApplicationContext();
        exoPlayer = new ExoPlayer.Builder(appContext).setLooper(Looper.getMainLooper()).build();
        Player.Listener listener = new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                switch (playbackState) {
                    case Player.STATE_READY:
                        if (tempQualityPosition != 0) {
                            exoPlayer.seekTo(tempQualityPosition);
                            tempQualityPosition = 0;
                        }
                        if (tempPrepare) {
                            if (mOnPreparedListener != null) {
                                mOnPreparedListener.onPrepared(BipExoPlayer.this);
                            }
                            tempPrepare = false;
                        }
                        if (tempSeek) {
                            if (mOnSeekCompleteListener != null) {
                                mOnSeekCompleteListener.onSeekComplete(BipExoPlayer.this);
                            }
                            tempSeek = false;
                        }
                        if (mOnInfoListener != null) {
                            mOnInfoListener.onInfo(BipExoPlayer.this, 1, 0);
                        }
                        break;
                    case Player.STATE_ENDED:
                        if (mOnCompletionListener != null) {
                            mOnCompletionListener.onCompletion(BipExoPlayer.this);
                        }
                        if (mOnInfoListener != null) {
                            mOnInfoListener.onInfo(BipExoPlayer.this, 1, 0);
                        }
                        break;
                    case Player.STATE_BUFFERING:
                        if (mOnInfoListener != null) {
                            mOnInfoListener.onInfo(BipExoPlayer.this, 0, 0);
                        }
                        break;
                    case Player.STATE_IDLE:
                        if (mOnInfoListener != null) {
                            mOnInfoListener.onInfo(BipExoPlayer.this, 1, 0);
                        }
                        break;
                }
                updateSurfaceScreenOn();
            }

            @Override
            public void onPlayerError(@NonNull PlaybackException error) {
                if (mOnErrorListener != null) {
                    mOnErrorListener.onError(BipExoPlayer.this, error.errorCode, 0);
                }
            }

            @Override
            public void onEvents(@NonNull Player player, Player.Events events) {
                if (events.contains(Player.EVENT_IS_LOADING_CHANGED)) {
                    if (mOnBufferingUpdateListener != null) {
                        mOnBufferingUpdateListener.onBufferingUpdate(BipExoPlayer.this, player.getBufferedPercentage());
                    }
                }
            }
        };
        exoPlayer.addListener(listener);
        exoPlayer.addAnalyticsListener(new EventLogger());
        httpFactory = new DefaultHttpDataSource.Factory();
        mediaSourceFactory = new DefaultMediaSourceFactory(new DefaultDataSource.Factory(appContext, httpFactory));
    }

    @Override
    public void prepareAsync() {
        tempPrepare = true;
        exoPlayer.prepare();
    }

    @Override
    public void setOnPreparedListener(OnPreparedListener listener) {
        mOnPreparedListener = listener;
    }

    @Override
    public void setSurface(Surface surface) {
        mSurfaceHolder = null;
        exoPlayer.setVideoSurface(surface);
        updateSurfaceScreenOn();
    }

    private void updateSurfaceScreenOn() {
        if (mSurfaceHolder != null) {
            mSurfaceHolder.setKeepScreenOn(mScreenOnWhilePlaying && isPlaying());
        }
    }

    @Override
    public void setDataSource(String path) {
        MediaItem mediaItem = MediaItem.fromUri(path);
        MediaSource mediaSource = mediaSourceFactory.createMediaSource(mediaItem);
        exoPlayer.setMediaSource(mediaSource);
    }

    @Override
    public void setDataSource(String path, Map<String, String> headers) {
        MediaItem mediaItem = MediaItem.fromUri(path);
        httpFactory.setDefaultRequestProperties(headers);
        MediaSource mediaSource = mediaSourceFactory.createMediaSource(mediaItem);
        exoPlayer.setMediaSource(mediaSource);
    }

    @Override
    public void setDataSource(Context context, Uri uri) {
        MediaItem mediaItem = MediaItem.fromUri(uri);
        MediaSource mediaSource = mediaSourceFactory.createMediaSource(mediaItem);
        exoPlayer.setMediaSource(mediaSource);
    }

    @Override
    public void setDataSource(Context context, Uri uri, Map<String, String> headers) {
        MediaItem mediaItem = MediaItem.fromUri(uri);
        httpFactory.setDefaultRequestProperties(headers);
        MediaSource mediaSource = mediaSourceFactory.createMediaSource(mediaItem);
        exoPlayer.setMediaSource(mediaSource);
    }

    @Override
    public void setDataSource(FileDescriptor fd) {

    }

    @Override
    public void setDataSource(FileDescriptor fd, long offset, long length) {

    }

    @Override
    public void setOnErrorListener(OnErrorListener listener) {
        mOnErrorListener = listener;
    }

    @Override
    public void release() {
        exoPlayer.release();
        mOnInfoListener = null;
        mOnBufferingUpdateListener = null;
        mOnSeekCompleteListener = null;
        mOnCompletionListener = null;
        mOnErrorListener = null;
        mOnPreparedListener = null;
    }

    @Override
    public void start() {
        exoPlayer.play();
    }

    @Override
    public void reset() {
        exoPlayer.stop();
    }

    @Override
    public void stop() {
        exoPlayer.stop();
    }

    @Override
    public boolean isPlaying() {
        return exoPlayer.isPlaying();
    }

    @Override
    public void pause() {
        exoPlayer.pause();
    }

    @Override
    public long getDuration() {
        return exoPlayer.getDuration();
    }

    @Override
    public long getCurrentPosition() {
        return exoPlayer.getCurrentPosition();
    }

    @Override
    public int getVideoWidth() {
        return exoPlayer.getVideoSize().width;
    }

    @Override
    public int getVideoHeight() {
        return exoPlayer.getVideoSize().height;
    }

    @Override
    public void setDisplay(SurfaceHolder sh) {
        mSurfaceHolder = sh;
        exoPlayer.setVideoSurfaceHolder(sh);
        updateSurfaceScreenOn();
    }

    @Override
    public void setScreenOnWhilePlaying(boolean screenOn) {
        if (mScreenOnWhilePlaying != screenOn) {
            mScreenOnWhilePlaying = screenOn;
            updateSurfaceScreenOn();
        }
    }

    @Override
    public void seekTo(long time) {
        tempSeek = true;
        exoPlayer.seekTo(time);
    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {
        mOnCompletionListener = listener;
    }

    @Override
    public void prepareQualityAsync(String path) {
        tempQualityPosition = getCurrentPosition();
        setDataSource(path);
        prepareAsync();
    }

    @Override
    public void setOnSeekCompleteListener(OnSeekCompleteListener listener) {
        mOnSeekCompleteListener = listener;
    }

    @Override
    public void setOnBufferingUpdateListener(OnBufferingUpdateListener listener) {
        mOnBufferingUpdateListener = listener;
    }

    @Override
    public void setOption(int category, String name, String value) {
        if (category == DefaultBIPPlayer.OPT_CATEGORY_FORMAT) {
            if (name.equals("user_agent")) {
                httpFactory.setUserAgent(value);
            } else if (name.equals("headers")) {
                String[] headersString = value.split("\r\n");
                for (String headerString : headersString) {
                    int index = headerString.indexOf(":");
                    headers.put(headerString.substring(0, index), headerString.substring(index + 1));
                    httpFactory.setDefaultRequestProperties(headers);
                }
            }
        } else if (category == DefaultBIPPlayer.OPT_CATEGORY_PLAYER) {
            if (name.equals("start-on-prepared")) {
                exoPlayer.setPlayWhenReady("1".equals(value));
            }
        }
    }

    @Override
    public void setOnInfoListener(OnInfoListener listener) {
        mOnInfoListener = listener;
    }

    @Override
    public int getFps() {
        return 0;
    }

    @Override
    public float getSpeed() {
        return exoPlayer.getPlaybackParameters().speed;
    }

    @Override
    public void setSpeed(float speed) {
        exoPlayer.setPlaybackSpeed(speed);
    }
}
