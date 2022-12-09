package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.constants.OpenSourceConstant;


public class GratitudeRecyclerViewAdapter extends RecyclerView.Adapter<GratitudeRecyclerViewAdapter.MyInnerViewHolder> {
    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_gratitude, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        holder.nameView.setText(OpenSourceConstant.openSourceNames[position]);
        holder.desView.setText(OpenSourceConstant.openSourceDesc[position]);
    }

    @Override
    public int getItemCount() {
        return OpenSourceConstant.openSourceNames.length;
    }

    static class MyInnerViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView desView;

        public MyInnerViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.lib_title);
            desView = itemView.findViewById(R.id.lib_desc);
        }
    }
}