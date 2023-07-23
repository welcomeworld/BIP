package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.CommentRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.RelatedRVAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.VideoSourceItemAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivityVideoPlayBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.DataActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.SettingsFragment;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.widget.BipPlayView;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.viewmodel.VideoPlayViewModel;
import com.github.welcomeworld.devbase.utils.ScreenUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayActivity extends BaseActivity<ActivityVideoPlayBinding> {
    public static final String EXTRA_VIDEO_LIST_BEAN = "extra_video_list_bean";
    public static final String EXTRA_SOURCE_SELECT_INDEX = "extra_source_select_index";
    public static final String EXTRA_VIDEO_TARGET_INDEX = "extra_download_target_index";

    VideoPlayViewModel viewModel = null;
    VideoSourceItemAdapter sourceAdapter = new VideoSourceItemAdapter();
    CommentRecyclerViewAdapter commentAdapter = new CommentRecyclerViewAdapter();

    RelatedRVAdapter relatedRVAdapter = new RelatedRVAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(VideoPlayViewModel.class);
        registerSomething();
        getViewBinding().videoPlayTopSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(this);
        viewModel.setSelectSourceIndex(getIntent().getIntExtra(EXTRA_SOURCE_SELECT_INDEX, 0));
        viewModel.setSelectVideoIndex(getIntent().getIntExtra(EXTRA_VIDEO_TARGET_INDEX, 0));
        List<VideoListBean> videoListBeans = parseVideoListFromIntent();
        if (videoListBeans == null || videoListBeans.size() == 0) {
            finish();
            return;
        }
        viewModel.changeVideoListBeans(videoListBeans);
        commentAdapter.setLoadSubCommentCallback(parentComment -> viewModel.loadSubComment(parentComment));
        getViewBinding().bottomCommentRv.setEndSize(76);
        getViewBinding().bottomCommentRv.setAdapter(commentAdapter);
        getViewBinding().bottomCommentRv.setLayoutManager(new LinearLayoutManager(this));
        getViewBinding().videoCommentButton.setOnClickListener(v -> showBottomComment());
        getViewBinding().videoPlayView.setBipPlayer(viewModel.getPlayer());
        getViewBinding().videoPlayView.playViewListener = new BipPlayView.PlayViewListener() {
            private int normalOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;

            @Override
            public void onQualityChangeClick() {
                viewModel.parseVideoBeanDetail(true);
            }

            @Override
            public void onFullScreenChange(boolean isFull) {
                int parentWidth;
                int parentHeight;
                if (isFull) {
                    ViewGroup contentView = findViewById(android.R.id.content);
                    parentWidth = contentView.getWidth();
                    parentHeight = contentView.getHeight();
                    getViewBinding().videoPlayView.presetSurfaceSize(parentWidth, parentHeight);
                    getViewBinding().playViewContainer.removeAllViews();
                    contentView.addView(getViewBinding().videoPlayView);
                    int targetOrientation = getViewBinding().videoPlayView.videoPreferOrientation();
                    normalOrientation = getRequestedOrientation();
                    if (normalOrientation != targetOrientation && !isTV()) {
                        setRequestedOrientation(targetOrientation);
                    }
                } else {
                    parentWidth = getViewBinding().playViewContainer.getWidth();
                    parentHeight = getViewBinding().playViewContainer.getHeight();
                    getViewBinding().videoPlayView.presetSurfaceSize(parentWidth, parentHeight);
                    ViewGroup contentView = findViewById(android.R.id.content);
                    contentView.removeView(getViewBinding().videoPlayView);
                    getViewBinding().playViewContainer.addView(getViewBinding().videoPlayView);
                    if (getRequestedOrientation() != normalOrientation) {
                        setRequestedOrientation(normalOrientation);
                    }
                }
                refreshSystemUIVisibility();
            }
        };
        sourceAdapter.setActionClickListener(new VideoSourceItemAdapter.ActionClickListener() {
            @Override
            public void onFavClick() {
                viewModel.changeFavStatus();
            }

            @Override
            public void onDownloadClick() {
                viewModel.handleDownloadClick();
            }

            @Override
            public void onBrowserClick() {
                IntentUtil.intentToBrowser(VideoPlayActivity.this, viewModel.getBrowserUrl());
            }

            @Override
            public void onRefreshClick() {
                viewModel.refreshVideo();
            }
        });
        sourceAdapter.setItemClickListener((rv, sourcePosition, position) -> viewModel.changeSelectItem(sourcePosition, position));
        relatedRVAdapter.itemClickListener = videoListBean -> {
            getViewBinding().videoPlayView.pause();
            Bundle bundle = new Bundle();
            ArrayList<VideoListBean> videoListBeans1 = new ArrayList<>();
            videoListBeans1.add(videoListBean);
            bundle.putParcelableArrayList(VideoPlayActivity.EXTRA_VIDEO_LIST_BEAN, videoListBeans1);
            IntentUtil.intentToVideoPlay(VideoPlayActivity.this, bundle);
        };
        getViewBinding().videoPlaySourceRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        getViewBinding().videoPlaySourceRv.setAdapter(new ConcatAdapter(sourceAdapter, relatedRVAdapter));
        viewModel.getVideoListBeanLive().observe(this, videoListBeanListLiveWrapper -> sourceAdapter.setData(videoListBeanListLiveWrapper.getData()));
        viewModel.getCurrentVideoBeanLive().observe(this, videoBean -> getViewBinding().videoPlayView.setCurrentVideoBean(videoBean));
        viewModel.getCommentDataLive().observe(this, commentDataWrapper -> {
            if (commentDataWrapper.getAction() == DataActionWrapper.REFRESH) {
                commentAdapter.setData(commentDataWrapper.getData());
            } else if (commentDataWrapper.getAction() == DataActionWrapper.MORE) {
                commentAdapter.addData(commentDataWrapper.getData());
            }
        });
        viewModel.getParentCommentLive().observe(this, parentComment -> commentAdapter.onLoadSubCommentSuccess(parentComment));
        viewModel.getCommentLive().observe(this, hasComment -> getViewBinding().videoCommentButton.setVisibility(hasComment ? View.VISIBLE : View.GONE));
        viewModel.getFavLive().observe(this, isFav -> sourceAdapter.setFav(isFav));
        viewModel.getSelectSourceIndexLive().observe(this, selectSourceIndex -> sourceAdapter.setSelectSourceIndex(selectSourceIndex));
        viewModel.getRelatedVideoLive().observe(this, relatedVideoList -> relatedRVAdapter.replaceAll(relatedVideoList));
        viewModel.getCurrentVideoListBeanLive().observe(this, videoListBean -> viewModel.updateRelatedVideos(videoListBean));
        viewModel.initCreate();
        getViewBinding().videoPlayView.setFullScreen(SettingsFragment.fullDefault());
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

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.onViewStop(isFinishing());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onViewDestroy();
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

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        if (isInMultiWindowMode && getViewBinding().videoPlayView.isFullScreen()) {
            getViewBinding().videoPlayView.setFullScreen(false);
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

    private void showBottomComment() {
        getViewBinding().videoBottomComment.setVisibility(View.VISIBLE);
        BottomSheetBehavior.from(getViewBinding().videoBottomCommentBehaviorView).setState(BottomSheetBehavior.STATE_EXPANDED);
        viewModel.loadComment();
    }

    private List<VideoListBean> parseVideoListFromIntent() {
        List<VideoListBean> videoListBeans = getIntent().getParcelableArrayListExtra(EXTRA_VIDEO_LIST_BEAN);
        Uri videoUri = getIntent().getData();
        if (videoUri != null && (videoListBeans == null || videoListBeans.size() == 0)) {
            BaseParser parser = ParserManager.getInstance().matchSource(videoUri);
            if (parser != null) {
                videoListBeans = parser.createVideoListBeans(videoUri);
            }
        }
        return videoListBeans;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (getViewBinding().videoPlayView.handleExternalKeyEvent(event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isTV() {
        UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        return uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION;
    }
}
