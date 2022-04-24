package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import android.os.Looper;

import androidx.annotation.WorkerThread;

import com.github.welcomeworld.bangumi.instrumentality.project.model.DownloadInfoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ThreadUtil;

import java.io.File;
import java.util.List;

public class DownloadInfoConfig {

    public static void updateOrSaveDownloadInfo(DownloadInfoBean data) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            AppBaseDatabase.getInstance().getDownloadInfoDao().setDownloadInfo(data);
        } else {
            ThreadUtil.defer().when(() -> AppBaseDatabase.getInstance().getDownloadInfoDao().setDownloadInfo(data));
        }
    }

    @WorkerThread
    public static List<DownloadInfoBean> getDownloadInfo() {
        return AppBaseDatabase.getInstance().getDownloadInfoDao().getAllDownloadInfo();
    }

    @WorkerThread
    public static DownloadInfoBean findDownloadInfo(String mediaId) {
        return AppBaseDatabase.getInstance().getDownloadInfoDao().findDownloadInfo(mediaId);
    }

    public static void deleteDownloadInfo(DownloadInfoBean downloadInfoBean) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            AppBaseDatabase.getInstance().getDownloadInfoDao().deleteDownloadInfo(downloadInfoBean);
            File downloadFile = new File(downloadInfoBean.getLocalPath());
            File parentDir = downloadFile.getParentFile();
            downloadFile.delete();
            if (parentDir != null && parentDir.list() != null && parentDir.list().length == 0) {
                parentDir.delete();
            }
        } else {
            ThreadUtil.defer().when(() -> {
                AppBaseDatabase.getInstance().getDownloadInfoDao().deleteDownloadInfo(downloadInfoBean);
                File downloadFile = new File(downloadInfoBean.getLocalPath());
                File parentDir = downloadFile.getParentFile();
                downloadFile.delete();
                if (parentDir != null && parentDir.list() != null && parentDir.list().length == 0) {
                    parentDir.delete();
                }
            });
        }
    }
}
