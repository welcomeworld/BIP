package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.FavOrHistoryRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.decoration.GridSpaceItemDecoration;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentMainCollectionBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.HistoryConfig;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

public class MainCollectionFragment extends BaseFragment<FragmentMainCollectionBinding> {

    FavOrHistoryRecyclerViewAdapter adapter;
    List<HistoryBean> data = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new FavOrHistoryRecyclerViewAdapter(getActivity());
        getVB().mainHomeRv.setAdapter(adapter);
        int listColumn = getResources().getInteger(R.integer.list_column);
        if (listColumn == 1) {
            getVB().mainHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), listColumn);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == adapter.getItemCount() - 1) {
                        return listColumn;
                    }
                    return 1;
                }
            });
            getVB().mainHomeRv.setLayoutManager(layoutManager);
        }
        RecyclerView.ItemDecoration itemDecoration = new GridSpaceItemDecoration(8);
        getVB().mainHomeRv.addItemDecoration(itemDecoration);
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
        ThreadUtil.defer().when(HistoryConfig::getFav).done(result -> {
            data = result;
            adapter.replaceAll(data);
            getVB().mainHomeSwipeRefresh.setRefreshing(false);
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
