package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.HotSearchRVAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentSearchHintBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.viewmodel.SearchViewModel;

public class SearchHintFragment extends BaseFragment<FragmentSearchHintBinding> {
    private final HotSearchRVAdapter adapter = new HotSearchRVAdapter();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SearchViewModel viewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
        getVB().hotSearchRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        getVB().hotSearchRv.setAdapter(adapter);
        adapter.hotSearchClickListener = hotSearch -> viewModel.setSearchText(hotSearch, true);
        viewModel.getHotSearchLive().observe(getViewLifecycleOwner(), hotSearch -> {
            if (hotSearch == null || hotSearch.isEmpty()) {
                getVB().hotSearchHeader.setVisibility(View.GONE);
            } else {
                getVB().hotSearchHeader.setVisibility(View.VISIBLE);
            }
            adapter.replaceAll(hotSearch);
        });
        viewModel.updateHotSearch();
    }
}
