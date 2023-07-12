package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
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
        adapter = new FavOrHistoryRecyclerViewAdapter(getActivity(), true);
        getVB().mainHomeRv.setAdapter(adapter);
        int listColumn = getResources().getInteger(R.integer.list_column);
        if (listColumn == 1) {
            getVB().mainHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            getVB().mainHomeRv.setLayoutManager(new GridLayoutManager(getActivity(), listColumn));
        }
        getVB().mainHomeSwipeRefresh.setOnRefreshListener(() -> refresh(true));
        getVB().mainHomeSwipeRefresh.setOnLoadListener(this::loadMore);
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
        if (getVB().mainHomeSwipeRefresh.isRefreshing() && !force) {
            return;
        }
        if (force) {
            moreTime = 0;
        }
        getVB().mainHomeSwipeRefresh.setRefreshing(true);
        ThreadUtil.defer().when(HistoryConfig::getHistory).done(result -> {
            data = result;
            adapter.replaceAll(data);
            getVB().mainHomeSwipeRefresh.setRefreshing(false);
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
