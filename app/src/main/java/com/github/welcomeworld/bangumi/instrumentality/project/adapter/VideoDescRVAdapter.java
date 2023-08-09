package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.RvVideoPlaySourceHeaderBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.devbase.utils.StringUtil;

public class VideoDescRVAdapter extends RecyclerView.Adapter<VideoDescRVAdapter.HeaderItemHolder> {
    VideoListBean data;
    VideoDescRVAdapter.ActionClickListener actionClickListener;
    private boolean isFav = false;

    @NonNull
    @Override
    public VideoDescRVAdapter.HeaderItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoDescRVAdapter.HeaderItemHolder(RvVideoPlaySourceHeaderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoDescRVAdapter.HeaderItemHolder itemHolder, int position) {
        itemHolder.binding.videoPlayTitle.setText(data.getTitle());
        if (!StringUtil.isEmpty(data.getVideoListDes())) {
            itemHolder.binding.videoPlayDes.setText(data.getVideoListDes());
        }
        itemHolder.binding.videoPlayCollection.setSelected(isFav);
        itemHolder.binding.videoPlayCollection.setOnClickListener(v -> {
            isFav = !isFav;
            itemHolder.binding.videoPlayCollection.setSelected(isFav);
            if (actionClickListener != null) {
                actionClickListener.onFavClick();
            }
        });
        itemHolder.binding.videoPlayDownload.setOnClickListener(v -> {
            if (actionClickListener != null) {
                actionClickListener.onDownloadClick();
            }
        });
        itemHolder.binding.videoPlayBrowser.setOnClickListener(v -> {
            if (actionClickListener != null) {
                actionClickListener.onBrowserClick();
            }
        });
        itemHolder.binding.videoPlayRefresh.setOnClickListener(v -> {
            if (actionClickListener != null) {
                actionClickListener.onRefreshClick();
            }
        });
        itemHolder.binding.videoPlayComment.setOnClickListener(v -> {
            if (actionClickListener != null) {
                actionClickListener.onCommentClick();
            }
        });
    }

    static class HeaderItemHolder extends RecyclerView.ViewHolder {
        RvVideoPlaySourceHeaderBinding binding;

        public HeaderItemHolder(RvVideoPlaySourceHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : 1;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(VideoListBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public interface ActionClickListener {
        void onFavClick();

        void onDownloadClick();

        void onBrowserClick();

        void onRefreshClick();

        void onCommentClick();
    }

    public void setActionClickListener(VideoDescRVAdapter.ActionClickListener actionClickListener) {
        this.actionClickListener = actionClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFav(boolean fav) {
        isFav = fav;
        notifyDataSetChanged();
    }
}
