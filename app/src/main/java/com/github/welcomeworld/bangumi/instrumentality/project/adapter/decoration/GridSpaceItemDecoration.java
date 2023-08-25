package com.github.welcomeworld.bangumi.instrumentality.project.adapter.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.utils.UIUtil;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int startSpace, topSpace, endSpace, bottomSpace;

    public GridSpaceItemDecoration(int space) {
        this(space, space, space, space);
    }

    public GridSpaceItemDecoration(int start, int top, int end, int bottom) {
        startSpace = UIUtil.dp2px(start);
        topSpace = UIUtil.dp2px(top);
        endSpace = UIUtil.dp2px(end);
        bottomSpace = UIUtil.dp2px(bottom);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(startSpace, topSpace, endSpace, bottomSpace);
    }
}
