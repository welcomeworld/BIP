package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Space;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.MainViewPagerAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_view_pager)
    ViewPager viewPager;
    @BindView(R.id.top_space)
    Space topSpace;
    @BindView(R.id.main_top)
    ConstraintLayout topContainer;

    MainViewPagerAdapter viewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(this);
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

    @OnClick({R.id.main_bottom_home,R.id.main_bottom_bangumi,R.id.main_bottom_collection,R.id.main_search})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_bottom_home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.main_bottom_bangumi:
                viewPager.setCurrentItem(1);
                break;
            case R.id.main_bottom_collection:
                viewPager.setCurrentItem(2);
                break;
            case R.id.main_search:
                IntentUtil.intentToSearch(this,null);
                break;
        }
    }


}