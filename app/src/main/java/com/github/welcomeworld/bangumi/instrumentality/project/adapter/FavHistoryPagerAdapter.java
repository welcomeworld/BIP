package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.HistoryFragment;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.MainCollectionFragment;

public class FavHistoryPagerAdapter extends FragmentStatePagerAdapter {
    public FavHistoryPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new MainCollectionFragment();
            default:
                return new HistoryFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
