package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.GratitudeRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentGratitudeBinding;
import com.github.welcomeworld.devbase.utils.ScreenUtil;

public class GratitudeFragment extends BaseFragment<FragmentGratitudeBinding> {
    GratitudeRecyclerViewAdapter recyclerViewAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getVB().topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(view.getContext());
        recyclerViewAdapter = new GratitudeRecyclerViewAdapter();
        getVB().gratitudeRecyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getVB().gratitudeRecyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        getVB().gratitudeRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
