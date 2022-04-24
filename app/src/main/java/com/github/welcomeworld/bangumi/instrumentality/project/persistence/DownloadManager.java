package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.model.DownloadInfoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.DownloadUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.NotifyManager;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ThreadUtil;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DownloadManager {
    private static final String TAG = "DownloadManager";
    private static final Map<String, Long> downloadLengths = new ConcurrentHashMap<>();
    private static long allDownloadLength = 0;
    private static long allDownloadingLength = 0;
    private static int downloadCompletedId = 1;

    public static void pauseDownload(DownloadInfoBean downloadInfoBean) {
        DownloadUtil.cancelDownload(downloadInfoBean.getMediaId(), downloadInfoBean.getDownloadUrl());
        downloadInfoBean.setDownloadState(DownloadInfoBean.PAUSE);
        DownloadInfoConfig.updateOrSaveDownloadInfo(downloadInfoBean);
    }

    public static void download(DownloadInfoBean downloadInfoBean) {
        ThreadUtil.defer().when(() -> {
            if (downloadInfoBean.getDownloadState() == DownloadInfoBean.COMPLETE) {
                LogUtil.e(TAG, "download repeat");
                return;
            }
            downloadInfoBean.setDownloadState(DownloadInfoBean.PREPARED);
            BaseParser parser = ParserManager.getInstance().getParser(downloadInfoBean.getVideoData().get(downloadInfoBean.getSelectSourceIndex()).getSourceName());
            Map<String, String> headers = null;
            if (parser != null) {
                headers = parser.getDownloadHeaders(downloadInfoBean.getVideoData().get(downloadInfoBean.getSelectSourceIndex()));
            }
            DownloadUtil.download(downloadInfoBean.getMediaId(), downloadInfoBean.getDownloadUrl(), new File(downloadInfoBean.getLocalPath()), headers, new DownloadCallback(downloadInfoBean));
        }).fail(throwable -> {
            LogUtil.e(TAG, "download failed with throwable");
            throwable.printStackTrace();
        });
    }

    public static void download(List<VideoListBean> videoListBeans, int selectListPosition) {
        VideoListBean videoListBean = videoListBeans.get(selectListPosition);
        VideoBean videoBean = videoListBean.getCurrentVideoBean();
        String mediaId = videoListBean.getSourceName() + ":" + videoBean.getVideoKey();
        ThreadUtil.defer().when(() -> {
            File dirFile = new File(BIPApp.getInstance().getExternalFilesDir(null), videoListBean.getSourceName() + "/" + videoListBean.getTitle());
            if (!dirFile.exists() && !dirFile.mkdirs()) {
                LogUtil.e(TAG, "download create file fail: " + dirFile.getAbsolutePath());
                return;
            }
            File file = new File(dirFile, videoBean.getTitle() + "_" + videoBean.getCurrentQualityBean().getQuality() + ".mp4");
            DownloadInfoBean downloadInfoBean = DownloadInfoConfig.findDownloadInfo(mediaId);
            if (downloadInfoBean == null) {
                downloadInfoBean = buildByVideoInfo(videoListBeans, selectListPosition, file);
                DownloadInfoConfig.updateOrSaveDownloadInfo(downloadInfoBean);
            }
            if (downloadInfoBean.getDownloadState() == DownloadInfoBean.COMPLETE) {
                LogUtil.e(TAG, "download repeat");
                return;
            }
            downloadInfoBean.setDownloadState(DownloadInfoBean.PREPARED);
            DownloadInfoBean finalDownloadInfoBean = downloadInfoBean;
            BaseParser parser = ParserManager.getInstance().getParser(videoListBean.getSourceName());
            Map<String, String> headers = null;
            if (parser != null) {

                headers = parser.getDownloadHeaders(videoListBean);
            }
            DownloadUtil.download(finalDownloadInfoBean.getMediaId(), finalDownloadInfoBean.getDownloadUrl(), file, headers, new DownloadCallback(downloadInfoBean));
        }).fail(throwable -> {
            LogUtil.e(TAG, "download failed with throwable");
            throwable.printStackTrace();
        });
    }

    private static class DownloadCallback implements DownloadUtil.DownloadCallback {
        DownloadInfoBean finalDownloadInfoBean;

        public DownloadCallback(DownloadInfoBean downloadInfoBean) {
            finalDownloadInfoBean = downloadInfoBean;
        }

        int mProgress = 0;
        long downloadTrafficTime = 0;
        long lastDownloadLength = 0;

        @Override
        public void onPreStart(long contentLength) {
            finalDownloadInfoBean.setContentLength(contentLength);
            finalDownloadInfoBean.setDownloadState(DownloadInfoBean.DOWNLOADING);
            DownloadInfoConfig.updateOrSaveDownloadInfo(finalDownloadInfoBean);
            if (!downloadLengths.containsKey(finalDownloadInfoBean.getMediaId())) {
                downloadLengths.put(finalDownloadInfoBean.getMediaId(), contentLength);
                allDownloadLength += contentLength;
            }
            showProgressNotification(finalDownloadInfoBean);
        }

        @Override
        public void onSuccess() {
            finalDownloadInfoBean.setDownloadState(DownloadInfoBean.COMPLETE);
            DownloadInfoConfig.updateOrSaveDownloadInfo(finalDownloadInfoBean);
            if (downloadLengths.containsKey(finalDownloadInfoBean.getMediaId())) {
                long length = downloadLengths.remove(finalDownloadInfoBean.getMediaId());
                allDownloadLength -= length;
                allDownloadingLength -= length;
            }
            showProgressNotification(null);
            NotifyManager.showDownloadCompletedNotify(BIPApp.getInstance(), downloadCompletedId++ % Constants.NotificationId.FIX_NOTIFY_ID_START, finalDownloadInfoBean.getTitle());
        }

        @Override
        public void onProgress(int progress) {
            if (progress != mProgress) {
                LogUtil.e(TAG, "download progress " + progress);
                long length = downloadLengths.get(finalDownloadInfoBean.getMediaId());
                allDownloadingLength += (length * (progress - mProgress) / 100);
                mProgress = progress;
                finalDownloadInfoBean.setDownloadingProgress(mProgress);
                showProgressNotification(finalDownloadInfoBean);
            }
        }

        @Override
        public void onDownload(long length) {
            long current = System.currentTimeMillis();
            if (downloadTrafficTime == 0) {
                downloadTrafficTime = current;
                lastDownloadLength = length;
            } else if (current - downloadTrafficTime > 950) {
//                LogUtil.e(TAG, "download speed:" + (length - lastDownloadLength) / (current - downloadTrafficTime) + "kb/s");
                lastDownloadLength = length;
                downloadTrafficTime = current;
            }
        }

        @Override
        public void onFailed() {
            LogUtil.e(TAG, "download failed ");
            finalDownloadInfoBean.setDownloadState(DownloadInfoBean.ERROR);
            DownloadInfoConfig.updateOrSaveDownloadInfo(finalDownloadInfoBean);
            if (downloadLengths.containsKey(finalDownloadInfoBean.getMediaId())) {
                long length = downloadLengths.remove(finalDownloadInfoBean.getMediaId());
                allDownloadLength -= length;
                allDownloadingLength -= (length * mProgress / 100);
            }
            showProgressNotification(null);
        }

    }

    private static void showProgressNotification(DownloadInfoBean downloadInfoBean) {
        if (downloadLengths.isEmpty()) {
            NotifyManager.cancelNotify(BIPApp.getInstance(), Constants.NotificationId.DOWNLOADING_NOTIFY_ID);
        } else if (downloadLengths.size() == 1) {
            if (downloadInfoBean != null) {
                NotifyManager.showDownloadNotify(BIPApp.getInstance(), Constants.NotificationId.DOWNLOADING_NOTIFY_ID, (int) (allDownloadingLength * 100 / allDownloadLength), downloadInfoBean.getTitle());
            } else {
                String mediaId = downloadLengths.keySet().iterator().next();
                DownloadInfoBean onlyDownloadInfoBean = DownloadInfoConfig.findDownloadInfo(mediaId);
                if (onlyDownloadInfoBean == null) {
                    NotifyManager.cancelNotify(BIPApp.getInstance(), Constants.NotificationId.DOWNLOADING_NOTIFY_ID);
                } else {
                    NotifyManager.showDownloadNotify(BIPApp.getInstance(), Constants.NotificationId.DOWNLOADING_NOTIFY_ID, (int) (allDownloadingLength * 100 / allDownloadLength), onlyDownloadInfoBean.getTitle());
                }
            }
        } else {
            String title = BIPApp.getInstance().getString(R.string.download_task, downloadLengths.size());
            NotifyManager.showDownloadNotify(BIPApp.getInstance(), Constants.NotificationId.DOWNLOADING_NOTIFY_ID, (int) (allDownloadingLength * 100 / allDownloadLength), title);
        }
    }

    public static DownloadInfoBean buildByVideoInfo(List<VideoListBean> videoListBeans, int selectListPosition, File destFile) {
        VideoListBean videoListBean = videoListBeans.get(selectListPosition);
        VideoBean videoBean = videoListBean.getCurrentVideoBean();
        DownloadInfoBean downloadInfoBean = new DownloadInfoBean();
        downloadInfoBean.setMediaId(videoListBean.getSourceName() + ":" + videoBean.getVideoKey());
        downloadInfoBean.setDownloadState(DownloadInfoBean.PREPARED);
        downloadInfoBean.setDownloadTime(System.currentTimeMillis());
        downloadInfoBean.setDuration(videoBean.getDuration());
        downloadInfoBean.setCover(videoListBean.getCover());
        downloadInfoBean.setCoverPortrait(videoListBean.isCoverPortrait());
        downloadInfoBean.setLocalPath(destFile.getAbsolutePath());
        downloadInfoBean.setVideoData(videoListBeans);
        downloadInfoBean.setSelectSourceIndex(selectListPosition);
        downloadInfoBean.setDownloadTargetPosition(videoListBean.getSelectIndex());
        if (videoListBean.getVideoBeanList().size() > 1) {
            downloadInfoBean.setTitle(videoListBean.getTitle() + " " + videoBean.getTitle());
        } else {
            downloadInfoBean.setTitle(videoListBean.getTitle());
        }
        downloadInfoBean.setSourceName(videoListBean.getSourceName());
        downloadInfoBean.setDownloadUrl(videoBean.getCurrentQualityBean().getRealVideoUrl());
        return downloadInfoBean;
    }
}
