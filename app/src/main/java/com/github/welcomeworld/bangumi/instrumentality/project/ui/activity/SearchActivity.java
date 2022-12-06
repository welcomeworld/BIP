package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.SearchResultRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivitySearchBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.ListActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.viewmodel.SearchViewModel;
import com.github.welcomeworld.devbase.utils.ScreenUtil;

import java.util.List;

public class SearchActivity extends BaseActivity<ActivitySearchBinding> {

    SearchResultRecyclerViewAdapter adapter = new SearchResultRecyclerViewAdapter(this);
    SearchViewModel viewModel = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        viewModel.getSearchDataLive().observe(this, homeLiveWrapper -> {
            List<VideoListBean> result = homeLiveWrapper.getData();
            if (homeLiveWrapper.getAction() == ListActionWrapper.REFRESH) {
                adapter.replaceAll(result);
                getViewBinding().searchResultSwipecontainer.setRefreshing(false);
            } else if (homeLiveWrapper.getAction() == ListActionWrapper.MORE) {
                adapter.addAll(result);
                getViewBinding().searchResultSwipecontainer.setLoading(false);
            }
        });
        getViewBinding().topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(this);
        getViewBinding().searchInput.setOnEditorActionListener((v, actionId, event) -> {
            getViewBinding().searchResultSwipecontainer.setRefreshing(true);
            viewModel.setSearchText(getViewBinding().searchInput.getText().toString(), true);
            return true;
        });
        getViewBinding().searchResultRecyclerview.setAdapter(adapter);
        getViewBinding().searchResultRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        getViewBinding().searchResultSwipecontainer.setOnRefreshListener(() -> viewModel.refresh());
        getViewBinding().searchResultSwipecontainer.setOnLoadListener(() -> viewModel.loadMore());
        getViewBinding().cancelButton.setOnClickListener(v -> finish());
    }
}
