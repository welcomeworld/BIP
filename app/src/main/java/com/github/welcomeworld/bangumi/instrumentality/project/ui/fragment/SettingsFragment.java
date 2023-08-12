package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.github.welcomeworld.bangumi.instrumentality.project.BuildConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentSettingsBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.SimpleContainerActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.viewmodel.SettingsViewModel;

public class SettingsFragment extends BaseFragment<FragmentSettingsBinding> implements View.OnClickListener {
    private SettingsViewModel viewModel = null;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        viewModel.getUseExoLive().observe(getViewLifecycleOwner(), useExo -> getVB().settingExoPlayer.setSelected(useExo));
        viewModel.getUseMediaCodecLive().observe(getViewLifecycleOwner(), useMediaCodec -> getVB().settingMediaCodec.setSelected(useMediaCodec));
        viewModel.getFullDefaultLive().observe(getViewLifecycleOwner(), fullDefault -> getVB().settingFullDefault.setSelected(fullDefault));
        viewModel.getBiliLoginLive().observe(getViewLifecycleOwner(), isLogin -> getVB().settingLogout.setVisibility(isLogin ? View.VISIBLE : View.GONE));
        getVB().settingVersionName.setText(BuildConfig.VERSION_NAME);
        getVB().settingVersion.setOnClickListener(this);
        getVB().settingOpensource.setOnClickListener(this);
        getVB().settingMediaCodec.setOnClickListener(this);
        getVB().settingExoPlayer.setOnClickListener(this);
        getVB().settingFullDefault.setOnClickListener(this);
        getVB().settingLogout.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.setting_version) {
            viewModel.checkUpdate();
        } else if (id == R.id.setting_opensource) {
            goGratitude();
        } else if (id == R.id.setting_logout) {
            viewModel.logout();
        } else if (id == R.id.setting_exoPlayer) {
            viewModel.setUseExoPlayer(view.isSelected());
        } else if (id == R.id.setting_mediaCodec) {
            viewModel.setUseMediaCodec(view.isSelected());
        } else if (id == R.id.setting_full_default) {
            viewModel.setFullDefault(view.isSelected());
        }
    }

    private void goGratitude() {
        SimpleContainerActivity.addFragment(getActivity(), GratitudeFragment.class, null);
    }
}
