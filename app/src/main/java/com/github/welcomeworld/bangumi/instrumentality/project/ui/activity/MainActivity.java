package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.MainViewPagerAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivityMainBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    MainViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewBinding().topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(this);
        viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        getViewBinding().mainViewPager.setAdapter(viewPagerAdapter);
        getViewBinding().mainViewPager.setOffscreenPageLimit(2);
        getViewBinding().mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.main_bottom_home) {
            getViewBinding().mainViewPager.setCurrentItem(0);
        } else if (id == R.id.main_bottom_bangumi) {
            getViewBinding().mainViewPager.setCurrentItem(1);
        } else if (id == R.id.main_bottom_mine) {
            getViewBinding().mainViewPager.setCurrentItem(2);
        } else if (id == R.id.main_search) {
            IntentUtil.intentToSearch(this, null);
        }
    }


}