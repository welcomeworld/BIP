package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivitySearchBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.SearchHintFragment;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.SearchResultFragment;
import com.github.welcomeworld.bangumi.instrumentality.project.viewmodel.SearchViewModel;

public class SearchActivity extends BaseActivity<ActivitySearchBinding> {
    private final static String TAG_RESULT = "result";
    private final static String TAG_HINT = "hint";

    SearchViewModel viewModel = null;
    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {

        @Override
        public void handleOnBackPressed() {
            if (!viewModel.onBackPressed()) {
                finish();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            addHintFragment();
        }
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        viewModel.getSearchTextLive().observe(this, searchText -> {
            String currentText = getVB().searchInput.getText().toString();
            if (!currentText.equals(searchText)) {
                getVB().searchInput.setText(searchText);
            }
            if (searchText.isEmpty()) {
                hideResult();
            } else {
                showResult();
            }
        });
        getVB().searchInput.setOnEditorActionListener((v, actionId, event) -> {
            viewModel.setSearchText(getVB().searchInput.getText().toString());
            return true;
        });
        getVB().cancelButton.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
        getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
    }

    private void hideResult() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment resultFragment = fragmentManager.findFragmentByTag(TAG_RESULT);
        Fragment hintFragment = fragmentManager.findFragmentByTag(TAG_HINT);
        if (resultFragment != null && hintFragment != null) {
            fragmentManager.beginTransaction()
                    .remove(resultFragment)
                    .show(hintFragment)
                    .commit();
        }
    }

    private void showResult() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment hintFragment = fragmentManager.findFragmentByTag(TAG_HINT);
        if (hintFragment != null) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new SearchResultFragment(), TAG_RESULT)
                    .hide(hintFragment)
                    .commit();
        }
    }

    private void addHintFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new SearchHintFragment(), TAG_HINT)
                .commit();
    }
}
