package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.MainViewPagerAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivityMainBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;
import com.github.welcomeworld.devbase.utils.ToastUtil;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    MainViewPagerAdapter viewPagerAdapter;
    private long clickBackTime = 0;

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
                getViewBinding().mainBottomHome.setSelected(position == 0);
                getViewBinding().mainBottomBangumi.setSelected(position == 1);
                getViewBinding().mainBottomMine.setSelected(position == 2);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getViewBinding().mainBottomHome.setSelected(true);
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

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - clickBackTime < 2000) {
            super.onBackPressed();
        } else {
            clickBackTime = System.currentTimeMillis();
            ToastUtil.showToast(R.string.click_again_back);
        }
    }
}