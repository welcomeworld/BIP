package com.github.welcomeworld.bangumi.instrumentality.project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.DataActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.ListActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.SafeLiveData;
import com.github.welcomeworld.bangumi.instrumentality.project.model.CommentBean;
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
import com.github.welcomeworld.bangumi.instrumentality.project.player.BipExoPlayer;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.SettingsFragment;
import com.github.welcomeworld.bipplayer.BIPPlayer;
import com.github.welcomeworld.bipplayer.BipDataSource;
import com.github.welcomeworld.bipplayer.DefaultBIPPlayer;
import com.github.welcomeworld.devbase.utils.ThreadUtil;
import com.github.welcomeworld.devbase.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VideoPlayViewModel extends ViewModel {
    BIPPlayer bipPlayer = SettingsFragment.useExoPlayer() ? new BipExoPlayer() : new DefaultBIPPlayer();
    private boolean isFinishing = false;
    BaseParser videoParser = null;
    boolean loadingComment;
    int currentPage = 1;
    HistoryBean historyBean;
    private int selectSourceIndex = 0;
    private int selectVideoIndex = 0;
    List<VideoListBean> videoListBeans;
    private VideoListBean currentVideoListBean;
    private VideoBean currentVideoBean;
    SafeLiveData<DataActionWrapper<CommentBean>> commentDataLive = new SafeLiveData<>();
    SafeLiveData<ListActionWrapper<VideoListBean>> videoListBeansLive = new SafeLiveData<>();
    private final SafeLiveData<Boolean> isFavLive = new SafeLiveData<>();
    private final SafeLiveData<Integer> selectSourceIndexLive = new SafeLiveData<>();
    private final SafeLiveData<VideoBean> currentVideoBeanLive = new SafeLiveData<>();
    private final SafeLiveData<Boolean> commentLive = new SafeLiveData<>();
    private final SafeLiveData<CommentBean.CommentDataBean> parentCommentLive = new SafeLiveData<>();

    public LiveData<Boolean> getFavLive() {
        return isFavLive;
    }

    public LiveData<Integer> getSelectSourceIndexLive() {
        return selectSourceIndexLive;
    }

    public LiveData<VideoBean> getCurrentVideoBeanLive() {
        return currentVideoBeanLive;
    }

    public LiveData<Boolean> getCommentLive() {
        return commentLive;
    }

    public LiveData<CommentBean.CommentDataBean> getParentCommentLive() {
        return parentCommentLive;
    }

    public void initCreate() {
        ThreadUtil.defer().when(() -> {
            initHistory();
            return ParserManager.getInstance().updateVideoList(videoListBeans, selectSourceIndex);
        }).done((result) -> {
            if (isFinishing) {
                return;
            }
            changeVideoListBeans(result);
            parseVideoBeanDetail(false);
            initPlayer();
        });
    }

    public void changeVideoListBeans(List<VideoListBean> videoListBeans) {
        this.videoListBeans = videoListBeans;
        videoListBeansLive.updateValueSafe(new ListActionWrapper<>(ListActionWrapper.REFRESH, videoListBeans));
        setCurrentVideoListBean(videoListBeans.get(selectSourceIndex));
    }

    public BIPPlayer getPlayer() {
        return bipPlayer;
    }

    public void setSelectVideoIndex(int videoIndex) {
        selectVideoIndex = videoIndex;
    }

    public void setSelectSourceIndex(int sourceIndex) {
        selectSourceIndex = sourceIndex;
        selectSourceIndexLive.updateValueSafe(selectSourceIndex);
    }

    public void changeSelectItem(int sourceIndex, int position) {
        setSelectSourceIndex(sourceIndex);
        setSelectVideoIndex(position);
        setCurrentVideoListBean(videoListBeans.get(selectSourceIndex));
        currentVideoListBean.setSelectIndex(position);
        setCurrentVideoBean(currentVideoListBean.getCurrentVideoBean());
        parseVideoBeanDetail(false);
    }

    public int getSelectSourceIndex() {
        return selectSourceIndex;
    }

    private void initPlayer() {
        Map<Integer, Map<String, String>> options = ParserManager.getInstance().getPlayerOptions(videoListBeans.get(selectSourceIndex));
        if (options != null && options.size() > 0) {
            for (Integer category : options.keySet()) {
                Map<String, String> categories = options.get(category);
                if (categories != null) {
                    for (String key : categories.keySet()) {
                        bipPlayer.setOption(category, key, categories.get(key));
                    }
                }
            }
            if (SettingsFragment.useMediaCodec()) {
                bipPlayer.setOption(DefaultBIPPlayer.OPT_CATEGORY_PLAYER, "mediacodec", "1");
            }
        }
    }

    public LiveData<DataActionWrapper<CommentBean>> getCommentDataLive() {
        return commentDataLive;
    }

    public LiveData<ListActionWrapper<VideoListBean>> getVideoListBeanLive() {
        return videoListBeansLive;
    }

    public boolean hasComment() {
        return videoParser != null && videoParser.hasComment();
    }

    public void loadComment() {
        if (loadingComment || currentPage != 1) {
            return;
        }
        loadingComment = true;
        ThreadUtil.defer().when(() -> videoParser.getComment(currentVideoBean, currentPage))
                .fail(ex -> loadingComment = false)
                .done(result -> {
                    if (isFinishing) {
                        return;
                    }
                    commentDataLive.updateValueSafe(new DataActionWrapper<>(DataActionWrapper.REFRESH, result));
                    currentPage++;
                    loadingComment = false;
                });
    }

    public void loadSubComment(CommentBean.CommentDataBean parentComment) {
        ThreadUtil.defer().when(() -> videoParser.getSubComment(parentComment)).done(result -> {
            {
                if (isFinishing) {
                    return;
                }
                if (parentComment.subComment.size() < parentComment.subCommentPageSize) {
                    parentComment.subComment.clear();
                    parentComment.subComment.addAll(result);
                } else {
                    parentComment.subComment.addAll(result);
                }
                parentComment.subLoading = false;
                parentCommentLive.updateValueSafe(parentComment);
            }
        });
    }

    public void onViewStop(boolean isFinishing) {
        saveHistory();
        this.isFinishing = isFinishing;
        if (isFinishing) {
            releasePlayer();
        }
    }

    public void onViewDestroy() {
        releasePlayer();
    }

    private void releasePlayer() {
        if (bipPlayer != null) {
            bipPlayer.release();
            bipPlayer = null;
        }
    }

    public void parseVideoBeanDetail(boolean fromQuality) {
        if (!fromQuality) {
            bipPlayer.stop();
        }
        ThreadUtil.defer().when(() -> {
            String mediaId = currentVideoListBean.getSourceName() + ":" + currentVideoBean.getVideoKey();
            DownloadInfoBean downloadInfoBean = DownloadInfoConfig.findDownloadInfo(mediaId);
            if (downloadInfoBean != null && downloadInfoBean.getDownloadState() == DownloadInfoBean.COMPLETE) {
                if (currentVideoBean.getCurrentQualityBean() == null) {
                    VideoQualityBean videoQualityBean = new VideoQualityBean();
                    videoQualityBean.setQuality(BIPApp.getInstance().getString(R.string.downloaded));
                    currentVideoBean.getQualityBeans().add(videoQualityBean);
                }
                currentVideoBean.getCurrentQualityBean().setRealVideoUrl(downloadInfoBean.getLocalPath());
            } else if (currentVideoBean.getCurrentQualityBean() == null || !currentVideoBean.getCurrentQualityBean().isAvailable()) {
                changeVideoListBeans(ParserManager.getInstance().updateVideoList(videoListBeans, selectSourceIndex));
            }
        }).fail((throwable) -> {
            throwable.printStackTrace();
            ToastUtil.showToast(R.string.video_url_parse_error);
        }).done((result) -> {
            if (isFinishing) {
                return;
            }
            if (currentVideoBean == null || currentVideoBean.getCurrentQualityBean() == null || !currentVideoBean.getCurrentQualityBean().isAvailable()) {
                ToastUtil.showToast(R.string.video_url_parse_error);
                return;
            }
            setCurrentVideoBean(currentVideoBean);
            if (bipPlayer == null) {
                return;
            }
            if (fromQuality) {
                bipPlayer.prepareQualityAsync(getDataSource(currentVideoBean.getCurrentQualityBean(), true, 0));
            } else {
                bipPlayer.setDataSource(getDataSource(currentVideoBean.getCurrentQualityBean(), false, currentVideoBean.getPlayPosition()));
                bipPlayer.prepareAsync();
            }
        });
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
            historyBean.setSelectSourceIndex(selectSourceIndex);
            changeVideoListBeans(historyBean.getVideoData());
        }
        ParserManager.getInstance().clearCache(videoListBeans.get(selectSourceIndex));
    }

    private void saveHistory() {
        historyBean.setSelectSourceIndex(selectSourceIndex);
        if (videoListBeans != null && videoListBeans.size() > selectSourceIndex) {
            historyBean.setVideoData(videoListBeans);
        }
        historyBean.setViewTime(System.currentTimeMillis());
        HistoryConfig.updateOrSaveHistory(historyBean);
    }

    public void changeFavStatus() {
        boolean fav = !historyBean.isFav();
        historyBean.setFav(fav);
        if (fav) {
            historyBean.setFavTime(System.currentTimeMillis());
        }
        if (videoListBeans != null && videoListBeans.size() > selectSourceIndex) {
            historyBean.setVideoData(videoListBeans);
        }
        HistoryConfig.updateOrSaveHistory(historyBean);
    }

    public void handleDownloadClick() {
        ThreadUtil.defer().when(() -> {
            String mediaId = currentVideoListBean.getSourceName() + ":" + currentVideoBean.getVideoKey();
            DownloadInfoBean downloadInfoBean = DownloadInfoConfig.findDownloadInfo(mediaId);
            if (downloadInfoBean == null || downloadInfoBean.getDownloadState() == DownloadInfoBean.ERROR) {
                DownloadManager.download(videoListBeans, getSelectSourceIndex());
            }
        }).fail(Throwable::printStackTrace);
    }

    public String getBrowserUrl() {
        return currentVideoBean.getUrl();
    }

    public void setCurrentVideoListBean(VideoListBean videoListBean) {
        currentVideoListBean = videoListBean;
        currentVideoListBean.setSelectIndex(selectVideoIndex);
        videoParser = ParserManager.getInstance().getParser(currentVideoListBean.getSourceName());
        setCurrentVideoBean(currentVideoListBean.getCurrentVideoBean());
        changeCommentLive(hasComment());
    }

    public void setCurrentVideoBean(VideoBean videoBean) {
        currentVideoBean = videoBean;
        currentVideoBeanLive.updateValueSafe(currentVideoBean);
    }

    public void changeCommentLive(Boolean hasComment) {
        commentLive.updateValueSafe(hasComment);
    }

    public void refreshVideo() {
        ParserManager.getInstance().clearCache(currentVideoListBean);
        if (currentVideoBean.getCurrentQualityBean() != null) {
            currentVideoBean.getCurrentQualityBean().setRealVideoUrl(null);
        }
        parseVideoBeanDetail(false);
    }

    private List<BipDataSource> getDataSource(VideoQualityBean qualityBean, boolean sync, long seekPosition) {
        List<BipDataSource> sources = new ArrayList<>();
        boolean hasVideo = qualityBean.getRealVideoUrl() != null && !qualityBean.getRealVideoUrl().isEmpty();
        boolean hasAudio = qualityBean.getRealAudioUrl() != null && !qualityBean.getRealAudioUrl().isEmpty();
        if (hasVideo) {
            BipDataSource videoSource = new BipDataSource();
            videoSource.source = qualityBean.getRealVideoUrl();
            videoSource.isSync = sync;
            videoSource.seekPosition = seekPosition;
            videoSource.isSingleSource = !hasAudio;
            sources.add(videoSource);
        }
        if (hasAudio) {
            BipDataSource audioSource = new BipDataSource();
            audioSource.source = qualityBean.getRealAudioUrl();
            audioSource.isSync = sync;
            audioSource.isSingleSource = !hasVideo;
            audioSource.seekPosition = seekPosition;
            sources.add(audioSource);
        }
        return sources;
    }
}
