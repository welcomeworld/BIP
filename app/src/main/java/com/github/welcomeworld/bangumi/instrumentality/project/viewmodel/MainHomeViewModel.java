package com.github.welcomeworld.bangumi.instrumentality.project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.welcomeworld.bangumi.instrumentality.project.livedata.ListActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

public class MainHomeViewModel extends ViewModel {
    private int pageNumber = 1;
    private boolean isLoading = false;
    MutableLiveData<ListActionWrapper<VideoListBean>> homeData = new MutableLiveData<>();

    public LiveData<ListActionWrapper<VideoListBean>> getHomeDataLive() {
        return homeData;
    }

    public void loadMore() {
        if (!isLoading) {
            getRecommend(pageNumber);
            pageNumber++;
        }
    }

    public void refresh() {
        if (!isLoading) {
            pageNumber = 1;
            getRecommend(pageNumber);
        }
    }

    private void getRecommend(int page) {
        ThreadUtil.defer().when(() -> ParserManager.getInstance().refreshRecommend(pageNumber)).fail(ex -> {
            pageNumber--;
            isLoading = false;
            ex.printStackTrace();
        }).done(result -> {
            isLoading = false;
            LogUtil.d("Home", "get recommend success:" + result.size());
            homeData.setValue(new ListActionWrapper<>(page == 1 ? ListActionWrapper.REFRESH : ListActionWrapper.MORE, result));
        });
    }
}
