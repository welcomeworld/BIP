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
import com.nisigada.common.devbase.utils.KVUtil;

public class SettingsFragment extends BaseFragment<FragmentSettingsBinding> implements View.OnClickListener {
    public static String TAG = "setting_fragment";
    private static final String SETTING_BACKUP_UPDATE = "setting_backup_update";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewBinding().topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(view.getContext());
        getViewBinding().settingVersionName.setText(BuildConfig.VERSION_NAME);
        getViewBinding().settingVersion.setOnClickListener(this);
        getViewBinding().settingOpensource.setOnClickListener(this);
        getViewBinding().settingBackupCheck.setChecked(KVUtil.getBoolean(SETTING_BACKUP_UPDATE));
        getViewBinding().settingBackupCheck.setOnCheckedChangeListener((buttonView, isChecked) -> KVUtil.putBoolean(SETTING_BACKUP_UPDATE, isChecked));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.setting_version) {
            if (KVUtil.getBoolean(SETTING_BACKUP_UPDATE)) {
                UpdateManager.checkUpdate(view.getContext(), "https://gitee.com/nisigada/software-update/raw/master/bip/version_bak.json", BuildConfig.VERSION_NAME);
            } else {
                UpdateManager.checkUpdate(view.getContext(), "https://gitee.com/nisigada/software-update/raw/master/bip/version.json", BuildConfig.VERSION_NAME);
            }
        } else if (id == R.id.setting_opensource) {

        }
    }
}
