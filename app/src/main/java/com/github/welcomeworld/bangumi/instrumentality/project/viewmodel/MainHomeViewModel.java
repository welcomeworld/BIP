package com.github.welcomeworld.bangumi.instrumentality.project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.welcomeworld.bangumi.instrumentality.project.livedata.HomeLiveWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

public class MainHomeViewModel extends ViewModel {
    MutableLiveData<HomeLiveWrapper> homeData = new MutableLiveData<>();

    public LiveData<HomeLiveWrapper> getHomeDataLive() {
        return homeData;
    }

    public void loadMore() {
        ThreadUtil.defer().when(() -> ParserManager.getInstance().getMoreRecommend()).done(result -> {
            homeData.setValue(new HomeLiveWrapper(HomeLiveWrapper.MORE,result));
        });
    }

    public void refresh() {
        ThreadUtil.defer().when(() -> ParserManager.getInstance().refreshRecommend()).done(result -> {
            homeData.setValue(new HomeLiveWrapper(HomeLiveWrapper.REFRESH,result));
        });
    }
}
