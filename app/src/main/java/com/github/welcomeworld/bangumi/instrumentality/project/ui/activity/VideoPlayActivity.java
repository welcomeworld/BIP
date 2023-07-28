package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.CommentRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.RelatedRVAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.VideoDescRVAdapter;
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
import com.github.welcomeworld.devbase.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayActivity extends BaseActivity<ActivityVideoPlayBinding> {
    public static final String EXTRA_VIDEO_LIST_BEAN = "extra_video_list_bean";
    public static final String EXTRA_SOURCE_SELECT_INDEX = "extra_source_select_index";
    public static final String EXTRA_VIDEO_TARGET_INDEX = "extra_download_target_index";

    VideoPlayViewModel viewModel = null;
    VideoSourceItemAdapter sourceAdapter = new VideoSourceItemAdapter();

    VideoDescRVAdapter descRVAdapter = new VideoDescRVAdapter();
    CommentRecyclerViewAdapter commentAdapter = new CommentRecyclerViewAdapter();

    RelatedRVAdapter relatedRVAdapter = new RelatedRVAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(VideoPlayViewModel.class);
        registerSomething();
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
        descRVAdapter.setActionClickListener(new VideoDescRVAdapter.ActionClickListener() {
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

            @Override
            public void onCommentClick() {
                if (viewModel.hasComment()) {
                    if (isCommentShowing()) {
                        hideComment();
                    } else {
                        showComment();
                    }
                } else {
                    ToastUtil.showToast(R.string.no_comment);
                }
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
        boolean desSplit = getViewBinding().videoPlayDesRv != null;
        if (desSplit) {
            getViewBinding().videoPlayDesRv.setLayoutManager(new LinearLayoutManager(this));
            getViewBinding().videoPlayDesRv.setAdapter(descRVAdapter);
            int listColumn = getResources().getInteger(R.integer.list_column);
            GridLayoutManager layoutManager = new GridLayoutManager(this,listColumn);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position < sourceAdapter.getItemCount()||position == (sourceAdapter.getItemCount()+relatedRVAdapter.getItemCount()-1)){
                        return listColumn;
                    }
                    return 1;
                }
            });
            getViewBinding().videoPlaySourceRv.setLayoutManager(layoutManager);
            getViewBinding().videoPlaySourceRv.setAdapter(new ConcatAdapter(sourceAdapter, relatedRVAdapter));
        } else {
            getViewBinding().videoPlaySourceRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            getViewBinding().videoPlaySourceRv.setAdapter(new ConcatAdapter(descRVAdapter, sourceAdapter, relatedRVAdapter));
        }
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
        viewModel.getFavLive().observe(this, isFav -> descRVAdapter.setFav(isFav));
        viewModel.getSelectSourceIndexLive().observe(this, selectSourceIndex -> sourceAdapter.setSelectSourceIndex(selectSourceIndex));
        viewModel.getRelatedVideoLive().observe(this, relatedVideoList -> relatedRVAdapter.replaceAll(relatedVideoList));
        viewModel.getCurrentVideoListBeanLive().observe(this, videoListBean -> {
            descRVAdapter.setData(videoListBean);
            viewModel.updateRelatedVideos(videoListBean);
        });
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
        } else if (isCommentShowing()) {
            hideComment();
        } else {
            super.onBackPressed();
        }
    }

    public void refreshSystemUIVisibility() {
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        if (getViewBinding().videoPlayView.isFullScreen()) {
            WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
            controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            controller.hide(WindowInsetsCompat.Type.systemBars());
        } else {
            WindowCompat.setDecorFitsSystemWindows(getWindow(), true);
            controller.show(WindowInsetsCompat.Type.systemBars());
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

    private boolean isCommentShowing() {
        return getViewBinding().bottomCommentRv.getVisibility() == View.VISIBLE;
    }

    private void showComment() {
        getViewBinding().bottomCommentRv.setVisibility(View.VISIBLE);
        viewModel.loadComment();
    }

    private void hideComment() {
        getViewBinding().bottomCommentRv.setVisibility(View.GONE);
    }
}
