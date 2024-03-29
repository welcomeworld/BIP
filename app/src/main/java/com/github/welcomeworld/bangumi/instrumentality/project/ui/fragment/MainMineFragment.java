package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentMainMineBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.LoginContainerActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.SimpleContainerActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;

public class MainMineFragment extends BaseFragment<FragmentMainMineBinding> implements View.OnClickListener {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getVB().mineCollection.setOnClickListener(this);
        getVB().mineHistory.setOnClickListener(this);
        getVB().mineAccounts.setOnClickListener(this);
        getVB().mineSettings.setOnClickListener(this);
        getVB().mineDownload.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.mine_collection) {
            IntentUtil.intentToFav(getActivity(), null);
        } else if (id == R.id.mine_history) {
            IntentUtil.intentToHistory(getActivity(), null);
        } else if (id == R.id.mine_accounts) {
            IntentUtil.intentToLoginContainer(getActivity(), LoginContainerActivity.getStartBundle(BiliParser.getInstance().getTag()));
        } else if (id == R.id.mine_settings) {
            IntentUtil.intentToSimpleContainer(getActivity(), SimpleContainerActivity.getStartBundle(SettingsFragment.class.getName(), null));
        } else if (id == R.id.mine_download) {
            IntentUtil.intentToSimpleContainer(getActivity(), SimpleContainerActivity.getStartBundle(DownloadFragment.class.getName(), null));
        }
    }
}
