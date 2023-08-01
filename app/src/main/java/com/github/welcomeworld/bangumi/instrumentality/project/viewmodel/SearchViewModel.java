package com.github.welcomeworld.bangumi.instrumentality.project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.welcomeworld.bangumi.instrumentality.project.livedata.ListActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.SafeLiveData;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SearchViewModel extends ViewModel {
    private String searchText = "";

    private final SafeLiveData<String> searchTextLive = new SafeLiveData<>();
    MutableLiveData<ListActionWrapper<VideoListBean>> searchResult = new MutableLiveData<>();
    private int searchPage = 1;
    private String searchRandomKey = "";

    private final SafeLiveData<Boolean> hideHint = new SafeLiveData<>();

    private final SafeLiveData<List<String>> hotSearchLive = new SafeLiveData<>();


    public void setSearchText(String searchText, boolean refresh) {
        this.searchText = searchText;
        searchTextLive.updateValueSafe(searchText);
        hideHint.updateValueSafe(true);
        if (refresh) {
            refresh();
        }
    }

    public LiveData<ListActionWrapper<VideoListBean>> getSearchDataLive() {
        return searchResult;
    }

    public void loadMore() {
        if (searchText == null || searchText.isEmpty()) {
            return;
        }
        ParserManager.getInstance().search(searchText, "" + searchPage++, new SearchCallback(searchRandomKey) {
            @Override
            public void onSearchResult(List<VideoListBean> result) {
                if (Objects.equals(searchValidateKey, searchRandomKey)) {
                    searchResult.setValue(new ListActionWrapper<>(ListActionWrapper.MORE, result));
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
                    searchResult.setValue(new ListActionWrapper<>(hasRefresh ? ListActionWrapper.MORE : ListActionWrapper.REFRESH, result));
                    hasRefresh = true;
                }
            }
        });
    }

    public LiveData<String> getSearchTextLive() {
        return searchTextLive;
    }

    public LiveData<Boolean> getHideHintLive() {
        return hideHint;
    }

    public LiveData<List<String>> getHotSearchLive() {
        return hotSearchLive;
    }

    public void updateHotSearch() {
        ThreadUtil.defer().when(() -> ParserManager.getInstance().getHotSearch()).done(hotSearchLive::updateValueSafe);
    }

    static abstract class SearchCallback implements BaseParser.SearchCallback {
        String searchValidateKey;

        SearchCallback(String searchRandomKey) {
            this.searchValidateKey = searchRandomKey;
        }
    }
}
