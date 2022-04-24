package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.github.welcomeworld.bangumi.instrumentality.project.model.DownloadInfoBean;

import java.util.List;

@Dao
public interface DownloadInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setDownloadInfo(DownloadInfoBean downloadInfoBean);

    @Query("select * from download where mediaId = :mediaId")
    DownloadInfoBean findDownloadInfo(String mediaId);

    @Query("select * from download order by downloadState asc, downloadTime desc")
    List<DownloadInfoBean> getAllDownloadInfo();

    @Delete
    void deleteDownloadInfo(DownloadInfoBean downloadInfoBean);
}
