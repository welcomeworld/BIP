package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.DownloadRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.decoration.GridSpaceItemDecoration;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentDownloadBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.DownloadInfoConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.widget.MaterialDialogBuilder;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

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
        getVB().downloadRv.setAdapter(adapter);
        int listColumn = getResources().getInteger(R.integer.list_column);
        if (listColumn == 1) {
            getVB().downloadRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            getVB().downloadRv.setLayoutManager(new GridLayoutManager(getActivity(), listColumn));
        }
        RecyclerView.ItemDecoration itemDecoration = new GridSpaceItemDecoration(8);
        getVB().downloadRv.addItemDecoration(itemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(downloadItemTouchHelpCallback);
        itemTouchHelper.attachToRecyclerView(getVB().downloadRv);
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
        MaterialDialogBuilder dialogBuilder = new MaterialDialogBuilder(requireActivity());
        dialogBuilder.setTitle(R.string.delete_record)
                .setMessage(R.string.delete_record_confirm)
                .setPositiveButton(R.string.delete, (dialog, which) -> adapter.removeItem(position))
                .setNegativeButton(R.string.cancel, (dialog, which) -> adapter.notifyItemChanged(position))
                .show();
    }
}
