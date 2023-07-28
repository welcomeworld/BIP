package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.decoration.SpaceItemDecoration;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;

import java.util.List;

public class VideoSourceItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<VideoListBean> data;
    ItemClickListener itemClickListener;
    private int selectSourceIndex = -1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_video_play_source, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder) holder;
        if (data.size() <= 1 && data.get(0).getVideoBeanList().size() <= 1) {
            itemHolder.videoItemRecyclerView.setVisibility(View.GONE);
            itemHolder.videoSourceTitleView.setVisibility(View.GONE);
        } else {
            itemHolder.videoItemRecyclerView.setVisibility(View.VISIBLE);
            itemHolder.videoSourceTitleView.setVisibility(View.VISIBLE);
        }
        itemHolder.videoSourceTitleView.setText(data.get(position).getSeasonTitle());
        if (position != selectSourceIndex) {
            itemHolder.itemAdapter.setSelectItemIndex(-1);
        } else {
            itemHolder.itemAdapter.setSelectItemIndex(data.get(position).getSelectIndex());
        }
        itemHolder.itemAdapter.setData(data.get(position));
        itemHolder.itemAdapter.setItemClickListener(itemPosition -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(itemHolder.videoItemRecyclerView, position, itemPosition);
            }
        });
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        TextView videoSourceTitleView;
        RecyclerView videoItemRecyclerView;
        VideoPlayItemAdapter itemAdapter = new VideoPlayItemAdapter();

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            videoSourceTitleView = itemView.findViewById(R.id.video_play_source_title);
            videoItemRecyclerView = itemView.findViewById(R.id.video_play_item_rv);
            videoItemRecyclerView.setLayoutManager(new LinearLayoutManager(videoItemRecyclerView.getContext(), RecyclerView.HORIZONTAL, false));
            videoItemRecyclerView.setAdapter(itemAdapter);
            RecyclerView.ItemDecoration itemDecoration = new SpaceItemDecoration(8);
            videoItemRecyclerView.addItemDecoration(itemDecoration);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public List<VideoListBean> getData() {
        return data;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<VideoListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(RecyclerView rv, int sourcePosition, int position);
    }

    public int getSelectSourceIndex() {
        return selectSourceIndex;
    }

    public void setSelectSourceIndex(int selectSourceIndex) {
        int oldIndex = this.selectSourceIndex;
        this.selectSourceIndex = selectSourceIndex;
        notifyItemChanged(oldIndex);
    }
}
