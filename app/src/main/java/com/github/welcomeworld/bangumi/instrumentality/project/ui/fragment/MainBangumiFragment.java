package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentMainBangumiBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.WebUtil;

public class MainBangumiFragment extends BaseFragment<FragmentMainBangumiBinding> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebUtil.initNormalWebView(getViewBinding().bangumiWebview,getViewBinding().bangumiWebFullContainer);
        getViewBinding().bangumiWebview.loadUrl("https://web.age-spa.com:8443/#/catalog");
    }
}
