package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.RvVideoLandscapeItemBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.RvVideoPortraitItemBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.DownloadInfoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.DownloadInfoConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.DownloadManager;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.VideoPlayActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.devbase.utils.ScreenUtil;
import com.github.welcomeworld.devbase.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class DownloadRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<DownloadInfoBean> data = new ArrayList<>();
    private static final int ITEM_PORTRAIT_TYPE = 0;
    private static final int ITEM_LANDSCAPE_TYPE = 2;
    Activity activity;

    public DownloadRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void replaceAll(List<DownloadInfoBean> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        if (position >= 0 && position < data.size()) {
            DownloadInfoBean removeData = data.remove(position);
            DownloadInfoConfig.deleteDownloadInfo(removeData);
            notifyItemRemoved(position);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_PORTRAIT_TYPE) {
            return new PortraitHolder(RvVideoPortraitItemBinding.inflate(inflater, parent, false));
        } else {
            return new LandscapeHolder(RvVideoLandscapeItemBinding.inflate(inflater, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        DownloadInfoBean downloadInfo = data.get(position);
        return downloadInfo.isCoverPortrait() ? ITEM_PORTRAIT_TYPE : ITEM_LANDSCAPE_TYPE;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        DownloadInfoBean downloadInfo = data.get(position);
        MyInnerViewHolder holder = (MyInnerViewHolder) viewHolder;
        holder.titleView.setText(downloadInfo.getTitle());
        if (downloadInfo.getDownloadState() == DownloadInfoBean.DOWNLOADING) {
            holder.tagView.setText(R.string.downloading);
        } else if (downloadInfo.getDownloadState() == DownloadInfoBean.PREPARED) {
            holder.tagView.setText(R.string.prepare);
        } else if (downloadInfo.getDownloadState() == DownloadInfoBean.COMPLETE) {
            holder.tagView.setText(StringUtil.formatFlow(downloadInfo.getContentLength()));
        } else if (downloadInfo.getDownloadState() == DownloadInfoBean.ERROR) {
            holder.tagView.setText(R.string.error);
        } else if (downloadInfo.getDownloadState() == DownloadInfoBean.PAUSE) {
            holder.tagView.setText(R.string.paused);
        }
        Glide.with(holder.coverView)
                .load(downloadInfo.getCover())
                .transform(new RoundedCorners(ScreenUtil.dp2px(BIPApp.getInstance(), 4)))
                .into(holder.coverView);
        if (downloadInfo.getDuration() == 0) {
            holder.durationView.setVisibility(View.INVISIBLE);
        } else {
            holder.durationView.setVisibility(View.VISIBLE);
            holder.durationView.setText(StringUtil.formatTime(downloadInfo.getDuration(), StringUtil.MILLISECOND));
        }
        holder.itemView.setOnClickListener(v -> {
            if (downloadInfo.getDownloadState() == DownloadInfoBean.COMPLETE) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(VideoPlayActivity.EXTRA_VIDEO_LIST_BEAN, (ArrayList<? extends Parcelable>) downloadInfo.getVideoData());
                bundle.putInt(VideoPlayActivity.EXTRA_SOURCE_SELECT_INDEX, downloadInfo.getSelectSourceIndex());
                bundle.putInt(VideoPlayActivity.EXTRA_VIDEO_TARGET_INDEX, downloadInfo.getDownloadTargetPosition());
                IntentUtil.intentToVideoPlay(activity, bundle);
            } else if (downloadInfo.getDownloadState() == DownloadInfoBean.DOWNLOADING) {
                DownloadManager.pauseDownload(downloadInfo);
            } else if (downloadInfo.getDownloadState() == DownloadInfoBean.PAUSE || downloadInfo.getDownloadState() == DownloadInfoBean.ERROR) {
                DownloadManager.download(downloadInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyInnerViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView tagView;
        ImageView coverView;
        TextView durationView;

        public MyInnerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class LandscapeHolder extends MyInnerViewHolder {
        RvVideoLandscapeItemBinding binding;

        public LandscapeHolder(RvVideoLandscapeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            titleView = binding.cardVideoTitle;
            tagView = binding.cardVideoLabel;
            coverView = binding.cardVideoCover;
            durationView = binding.cardVideoDuration;
        }
    }

    public static class PortraitHolder extends MyInnerViewHolder {
        RvVideoPortraitItemBinding binding;

        public PortraitHolder(RvVideoPortraitItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            titleView = binding.cardVideoTitle;
            tagView = binding.cardVideoLabel;
            coverView = binding.cardVideoCover;
            durationView = binding.cardVideoDuration;
        }
    }
}
