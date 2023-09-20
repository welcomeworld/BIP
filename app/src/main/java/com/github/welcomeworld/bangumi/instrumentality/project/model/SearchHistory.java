package com.github.welcomeworld.bangumi.instrumentality.project.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "search_history")
public class SearchHistory {
    public SearchHistory(@NonNull String searchKey, long searchTime) {
        this.searchKey = searchKey;
        this.searchTime = searchTime;
    }

    @NonNull
    @PrimaryKey
    public String searchKey;
    public long searchTime;
}
