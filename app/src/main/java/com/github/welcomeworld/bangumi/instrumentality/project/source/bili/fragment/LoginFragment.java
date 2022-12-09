package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.BiliFragmentLoginBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.BaseFragment;
import com.github.welcomeworld.devbase.utils.ThreadUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.WebUtil;

public class LoginFragment extends BaseFragment<BiliFragmentLoginBinding> {
    private boolean userNameNull = true;
    private boolean passwordNull = true;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebUtil.initNormalWebView(getVB().validatorWeb, null);
        getVB().loginLogin.setOnClickListener(v -> login());
        getVB().loginPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordTextChange(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        getVB().loginUsernameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usernameTextChange(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void login() {
        Editable username = getVB().loginUsernameInput.getText();
        Editable password = getVB().loginPasswordInput.getText();
        if (username == null || password == null) {
            return;
        }
        ThreadUtil.defer().when(() -> BiliParser.login(username.toString(), password.toString())).done((result) -> {
            if (result.startsWith("http")) {
                getVB().validatorWeb.setVisibility(View.VISIBLE);
                getVB().validatorWeb.loadUrl(result);
            } else if ("success".equals(result)) {
                if (getActivity() != null) {
                    getActivity().finish();
                }
            }
        }).fail(Throwable::printStackTrace);
    }

    public void usernameTextChange(CharSequence charSequence) {
        userNameNull = charSequence.toString().trim().equals("");
        getVB().loginLogin.setEnabled(!userNameNull && !passwordNull);
    }

    public void passwordTextChange(CharSequence charSequence) {
        passwordNull = charSequence.toString().trim().equals("");
        getVB().loginLogin.setEnabled(!userNameNull && !passwordNull);
    }
}
