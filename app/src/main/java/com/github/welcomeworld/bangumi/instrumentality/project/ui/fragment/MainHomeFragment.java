package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.MainHomeRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentMainHomeBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.HomeLiveWrapper;
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
        getViewBinding().mainHomeRv.setAdapter(adapter);
        getViewBinding().mainHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        getViewBinding().mainHomeSwipeRefresh.setOnRefreshListener(() -> refresh(true));
        getViewBinding().mainHomeSwipeRefresh.setOnLoadListener(this::loadMore);
        viewModel.getHomeDataLive().observe(getViewLifecycleOwner(), homeLiveWrapper -> {
            if (homeLiveWrapper.getAction() == HomeLiveWrapper.REFRESH) {
                data = homeLiveWrapper.getData();
                adapter.replaceAll(data);
                getViewBinding().mainHomeSwipeRefresh.setRefreshing(false);
                if (data != null && data.size() > 0) {
                    loadMore();
                }
            }else if(homeLiveWrapper.getAction() == HomeLiveWrapper.MORE){
                List<VideoListBean> result = homeLiveWrapper.getData();
                data.addAll(result);
                adapter.addAll(result);
                getViewBinding().mainHomeSwipeRefresh.setLoading(false);
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
        if (getViewBinding().mainHomeSwipeRefresh.isRefreshing() && !force) {
            return;
        }
        getViewBinding().mainHomeSwipeRefresh.setRefreshing(true);
        viewModel.refresh();
    }

    private void loadMore() {
        viewModel.loadMore();
    }
}
