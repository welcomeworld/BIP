package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.MainHomeRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.decoration.GridSpaceItemDecoration;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentMainHomeBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.ListActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.viewmodel.MainHomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainHomeFragment extends BaseFragment<FragmentMainHomeBinding> {

    MainHomeRecyclerViewAdapter adapter;
    List<VideoListBean> data = new ArrayList<>();
    MainHomeViewModel viewModel = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this).get(MainHomeViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MainHomeRecyclerViewAdapter(getActivity());
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
        getVB().mainHomeRv.setAdapter(adapter);
        getVB().mainHomeSwipeRefresh.setOnRefreshListener(() -> refresh(true));
        getVB().mainHomeSwipeRefresh.setOnLoadListener(this::loadMore);
        viewModel.getHomeDataLive().observe(getViewLifecycleOwner(), homeLiveWrapper -> {
            if (homeLiveWrapper.getAction() == ListActionWrapper.REFRESH) {
                data = homeLiveWrapper.getData();
                adapter.replaceAll(data);
                getVB().mainHomeSwipeRefresh.setRefreshing(false);
                if (data != null && data.size() > 0) {
                    loadMore();
                }
            } else if (homeLiveWrapper.getAction() == ListActionWrapper.MORE) {
                List<VideoListBean> result = homeLiveWrapper.getData();
                data.addAll(result);
                adapter.addAll(result);
                getVB().mainHomeSwipeRefresh.setLoading(false);
            }
        });
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
        getVB().mainHomeSwipeRefresh.setRefreshing(true);
        viewModel.refresh();
    }

    private void loadMore() {
        viewModel.loadMore();
    }
}
