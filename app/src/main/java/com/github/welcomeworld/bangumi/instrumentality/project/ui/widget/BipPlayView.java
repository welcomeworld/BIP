package com.github.welcomeworld.bangumi.instrumentality.project.ui.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BiliDanmukuParser;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.SettingConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.BaseActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.StringUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ThreadUtil;
import com.github.welcomeworld.bipplayer.BIPPlayer;
import com.github.welcomeworld.devbase.utils.StringUtils;
import com.github.welcomeworld.devbase.utils.ToastUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BipPlayView extends ConstraintLayout {
    ConstraintLayout controllerParent;
    private ImageView playPauseView;
    private SeekBar videoSeekView;
    private TextView videoPositionView;
    private TextView videoDurationView;
    private TextView videoQualityView;
    private ImageView videoFullScreenView;
    private ProgressBar videoBottomProgressView;
    private SurfaceView surfaceView;
    private SurfaceHolder videoSurfaceHolder;
    private BaseActivity detachActivity;
    private TextView bottomShadowView;
    private TextView topShadowView;
    private TextView titleView;
    private IDanmakuView danmakuView;
    private TextView batteryView;
    private TextView timeView;
    private TextView fastForwardView;
    private ProgressBar videoBufferingView;
    BIPPlayer bipPlayer;
    boolean isFullScreen;
    VideoBean currentVideoBean;
    DanmakuContext danmakuContext;
    BaseDanmakuParser baseDanmakuParser;
    private long duration = 0;
    private final int SEEKBAR_MAX = 1000;
    private double lastXpercentage = 0;
    private double lastYpercentage = 0;
    private boolean fastfowarding = false;
    private boolean skipAction = false;
    private long fastforward_record = 0;
    private boolean userSeeking = false;
    private float playSpeed = 1.0f;
    private boolean longPressSpeedChange = false;

    private BIPPlayer.OnPreparedListener mOnPreparedListener;
    private BIPPlayer.OnCompletionListener mOnCompletionListener;
    private BIPPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;
    private BIPPlayer.OnSeekCompleteListener mOnSeekCompleteListener;
    private BIPPlayer.OnErrorListener mOnErrorListener;
    private BIPPlayer.OnInfoListener mOnInfoListener;

    private static String TAG = "BipPlayView";

    public BipPlayView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BipPlayView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BipPlayView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public BipPlayView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {
        if (context instanceof Activity) {
            detachActivity = (BaseActivity) context;
        }
        View itemView = LayoutInflater.from(context).inflate(R.layout.custom_play_view, this);
        playPauseView = itemView.findViewById(R.id.bip_play_view_play_pause);
        videoSeekView = itemView.findViewById(R.id.bip_play_view_seek_bar);
        videoSeekView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    long targetTime = progress * bipPlayer.getDuration() / SEEKBAR_MAX;
                    String seekText = StringUtils.formatTime(targetTime) + "/" + StringUtils.formatTime(duration);
                    fastForwardView.setText(seekText);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                playPauseView.removeCallbacks(hideControllerRunnable);
                fastForwardView.setVisibility(VISIBLE);
                userSeeking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                playPauseView.postDelayed(hideControllerRunnable, 3500);
                fastForwardView.setVisibility(GONE);
                userSeeking = false;
                if (bipPlayer != null && bipPlayer.getDuration() > 0) {
                    long targetTime = seekBar.getProgress() * bipPlayer.getDuration() / SEEKBAR_MAX;
                    videoPositionView.setText(StringUtil.formatTime(targetTime));
                    bipPlayer.seekTo(targetTime);
                    videoBottomProgressView.setProgress(seekBar.getProgress());
                    removeCallbacks(progressChangeRunnable);
                    post(progressChangeRunnable);
                }
            }
        });
        bottomShadowView = itemView.findViewById(R.id.bip_play_view_bottom_shadow);
        topShadowView = itemView.findViewById(R.id.bip_play_view_top_shadow);
        titleView = itemView.findViewById(R.id.bip_play_view_title);
        batteryView = itemView.findViewById(R.id.bip_play_view_battery);
        timeView = itemView.findViewById(R.id.bip_play_view_time);
        fastForwardView = itemView.findViewById(R.id.bip_play_view_fast_forward);
        videoPositionView = itemView.findViewById(R.id.bip_play_view_current_position);
        videoDurationView = itemView.findViewById(R.id.bip_play_view_duration);
        videoBottomProgressView = itemView.findViewById(R.id.bip_play_view_bottom_progress);
        videoBufferingView = itemView.findViewById(R.id.bip_play_view_buffering);
        controllerParent = itemView.findViewById(R.id.bip_play_view_controller_parent);
        videoQualityView = itemView.findViewById(R.id.bip_play_view_quality);
        videoQualityView.setOnClickListener(playItemClickListener);
        videoFullScreenView = itemView.findViewById(R.id.bip_play_view_fullscreen);
        videoFullScreenView.setOnClickListener(playItemClickListener);
        surfaceView = itemView.findViewById(R.id.bip_play_view_surface);
        surfaceView.getHolder().addCallback(surfaceHolderCallback);
        gestureDetectorCompat = new GestureDetectorCompat(context, gestureListener);
        setOnTouchListener((v, event) -> {
            LogUtil.e("GestureTest", "Touch");
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (bipPlayer != null) {
                    if (fastfowarding) {
                        fastForwardView.setVisibility(GONE);
                        bipPlayer.seekTo(fastforward_record);
                        playPauseView.postDelayed(hideControllerRunnable, 3500);
                        userSeeking = false;
                    }
                    if (longPressSpeedChange) {
                        playSpeed = 1.0f;
                        bipPlayer.setSpeed(playSpeed);
                    }
                }
                lastXpercentage = 0;
                lastYpercentage = 0;
                skipAction = false;
                fastfowarding = false;
                fastforward_record = 0;
                longPressSpeedChange = false;
            }
            gestureDetectorCompat.onTouchEvent(event);
            return true;
        });
        playPauseView.setOnClickListener(playItemClickListener);
        danmakuView = itemView.findViewById(R.id.bip_play_danmaku_view);
        setTime();
    }

    SurfaceHolder.Callback surfaceHolderCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            LogUtil.e("SurfaceTest", "Created");
            videoSurfaceHolder = holder;
            if (bipPlayer != null) {
                bipPlayer.setDisplay(videoSurfaceHolder);
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            videoSurfaceHolder = holder;
            if (bipPlayer != null) {
                bipPlayer.setDisplay(videoSurfaceHolder);
            }
            LogUtil.e("SurfaceTest", "Change");
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            LogUtil.e("SurfaceTest", "Destroy");
//            bipPlayer.pause();
        }
    };

    private GestureDetectorCompat gestureDetectorCompat;
    private final GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() {

        long lastTapUpTime = 0;
        private int screen_width = -1;
        private int screen_height = -1;
        private static final long FASTFORWARD_MAX = 10000;

        @Override
        public boolean onDown(MotionEvent e) {
            screen_height = getResources().getDisplayMetrics().heightPixels;
            screen_width = getResources().getDisplayMetrics().widthPixels;
            LogUtil.e("GestureTest", "Down x:" + e.getX() + " y:" + e.getY() + " width:" + getWidth() + " height:" + getHeight());
            if (e.getX() < getWidth() / 4D || e.getX() > getWidth() * 3 / 4d || e.getY() < getHeight() / 4D || e.getY() > getHeight() * 3 / 4d) {
                LogUtil.e("GestureTest", "down skip");
                skipAction = true;
            }
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            LogUtil.e("GestureTest", "SingleTap");
            if (qualityView != null) {
                hideQualityWindow();
                return true;
            }
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTapUpTime < 400) {
                onDoubleTapUp(e);
            } else {
                ThreadUtil.postDelayed(100, new Runnable() {
                    @Override
                    public void run() {
                        onRealSingleTapUp();
                    }
                });
            }
            lastTapUpTime = currentTime;
            return false;
        }

        private void onRealSingleTapUp() {
            if (isControllerShowing()) {
                hideController();
            } else {
                showController();
                playPauseView.setSelected(bipPlayer != null && bipPlayer.isPlaying());
            }
        }

        public void onDoubleTapUp(MotionEvent e) {
            playOrPause();
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            String fastfowardText;
            double change;
            double xpercentage = -distanceX / screen_width * 2 + lastXpercentage;
            double ypercentage = distanceY / screen_height * 2 + lastYpercentage;
            if (e2.getX() < getWidth() / 4D || e2.getX() > getWidth() * 3 / 4d || e2.getY() < getHeight() / 4D || e2.getY() > getHeight() * 3 / 4d || bipPlayer == null) {
                LogUtil.e("GestureTest", "scroll skip");
                return false;
            }
            if (!fastfowarding && !skipAction) {
                if (xpercentage > 0.1 || xpercentage < -0.1) {
                    LogUtil.e("GestureTest", "set fastfoward");
                    fastfowarding = true;
                    fastforward_record = bipPlayer.getCurrentPosition();
                    fastfowardText = StringUtils.formatTime(fastforward_record) + "/" + StringUtils.formatTime(duration);
                    fastForwardView.setText(fastfowardText);
                    fastForwardView.setVisibility(VISIBLE);
                    showController();
                    playPauseView.removeCallbacks(hideControllerRunnable);
                    userSeeking = true;
                } else if (ypercentage > 0.1 || ypercentage < -0.1) {
                    LogUtil.e("GestureTest", "scroll set skip");
                    skipAction = true;
                } else {
                    lastYpercentage = ypercentage;
                    lastXpercentage = xpercentage;
                }
                return false;
            }
            if (fastfowarding) {
                LogUtil.e("GestureTest", "scroll fastfoward");
                change = xpercentage * FASTFORWARD_MAX + fastforward_record > duration ? duration : xpercentage * FASTFORWARD_MAX + fastforward_record;
                fastforward_record = (long) change;
                fastfowardText = StringUtils.formatTime(fastforward_record) + "/" + StringUtils.formatTime(duration);
                fastForwardView.setText(fastfowardText);
                videoSeekView.setProgress((int) (change * SEEKBAR_MAX / duration));
                lastXpercentage = 0;
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            if (e.getX() > (float) screen_width * 2 / 3) {
                longPressSpeedChange = true;
                if (bipPlayer != null) {
                    if (e.getY() < (float) screen_height / 3) {
                        playSpeed = 4f;
                    } else if (e.getY() < (float) screen_height * 2 / 3) {
                        playSpeed = 3f;
                    } else {
                        playSpeed = 2f;
                    }
                    bipPlayer.setSpeed(playSpeed);
                }
            } else if (e.getX() < (float) screen_width / 3) {
                longPressSpeedChange = true;
                if (bipPlayer != null) {
                    if (e.getY() < (float) screen_height / 2) {
                        playSpeed = 0.75f;
                    } else if (e.getY() < (float) screen_height * 2 / 3) {
                        playSpeed = 0.5f;
                    } else {
                        playSpeed = 0.25f;
                    }
                    bipPlayer.setSpeed(playSpeed);
                }
            }
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    };

    private void hideController() {
        playPauseView.setVisibility(GONE);
        videoSeekView.setVisibility(GONE);
        bottomShadowView.setVisibility(GONE);
        topShadowView.setVisibility(GONE);
        titleView.setVisibility(GONE);
        batteryView.setVisibility(GONE);
        timeView.setVisibility(GONE);
        videoPositionView.setVisibility(GONE);
        videoDurationView.setVisibility(GONE);
        if (!isFullScreen) {
            videoBottomProgressView.setVisibility(VISIBLE);
        }
        videoFullScreenView.setVisibility(GONE);
        videoQualityView.setVisibility(GONE);
    }

    private void showController() {
        playPauseView.setVisibility(VISIBLE);
        videoSeekView.setVisibility(VISIBLE);
        videoPositionView.setVisibility(VISIBLE);
        videoDurationView.setVisibility(VISIBLE);
        bottomShadowView.setVisibility(VISIBLE);
        topShadowView.setVisibility(VISIBLE);
        titleView.setVisibility(VISIBLE);
        if (isFullScreen) {
            batteryView.setVisibility(VISIBLE);
            timeView.setVisibility(VISIBLE);
        }
        videoBottomProgressView.setVisibility(GONE);
        if (!isFullScreen && bipPlayer != null && bipPlayer.getVideoWidth() > 0) {
            videoFullScreenView.setVisibility(VISIBLE);
        }
        if (isFullScreen && bipPlayer != null) {
            videoQualityView.setVisibility(VISIBLE);
        }
        playPauseView.removeCallbacks(hideControllerRunnable);
        playPauseView.postDelayed(hideControllerRunnable, 3500);
    }

    private boolean isControllerShowing() {
        return playPauseView.getVisibility() == VISIBLE;
    }

    private final Runnable hideControllerRunnable = new Runnable() {
        @Override
        public void run() {
            hideController();
        }
    };


    private final OnClickListener playItemClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            playPauseView.removeCallbacks(hideControllerRunnable);
            playPauseView.postDelayed(hideControllerRunnable, 3500);
            int vId = v.getId();
            if (vId == R.id.bip_play_view_play_pause) {
                playOrPause();
            } else if (vId == R.id.bip_play_view_fullscreen) {
                setFullScreen(!isFullScreen);
            } else if (vId == R.id.bip_play_view_quality) {
                if (currentVideoBean != null && currentVideoBean.getQualityBeans() != null && currentVideoBean.getQualityBeans().size() > 1) {
                    hideController();
                    showQualityWindow();
                } else {
                    LogUtil.e("BipPlayView", "Data not valid");
                }
            }
        }
    };

    public void resizeWithVideoSize() {
        resizeWithVideoSize(bipPlayer.getVideoWidth(), bipPlayer.getVideoHeight());
    }

    private void resizeWithVideoSize(int videoWidth, int videoHeight) {
        ViewGroup.LayoutParams parentLayoutParams = getLayoutParams();
        ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
        int screenShort = ScreenUtil.getScreenWidth(getContext());
        int screenLong = ScreenUtil.getScreenHeight(getContext());
        if (videoWidth <= 0 || videoHeight <= 0) {
            videoWidth = screenShort;
            videoHeight = screenShort * 9 / 16;
        }
        LogUtil.e("SurfaceTest", "screen " + screenShort + ":" + screenLong + "  video" + videoWidth + ":" + videoHeight);
        if (videoWidth > videoHeight) {
            // normal video
            if (isFullScreen) {
                parentLayoutParams.height = screenShort;
                parentLayoutParams.width = screenLong;
                if (videoHeight / videoWidth > screenShort / screenLong) {
                    layoutParams.width = screenLong;
                    layoutParams.height = (layoutParams.width * videoHeight) / videoWidth;
                } else {
                    layoutParams.height = screenShort;
                    layoutParams.width = (layoutParams.height * videoWidth) / videoHeight;
                }
            } else {
                layoutParams.width = screenShort;
                layoutParams.height = (layoutParams.width * videoHeight) / videoWidth;
                parentLayoutParams.height = layoutParams.height;
                parentLayoutParams.width = screenShort;
            }
        } else {
            // vertical video
            if (isFullScreen) {
                parentLayoutParams.height = screenLong;
                parentLayoutParams.width = screenShort;
                if (videoWidth / videoHeight > screenShort / screenLong) {
                    layoutParams.height = screenLong;
                    layoutParams.width = (layoutParams.height * videoWidth) / videoHeight;
                } else {
                    layoutParams.width = screenShort;
                    layoutParams.height = (layoutParams.width * videoHeight) / videoWidth;
                }
            } else {
                layoutParams.width = screenShort * 3 / 4;
                layoutParams.height = (layoutParams.width * videoHeight) / videoWidth;
                parentLayoutParams.height = layoutParams.height;
                parentLayoutParams.width = screenShort;
            }
        }
        surfaceView.requestLayout();
    }

    public SurfaceView getSurfaceView() {
        return surfaceView;
    }

    public void setSurfaceView(SurfaceView surfaceView) {
        this.surfaceView = surfaceView;
    }

    public BIPPlayer getBipPlayer() {
        return bipPlayer;
    }

    public void setBipPlayer(BIPPlayer bipPlayer) {
        this.bipPlayer = bipPlayer;
        if (bipPlayer == null) {
            return;
        }
        bipPlayer.setOnPreparedListener(mediaPlayer -> {
            duration = mediaPlayer.getDuration();
            resizeWithVideoSize(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
            videoDurationView.setText(StringUtil.formatTime(duration));
            if (duration > 0 && currentVideoBean != null) {
                currentVideoBean.setDuration(duration);
            }
            if (currentVideoBean != null && currentVideoBean.getPlayPosition() > 1000 && currentVideoBean.getPlayPosition() < bipPlayer.getDuration() - 5000) {
                bipPlayer.seekTo(currentVideoBean.getPlayPosition());
            }
            if (isControllerShowing() && !isFullScreen) {
                videoFullScreenView.setVisibility(VISIBLE);
            }
            if (danmakuView.isPrepared()) {
                danmakuView.start(bipPlayer.getCurrentPosition());
            }
            LogUtil.e("BIPPlayer", "prepared");
            post(progressChangeRunnable);
            if (mOnPreparedListener != null) {
                mOnPreparedListener.onPrepared(mediaPlayer);
            }
        });
        bipPlayer.setOnErrorListener((mp, what, extra) -> {
            ToastUtil.showToast(R.string.video_load_error);
            LogUtil.e("BIPPlayer", "error:" + what + "extra:" + extra);
            removeCallbacks(progressChangeRunnable);
            if (mOnErrorListener != null) {
                mOnErrorListener.onError(mp, what, extra);
            }
            return false;
        });
        bipPlayer.setOnCompletionListener(bp -> {
            removeCallbacks(progressChangeRunnable);
            if (bp.getDuration() > 0) {
                videoPositionView.setText(StringUtil.formatTime(bipPlayer.getDuration()));
                if (!userSeeking) {
                    videoSeekView.setProgress(1000);
                }
                videoBottomProgressView.setProgress(1000);
                playPauseView.setSelected(false);
            }
            if (mOnCompletionListener != null) {
                mOnCompletionListener.onCompletion(bp);
            }
        });
        bipPlayer.setOnBufferingUpdateListener((bp, percent) -> {
            videoSeekView.setSecondaryProgress(percent * 10);
            if (mOnBufferingUpdateListener != null) {
                mOnBufferingUpdateListener.onBufferingUpdate(bp, percent);
            }
        });
        bipPlayer.setOnInfoListener((bp, what, extra) -> {
            switch (what) {
                case 0:
                    videoBufferingView.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    videoBufferingView.setVisibility(View.GONE);
                    break;
            }
            if (mOnInfoListener != null) {
                mOnInfoListener.onInfo(bp, what, extra);
            }
        });
        bipPlayer.setOnSeekCompleteListener(bp -> {
            removeCallbacks(progressChangeRunnable);
            post(progressChangeRunnable);
            if (danmakuView.isPrepared()) {
                danmakuView.start(bipPlayer.getCurrentPosition());
            }
            LogUtil.e(TAG, "seek completed" + bipPlayer.getCurrentPosition());
            if (mOnSeekCompleteListener != null) {
                mOnSeekCompleteListener.onSeekComplete(bp);
            }
        });
        if (videoSurfaceHolder != null) {
            bipPlayer.setDisplay(videoSurfaceHolder);
        }
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (fullScreen && detachActivity.isInMultiWindowMode()) {
                ToastUtil.showToast(R.string.multiWindow_not_allow);
                return;
            }
        }
        isFullScreen = fullScreen;
        if (isFullScreen) {
            videoFullScreenView.setVisibility(GONE);
            if (isControllerShowing()) {
                videoQualityView.setVisibility(VISIBLE);
                batteryView.setVisibility(VISIBLE);
                timeView.setVisibility(VISIBLE);
            }
        } else {
            videoQualityView.setVisibility(GONE);
            batteryView.setVisibility(GONE);
            timeView.setVisibility(GONE);
            if (isControllerShowing() && bipPlayer.getVideoWidth() > 0) {
                videoFullScreenView.setVisibility(VISIBLE);
            }
        }
        int videoWidth = bipPlayer.getVideoWidth();
        int videoHeight = bipPlayer.getVideoHeight();
        if (isFullScreen) {
            int targetOrientation = videoWidth > videoHeight ? ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            if (detachActivity != null && detachActivity.getRequestedOrientation() != targetOrientation) {
                detachActivity.setRequestedOrientation(targetOrientation);
            }
        } else {
            if (detachActivity != null && detachActivity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                detachActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
        if (detachActivity != null) {
            detachActivity.refreshSystemUIVisibility();
        }
        resizeWithVideoSize(videoWidth, videoHeight);
    }

    private final Runnable progressChangeRunnable = new Runnable() {
        @Override
        public void run() {
            if (bipPlayer != null && bipPlayer.getDuration() > 0) {
                videoPositionView.setText(StringUtil.formatTime(bipPlayer.getCurrentPosition()));
                if (!userSeeking) {
                    videoSeekView.setProgress((int) (bipPlayer.getCurrentPosition() * 1000 / bipPlayer.getDuration()));
                }
                videoBottomProgressView.setProgress((int) (bipPlayer.getCurrentPosition() * 1000 / bipPlayer.getDuration()));
                playPauseView.setSelected(bipPlayer.isPlaying());
                if (bipPlayer.isPlaying()) {
                    currentVideoBean.setPlayPosition(bipPlayer.getCurrentPosition());
                }
            }
            postDelayed(this, 400);
        }
    };

    public VideoBean getCurrentVideoBean() {
        return currentVideoBean;
    }

    public void setCurrentVideoBean(VideoBean currentVideoBean) {
        this.currentVideoBean = currentVideoBean;
        if (currentVideoBean != null) {
            if (currentVideoBean.getCurrentQualityBean() != null) {
                videoQualityView.setText(currentVideoBean.getCurrentQualityBean().getQuality());
            }
            if (!StringUtil.isEmpty(currentVideoBean.getTitle())) {
                titleView.setText(currentVideoBean.getTitle());
            }
            if (!StringUtil.isEmpty(currentVideoBean.getDanmakuUrl())) {
                initDanmaku();
            }
        }
    }

    public void playOrPause() {
        if (bipPlayer != null) {
            removeCallbacks(progressChangeRunnable);
            if (bipPlayer.isPlaying()) {
                bipPlayer.pause();
                playPauseView.setSelected(false);
                danmakuView.pause();
            } else {
                bipPlayer.start();
                playPauseView.setSelected(true);
                post(progressChangeRunnable);
                if (danmakuView.isPrepared()) {
                    danmakuView.start(bipPlayer.getCurrentPosition());
                }
            }
        }
    }

    ViewGroup qualityView;

    public void showQualityWindow() {
        int videoWidth = bipPlayer.getVideoWidth();
        int videoHeight = bipPlayer.getVideoHeight();
        boolean isLandscape;
        int qualityLayout;
        int qualityItemLayout;
        if (videoHeight > videoWidth) {
            isLandscape = false;
            qualityLayout = R.layout.dlg_quality_select_portrait;
            qualityItemLayout = R.layout.dlg_quality_portrait_item;
        } else {
            qualityLayout = R.layout.dlg_quality_select_landscape;
            qualityItemLayout = R.layout.dlg_quality_landscape_item;
            isLandscape = true;
        }
        hideQualityWindow();
        if (currentVideoBean.getQualityBeans().size() <= 1) {
            return;
        }
        qualityView = (ViewGroup) LayoutInflater.from(detachActivity).inflate(qualityLayout, this, false);
        for (int i = currentVideoBean.getQualityBeans().size() - 1; i >= 0; i--) {
            VideoQualityBean videoQualityBean = currentVideoBean.getQualityBeans().get(i);
            TextView itemView = (TextView) LayoutInflater.from(detachActivity).inflate(qualityItemLayout, qualityView, false);
            itemView.setText(videoQualityBean.getQuality());
            LogUtil.e("BipPlayView", "showQuality" + videoQualityBean.getQuality());
            if (i == currentVideoBean.getCurrentQualityIndex()) {
                itemView.setSelected(true);
            }
            int finalI = i;
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideQualityWindow();
                    currentVideoBean.setCurrentQualityIndex(finalI);
                    SettingConfig.setCurrentQuality(videoQualityBean.getQuality());
                    if (playViewListener != null) {
                        playViewListener.onQualityChangeClick();
                    }
                }
            });
            qualityView.addView(itemView);
        }
        addView(qualityView);
    }

    public void hideQualityWindow() {
        if (qualityView != null) {
            removeView(qualityView);
            qualityView = null;
        }
    }

    PlayViewListener playViewListener;

    public interface PlayViewListener {
        void onQualityChangeClick();
    }

    public PlayViewListener getPlayViewListener() {
        return playViewListener;
    }

    public void setPlayViewListener(PlayViewListener playViewListener) {
        this.playViewListener = playViewListener;
    }

    private void initDanmaku() {
        HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5);
        HashMap<Integer, Boolean> overlappingEnable = new HashMap<>();
        overlappingEnable.put(BaseDanmaku.TYPE_FIX_TOP, true);
        overlappingEnable.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        danmakuContext = DanmakuContext.create();
        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3);
        danmakuContext.setDuplicateMergingEnabled(false);
        danmakuContext.setMaximumLines(maxLinesPair);
        danmakuContext.setScaleTextSize(1);
        danmakuContext.setScrollSpeedFactor(1);
        danmakuContext.preventOverlapping(overlappingEnable);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                Log.e("danmaku", "prepared");
                if (bipPlayer != null && bipPlayer.isPlaying()) {
                    danmakuView.start(bipPlayer.getCurrentPosition());
                }
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {
                Log.e("danmaku", "shown");
            }

            @Override
            public void drawingFinished() {
                Log.e("danmaku", "finished");
            }
        });
        createDanmakuParser(currentVideoBean.getDanmakuUrl());
        danmakuView.enableDanmakuDrawingCache(true);
    }

    private void createDanmakuParser(String uri) {
        if (uri == null) {
            return;
        }
        ILoader iLoader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        okHttpClient.newCall(new Request.Builder().url(uri).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.getMessage() == null ? "消息体为空！instance:" + e : e.getMessage());
                ToastUtil.showToast("弹幕加载失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                try {
                    InflaterInputStream deflaterInputStream = new InflaterInputStream(response.body().byteStream(), new Inflater(true));
                    int a;
                    while ((a = deflaterInputStream.read()) != -1) {
                        outputStream.write(a);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Malformedmessage" + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "message" + e.getMessage());
                }
                try {
                    iLoader.load(new ByteArrayInputStream(outputStream.toByteArray()));
                } catch (IllegalDataException e) {
                    e.printStackTrace();
                    Log.e(TAG, "error Uri:" + uri + "message" + e.getMessage());
                }
                BaseDanmakuParser parser = new BiliDanmukuParser();
                parser.load(iLoader.getDataSource());
                baseDanmakuParser = parser;
                danmakuView.prepare(baseDanmakuParser, danmakuContext);
            }
        });
    }

    public void setBattery(int battery) {
        String batteryStr = battery + "%";
        batteryView.setText(batteryStr);
    }

    public void setTime() {
        String time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE);
        timeView.setText(time);
    }

    public void setOnPreparedListener(BIPPlayer.OnPreparedListener mOnPreparedListener) {
        this.mOnPreparedListener = mOnPreparedListener;
    }

    public void setOnCompletionListener(BIPPlayer.OnCompletionListener mOnCompletionListener) {
        this.mOnCompletionListener = mOnCompletionListener;
    }

    public void setOnBufferingUpdateListener(BIPPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener) {
        this.mOnBufferingUpdateListener = mOnBufferingUpdateListener;
    }

    public void setOnSeekCompleteListener(BIPPlayer.OnSeekCompleteListener mOnSeekCompleteListener) {
        this.mOnSeekCompleteListener = mOnSeekCompleteListener;
    }

    public void setOnErrorListener(BIPPlayer.OnErrorListener mOnErrorListener) {
        this.mOnErrorListener = mOnErrorListener;
    }

    public void setOnInfoListener(BIPPlayer.OnInfoListener mOnInfoListener) {
        this.mOnInfoListener = mOnInfoListener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        danmakuView.release();
    }
}
