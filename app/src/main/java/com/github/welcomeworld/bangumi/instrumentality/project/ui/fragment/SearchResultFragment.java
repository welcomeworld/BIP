package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.SearchResultRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.decoration.GridSpaceItemDecoration;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentSearchResultBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.ListActionWrapper;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.VideoPlayActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends BaseFragment<FragmentSearchResultBinding> {
    SearchResultRecyclerViewAdapter adapter = new SearchResultRecyclerViewAdapter();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SearchViewModel viewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
        viewModel.getSearchDataLive().observe(getViewLifecycleOwner(), homeLiveWrapper -> {
            List<VideoListBean> result = homeLiveWrapper.getData();
            if (homeLiveWrapper.getAction() == ListActionWrapper.REFRESH) {
                adapter.replaceAll(result);
                getVB().searchResultSwipecontainer.setRefreshing(false);
            } else if (homeLiveWrapper.getAction() == ListActionWrapper.MORE) {
                adapter.addAll(result);
                getVB().searchResultSwipecontainer.setLoading(false);
            }
        });
        getVB().searchResultRecyclerview.setAdapter(adapter);
        int listColumn = getResources().getInteger(R.integer.list_column);
        if (listColumn == 1) {
            getVB().searchResultRecyclerview.setLayoutManager(new LinearLayoutManager(requireActivity()));
        } else {
            getVB().searchResultRecyclerview.setLayoutManager(new GridLayoutManager(requireActivity(), listColumn));
        }
        RecyclerView.ItemDecoration itemDecoration = new GridSpaceItemDecoration(8);
        getVB().searchResultRecyclerview.addItemDecoration(itemDecoration);
        getVB().searchResultSwipecontainer.setOnRefreshListener(viewModel::refresh);
        getVB().searchResultSwipecontainer.setOnLoadListener(viewModel::loadMore);
        adapter.searchResultListener = clickResult -> {
            Bundle bundle = new Bundle();
            ArrayList<VideoListBean> videoListBeans = new ArrayList<>();
            videoListBeans.add(clickResult);
            bundle.putParcelableArrayList(VideoPlayActivity.EXTRA_VIDEO_LIST_BEAN, videoListBeans);
            IntentUtil.intentToVideoPlay(requireActivity(), bundle);
        };
    }
}
