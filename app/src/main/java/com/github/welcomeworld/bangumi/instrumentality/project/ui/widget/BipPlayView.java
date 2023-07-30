package com.github.welcomeworld.bangumi.instrumentality.project.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.QualityRvAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BiliDanmukuParser;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.SettingConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.bipplayer.BIPPlayer;
import com.github.welcomeworld.devbase.utils.StringUtil;
import com.github.welcomeworld.devbase.utils.ThreadUtil;
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
    private ImageView playPauseView;
    private SeekBar videoSeekView;
    private TextView videoPositionView;
    private TextView videoDurationView;
    private TextView videoQualityView;
    private ImageView videoFullScreenView;
    private ProgressBar videoBottomProgressView;
    private SurfaceView surfaceView;
    private SurfaceHolder videoSurfaceHolder;
    private TextView bottomShadowView;
    private TextView topShadowView;
    private TextView titleView;
    private IDanmakuView danmakuView;
    private BatteryView batteryView;
    private TextView timeView;
    private TextView fastForwardView;
    private ProgressBar videoBufferingView;
    private ImageView danmakuSwitchView;
    BIPPlayer bipPlayer;
    boolean isFullScreen;
    VideoBean currentVideoBean;
    DanmakuContext danmakuContext;
    private long duration = 0;
    private final int SEEKBAR_MAX = 1000;
    private double lastXpercentage = 0;
    private double lastYpercentage = 0;
    private boolean fastfowarding = false;
    private boolean skipAction = false;
    private long fastforward_record = 0;
    private boolean userSeeking = false;
    private boolean fullScreenChanging = false;
    private float playSpeed = 1.0f;
    private boolean longPressSpeedChange = false;

    private BIPPlayer.OnPreparedListener mOnPreparedListener;
    private BIPPlayer.OnCompletionListener mOnCompletionListener;
    private BIPPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;
    private BIPPlayer.OnSeekCompleteListener mOnSeekCompleteListener;
    private BIPPlayer.OnErrorListener mOnErrorListener;
    private BIPPlayer.OnInfoListener mOnInfoListener;

    private OkHttpClient okHttpClient;

    private RecyclerView qualityRv;
    private final QualityRvAdapter qualityRvAdapter = new QualityRvAdapter();

    private static final String TAG = "BipPlayView";

    private final ViewTreeObserver.OnGlobalFocusChangeListener focusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() {
        @Override
        public void onGlobalFocusChanged(View oldFocus, View newFocus) {
            if (!hasFocus() && !fullScreenChanging) {
                hideController();
                getViewTreeObserver().removeOnGlobalFocusChangeListener(focusChangeListener);
                if (userSeeking) {
                    userSeeking = false;
                    fastForwardView.setVisibility(GONE);
                }
            } else if (isShowingQualityWindow() && qualityRv.indexOfChild(oldFocus) >= 0 && qualityRv.indexOfChild(newFocus) < 0) {
                hideQualityWindow();
            }
        }
    };

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

    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.custom_play_view, this);
        playPauseView = itemView.findViewById(R.id.bip_play_view_play_pause);
        videoSeekView = itemView.findViewById(R.id.bip_play_view_seek_bar);
        videoSeekView.setKeyProgressIncrement(5);
        videoSeekView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    long targetTime = progress * bipPlayer.getDuration() / SEEKBAR_MAX;
                    String seekText = StringUtil.formatTime(targetTime) + "/" + StringUtil.formatTime(duration);
                    fastForwardView.setText(seekText);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                cancelHideController();
                fastForwardView.setVisibility(VISIBLE);
                userSeeking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                scheduleHideController();
                fastForwardView.setVisibility(GONE);
                userSeeking = false;
                if (bipPlayer != null && bipPlayer.getDuration() > 0) {
                    long targetTime = seekBar.getProgress() * bipPlayer.getDuration() / SEEKBAR_MAX;
                    videoPositionView.setText(StringUtil.formatTime(targetTime));
                    bipPlayer.seekTo(targetTime);
                    videoBottomProgressView.setProgress(seekBar.getProgress());
                    removeCallbacks(progressChangeRunnable);
                    postDelayed(progressChangeRunnable, 150);
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
        videoQualityView = itemView.findViewById(R.id.bip_play_view_quality);
        videoQualityView.setOnClickListener(playItemClickListener);
        videoFullScreenView = itemView.findViewById(R.id.bip_play_view_fullscreen);
        videoFullScreenView.setOnClickListener(playItemClickListener);
        surfaceView = itemView.findViewById(R.id.bip_play_view_surface);
        videoSurfaceHolder = surfaceView.getHolder();
        videoSurfaceHolder.addCallback(surfaceHolderCallback);
        gestureDetectorCompat = new GestureDetectorCompat(context, gestureListener);
        setOnTouchListener((v, event) -> {
            LogUtil.e("GestureTest", "Touch");
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (bipPlayer != null) {
                    if (fastfowarding) {
                        fastForwardView.setVisibility(GONE);
                        bipPlayer.seekTo(fastforward_record);
                        scheduleHideController();
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
        danmakuSwitchView = itemView.findViewById(R.id.bip_play_view_danmaku_switch);
        danmakuSwitchView.setOnClickListener(playItemClickListener);
        danmakuSwitchView.setSelected(SettingConfig.isDanmakuOpen());
        initDanmaku();
        qualityRv = itemView.findViewById(R.id.bip_play_view_quality_rv);
        qualityRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, true));
        qualityRv.setAdapter(qualityRvAdapter);
        qualityRvAdapter.itemClickListener = (position, qualityBean) -> {
            currentVideoBean.setCurrentQualityIndex(position);
            SettingConfig.setCurrentQuality(qualityBean.getQuality());
            if (playViewListener != null) {
                playViewListener.onQualityChangeClick();
            }
            hideQualityWindow();
        };
    }

    SurfaceHolder.Callback surfaceHolderCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            LogUtil.d("SurfaceTest", "Created");
            if (bipPlayer != null) {
                bipPlayer.setDisplay(videoSurfaceHolder);
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (bipPlayer != null) {
                bipPlayer.setDisplay(videoSurfaceHolder);
            }
            LogUtil.d("SurfaceTest", "Change:" + width + ":" + height);
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            LogUtil.d("SurfaceTest", "Destroy");
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
            if (e.getX() < getWidth() / 4D || e.getX() > getWidth() * 3 / 4d || e.getY() < getHeight() / 4D || e.getY() > getHeight() * 3 / 4d) {
                skipAction = true;
            }
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            LogUtil.d("GestureTest", "SingleTap");
            if (isShowingQualityWindow()) {
                hideQualityWindow();
                return true;
            }
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTapUpTime < 400) {
                onDoubleTapUp();
            } else {
                ThreadUtil.postDelayed(100, this::onRealSingleTapUp);
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

        public void onDoubleTapUp() {
            playOrPause();
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            String fastfowardText;
            double change;
            double xpercentage = -distanceX / screen_width * 2 + lastXpercentage;
            double ypercentage = distanceY / screen_height * 2 + lastYpercentage;
            if (e2.getX() < getWidth() / 4D || e2.getX() > getWidth() * 3 / 4d || e2.getY() < getHeight() / 4D || e2.getY() > getHeight() * 3 / 4d || bipPlayer == null) {
                return false;
            }
            if (!fastfowarding && !skipAction) {
                if (xpercentage > 0.1 || xpercentage < -0.1) {
                    fastfowarding = true;
                    fastforward_record = bipPlayer.getCurrentPosition();
                    fastfowardText = StringUtil.formatTime(fastforward_record) + "/" + StringUtil.formatTime(duration);
                    fastForwardView.setText(fastfowardText);
                    fastForwardView.setVisibility(VISIBLE);
                    showController();
                    cancelHideController();
                    userSeeking = true;
                } else if (ypercentage > 0.1 || ypercentage < -0.1) {
                    skipAction = true;
                } else {
                    lastYpercentage = ypercentage;
                    lastXpercentage = xpercentage;
                }
                return false;
            }
            if (fastfowarding) {
                change = xpercentage * FASTFORWARD_MAX + fastforward_record > duration ? duration : xpercentage * FASTFORWARD_MAX + fastforward_record;
                fastforward_record = (long) change;
                fastfowardText = StringUtil.formatTime(fastforward_record) + "/" + StringUtil.formatTime(duration);
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
        danmakuSwitchView.setVisibility(GONE);
        hideQualityWindow();
    }

    private void showController() {
        playPauseView.setVisibility(VISIBLE);
        videoSeekView.setVisibility(VISIBLE);
        videoPositionView.setVisibility(VISIBLE);
        videoDurationView.setVisibility(VISIBLE);
        bottomShadowView.setVisibility(VISIBLE);
        topShadowView.setVisibility(VISIBLE);
        titleView.setVisibility(VISIBLE);
        batteryView.setVisibility(VISIBLE);
        timeView.setVisibility(VISIBLE);
        videoBottomProgressView.setVisibility(GONE);
        videoFullScreenView.setVisibility(VISIBLE);
        if (bipPlayer != null) {
            videoQualityView.setVisibility(VISIBLE);
        }
        cancelHideController();
        scheduleHideController();
        danmakuSwitchView.setVisibility(VISIBLE);
    }

    private boolean isControllerShowing() {
        return playPauseView.getVisibility() == VISIBLE;
    }

    private final Runnable hideControllerRunnable = this::hideController;


    private final OnClickListener playItemClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            cancelHideController();
            boolean scheduleHide = true;
            int vId = v.getId();
            if (vId == R.id.bip_play_view_play_pause) {
                playOrPause();
            } else if (vId == R.id.bip_play_view_fullscreen) {
                setFullScreen(!isFullScreen);
            } else if (vId == R.id.bip_play_view_quality) {
                if (currentVideoBean != null && currentVideoBean.getQualityBeans() != null && currentVideoBean.getQualityBeans().size() > 1) {
                    if (isShowingQualityWindow()) {
                        hideQualityWindow();
                    } else {
                        showQualityWindow();
                        scheduleHide = false;
                    }
                } else {
                    LogUtil.e("BipPlayView", "Data not valid");
                }
            } else if (vId == R.id.bip_play_view_danmaku_switch) {
                v.setSelected(!v.isSelected());
                SettingConfig.setDanmakuOpen(v.isSelected());
                if (v.isSelected()) {
                    danmakuView.show();
                } else {
                    danmakuView.hide();
                }
            }
            if (scheduleHide) {
                scheduleHideController();
            }
        }
    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resizeSurfaceSize();
    }

    private void resizeSurfaceSize() {
        resizeSurfaceSize(getVideoWidth(), getVideoHeight(), getWidth(), getHeight(), true);
    }

    public void presetSurfaceSize(int parentWidth, int parentHeight) {
        resizeSurfaceSize(getVideoWidth(), getVideoHeight(), parentWidth, parentHeight, false);
    }

    private void resizeSurfaceSize(int videoWidth, int videoHeight, int parentWidth, int parentHeight, boolean reLayout) {
        ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
        if (videoWidth <= 0 || videoHeight <= 0 || parentHeight <= 0) {
            return;
        }
        int targetWidth;
        int targetHeight;
        if (videoWidth * 1.0 / videoHeight > parentWidth * 1.0 / parentHeight) {
            targetWidth = parentWidth;
            targetHeight = (targetWidth * videoHeight) / videoWidth;
        } else {
            targetHeight = parentHeight;
            targetWidth = (targetHeight * videoWidth) / videoHeight;
        }
        if (sizeDiff(surfaceView.getMeasuredWidth(), targetWidth) || sizeDiff(surfaceView.getMeasuredHeight(), targetHeight)) {
            layoutParams.width = targetWidth;
            layoutParams.height = targetHeight;
            if (reLayout) {
                surfaceView.requestLayout();
            }
        }
    }

    private boolean sizeDiff(int original, int target) {
        return Math.abs(target - original) > 5;
    }

    public void setBipPlayer(BIPPlayer bipPlayer) {
        this.bipPlayer = bipPlayer;
        if (bipPlayer == null) {
            return;
        }
        bipPlayer.setOnPreparedListener(mediaPlayer -> {
            duration = mediaPlayer.getDuration();
            resizeSurfaceSize();
            videoDurationView.setText(StringUtil.formatTime(duration));
            if (duration > 0 && currentVideoBean != null) {
                currentVideoBean.setDuration(duration);
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
        bipPlayer.setDisplay(videoSurfaceHolder);
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        if (isFullScreen == fullScreen) {
            return;
        }
        fullScreenChanging = true;
        isFullScreen = fullScreen;
        videoFullScreenView.setSelected(fullScreen);
        playViewListener.onFullScreenChange(isFullScreen);
        videoFullScreenView.requestFocus();
        fullScreenChanging = false;
    }

    public int videoPreferOrientation() {
        int videoWidth = getVideoWidth();
        int videoHeight = getVideoHeight();
        return videoWidth > videoHeight ? ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
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
                createDanmakuParser(currentVideoBean.getDanmakuUrl());
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

    public void pause() {
        if (bipPlayer != null) {
            removeCallbacks(progressChangeRunnable);
            if (bipPlayer.isPlaying()) {
                bipPlayer.pause();
                playPauseView.setSelected(false);
                danmakuView.pause();
            }
        }
    }

    public void showQualityWindow() {
        if (currentVideoBean.getQualityBeans().size() <= 1) {
            return;
        }
        qualityRvAdapter.replaceAll(currentVideoBean.getQualityBeans());
        qualityRv.setVisibility(View.VISIBLE);
    }

    public void hideQualityWindow() {
        qualityRv.setVisibility(View.GONE);
        scheduleHideController();
    }

    public PlayViewListener playViewListener;

    public interface PlayViewListener {
        void onQualityChangeClick();

        void onFullScreenChange(boolean isFull);
    }

    private void initDanmaku() {
        HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 6);
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
                Log.e(TAG, "danmakuView prepared");
                post(() -> {
                    if (bipPlayer != null && bipPlayer.isPlaying()) {
                        danmakuView.start(bipPlayer.getCurrentPosition());
                    }
                });
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {
                Log.e(TAG, "finished");
            }
        });
        danmakuView.enableDanmakuDrawingCache(true);
        okHttpClient = new OkHttpClient().newBuilder().build();
    }

    private void createDanmakuParser(String uri) {
        if (uri == null) {
            return;
        }
        ILoader iLoader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
        okHttpClient.newCall(new Request.Builder().url(uri).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, e.getMessage() == null ? "消息体为空！instance:" + e : e.getMessage());
                ToastUtil.showToast("弹幕加载失败");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
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
                danmakuView.prepare(parser, danmakuContext);
            }
        });
    }

    public void setBattery(int battery) {
        if (battery < 50) {
            batteryView.setBatteryColor(Color.RED);
        } else {
            batteryView.setBatteryColor(Color.GREEN);
        }
        batteryView.setBattery(battery);
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
        okHttpClient.dispatcher().cancelAll();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (!userSeeking && videoSeekView.isFocused() && (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT)) {
                fastForwardView.setVisibility(VISIBLE);
                userSeeking = true;
            }
        } else if (event.getAction() == KeyEvent.ACTION_UP) {
            if (userSeeking && videoSeekView.isFocused() && (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT)) {
                fastForwardView.setVisibility(GONE);
                userSeeking = false;
                if (bipPlayer != null && bipPlayer.getDuration() > 0) {
                    long targetTime = videoSeekView.getProgress() * bipPlayer.getDuration() / SEEKBAR_MAX;
                    videoPositionView.setText(StringUtil.formatTime(targetTime));
                    bipPlayer.seekTo(targetTime);
                    videoBottomProgressView.setProgress(videoSeekView.getProgress());
                    removeCallbacks(progressChangeRunnable);
                    postDelayed(progressChangeRunnable, 150);
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            showController();
            cancelHideController();
            getViewTreeObserver().removeOnGlobalFocusChangeListener(focusChangeListener);
            getViewTreeObserver().addOnGlobalFocusChangeListener(focusChangeListener);
            playPauseView.requestFocus();
        }
    }

    public int getVideoWidth() {
        int videoWidth = bipPlayer == null ? 0 : bipPlayer.getVideoWidth();
        int qualityWidth = 0;
        if (getCurrentVideoBean() != null && getCurrentVideoBean().getCurrentQualityBean() != null) {
            qualityWidth = getCurrentVideoBean().getCurrentQualityBean().videoWidth;
        }
        return videoWidth == 0 ? qualityWidth : videoWidth;
    }

    public int getVideoHeight() {
        int videoHeight = bipPlayer == null ? 0 : bipPlayer.getVideoHeight();
        int qualityHeight = 0;
        if (getCurrentVideoBean() != null && getCurrentVideoBean().getCurrentQualityBean() != null) {
            qualityHeight = getCurrentVideoBean().getCurrentQualityBean().videoHeight;
        }
        return videoHeight == 0 ? qualityHeight : videoHeight;
    }

    public boolean handleExternalKeyEvent(KeyEvent event) {
        if (isFullScreen() && !hasFocus() && needHandleKey(event.getKeyCode())) {
            requestFocus();
            return true;
        }
        return false;
    }

    @Override
    public View focusSearch(View focused, int direction) {
        View next = super.focusSearch(focused, direction);
        if (isFullScreen()) {
            if (next == null || !recursiveLoopChildren(this, next)) {
                return focused;
            }
        }
        return next;
    }

    public boolean recursiveLoopChildren(ViewGroup parent, View target) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                if (recursiveLoopChildren((ViewGroup) child, target)) {
                    return true;
                }
            } else {
                if (child == target) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean needHandleKey(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_UP:
            case KeyEvent.KEYCODE_DPAD_DOWN:
            case KeyEvent.KEYCODE_DPAD_CENTER:
                return true;
            default:
                return false;
        }
    }

    private void cancelHideController() {
        removeCallbacks(hideControllerRunnable);
    }

    private void scheduleHideController() {
        postDelayed(hideControllerRunnable, 4000);
    }

    private boolean isShowingQualityWindow() {
        return qualityRv.getVisibility() == View.VISIBLE;
    }
}
