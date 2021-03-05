package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.VideoPlayItemAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.player.BIPPlayer;
import com.github.welcomeworld.bangumi.instrumentality.project.player.IjkBIPPlayerImpl;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.widget.BipPlayView;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.StringUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ThreadUtil;

import java.util.ArrayList;

import butterknife.BindView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoPlayActivity extends BaseActivity {
    @BindView(R.id.video_play_title)
    TextView titleView;
    @BindView(R.id.video_play_des)
    TextView desView;
    @BindView(R.id.video_play_item_rv)
    RecyclerView playItemRv;
    @BindView(R.id.video_play_view)
    BipPlayView playView;
    @BindView(R.id.video_play_top_space)
    Space topSpace;

    VideoPlayItemAdapter itemAdapter = new VideoPlayItemAdapter();


    public static final String EXTRA_VIDEO_LIST_BEAN = "extra_video_list_bean";

    ArrayList<VideoListBean> videoListBeans;
    private int selectSourceIndex = 0;
    VideoListBean currentVideoListBean;
    VideoBean currentVideoBean;
    BIPPlayer bipPlayer = new IjkBIPPlayerImpl();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(this);
        videoListBeans = getIntent().getParcelableArrayListExtra(EXTRA_VIDEO_LIST_BEAN);
        if (videoListBeans == null || videoListBeans.size() == 0) {
            finish();
            return;
        }
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        playView.setBipPlayer(bipPlayer);
        currentVideoListBean = videoListBeans.get(selectSourceIndex);
        titleView.setText(currentVideoListBean.getTitle());
        if (!StringUtil.isEmpty(currentVideoListBean.getVideoListDes())) {
            desView.setText(currentVideoListBean.getVideoListDes());
        }
        currentVideoBean = currentVideoListBean.getCurrentVideoBean();
        itemAdapter.setData(currentVideoListBean);
        itemAdapter.setItemClickListener(new VideoPlayItemAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int oldIndex = currentVideoListBean.getSelectIndex();
                currentVideoListBean.setSelectIndex(position);
                currentVideoBean = currentVideoListBean.getCurrentVideoBean();
                itemAdapter.notifyItemChanged(oldIndex);
                parseVideoBeanDetail();
            }
        });
        playItemRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        playItemRv.setAdapter(itemAdapter);
        parseVideoBeanDetail();

    }

    private void parseVideoBeanDetail() {
        bipPlayer.updatePlayer();
        if (currentVideoBean.getQualityBeans() == null || currentVideoBean.getQualityBeans().size() == 0) {
            LogUtil.e("BIPPlayer", "start parse");
            ThreadUtil.defer().when(() -> {
                ParserManager.getInstance().parseVideoListRealInfo(currentVideoListBean);
                currentVideoBean = currentVideoListBean.getCurrentVideoBean();
            }).fail((throwable) -> {
                throwable.printStackTrace();
                LogUtil.e("BIPPlayer", "parse faile" + throwable.getMessage());
            }).done((result) -> {
                LogUtil.e("BIPPlayer", "finish parse");
                itemAdapter.notifyDataSetChanged();
                if (!StringUtil.isEmpty(currentVideoListBean.getVideoListDes())) {
                    desView.setText(currentVideoListBean.getVideoListDes());
                }
                if (currentVideoBean == null || currentVideoBean.getCurrentQualityBean() == null) {
                    return;
                }
                bipPlayer.setVideoQualityBean(currentVideoBean.getCurrentQualityBean());
                bipPlayer.prepareAsync();
            });
        } else {
            LogUtil.e("BIPPlayer", "prepared directly");
            bipPlayer.setVideoQualityBean(currentVideoBean.getCurrentQualityBean());
            bipPlayer.prepareAsync();
            //todo
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bipPlayer != null) {
            bipPlayer.release();
            bipPlayer = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (playView.isFullScreen()) {
            playView.setFullScreen(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void refreshSystemUIVisibility() {
        if (playView.isFullScreen()) {
            topSpace.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {//4.4到5.0
                WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
                localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            }
        } else {
            topSpace.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                getWindow().setStatusBarColor(Color.BLACK);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                getWindow().setStatusBarColor(Color.BLACK);
            }
        }
    }
}
