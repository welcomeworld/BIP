package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.MainBangumiFragment;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.MainHomeFragment;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.MainMineFragment;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    public MainViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new MainBangumiFragment();
            case 2:
                return new MainMineFragment();
            default:
                return new MainHomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
