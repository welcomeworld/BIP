package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.FavOrHistoryRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentHistoryBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.HistoryConfig;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding> {
    FavOrHistoryRecyclerViewAdapter adapter;
    List<HistoryBean> data = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new FavOrHistoryRecyclerViewAdapter(getActivity());
        getViewBinding().mainHomeRv.setAdapter(adapter);
        getViewBinding().mainHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        getViewBinding().mainHomeSwipeRefresh.setOnRefreshListener(() -> refresh(true));
        getViewBinding().mainHomeSwipeRefresh.setOnLoadListener(this::loadMore);
        refresh(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh(false);
    }

    private void refresh(boolean force) {
        if ((!force && data != null && data.size() > 0)) {
            return;
        }
        if (getViewBinding().mainHomeSwipeRefresh.isRefreshing() && !force) {
            return;
        }
        if (force) {
            moreTime = 0;
        }
        getViewBinding().mainHomeSwipeRefresh.setRefreshing(true);
        ThreadUtil.defer().when(HistoryConfig::getHistory).done(result -> {
            data = result;
            adapter.replaceAll(data);
            getViewBinding().mainHomeSwipeRefresh.setRefreshing(false);
            if (result != null && result.size() > 0) {
                loadMore();
            }
        }).fail(Throwable::printStackTrace);
    }

    int moreTime = 4;

    private void loadMore() {
        if (moreTime >= 4) {
            return;
        }
        moreTime++;
    }
}
