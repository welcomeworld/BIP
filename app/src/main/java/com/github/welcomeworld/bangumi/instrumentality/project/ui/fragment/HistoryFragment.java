package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.FavOrHistoryRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentHistoryBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.HistoryConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

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
        getViewBinding().mainHomeRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == SCROLL_STATE_IDLE) {
                    scrollHideBottom();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void scrollHideBottom() {
        Log.e("SwipeRefresh", "scrollHide");
        if (getContext() == null) {
            return;
        }
        int footerHeight = ScreenUtil.dp2px(getContext(), 96);
        final int offset = getViewBinding().mainHomeRv.computeVerticalScrollOffset();
        final int range = getViewBinding().mainHomeRv.computeVerticalScrollRange() - getViewBinding().mainHomeRv.computeVerticalScrollExtent();
        if (offset > range - footerHeight) {
            getViewBinding().mainHomeRv.smoothScrollBy(0, range - offset - footerHeight);
        }

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
        });
    }

    int moreTime = 4;

    private void loadMore() {
        if (moreTime >= 4) {
            return;
        }
        moreTime++;
    }
}
