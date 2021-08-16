package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void setHistory(HistoryBean historyBean);

    @Update
    public void updateHistory(HistoryBean historyBean);

    @Delete
    public void deleteHistory(HistoryBean historyBean);

    @Query("select * from history where isFav = 1 order by favTime desc")
    public List<HistoryBean> getAllFav();

    @Query("select * from history order by viewTime desc")
    public List<HistoryBean> getAllHistory();

    @Query("select * from history where uid = :uid and vid = :vid")
    public HistoryBean findHistory(int uid,String vid);
}
