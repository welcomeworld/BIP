package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.welcomeworld.app_update.UpdateManager;
import com.github.welcomeworld.bangumi.instrumentality.project.BuildConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentSettingsBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;

public class SettingsFragment extends BaseFragment<FragmentSettingsBinding> implements View.OnClickListener {
    public static String TAG = "setting_fragment";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewBinding().topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(view.getContext());
        getViewBinding().settingVersionName.setText(BuildConfig.VERSION_NAME);
        getViewBinding().settingVersion.setOnClickListener(this);
        getViewBinding().settingOpensource.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.setting_version) {
            UpdateManager.checkUpdate(view.getContext(),"https://gitee.com/nisigada/software-update/raw/master/bip/version.json",BuildConfig.VERSION_NAME);
        } else if (id == R.id.setting_opensource) {

        }
    }
}
