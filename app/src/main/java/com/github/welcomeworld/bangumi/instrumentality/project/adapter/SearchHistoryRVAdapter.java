package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.RvSearchHistoryBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchHistoryRVAdapter extends RecyclerView.Adapter<SearchHistoryRVAdapter.SearchHistoryHolder> {
    List<String> data = new ArrayList<>();
    public SearchHistoryClickListener searchHistoryClickListener;

    @NonNull
    @Override
    public SearchHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchHistoryHolder(RvSearchHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryHolder holder, int position) {
        String history = data.get(position);
        holder.binding.getRoot().setText(history);
        holder.binding.getRoot().setOnClickListener(v -> {
            if (searchHistoryClickListener != null) {
                searchHistoryClickListener.onHistoryClick(history);
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

    static class SearchHistoryHolder extends RecyclerView.ViewHolder {
        RvSearchHistoryBinding binding;

        public SearchHistoryHolder(RvSearchHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface SearchHistoryClickListener {
        void onHistoryClick(String history);
    }
}
