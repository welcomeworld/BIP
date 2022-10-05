package com.github.welcomeworld.bangumi.instrumentality.project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.welcomeworld.bangumi.instrumentality.project.livedata.ListLiveWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

public class SearchViewModel extends ViewModel {
    private String searchText = "";
    MutableLiveData<ListLiveWrapper<VideoListBean>> homeData = new MutableLiveData<>();
    private int searchPage = 1;

    public void setSearchText(String searchText, boolean refresh) {
        this.searchText = searchText;
        if (refresh) {
            refresh();
        }
    }

    public LiveData<ListLiveWrapper<VideoListBean>> getSearchDataLive() {
        return homeData;
    }

    public void loadMore() {
        if (searchText == null || searchText.isEmpty()) {
            return;
        }
        ThreadUtil.defer().when(() -> ParserManager.getInstance().search(searchText, "" + searchPage++)).done((searchData) -> {
            homeData.setValue(new ListLiveWrapper<>(ListLiveWrapper.MORE, searchData));
        });
    }

    public void refresh() {
        if (searchText == null || searchText.isEmpty()) {
            return;
        }
        searchPage = 1;
        ThreadUtil.defer().when(() -> ParserManager.getInstance().search(searchText, "" + searchPage++)).done((searchData) -> {
            homeData.setValue(new ListLiveWrapper<>(ListLiveWrapper.REFRESH, searchData));
        });
    }
}
