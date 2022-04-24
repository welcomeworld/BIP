package com.github.welcomeworld.bangumi.instrumentality.project.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.welcomeworld.bangumi.instrumentality.project.R;


public class NormalCustomDialog extends Dialog {
    private TextView mTitleView;
    private TextView mContentView;
    private TextView mPositiveTxt;
    private TextView mNegativeTxt;

    public NormalCustomDialog setDlg_layout(int dlg_layout) {
        this.dlg_layout = dlg_layout;
        return this;
    }

    public View getDlgContentView() {
        return dlgContentView;
    }

    public void setDlgContentView(View dlgContentView) {
        this.dlgContentView = dlgContentView;
    }

    private int dlg_layout;
    private View dlgContentView;

    private String mTitle;
    private String mContent;
    private String mPositiveText;
    private String mNegativeText;
    private int uiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    public NormalCustomDialog setCancelAble(boolean cancelAble) {
        this.cancelAble = cancelAble;
        return this;
    }

    public NormalCustomDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public NormalCustomDialog setMatchHeight(boolean matchHeight) {
        this.matchHeight = matchHeight;
        return this;
    }

    public NormalCustomDialog setMatchWidth(boolean matchWidth) {
        this.matchWidth = matchWidth;
        return this;
    }

    public NormalCustomDialog setDismissNegative(boolean dismissNegative) {
        this.dismissNegative = dismissNegative;
        return this;
    }

    public NormalCustomDialog setUiVisibility(int uiVisibility) {
        this.uiVisibility = uiVisibility;
        return this;
    }

    private boolean cancelAble = false;
    private int gravity;
    private boolean matchHeight = false;
    private boolean matchWidth = false;
    private boolean dismissNegative = true;

    private OnPositiveClickListener mPositiveClickListener;
    private OnNegativeClickListener mNegativeClickListener;

    public NormalCustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
    }

    public NormalCustomDialog createDialog() {
        if (dlgContentView != null) {
            setContentView(dlgContentView);
        } else {
            setContentView(dlg_layout);
        }
        setCanceledOnTouchOutside(cancelAble);
        initView();
        return this;
    }

    private void initView() {
        initTitle();
        initContent();
        initPositive();
        initNegative();
        initKeyEvent();
    }

    private void initTitle() {
        mTitleView = findViewById(R.id.dlg_title);
        if (mTitleView != null) {
            if (!TextUtils.isEmpty(mTitle)) {
                mTitleView.setText(mTitle);
            }
        }
    }

    private void initContent() {
        mContentView = findViewById(R.id.dialog_content);
        if (mContentView != null) {
            if (!TextUtils.isEmpty(mContent)) {
                mContentView.setText(mContent);
            }
        }
    }

    private void initPositive() {
        mPositiveTxt = findViewById(R.id.dialog_positive);
        if (mPositiveTxt != null) {
            if (!TextUtils.isEmpty(mPositiveText)) {
                mPositiveTxt.setText(mPositiveText);
            }
            mPositiveTxt.setOnClickListener(v -> {
                if (mPositiveClickListener != null) {
                    mPositiveClickListener.onClick(NormalCustomDialog.this, v);
                }
            });
        }
    }

    private void initNegative() {
        mNegativeTxt = findViewById(R.id.dialog_negative);
        if (mNegativeTxt != null) {
            if (!TextUtils.isEmpty(mNegativeText)) {
                mNegativeTxt.setText(mNegativeText);
            }
            mNegativeTxt.setOnClickListener(v -> {
                if (mNegativeClickListener != null) {
                    mNegativeClickListener.onClick(NormalCustomDialog.this, v, false);
                }
                if (dismissNegative) {
                    dismiss();
                }
            });
        }
    }

    private void initKeyEvent() {
        setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (mNegativeClickListener != null) {
                    mNegativeClickListener.onClick(NormalCustomDialog.this, mNegativeTxt, true);
                }
                if (dismissNegative) {
                    dismiss();
                }
                return true;
            }
            return false;
        });
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public NormalCustomDialog setDialogTitle(String title) {
        mTitle = title;
        return this;
    }


    /**
     * 设置按钮文本
     *
     * @param text
     */
    public NormalCustomDialog setPositiveText(String text) {
        mPositiveText = text;
        return this;
    }

    /**
     * 设置按钮文本
     *
     * @param text
     */
    public NormalCustomDialog setNegativeText(String text) {

        mNegativeText = text;
        return this;
    }

    /**
     * 设置文本
     *
     * @param content
     */
    public NormalCustomDialog setContent(String content) {
        mContent = content;
        return this;
    }

    public NormalCustomDialog setPositiveClickListener(OnPositiveClickListener mPositiveClickListener) {
        this.mPositiveClickListener = mPositiveClickListener;
        return this;
    }

    public NormalCustomDialog setNegativeClickListener(OnNegativeClickListener mNegativeClickListener) {
        this.mNegativeClickListener = mNegativeClickListener;
        return this;
    }

    public interface OnPositiveClickListener {
        void onClick(NormalCustomDialog dialog, View view);
    }

    public interface OnNegativeClickListener {
        void onClick(NormalCustomDialog dialog, View view, boolean keyBack);
    }

    public interface setOnCheckedChangeListener {
        void onCheckedChange(boolean isCheck);
    }

    @Override
    public void show() {
        try {
            Window dialogWindow = getWindow();
            if (dialogWindow != null) {
                dialogWindow.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            }
            super.show();
            if (dialogWindow != null) {
                dialogWindow.getDecorView().setSystemUiVisibility(uiVisibility);
                dialogWindow.setGravity(gravity);
                dialogWindow.setLayout(matchWidth ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT, matchHeight ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
                dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            }
        } catch (Exception e) {
            //ignore
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            //ignore
        }
    }
}

