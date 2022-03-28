package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.BiliFragmentWebLoginBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebLoginUrlBean;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.BaseFragment;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.StringUtil;
import com.github.welcomeworld.zxing.QRCodeAPI;
import com.nisigada.common.devbase.utils.ThreadUtil;
import com.nisigada.common.devbase.utils.ToastUtil;

public class WebLoginFragment extends BaseFragment<BiliFragmentWebLoginBinding> {
    private String oauthKey = null;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewBinding().loginRefreshQr.setOnClickListener(v -> refreshQRCode());
        getViewBinding().loginLoginQr.setOnClickListener(v -> checkLogin());
        refreshQRCode();
    }


    public void refreshQRCode() {
        ThreadUtil.defer().when(() -> {
            WebLoginUrlBean.Data urlData = BiliParser.getWebLoginUrl();
            if (urlData == null) {
                return null;
            } else {
                oauthKey = urlData.getOauthKey();
                return urlData.getUrl();
            }
        }).done((result) -> {
            if (getActivity() == null) {
                return;
            }
            if (StringUtil.isEmpty(result)) {
                ToastUtil.showToast(R.string.get_qrcode_fail);
            } else {
                getViewBinding().loginQr.setImageBitmap(QRCodeAPI.QREncode(result, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 500, 500));
            }
        });
    }

    public void checkLogin() {
        ThreadUtil.defer().when(() -> BiliParser.getWebLoginInfo(oauthKey)).done((result) -> {
            if (getActivity() == null) {
                return;
            }
            if (result) {
                ToastUtil.showToast(R.string.login_success);
                getActivity().finish();
            }
        });
    }
}
