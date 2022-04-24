package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.DownloadRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentDownloadBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.DownloadInfoConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.widget.NormalCustomDialog;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ThreadUtil;

public class DownloadFragment extends BaseFragment<FragmentDownloadBinding> {
    DownloadRecyclerViewAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        adapter = new DownloadRecyclerViewAdapter(getActivity());
        loadData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getViewBinding().topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(view.getContext());
        getViewBinding().downloadRv.setAdapter(adapter);
        getViewBinding().downloadRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(downloadItemTouchHelpCallback);
        itemTouchHelper.attachToRecyclerView(getViewBinding().downloadRv);
    }

    private void loadData() {
        ThreadUtil.defer().when(DownloadInfoConfig::getDownloadInfo).done(result -> adapter.replaceAll(result));
    }

    private final ItemTouchHelper.Callback downloadItemTouchHelpCallback = new ItemTouchHelper.Callback() {

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.START | ItemTouchHelper.END);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            showDeleteConfirmDialog(viewHolder.getAdapterPosition());
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return super.isLongPressDragEnabled();
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return super.isItemViewSwipeEnabled();
        }
    };

    private void showDeleteConfirmDialog(int position) {
        int uiFlag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            uiFlag = uiFlag | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        NormalCustomDialog moreDialog = new NormalCustomDialog(requireActivity(), R.style.normalDialogTheme);
        moreDialog.setCancelAble(false)
                .setGravity(Gravity.CENTER)
                .setUiVisibility(uiFlag)
                .setDlg_layout(R.layout.dlg_delete_confirm)
                .setPositiveClickListener((dialog, view) -> {
                    adapter.removeItem(position);
                    dialog.dismiss();
                })
                .setNegativeClickListener((dialog, view, keyBack) -> adapter.notifyItemChanged(position))
                .createDialog();
        moreDialog.show();
    }
}
