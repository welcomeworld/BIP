package com.github.welcomeworld.bangumi.instrumentality.project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.welcomeworld.bangumi.instrumentality.project.livedata.ListActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SearchViewModel extends ViewModel {
    private String searchText = "";
    MutableLiveData<ListActionWrapper<VideoListBean>> homeData = new MutableLiveData<>();
    private int searchPage = 1;
    private String searchRandomKey = "";

    public void setSearchText(String searchText, boolean refresh) {
        this.searchText = searchText;
        if (refresh) {
            refresh();
        }
    }

    public LiveData<ListActionWrapper<VideoListBean>> getSearchDataLive() {
        return homeData;
    }

    public void loadMore() {
        if (searchText == null || searchText.isEmpty()) {
            return;
        }
        ParserManager.getInstance().search(searchText, "" + searchPage++, new SearchCallback(searchRandomKey) {
            @Override
            public void onSearchResult(List<VideoListBean> result) {
                if (Objects.equals(searchValidateKey, searchRandomKey)) {
                    homeData.setValue(new ListActionWrapper<>(ListActionWrapper.MORE, result));
                }
            }
        });
    }

    public void refresh() {
        if (searchText == null || searchText.isEmpty()) {
            return;
        }
        searchRandomKey = UUID.randomUUID().toString();
        searchPage = 1;
        ParserManager.getInstance().search(searchText, "" + searchPage++, new SearchCallback(searchRandomKey) {
            boolean hasRefresh = false;

            @Override
            public void onSearchResult(List<VideoListBean> result) {
                if (Objects.equals(searchValidateKey, searchRandomKey)) {
                    homeData.setValue(new ListActionWrapper<>(hasRefresh ? ListActionWrapper.MORE : ListActionWrapper.REFRESH, result));
                    hasRefresh = true;
                }
            }
        });
    }

    static abstract class SearchCallback implements BaseParser.SearchCallback {
        String searchValidateKey;

        SearchCallback(String searchRandomKey) {
            this.searchValidateKey = searchRandomKey;
        }
    }
}
