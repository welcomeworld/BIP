package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.github.welcomeworld.bangumi.instrumentality.project.model.SearchHistory;

import java.util.List;

@Dao
public interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setSearchHistory(SearchHistory searchHistory);

    @Query("select searchKey from search_history order by searchTime desc limit 40")
    LiveData<List<String>> getAllSearchHistory();

    @Query("delete from search_history")
    void deleteAllSearchHistory();
}
