package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.RvHotSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class HotSearchRVAdapter extends RecyclerView.Adapter<HotSearchRVAdapter.HotSearchHolder> {
    List<String> data = new ArrayList<>();
    public HotSearchClickListener hotSearchClickListener;

    @NonNull
    @Override
    public HotSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotSearchHolder(RvHotSearchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotSearchHolder holder, int position) {
        String hotSearch = data.get(position);
        holder.binding.getRoot().setText(BIPApp.getInstance().getString(R.string.hot_search_placeholder, position + 1, hotSearch));
        holder.binding.getRoot().setOnClickListener(v -> {
            if (hotSearchClickListener != null) {
                hotSearchClickListener.onHotSearchClick(hotSearch);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void replaceAll(List<String> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    static class HotSearchHolder extends RecyclerView.ViewHolder {
        RvHotSearchBinding binding;

        public HotSearchHolder(RvHotSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface HotSearchClickListener {
        void onHotSearchClick(String hotSearch);
    }
}
