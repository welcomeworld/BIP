package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.HotSearchRVAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.SearchHistoryRVAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.decoration.GridSpaceItemDecoration;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentSearchHintBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.viewmodel.SearchViewModel;
import com.google.android.flexbox.FlexboxLayoutManager;

public class SearchHintFragment extends BaseFragment<FragmentSearchHintBinding> {
    private final HotSearchRVAdapter adapter = new HotSearchRVAdapter();
    private final SearchHistoryRVAdapter historyAdapter = new SearchHistoryRVAdapter();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SearchViewModel viewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
        getVB().hotSearchRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        getVB().hotSearchRv.setAdapter(adapter);
        adapter.hotSearchClickListener = viewModel::setSearchText;
        viewModel.getHotSearchLive().observe(getViewLifecycleOwner(), hotSearch -> {
            if (hotSearch == null || hotSearch.isEmpty()) {
                getVB().groupHotSearch.setVisibility(View.GONE);
            } else {
                getVB().groupHotSearch.setVisibility(View.VISIBLE);
            }
            adapter.replaceAll(hotSearch);
        });
        viewModel.updateHotSearch();
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(requireContext());
        getVB().searchHistoryRv.setLayoutManager(layoutManager);
        getVB().searchHistoryRv.setAdapter(historyAdapter);
        RecyclerView.ItemDecoration itemDecoration = new GridSpaceItemDecoration(8, 4);
        getVB().searchHistoryRv.addItemDecoration(itemDecoration);
        historyAdapter.searchHistoryClickListener = viewModel::setSearchText;
        viewModel.getSearchHistoryLive().observe(getViewLifecycleOwner(), histories -> {
            if (histories == null || histories.isEmpty()) {
                getVB().groupSearchHistory.setVisibility(View.GONE);
            } else {
                getVB().groupSearchHistory.setVisibility(View.VISIBLE);
            }
            historyAdapter.replaceAll(histories);
        });
        getVB().searchHistoryClear.setOnClickListener(v -> viewModel.deleteAllSearchHistory());
    }
}
