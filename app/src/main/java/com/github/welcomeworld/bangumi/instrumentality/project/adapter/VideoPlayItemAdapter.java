package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;

public class VideoPlayItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    VideoListBean data;
    ItemClickListener itemClickListener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_video_play_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.videoItemNameView.setText(data.getVideoBeanList().get(position).getTitle());
        itemHolder.videoItemNameView.setSelected(position == data.getSelectIndex());
        itemHolder.videoItemNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                if(itemClickListener!=null){
                    itemClickListener.onItemClick(position);
                }
            }
        });
    }

    static class ItemHolder extends RecyclerView.ViewHolder{
        TextView videoItemNameView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            videoItemNameView = itemView.findViewById(R.id.video_item_name);
        }
    }

    @Override
    public int getItemCount() {
        return data == null||data.getVideoBeanList() == null?0:data.getVideoBeanList().size();
    }

    public VideoListBean getData() {
        return data;
    }

    public void setData(VideoListBean data) {
        this.data = data;
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        public void onItemClick(int position);
    }
}
