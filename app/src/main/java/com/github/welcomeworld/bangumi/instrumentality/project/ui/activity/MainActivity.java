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
import com.github.welcomeworld.devbase.utils.ToastUtil;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    private long lastClickBackTime = 0;
    private final View.OnClickListener clickListener = view -> {
        int id = view.getId();
        if (id == R.id.main_bottom_home) {
            getVB().mainViewPager.setCurrentItem(0);
        } else if (id == R.id.main_bottom_bangumi) {
            getVB().mainViewPager.setCurrentItem(1);
        } else if (id == R.id.main_bottom_mine) {
            getVB().mainViewPager.setCurrentItem(2);
        } else if (id == R.id.main_search) {
            IntentUtil.intentToSearch(MainActivity.this, null);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getVB().mainSearch.setOnClickListener(clickListener);
        getVB().mainBottomHome.setOnClickListener(clickListener);
        getVB().mainBottomBangumi.setOnClickListener(clickListener);
        getVB().mainBottomMine.setOnClickListener(clickListener);
        getVB().mainViewPager.setAdapter(viewPagerAdapter);
        getVB().mainViewPager.setOffscreenPageLimit(2);
        getVB().mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getVB().mainBottomHome.setSelected(position == 0);
                getVB().mainBottomBangumi.setSelected(position == 1);
                getVB().mainBottomMine.setSelected(position == 2);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getVB().mainBottomHome.setSelected(true);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastClickBackTime < 2000) {
            super.onBackPressed();
        } else {
            lastClickBackTime = System.currentTimeMillis();
            ToastUtil.showToast(R.string.click_again_back);
        }
    }
}