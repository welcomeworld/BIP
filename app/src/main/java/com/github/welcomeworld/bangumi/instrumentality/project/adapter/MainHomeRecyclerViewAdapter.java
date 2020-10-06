package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<VideoListBean> data = new ArrayList<>();
    Context context;

    public void replaceAll(List<VideoListBean> data){
        this.data.clear();
        if(data!=null){
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<VideoListBean> data){
        if(data!=null){
            int start = this.data.size();
            this.data.addAll(data);
            notifyItemRangeInserted(start,data.size());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_home_item,parent,false);
        context=parent.getContext();
        return new MyInnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyInnerViewHolder holder = (MyInnerViewHolder) viewHolder;
        VideoListBean currentData=data.get(position);
        holder.titleView.setText(currentData.getTitle());
        holder.tagView.setText(String.format(Locale.CHINA,"%s·%s","鬼畜","鬼畜调教"));
        Glide.with(context).load(currentData.getCover()).into(holder.coverView);
        holder.danmakuView.setText(StringUtil.formatNumber(199));
        holder.playView.setText(StringUtil.formatNumber(33));
        holder.durationView.setText(StringUtil.formatTime(69,StringUtil.SECOND));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent=new Intent("com.github.welcomeworld.simplebili.action.VIDEODETAIL");
                context.startActivity(playIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyInnerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.card_video_title)
        TextView titleView;
        @BindView(R.id.card_video_label)
        TextView tagView;
        @BindView(R.id.card_video_cover)
        ImageView coverView;
        @BindView(R.id.card_video_danmaku_num)
        TextView danmakuView;
        @BindView(R.id.card_video_play_num)
        TextView playView;
        @BindView(R.id.card_video_duration)
        TextView durationView;
        @BindView(R.id.recommend_card)
        FrameLayout cardView;
        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
