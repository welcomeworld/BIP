package com.github.welcomeworld.bangumi.instrumentality.project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.welcomeworld.bangumi.instrumentality.project.livedata.ListActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.SafeLiveData;
import com.github.welcomeworld.bangumi.instrumentality.project.model.SearchHistory;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.SearchHistoryRepo;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SearchViewModel extends ViewModel {
    private String searchText = "";

    private final SafeLiveData<String> searchTextLive = new SafeLiveData<>("");
    MutableLiveData<ListActionWrapper<VideoListBean>> searchResult = new MutableLiveData<>();
    private int searchPage = 1;
    private String searchRandomKey = "";

    private final SafeLiveData<List<String>> hotSearchLive = new SafeLiveData<>(Collections.emptyList());

    public void setSearchText(String searchText) {
        this.searchText = searchText;
        searchTextLive.updateValueSafe(searchText);
        if (!isSearchTextEmpty()) {
            SearchHistoryRepo.updateOrSaveHistory(new SearchHistory(searchText, System.currentTimeMillis()));
        }
        refresh();
    }

    public LiveData<ListActionWrapper<VideoListBean>> getSearchDataLive() {
        return searchResult;
    }

    public void loadMore() {
        if (isSearchTextEmpty()) {
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
        searchRandomKey = UUID.randomUUID().toString();
        searchPage = 1;
        if (isSearchTextEmpty()) {
            searchResult.setValue(new ListActionWrapper<>(ListActionWrapper.REFRESH, Collections.emptyList()));
            return;
        }
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

    public LiveData<List<String>> getHotSearchLive() {
        return hotSearchLive;
    }

    public LiveData<List<String>> getSearchHistoryLive() {
        return SearchHistoryRepo.getHistory();
    }

    public void deleteAllSearchHistory() {
        SearchHistoryRepo.deleteAllHistory();
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

    public boolean onBackPressed() {
        if (isSearchTextEmpty()) {
            return false;
        } else {
            setSearchText("");
            return true;
        }
    }

    public boolean isSearchTextEmpty() {
        return searchText == null || searchText.isEmpty();
    }
}
