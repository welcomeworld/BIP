package com.github.welcomeworld.bangumi.instrumentality.project.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.model.CommentBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CommentBean data;
    private LoadSubCommentCallback loadSubCommentCallback;
    private static final int FOOTER_TYPE = 1;
    public boolean noMoreData;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(CommentBean data) {
        this.data = data;
        noMoreData = false;
        notifyDataSetChanged();
    }

    public void addData(CommentBean data) {
        if (!this.data.commentId.equals(data.commentId)) {
            return;
        }
        int size = data.comments.size();
        int start = this.data.comments.size();
        this.data.comments.addAll(data.comments);
        notifyItemRangeInserted(start, size);
    }

    public void setLoadSubCommentCallback(LoadSubCommentCallback loadSubCommentCallback) {
        this.loadSubCommentCallback = loadSubCommentCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == FOOTER_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_comment_footer, parent, false);
            return new FooterViewHolder(view);
        }
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_comment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof FooterViewHolder) {
            if (noMoreData) {
                ((FooterViewHolder) viewHolder).noMoreView.setVisibility(View.VISIBLE);
                ((FooterViewHolder) viewHolder).progressBar.setVisibility(View.GONE);
            } else {
                ((FooterViewHolder) viewHolder).noMoreView.setVisibility(View.GONE);
                ((FooterViewHolder) viewHolder).progressBar.setVisibility(View.VISIBLE);
            }
        } else if (viewHolder instanceof CommentViewHolder) {
            CommentViewHolder holder = (CommentViewHolder) viewHolder;
            CommentBean.CommentDataBean commentData = data.comments.get(position);
            holder.upperNameView.setText(commentData.upperName);
            holder.commentContentView.setText(commentData.comment);
            holder.commentUpTimeView.setText(formatTime(holder.commentUpTimeView.getContext(), commentData.upTime));
            Glide.with(holder.upperAvatarView).load(commentData.avatar).transform(new RoundedCorners(16)).into(holder.upperAvatarView);
            if (commentData.subComment != null) {
                if (commentData.subCommentCount > commentData.subCommentExpandSize) {
                    if (commentData.subCommentExpand) {
                        int currentPage = commentData.currentSubPage;
                        int pageSize = commentData.subCommentPageSize;
                        int start = (currentPage - 1) * pageSize;
                        int end = Math.min(commentData.subComment.size(), currentPage * pageSize);
                        end = (int) Math.min(end, commentData.subCommentCount);
                        start = commentData.subComment.size() <= start ? 0 : start;
                        holder.subCommentAdapter.setSubComments(commentData.subComment.subList(start, end));
                        holder.subCommentPageView.setText(holder.upperAvatarView.getContext().getString(R.string.sub_reply_page, commentData.currentSubPage));
                        holder.subCommentPreviousView.setEnabled(commentData.currentSubPage != 1);
                        long maxPage = Math.round(commentData.subCommentCount * 1.0 / commentData.subCommentPageSize + 0.5);
                        holder.subCommentNextView.setEnabled(commentData.currentSubPage != maxPage);
                        holder.subCommentAllView.setVisibility(View.GONE);
                        holder.subCommentCollapseView.setVisibility(View.VISIBLE);
                        if (commentData.subCommentCount > commentData.subCommentPageSize) {
                            holder.subCommentPreviousView.setVisibility(View.VISIBLE);
                            holder.subCommentNextView.setVisibility(View.VISIBLE);
                            holder.subCommentPageView.setVisibility(View.VISIBLE);
                        } else {
                            holder.subCommentPreviousView.setVisibility(View.GONE);
                            holder.subCommentNextView.setVisibility(View.GONE);
                            holder.subCommentPageView.setVisibility(View.GONE);
                        }
                    } else {
                        int end = Math.min(commentData.subComment.size(), commentData.subCommentExpandSize);
                        holder.subCommentAdapter.setSubComments(commentData.subComment.subList(0, end));
                        holder.subCommentAllView.setVisibility(View.VISIBLE);
                        holder.subCommentCollapseView.setVisibility(View.GONE);
                        holder.subCommentPreviousView.setVisibility(View.GONE);
                        holder.subCommentNextView.setVisibility(View.GONE);
                        holder.subCommentPageView.setVisibility(View.GONE);

                    }
                    holder.subCommentAllView.setText(holder.subCommentAllView.getContext().getString(R.string.reply, commentData.subCommentCount));
                    holder.subCommentAllView.setOnClickListener(v -> {
                        commentData.subCommentExpand = true;
                        if (commentData.subComment.size() < commentData.subCommentPageSize && !commentData.subLoading) {
                            if (loadSubCommentCallback != null) {
                                commentData.subLoading = true;
                                loadSubCommentCallback.loadSubComment(commentData);
                            }
                        } else {
                            notifyItemChanged(holder.getBindingAdapterPosition());
                        }
                    });
                    holder.subCommentCollapseView.setOnClickListener(v -> {
                        commentData.subCommentExpand = false;
                        notifyItemChanged(holder.getBindingAdapterPosition());
                    });
                    holder.subCommentPreviousView.setOnClickListener(v -> {
                        commentData.currentSubPage--;
                        notifyItemChanged(holder.getBindingAdapterPosition());
                    });
                    holder.subCommentNextView.setOnClickListener(v -> {
                        commentData.currentSubPage++;
                        if (commentData.subComment.size() < commentData.currentSubPage * commentData.subCommentPageSize && !commentData.subLoading && commentData.subComment.size() < commentData.subCommentCount) {
                            if (loadSubCommentCallback != null) {
                                commentData.subLoading = true;
                                loadSubCommentCallback.loadSubComment(commentData);
                            }
                        }
                        notifyItemChanged(holder.getBindingAdapterPosition());
                    });
                } else {
                    holder.subCommentAdapter.setSubComments(commentData.subComment);
                    holder.subCommentAllView.setVisibility(View.GONE);
                    holder.subCommentCollapseView.setVisibility(View.GONE);
                    holder.subCommentPreviousView.setVisibility(View.GONE);
                    holder.subCommentNextView.setVisibility(View.GONE);
                    holder.subCommentPageView.setVisibility(View.GONE);
                }
                holder.commentSubLayout.setVisibility(View.VISIBLE);
            } else {
                holder.commentSubLayout.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null || data.comments.size() == 0 ? 0 : data.comments.size() + 1;
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        SubCommentRecyclerViewAdapter subCommentAdapter = new SubCommentRecyclerViewAdapter();
        RecyclerView subCommentRv;
        ImageView upperAvatarView;
        TextView upperNameView;
        TextView commentContentView;
        TextView commentUpTimeView;
        LinearLayout commentSubLayout;
        TextView subCommentAllView;
        TextView subCommentCollapseView;
        ImageButton subCommentPreviousView;
        ImageButton subCommentNextView;
        TextView subCommentPageView;


        public CommentViewHolder(View itemView) {
            super(itemView);
            upperNameView = itemView.findViewById(R.id.comment_upper_name_view);
            commentContentView = itemView.findViewById(R.id.comment_content_view);
            upperAvatarView = itemView.findViewById(R.id.comment_upper_avatar_view);
            commentUpTimeView = itemView.findViewById(R.id.comment_up_time_view);
            commentSubLayout = itemView.findViewById(R.id.comment_sub_layout);
            subCommentAllView = itemView.findViewById(R.id.comment_sub_all);
            subCommentRv = itemView.findViewById(R.id.comment_sub_rv);
            subCommentRv.setAdapter(subCommentAdapter);
            subCommentRv.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            subCommentCollapseView = itemView.findViewById(R.id.comment_sub_collapse);
            subCommentPreviousView = itemView.findViewById(R.id.comment_sub_previous);
            subCommentNextView = itemView.findViewById(R.id.comment_sub_next);
            subCommentPageView = itemView.findViewById(R.id.comment_sub_page);
        }
    }

    private String formatTime(Context context, long time) {
        long diff = System.currentTimeMillis() - time;
        if (diff < 60 * 60 * 1000) {
            return diff / 1000 / 60 + context.getString(R.string.minute_ago);
        } else if (diff < 24 * 60 * 60 * 1000) {
            return diff / 1000 / 60 / 60 + context.getString(R.string.hour_ago);
        } else {
            Calendar calendar = new GregorianCalendar();
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            if (calendar.getTimeInMillis() < time) {
                return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date(time));
            }
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(new Date(time));
        }
    }

    public interface LoadSubCommentCallback {
        void loadSubComment(CommentBean.CommentDataBean parentComment);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER_TYPE;
        }
        return 0;
    }

    public void onLoadSubCommentSuccess(CommentBean.CommentDataBean parentComment) {
        int index = data.comments.indexOf(parentComment);
        if (index >= 0) {
            notifyItemChanged(index);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView noMoreView;

        public FooterViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.footer_progress);
            noMoreView = itemView.findViewById(R.id.footer_no_more);
        }
    }
}
