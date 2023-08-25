package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.RvVideoLandscapeItemBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.RvVideoPortraitItemBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.VideoPlayActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.devbase.utils.ScreenUtil;
import com.github.welcomeworld.devbase.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchResultRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<VideoListBean> data = new ArrayList<>();

    private static final int ITEM_PORTRAIT_TYPE = 0;
    private static final int FOOTER_TYPE = 1;
    private static final int ITEM_LANDSCAPE_TYPE = 2;
    Activity activity;

    public SearchResultRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void replaceAll(List<VideoListBean> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<VideoListBean> data) {
        if (data != null) {
            int start = this.data.size();
            this.data.addAll(data);
            notifyItemRangeInserted(start, data.size());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == FOOTER_TYPE) {
            return new FooterViewHolder(inflater.inflate(R.layout.rv_home_footer, parent, false));
        } else if (viewType == ITEM_PORTRAIT_TYPE) {
            return new PortraitHolder(RvVideoPortraitItemBinding.inflate(inflater, parent, false));
        } else {
            return new LandscapeHolder(RvVideoLandscapeItemBinding.inflate(inflater, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER_TYPE;
        }
        return data.get(position).isCoverPortrait() ? ITEM_PORTRAIT_TYPE : ITEM_LANDSCAPE_TYPE;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (position == data.size()) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) viewHolder;
            if (data.size() == 0) {
                footerViewHolder.itemView.setVisibility(View.GONE);
            } else {
                footerViewHolder.itemView.setVisibility(View.VISIBLE);
            }
            return;
        }

        MyInnerViewHolder holder = (MyInnerViewHolder) viewHolder;
        VideoListBean currentData = data.get(position);
        holder.titleView.setText(currentData.getTitle());
        if (StringUtil.isEmpty(currentData.getTag())) {
            holder.tagView.setVisibility(View.INVISIBLE);
        } else {
            holder.tagView.setVisibility(View.VISIBLE);
            holder.tagView.setText(currentData.getTag());
        }
        Glide.with(holder.coverView).load(currentData.getCover()).transform(new RoundedCorners(ScreenUtil.dp2px(BIPApp.getInstance(), 4))).into(holder.coverView);
        if (currentData.getCurrentVideoBean() == null || currentData.getCurrentVideoBean().getDuration() == 0) {
            holder.durationView.setVisibility(View.INVISIBLE);
        } else {
            holder.durationView.setVisibility(View.VISIBLE);
            holder.durationView.setText(StringUtil.formatTime(currentData.getCurrentVideoBean().getDuration(), StringUtil.MILLISECOND));
        }
        holder.itemView.findViewById(R.id.item_content).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            ArrayList<VideoListBean> videoListBeans = new ArrayList<>();
            videoListBeans.add(currentData);
            bundle.putParcelableArrayList(VideoPlayActivity.EXTRA_VIDEO_LIST_BEAN, videoListBeans);
            IntentUtil.intentToVideoPlay(activity, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
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

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public FooterViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.footer_progress);
        }
    }
}
