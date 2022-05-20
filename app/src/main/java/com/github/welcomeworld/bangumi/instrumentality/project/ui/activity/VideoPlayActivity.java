package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.VideoSourceItemAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivityVideoPlayBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.DownloadInfoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.DownloadInfoConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.DownloadManager;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.HistoryConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.widget.BipPlayView;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.devbase.utils.ScreenUtil;
import com.github.welcomeworld.devbase.utils.ThreadUtil;
import com.github.welcomeworld.devbase.utils.ToastUtil;
import com.github.welcomeworld.bipplayer.BIPPlayer;
import com.github.welcomeworld.bipplayer.DefaultBIPPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class VideoPlayActivity extends BaseActivity<ActivityVideoPlayBinding> {

    VideoSourceItemAdapter sourceAdapter = new VideoSourceItemAdapter();


    public static final String EXTRA_VIDEO_LIST_BEAN = "extra_video_list_bean";
    public static final String EXTRA_VIDEO_SELECT_INDEX = "extra_video_select_index";
    public static final String EXTRA_DOWNLOAD_TARGET_INDEX = "extra_download_target_index";


    List<VideoListBean> videoListBeans;
    private int selectSourceIndex = 0;
    VideoListBean currentVideoListBean;
    VideoBean currentVideoBean;
    BIPPlayer bipPlayer = new DefaultBIPPlayer();
    HistoryBean historyBean;
    Uri videoUri = null;
    int downloadTarget = -1;

    @Override
    protected void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        saveHistory();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerSomething();
        getViewBinding().videoPlayTopSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(this);
        videoListBeans = getIntent().getParcelableArrayListExtra(EXTRA_VIDEO_LIST_BEAN);
        selectSourceIndex = getIntent().getIntExtra(EXTRA_VIDEO_SELECT_INDEX, 0);
        downloadTarget = getIntent().getIntExtra(EXTRA_DOWNLOAD_TARGET_INDEX, -1);
        videoUri = getIntent().getData();
        if (videoUri != null && (videoListBeans == null || videoListBeans.size() == 0)) {
            BaseParser parser = ParserManager.getInstance().matchSource(videoUri);
            if (parser != null) {
                videoListBeans = parser.createVideoListBeans(videoUri);
            }
        }
        if (videoListBeans == null || videoListBeans.size() == 0) {
            finish();
            return;
        }
        initPlayer();
        ThreadUtil.defer().when(() -> {
            initHistory();
            videoListBeans = ParserManager.getInstance().updateVideoList(videoListBeans, selectSourceIndex);
            LogUtil.e("VideoPlayActivity", videoListBeans.toString());
        }).done((result) -> initCreate()).fail(throwable -> {
            LogUtil.e("VideoPlayActivity", "init with fail");
            throwable.printStackTrace();
            initCreate();
        });
    }

    private void initPlayer() {
        Map<Integer, Map<String, String>> options = ParserManager.getInstance().getPlayerOptions(videoListBeans.get(selectSourceIndex));
        if (options != null && options.size() > 0) {
            for (Integer category : options.keySet()) {
                for (String key : options.get(category).keySet()) {
                    bipPlayer.setOption(category, key, options.get(category).get(key));
                }
            }
        }
    }

    private void initHistory() {
        historyBean = HistoryConfig.findHistory(0, HistoryBean.getVid(videoListBeans));
        if (historyBean == null) {
            historyBean = new HistoryBean();
            historyBean.setActive(true);
            historyBean.setCover(currentVideoListBean == null ? null : currentVideoListBean.getCover());
            historyBean.setSelectSourceIndex(selectSourceIndex);
            historyBean.setTitle(currentVideoListBean == null ? null : currentVideoListBean.getTitle());
            historyBean.setCoverPortrait(currentVideoListBean != null && currentVideoListBean.isCoverPortrait());
            historyBean.setVideoData(videoListBeans);
            historyBean.setViewTime(System.currentTimeMillis());
        } else {
            if (downloadTarget == -1) {
                selectSourceIndex = historyBean.getSelectSourceIndex();
            }
            videoListBeans = historyBean.getVideoData();
        }
        ParserManager.getInstance().clearCache(videoListBeans.get(selectSourceIndex));
    }

    private void registerSomething() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(playReceiver, intentFilter);
    }

    private void unregisterSomething() {
        unregisterReceiver(playReceiver);
    }

    private void saveHistory() {
        historyBean.setSelectSourceIndex(selectSourceIndex);
        historyBean.setVideoData(videoListBeans);
        historyBean.setViewTime(System.currentTimeMillis());
        HistoryConfig.updateOrSaveHistory(historyBean);
    }

    private void changeFavStatus(boolean fav) {
        historyBean.setFav(fav);
        if (fav) {
            historyBean.setFavTime(System.currentTimeMillis());
        }
        HistoryConfig.updateOrSaveHistory(historyBean);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveHistory();
    }

    private void initCreate() {
        if (isFinishing()) {
            return;
        }
        getViewBinding().videoPlayView.setBipPlayer(bipPlayer);
        getViewBinding().videoPlayView.setPlayViewListener(playViewListener);
        currentVideoListBean = videoListBeans.get(selectSourceIndex);
        if (downloadTarget != -1) {
            currentVideoListBean.setSelectIndex(downloadTarget);
        }
        saveHistory();
        currentVideoBean = currentVideoListBean.getCurrentVideoBean();
        sourceAdapter.setSelectSourceIndex(selectSourceIndex);
        sourceAdapter.setFav(historyBean.isFav());
        sourceAdapter.setData(videoListBeans);
        sourceAdapter.setActionClickListener(new VideoSourceItemAdapter.ActionClickListener() {
            @Override
            public void onFavClick() {
                changeFavStatus(!historyBean.isFav());
            }

            @Override
            public void onDownloadClick() {
                ThreadUtil.defer().when(() -> {
                    String mediaId = currentVideoListBean.getSourceName() + ":" + currentVideoBean.getVideoKey();
                    DownloadInfoBean downloadInfoBean = DownloadInfoConfig.findDownloadInfo(mediaId);
                    if (downloadInfoBean == null || downloadInfoBean.getDownloadState() == DownloadInfoBean.ERROR) {
                        DownloadManager.download(videoListBeans, selectSourceIndex);
                    }
                }).fail(throwable -> {
                    LogUtil.e("VideoPlayActivity", "download error");
                    throwable.printStackTrace();
                });
            }

            @Override
            public void onBrowserClick() {
                IntentUtil.intentToBrowser(VideoPlayActivity.this, currentVideoBean.getUrl());
            }

            @Override
            public void onRefreshClick() {
                ParserManager.getInstance().clearCache(videoListBeans.get(selectSourceIndex));
                parseVideoBeanDetail();
            }
        });
        sourceAdapter.setItemClickListener((rv, sourcePosition, position) -> {
            selectSourceIndex = sourcePosition;
            int oldSourceIndex = sourceAdapter.getSelectSourceIndex();
            sourceAdapter.setSelectSourceIndex(sourcePosition);
            if (oldSourceIndex == sourcePosition) {
                int oldIndex = currentVideoListBean.getSelectIndex();
                currentVideoListBean.setSelectIndex(position);
                currentVideoBean = currentVideoListBean.getCurrentVideoBean();
                getViewBinding().videoPlayView.setCurrentVideoBean(currentVideoBean);
                if (rv.getAdapter() != null) {
                    rv.getAdapter().notifyItemChanged(oldIndex);
                }
            } else {
                currentVideoListBean = videoListBeans.get(selectSourceIndex);
                currentVideoListBean.setSelectIndex(position);
                currentVideoBean = currentVideoListBean.getCurrentVideoBean();
                getViewBinding().videoPlayView.setCurrentVideoBean(currentVideoBean);
                sourceAdapter.notifyItemChanged(oldSourceIndex);
            }
            parseVideoBeanDetail();
        });
        getViewBinding().videoPlaySourceRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        getViewBinding().videoPlaySourceRv.setAdapter(sourceAdapter);
        parseVideoBeanDetail();
    }

    private void parseVideoBeanDetail() {
        parseVideoBeanDetail(false);
    }

    private void parseVideoBeanDetail(boolean quality) {
        if (!quality) {
            bipPlayer.stop();
        }
        ThreadUtil.defer().when(() -> {
            String mediaId = currentVideoListBean.getSourceName() + ":" + currentVideoBean.getVideoKey();
            DownloadInfoBean downloadInfoBean = DownloadInfoConfig.findDownloadInfo(mediaId);
            if (downloadInfoBean != null && downloadInfoBean.getDownloadState() == DownloadInfoBean.COMPLETE) {
                if (currentVideoBean.getCurrentQualityBean() == null) {
                    VideoQualityBean videoQualityBean = new VideoQualityBean();
                    videoQualityBean.setQuality(getString(R.string.downloaded));
                    currentVideoBean.getQualityBeans().add(videoQualityBean);
                }
                currentVideoBean.getCurrentQualityBean().setRealVideoUrl(downloadInfoBean.getLocalPath());
            } else if (currentVideoBean.getCurrentQualityBean() == null || currentVideoBean.getCurrentQualityBean().getRealVideoUrl() == null) {
                LogUtil.e("BIPPlayer", "start parse");
                videoListBeans = ParserManager.getInstance().updateVideoList(videoListBeans, selectSourceIndex);
                currentVideoListBean = videoListBeans.get(selectSourceIndex);
                currentVideoBean = currentVideoListBean.getCurrentVideoBean();
            }
        }).fail((throwable) -> {
            throwable.printStackTrace();
            ToastUtil.showToast(R.string.video_url_parse_error);
            LogUtil.e("BIPPlayer", "parse faile" + throwable.getMessage());
        }).done((result) -> {
            if (isFinishing()) {
                return;
            }
            LogUtil.e("BIPPlayer", "finish parse");
            getViewBinding().videoPlayView.setCurrentVideoBean(currentVideoBean);
            if (currentVideoBean == null || currentVideoBean.getCurrentQualityBean() == null || currentVideoBean.getCurrentQualityBean().getRealVideoUrl() == null) {
                ToastUtil.showToast(R.string.video_url_parse_error);
                return;
            }
            if (quality) {
                bipPlayer.prepareQualityAsync(currentVideoBean.getCurrentQualityBean().getRealVideoUrl());
            } else {
                bipPlayer.setDataSource(currentVideoBean.getCurrentQualityBean().getRealVideoUrl());
                bipPlayer.prepareAsync();
            }
            saveHistory();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bipPlayer != null) {
            bipPlayer.release();
            bipPlayer = null;
        }
        unregisterSomething();
    }

    @Override
    public void onBackPressed() {
        if (getViewBinding().videoPlayView.isFullScreen()) {
            getViewBinding().videoPlayView.setFullScreen(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void refreshSystemUIVisibility() {
        if (getViewBinding().videoPlayView.isFullScreen()) {
            getViewBinding().videoPlayTopSpace.setVisibility(View.GONE);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getViewBinding().videoPlayTopSpace.setVisibility(View.VISIBLE);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    private final BipPlayView.PlayViewListener playViewListener = () -> parseVideoBeanDetail(true);

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        if (isInMultiWindowMode) {
            if (getViewBinding().videoPlayView.isFullScreen()) {
                getViewBinding().videoPlayView.setFullScreen(false);
            }
        }
    }

    private final BroadcastReceiver playReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Intent.ACTION_BATTERY_CHANGED:
                    int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                    int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
                    getViewBinding().videoPlayView.setBattery(level * 100 / scale);
                    break;
                case Intent.ACTION_TIME_TICK:
                    getViewBinding().videoPlayView.setTime();
                    break;
            }
        }
    };
}
