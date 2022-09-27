package com.github.welcomeworld.bangumi.instrumentality.project.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


public class HideEndRecyclerView extends RecyclerView {
    //dp
    float endSize = 96f;
    OnScrollListener hideEndListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            if (newState == SCROLL_STATE_IDLE) {
                if (getContext() == null || getLayoutManager() == null) {
                    return;
                }
                int footerHeight = dp2px(getContext(), endSize);
                if (getLayoutManager().canScrollHorizontally()) {
                    final int offset = computeHorizontalScrollOffset();
                    final int range = computeHorizontalScrollRange() - computeHorizontalScrollExtent();
                    if (offset > range - footerHeight) {
                        smoothScrollBy(range - offset - footerHeight, 0);
                    }
                } else if (getLayoutManager().canScrollVertically()) {
                    final int offset = computeVerticalScrollOffset();
                    final int range = computeVerticalScrollRange() - computeVerticalScrollExtent();
                    if (offset > range - footerHeight) {
                        smoothScrollBy(0, range - offset - footerHeight);
                    }
                }
            }
        }
    };

    public HideEndRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public HideEndRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HideEndRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addOnScrollListener(hideEndListener);
    }

    public void setEndSize(float endSize) {
        this.endSize = endSize;
    }

    private static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
