package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivitySimpleContainerBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;

public class SimpleContainerActivity extends BaseActivity<ActivitySimpleContainerBinding> {
    private static final String EXTRA_TAG = "extra_fragment_tag";
    private static final String EXTRA_DATA = "extra_fragment_data";


    public static Bundle getStartBundle(String tag, Bundle fragmentBundle) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TAG, tag);
        if (fragmentBundle != null) {
            bundle.putBundle(EXTRA_DATA, fragmentBundle);
        }
        return bundle;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String tag = getIntent().getStringExtra(EXTRA_TAG);
        Class<Fragment> fragmentClass;
        try {
            fragmentClass = (Class<Fragment>) Class.forName(tag);
        } catch (ClassNotFoundException e) {
            finish();
            return;
        }
        addFragment(this, fragmentClass, getIntent().getBundleExtra(EXTRA_DATA));
    }

    public static void addFragment(Activity container, Class<? extends Fragment> fragmentClass, Bundle fragmentBundle) {
        addFragment(container, fragmentClass, fragmentBundle, true);
    }

    public static void addFragment(Activity container, Class<? extends Fragment> fragmentClass, Bundle fragmentBundle, boolean isContainer) {
        if (container != null) {
            if (isContainer && container instanceof FragmentActivity) {
                Fragment fragment;
                String simpleName = fragmentClass.getSimpleName();
                try {
                    fragment = fragmentClass.newInstance();
                    if (fragmentBundle != null) {
                        fragment.setArguments(fragmentBundle);
                    }
                } catch (Exception e) {
                    return;
                }
                if (((FragmentActivity) container).getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
                    ((FragmentActivity) container).getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, simpleName).commit();
                } else {
                    ((FragmentActivity) container).getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, simpleName).addToBackStack(simpleName).commit();
                }
            } else {
                IntentUtil.intentToSimpleContainer(container, SimpleContainerActivity.getStartBundle(fragmentClass.getName(), fragmentBundle));
            }
        }

    }
}
