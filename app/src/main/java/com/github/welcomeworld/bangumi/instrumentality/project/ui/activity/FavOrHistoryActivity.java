package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.FavHistoryPagerAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivityFavHistoryBinding;
import com.google.android.material.tabs.TabLayout;

public class FavOrHistoryActivity extends BaseActivity<ActivityFavHistoryBinding> {
    FavHistoryPagerAdapter viewPagerAdapter;
    public static final String EXTRA_PAGE_INDEX = "extra_page_index";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPagerAdapter = new FavHistoryPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        getVB().mainViewPager.setAdapter(viewPagerAdapter);
        getVB().mainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(getVB().topTab));
        getVB().topTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(getVB().mainViewPager));
        int pageIndex = getIntent().getIntExtra(EXTRA_PAGE_INDEX, 0);
        getVB().mainViewPager.setCurrentItem(pageIndex);
    }
}
