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
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.devbase.utils.StringUtil;

import java.util.List;

public class VideoSourceItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<VideoListBean> data;
    ItemClickListener itemClickListener;
    ActionClickListener actionClickListener;
    private boolean isFav = false;
    private int selectSourceIndex = -1;

    private static final int HEADER_TYPE = 233;
    private static final int ITEM_TYPE = 234;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            return new HeaderItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_video_play_source_header, parent, false));

        }
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_video_play_source, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        } else {
            return ITEM_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderItemHolder) {
            LogUtil.e("SourceAdapter", data.get(position).toString());
            HeaderItemHolder itemHolder = (HeaderItemHolder) holder;
            itemHolder.videoPlayTitle.setText(data.get(position).getTitle());
            if (!StringUtil.isEmpty(data.get(position).getVideoListDes())) {
                itemHolder.videoPlayDes.setText(data.get(position).getVideoListDes());
            }
            itemHolder.favView.setSelected(isFav);
            itemHolder.favView.setOnClickListener(v -> {
                isFav = !isFav;
                itemHolder.favView.setSelected(isFav);
                if (actionClickListener != null) {
                    actionClickListener.onFavClick();
                }
            });
            itemHolder.downloadView.setOnClickListener(v -> {
                if (actionClickListener != null) {
                    actionClickListener.onDownloadClick();
                }
            });
            itemHolder.browserView.setOnClickListener(v -> {
                if (actionClickListener != null) {
                    actionClickListener.onBrowserClick();
                }
            });
            itemHolder.refreshView.setOnClickListener(v -> {
                if (actionClickListener != null) {
                    actionClickListener.onRefreshClick();
                }
            });
        }
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

    static class HeaderItemHolder extends ItemHolder {
        TextView videoPlayTitle;
        TextView videoPlayDes;
        TextView favView;
        TextView downloadView;
        TextView browserView;
        TextView refreshView;

        public HeaderItemHolder(@NonNull View itemView) {
            super(itemView);
            videoPlayTitle = itemView.findViewById(R.id.video_play_title);
            videoPlayDes = itemView.findViewById(R.id.video_play_des);
            favView = itemView.findViewById(R.id.video_play_collection);
            downloadView = itemView.findViewById(R.id.video_play_download);
            browserView = itemView.findViewById(R.id.video_play_browser);
            refreshView = itemView.findViewById(R.id.video_play_refresh);

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

    public interface ActionClickListener {
        void onFavClick();

        void onDownloadClick();

        void onBrowserClick();

        void onRefreshClick();

    }

    public void setActionClickListener(ActionClickListener actionClickListener) {
        this.actionClickListener = actionClickListener;
    }

    public int getSelectSourceIndex() {
        return selectSourceIndex;
    }

    public void setSelectSourceIndex(int selectSourceIndex) {
        int oldIndex = this.selectSourceIndex;
        this.selectSourceIndex = selectSourceIndex;
        notifyItemChanged(oldIndex);
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
