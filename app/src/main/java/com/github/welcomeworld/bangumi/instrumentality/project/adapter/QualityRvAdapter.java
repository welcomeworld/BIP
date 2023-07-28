package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.RvQualityItemBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoQualityBean;

import java.util.ArrayList;
import java.util.List;

public class QualityRvAdapter extends RecyclerView.Adapter<QualityRvAdapter.QualityHolder> {
    List<VideoQualityBean> data = new ArrayList<>();
    public ItemClickListener itemClickListener;

    @NonNull
    @Override
    public QualityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QualityHolder(RvQualityItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QualityHolder holder, int position) {
        VideoQualityBean qualityBean = data.get(position);
        holder.binding.qualityItem.setText(qualityBean.getQuality());
        holder.binding.qualityItem.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position, qualityBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void replaceAll(List<VideoQualityBean> data) {
        if (isDataDiff(data)) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    private boolean isDataDiff(List<VideoQualityBean> data) {
        if (data.size() == this.data.size()) {
            for (int qualityIndex = 0; qualityIndex < data.size(); qualityIndex++) {
                if (!data.get(qualityIndex).getQuality().equals(this.data.get(qualityIndex).getQuality())) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

    static class QualityHolder extends RecyclerView.ViewHolder {
        RvQualityItemBinding binding;

        public QualityHolder(RvQualityItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ItemClickListener {
        void onItemClick(int position, VideoQualityBean qualityBean);
    }
}
