package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.player.BIPPlayer;
import com.github.welcomeworld.bangumi.instrumentality.project.player.IjkBIPPlayerImpl;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import java.util.ArrayList;

import butterknife.BindView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoPlayActivity extends BaseActivity {
    @BindView(R.id.video_play_title)
    TextView titleView;
    @BindView(R.id.video_play_surface_view)
    SurfaceView videoSurfaceView;


    public static final String EXTRA_VIDEO_LIST_BEAN = "extra_video_list_bean";

    ArrayList<VideoListBean> videoListBeans;
    private int selectSourceIndex = 0;
    VideoListBean currentVideoListBean;
    VideoBean currentVideoBean;
    BIPPlayer bipPlayer = new IjkBIPPlayerImpl();
    SurfaceHolder videoSurfaceHolder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        videoListBeans = getIntent().getParcelableArrayListExtra(EXTRA_VIDEO_LIST_BEAN);
        if(videoListBeans==null||videoListBeans.size() == 0){
            finish();
            return;
        }
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        currentVideoListBean = videoListBeans.get(selectSourceIndex);
        titleView.setText(currentVideoListBean.getTitle());
        currentVideoBean = currentVideoListBean.getCurrentVideoBean();
        videoSurfaceView.getHolder().addCallback(surfaceHolderCallback);

        if(currentVideoBean.getQualityBeans()==null||currentVideoBean.getQualityBeans().size() == 0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ParserManager.getInstance().parseVideoListRealInfo(currentVideoListBean);
                    currentVideoBean = currentVideoListBean.getCurrentVideoBean();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bipPlayer.setVideoQualityBean(currentVideoBean.getCurrentQualityBean());
                            bipPlayer.setOnPreparedListener(new BIPPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(BIPPlayer mediaPlayer) {
                                    LogUtil.e("BIPPlayer","prepared");
//                                    mediaPlayer.start();
                                }
                            });
                            bipPlayer.setOnErrorListener(new BIPPlayer.OnErrorListener() {
                                @Override
                                public boolean onError(BIPPlayer mp, int what, int extra) {
                                    LogUtil.e("BIPPlayer","error:"+what+"extra:"+extra);

                                    return false;
                                }
                            });
                            bipPlayer.prepareAsync();
                        }
                    });
                }
            }).start();
        }else {
            bipPlayer.setVideoQualityBean(currentVideoBean.getCurrentQualityBean());
            bipPlayer.setOnPreparedListener(mediaPlayer -> {
                LogUtil.e("BIPPlayer","prepared");
//                mediaPlayer.start();
            });
            bipPlayer.setOnErrorListener(new BIPPlayer.OnErrorListener() {
                @Override
                public boolean onError(BIPPlayer mp, int what, int extra) {
                    LogUtil.e("BIPPlayer","error:"+what+"extra:"+extra);
                    return false;
                }
            });
            bipPlayer.prepareAsync();
            //todo
        }

    }

    SurfaceHolder.Callback surfaceHolderCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            videoSurfaceHolder = holder;
            bipPlayer.setSurface(holder.getSurface());
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            videoSurfaceHolder = holder;
            bipPlayer.setSurface(holder.getSurface());
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
//            bipPlayer.pause();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bipPlayer!=null){
            bipPlayer.release();
            bipPlayer = null;
        }
    }
}
