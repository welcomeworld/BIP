package com.github.welcomeworld.bangumi.instrumentality.project.ui.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
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
import com.github.welcomeworld.bangumi.instrumentality.project.player.BIPPlayer;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.BaseActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.StringUtil;

public class BipPlayView extends ConstraintLayout {
    ConstraintLayout controllerParent;
    private ImageView playPauseView;
    private SeekBar videoSeekView;
    private TextView videoPositionView;
    private TextView videoDurationView;
    private ImageView videoFullScreenView;
    private ProgressBar videoBottomProgressView;
    private SurfaceView surfaceView;
    private SurfaceHolder videoSurfaceHolder;
    private BaseActivity detachActivity;
    BIPPlayer bipPlayer;
    boolean isFullScreen;

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

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                playPauseView.removeCallbacks(hideControllerRunnable);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                playPauseView.postDelayed(hideControllerRunnable,3000);
                if (bipPlayer != null && bipPlayer.getDuration() > 0) {
                    long targetTime = seekBar.getProgress() * bipPlayer.getDuration() / 1000;
                    videoPositionView.setText(StringUtil.formatTime(targetTime));
                    bipPlayer.seekTo(targetTime);
                    videoBottomProgressView.setProgress(seekBar.getProgress());
                }
            }
        });
        videoPositionView = itemView.findViewById(R.id.bip_play_view_current_position);
        videoDurationView = itemView.findViewById(R.id.bip_play_view_duration);
        videoBottomProgressView = itemView.findViewById(R.id.bip_play_view_bottom_progress);
        controllerParent = itemView.findViewById(R.id.bip_play_view_controller_parent);
        videoFullScreenView = itemView.findViewById(R.id.bip_play_view_fullscreen);
        videoFullScreenView.setOnClickListener(playItemClickListener);
        surfaceView = itemView.findViewById(R.id.bip_play_view_surface);
        surfaceView.getHolder().addCallback(surfaceHolderCallback);
        gestureDetectorCompat = new GestureDetectorCompat(context, gestureListener);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.e("GestureTest", "Touch");
                return gestureDetectorCompat.onTouchEvent(event);
//                return false;
            }
        });
        playPauseView.setOnClickListener(playItemClickListener);
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
//            videoSurfaceHolder = holder;
//            if (bipPlayer != null) {
//                bipPlayer.setSurface(holder.getSurface());
//            }
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
        @Override
        public boolean onDown(MotionEvent e) {
            LogUtil.e("GestureTest", "Down");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            LogUtil.e("GestureTest", "SingleTap");
            if (isControllerShowing()) {
                hideController();
            } else {
                showController();
                playPauseView.setSelected(bipPlayer != null && bipPlayer.isPlaying());
            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    };

    private void hideController() {
        playPauseView.setVisibility(GONE);
        videoSeekView.setVisibility(GONE);
        videoPositionView.setVisibility(GONE);
        videoDurationView.setVisibility(GONE);
        if (!isFullScreen) {
            videoBottomProgressView.setVisibility(VISIBLE);
        }
        videoFullScreenView.setVisibility(GONE);
    }

    private void showController() {
        playPauseView.setVisibility(VISIBLE);
        videoSeekView.setVisibility(VISIBLE);
        videoPositionView.setVisibility(VISIBLE);
        videoDurationView.setVisibility(VISIBLE);
        videoBottomProgressView.setVisibility(GONE);
        if (!isFullScreen && bipPlayer != null && bipPlayer.getVideoWidth() > 0) {
            videoFullScreenView.setVisibility(VISIBLE);
        }
        playPauseView.removeCallbacks(hideControllerRunnable);
        playPauseView.postDelayed(hideControllerRunnable,3000);
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
            playPauseView.postDelayed(hideControllerRunnable,3000);
            if (v.getId() == R.id.bip_play_view_play_pause) {
                if (bipPlayer != null) {
                    if (bipPlayer.isPlaying()) {
                        bipPlayer.pause();
                        playPauseView.setSelected(false);
                    } else {
                        bipPlayer.start();
                        playPauseView.setSelected(true);
                    }
                }
            } else if (v.getId() == R.id.bip_play_view_fullscreen) {
                setFullScreen(!isFullScreen);
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
        bipPlayer.setOnPreparedListener(mediaPlayer -> {
            resizeWithVideoSize(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
            videoDurationView.setText(StringUtil.formatTime(mediaPlayer.getDuration()));
            if (isControllerShowing() && !isFullScreen) {
                videoFullScreenView.setVisibility(VISIBLE);
            }
            LogUtil.e("BIPPlayer", "prepared");
            post(progressChangeRunnable);
        });
        bipPlayer.setOnErrorListener((mp, what, extra) -> {
            LogUtil.e("BIPPlayer", "error:" + what + "extra:" + extra);
            removeCallbacks(progressChangeRunnable);
            return false;
        });
        bipPlayer.setOnCompletionListener(new BIPPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(BIPPlayer bp) {
                removeCallbacks(progressChangeRunnable);
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
        isFullScreen = fullScreen;
        if (isFullScreen) {
            videoFullScreenView.setVisibility(GONE);
        } else if (isControllerShowing() && bipPlayer.getVideoWidth() > 0) {
            videoFullScreenView.setVisibility(VISIBLE);
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

    private Runnable progressChangeRunnable = new Runnable() {
        @Override
        public void run() {
            if (bipPlayer != null && bipPlayer.getDuration() > 0) {
                videoPositionView.setText(StringUtil.formatTime(bipPlayer.getCurrentPosition()));
                videoSeekView.setProgress((int) (bipPlayer.getCurrentPosition()*1000/bipPlayer.getDuration()));
                videoBottomProgressView.setProgress((int) (bipPlayer.getCurrentPosition()*1000/bipPlayer.getDuration()));
            }
            postDelayed(this,400);
        }
    };
}
