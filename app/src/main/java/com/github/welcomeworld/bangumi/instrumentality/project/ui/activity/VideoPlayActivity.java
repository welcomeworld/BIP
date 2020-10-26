package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.player.BIPPlayer;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.StringUtil;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

public class VideoPlayActivity extends BaseActivity {
    @BindView(R.id.video_play_title)
    TextView titleView;


    public static final String EXTRA_VIDEO_LIST_BEAN = "extra_video_list_bean";

    ArrayList<VideoListBean> videoListBeans;
    private int selectSourceIndex = 0;
    VideoListBean currentVideoListBean;
    VideoBean currentVideoBean;
    BIPPlayer bipPlayer = new BIPPlayer();

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
        currentVideoListBean = videoListBeans.get(selectSourceIndex);
        titleView.setText(currentVideoListBean.getTitle());
        currentVideoBean = currentVideoListBean.getCurrentVideoBean();
        if(currentVideoBean.getQualityBeans()==null||currentVideoBean.getQualityBeans().size() == 0){
            ParserManager.getInstance().parseVideoListRealInfo(currentVideoListBean);
        }else {
            //todo
        }
        bipPlayer.setVideoQualityBean(currentVideoBean.getCurrentQualityBean());
        bipPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        bipPlayer.prepareAsync();

    }
}
