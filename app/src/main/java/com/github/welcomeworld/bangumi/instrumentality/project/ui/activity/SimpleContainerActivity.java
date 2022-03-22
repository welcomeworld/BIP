package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivitySimpleContainerBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.SettingsFragment;

public class SimpleContainerActivity extends BaseActivity<ActivitySimpleContainerBinding>{
    private static String EXTRA_TAG  = "extra_fragment_tag";
    public static Bundle getStartBundle(String tag){
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TAG,tag);
        return bundle;
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String tag = getIntent().getStringExtra(EXTRA_TAG);
        if(SettingsFragment.TAG.equals(tag)){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
        }
    }
}
