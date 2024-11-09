package com.github.welcomeworld.bangumi.instrumentality.project.ui.widget;

import android.content.Context;

import androidx.annotation.NonNull;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MaterialDialogBuilder extends MaterialAlertDialogBuilder {
    public MaterialDialogBuilder(@NonNull Context context) {
        this(context, R.style.normalDialogTheme);
    }

    public MaterialDialogBuilder(@NonNull Context context, int overrideThemeResId) {
        super(context, overrideThemeResId);
    }
}
