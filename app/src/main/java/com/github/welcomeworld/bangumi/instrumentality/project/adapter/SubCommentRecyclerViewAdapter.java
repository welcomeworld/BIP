package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.model.CommentBean;

import java.util.ArrayList;
import java.util.List;

public class SubCommentRecyclerViewAdapter extends RecyclerView.Adapter<SubCommentRecyclerViewAdapter.SubCommentViewHolder> {
    private final List<CommentBean.CommentDataBean> subComments = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setSubComments(List<CommentBean.CommentDataBean> subComments) {
        this.subComments.clear();
        this.subComments.addAll(subComments);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubCommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_sub_comment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubCommentViewHolder holder, int position) {
        Context context = holder.subCommentView.getContext();
        CommentBean.CommentDataBean commentData = subComments.get(position);
        String subCommentContent = context.getString(R.string.sub_reply_content, commentData.upperName, commentData.comment);
        holder.subCommentView.setText(Html.fromHtml(subCommentContent));
    }

    @Override
    public int getItemCount() {
        return subComments == null ? 0 : subComments.size();
    }

    static class SubCommentViewHolder extends RecyclerView.ViewHolder {
        TextView subCommentView;


        public SubCommentViewHolder(View itemView) {
            super(itemView);
            subCommentView = itemView.findViewById(R.id.comment_sub_item_content);
        }
    }
}
