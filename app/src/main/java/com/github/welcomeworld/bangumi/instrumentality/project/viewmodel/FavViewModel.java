package com.github.welcomeworld.bangumi.instrumentality.project.viewmodel;

import androidx.lifecycle.ViewModel;

import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.HistoryConfig;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

public class FavViewModel extends ViewModel {
    public void removeFav(HistoryBean historyBean) {
        ThreadUtil.defer().when(() -> HistoryConfig.removeFav(historyBean));
    }
}
