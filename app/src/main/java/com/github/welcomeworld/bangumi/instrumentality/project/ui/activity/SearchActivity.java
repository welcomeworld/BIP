package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.SearchResultRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.decoration.GridSpaceItemDecoration;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivitySearchBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.ListActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.viewmodel.SearchViewModel;

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
                getVB().searchResultSwipecontainer.setRefreshing(false);
            } else if (homeLiveWrapper.getAction() == ListActionWrapper.MORE) {
                adapter.addAll(result);
                getVB().searchResultSwipecontainer.setLoading(false);
            }
        });
        viewModel.getHideHintLive().observe(this, hideHint -> {
            if (hideHint) {
                getVB().getRoot().removeView(getVB().fragmentContainer);
                getVB().searchResultSwipecontainer.setRefreshing(true);
            }
        });
        viewModel.getSearchTextLive().observe(this, searchText -> {
            String currentText = getVB().searchInput.getText().toString();
            if (!currentText.equals(searchText)) {
                getVB().searchInput.setText(searchText);
            }
        });
        getVB().searchInput.setOnEditorActionListener((v, actionId, event) -> {
            viewModel.setSearchText(getVB().searchInput.getText().toString(), true);
            return true;
        });
        getVB().searchResultRecyclerview.setAdapter(adapter);
        int listColumn = getResources().getInteger(R.integer.list_column);
        if (listColumn == 1) {
            getVB().searchResultRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        } else {
            getVB().searchResultRecyclerview.setLayoutManager(new GridLayoutManager(this, listColumn));
        }
        RecyclerView.ItemDecoration itemDecoration = new GridSpaceItemDecoration(8);
        getVB().searchResultRecyclerview.addItemDecoration(itemDecoration);
        getVB().searchResultSwipecontainer.setOnRefreshListener(() -> viewModel.refresh());
        getVB().searchResultSwipecontainer.setOnLoadListener(() -> viewModel.loadMore());
        getVB().cancelButton.setOnClickListener(v -> finish());
    }
}
