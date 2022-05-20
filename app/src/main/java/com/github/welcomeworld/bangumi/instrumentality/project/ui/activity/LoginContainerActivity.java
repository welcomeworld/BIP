package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivityLoginCntainerBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.BaseParser;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;
import com.github.welcomeworld.devbase.utils.ToastUtil;

public class LoginContainerActivity extends BaseActivity<ActivityLoginCntainerBinding> {
    private static String EXTRA_TAG = "extra_parser_tag";

    public static Bundle getStartBundle(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TAG, tag);
        return bundle;
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String tag = getIntent().getStringExtra(EXTRA_TAG);
        BaseParser parser = ParserManager.getInstance().getParser(tag);
        if (parser != null && parser.getLoginFragment() != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, parser.getLoginFragment()).commit();
        } else {
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BiliParser.checkLogin()) {
            ToastUtil.showToast(R.string.login_success);
            finish();
        }
    }
}
