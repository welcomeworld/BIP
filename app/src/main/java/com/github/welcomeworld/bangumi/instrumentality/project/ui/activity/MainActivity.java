package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.MainViewPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_view_pager)
    ViewPager viewPager;

    MainViewPagerAdapter viewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    @OnClick({R.id.main_bottom_home,R.id.main_bottom_bangumi,R.id.main_bottom_dynamic,R.id.main_bottom_profile})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_bottom_home:
                break;
            case R.id.main_bottom_bangumi:
                break;
            case R.id.main_bottom_dynamic:
                break;
            case R.id.main_bottom_profile:
                break;
        }
    }


}