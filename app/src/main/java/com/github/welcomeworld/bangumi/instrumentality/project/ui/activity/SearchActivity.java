package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.SearchResultRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivitySearchBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.widget.SwiperefreshContainer;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.StringUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ThreadUtil;

public class SearchActivity extends BaseActivity<ActivitySearchBinding> {

    private String searchText = "";
    SearchResultRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewBinding().topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(this);
        getViewBinding().searchInput.setOnEditorActionListener((v, actionId, event) -> {
            searchText = getViewBinding().searchInput.getText().toString();
            refresh();
            return true;
        });
        adapter = new SearchResultRecyclerViewAdapter(this);
        getViewBinding().searchResultRecyclerview.setAdapter(adapter);
        getViewBinding().searchResultRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        getViewBinding().searchResultSwipecontainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!StringUtil.isEmpty(searchText)) {
                    refresh();
                }
            }
        });
        getViewBinding().searchResultSwipecontainer.setOnLoadListener(new SwiperefreshContainer.OnLoadListener() {
            @Override
            public void onLoad() {
//                load();
            }
        });
    }

    public void onClick(View view) {
        finish();
    }

    public void refresh() {
        if (searchText == null || searchText.isEmpty()) {
            return;
        }
        getViewBinding().searchResultSwipecontainer.setRefreshing(true);
        ThreadUtil.defer().when(() -> ParserManager.getInstance().search(searchText, "" + 1)).done((searchData) -> {
            getViewBinding().searchResultSwipecontainer.setRefreshing(false);
            adapter.replaceAll(searchData);
            getViewBinding().searchResultRecyclerview.scrollToPosition(0);
        });
    }
}
