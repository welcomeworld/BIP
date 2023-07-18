package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.RvVideoPlayItemBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;

public class VideoPlayItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    VideoListBean data;
    ItemClickListener itemClickListener;
    private int selectItemIndex = -1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(RvVideoPlayItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.binding.videoItemName.setText(data.getVideoBeanList().get(position).getTitle());
        itemHolder.binding.videoItemName.setSelected(position == selectItemIndex);
        itemHolder.binding.videoItemName.setOnClickListener(v -> {
            v.setSelected(true);
            int itemPosition = holder.getBindingAdapterPosition();
            selectItemIndex = itemPosition;
            if (itemClickListener != null) {
                itemClickListener.onItemClick(itemPosition);
            }
        });
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        RvVideoPlayItemBinding binding;

        public ItemHolder(RvVideoPlayItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public int getItemCount() {
        return data == null || data.getVideoBeanList() == null ? 0 : data.getVideoBeanList().size();
    }

    public VideoListBean getData() {
        return data;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(VideoListBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public void setSelectItemIndex(int selectItemIndex) {
        this.selectItemIndex = selectItemIndex;
    }
}
