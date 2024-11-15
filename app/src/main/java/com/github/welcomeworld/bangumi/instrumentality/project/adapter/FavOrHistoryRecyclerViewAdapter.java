package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.VideoPlayActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.devbase.utils.ScreenUtil;
import com.github.welcomeworld.devbase.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class FavOrHistoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<HistoryBean> data = new ArrayList<>();
    Context context;

    private static final int ITEM_PORTRAIT_TYPE = 0;
    private static final int FOOTER_TYPE = 1;
    private static int ITEM_LANDSCAPE_TYPE = 2;
    Activity activity;
    boolean showHistory;

    public ItemListener itemListener = null;

    public FavOrHistoryRecyclerViewAdapter(Activity activity) {
        this(activity, false);
    }

    public FavOrHistoryRecyclerViewAdapter(Activity activity, boolean showHistory) {
        this.activity = activity;
        this.showHistory = showHistory;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void replaceAll(List<HistoryBean> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == FOOTER_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_home_footer, parent, false);
            return new FooterViewHolder(view);
        } else if (viewType == ITEM_PORTRAIT_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_fav_portrait_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_fav_landscape_item, parent, false);
        }
        context = parent.getContext();
        return new MyInnerViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER_TYPE;
        }
        HistoryBean historyData = data.get(position);
        return historyData.getVideoData().get(historyData.getSelectSourceIndex()).isCoverPortrait() ? ITEM_PORTRAIT_TYPE : ITEM_LANDSCAPE_TYPE;
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
        HistoryBean historyData = data.get(position);
        MyInnerViewHolder holder = (MyInnerViewHolder) viewHolder;
        VideoListBean currentData = historyData.getVideoData().get(historyData.getSelectSourceIndex());
        holder.titleView.setText(currentData.getTitle());
        if (StringUtil.isEmpty(currentData.getTag())) {
            holder.tagView.setVisibility(View.INVISIBLE);
        } else {
            holder.tagView.setVisibility(View.VISIBLE);
            holder.tagView.setText(currentData.getTag());
        }
        Glide.with(context).load(currentData.getCover()).transform(new RoundedCorners(ScreenUtil.dp2px(context, 4))).into(holder.coverView);
        if (currentData.getCurrentVideoBean() == null || currentData.getCurrentVideoBean().getDuration() == 0) {
            holder.durationView.setVisibility(View.INVISIBLE);
        } else {
            holder.durationView.setVisibility(View.VISIBLE);
            String duration = StringUtil.formatTime(currentData.getCurrentVideoBean().getDuration(), StringUtil.MILLISECOND);
            String playPosition = StringUtil.formatTime(currentData.getCurrentVideoBean().getPlayPosition(), StringUtil.MILLISECOND);
            String durationText = showHistory ? playPosition + "/" + duration : duration;
            holder.durationView.setText(durationText);
        }
        if (currentData.getVideoBeanList().size() > 1 && showHistory) {
            holder.itemIndexView.setVisibility(View.VISIBLE);
            holder.itemIndexView.setText(activity.getString(R.string.play_item_index, currentData.getSelectIndex() + 1));
        }
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(VideoPlayActivity.EXTRA_VIDEO_LIST_BEAN, (ArrayList<? extends Parcelable>) historyData.getVideoData());
            bundle.putInt(VideoPlayActivity.EXTRA_SOURCE_SELECT_INDEX, historyData.getSelectSourceIndex());
            bundle.putInt(VideoPlayActivity.EXTRA_VIDEO_TARGET_INDEX, currentData.getSelectIndex());
            IntentUtil.intentToVideoPlay(activity, bundle);
        });
        holder.itemView.setOnLongClickListener(v -> {
            if (itemListener != null) {
                itemListener.onLongClick(historyData);
                return true;
            }
            return false;
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
        TextView itemIndexView;

        public MyInnerViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.card_video_title);
            tagView = itemView.findViewById(R.id.card_video_label);
            coverView = itemView.findViewById(R.id.card_video_cover);
            durationView = itemView.findViewById(R.id.card_video_duration);
            itemIndexView = itemView.findViewById(R.id.card_video_item);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public FooterViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.footer_progress);
        }
    }

    public interface ItemListener {
        void onLongClick(HistoryBean history);
    }
}
