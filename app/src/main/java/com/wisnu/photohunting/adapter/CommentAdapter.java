package com.wisnu.photohunting.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.wisnu.photohunting.R;
import com.wisnu.photohunting.model.Comment;

import java.util.List;

/**
 * ---------------------------------------------------------------------------------------------
 * Dibawah ini adalah kelas adapter yang bertindak sebagai penghubung antara data - view.
 * Layout item     : R.layout.item_row_comment
 * ---------------------------------------------------------------------------------------------
 *
 * @author Wisnu Nugroho
 * @version 0.1
 * @since 19/10/2015
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> commentList;
    private int lastPosition = -1;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int itemLayout = R.layout.item_row_comment;
        return new ViewHolder(LayoutInflater.from(context).inflate(itemLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = commentList.get(position).getName();
        String comment = commentList.get(position).getContent();
        String date = commentList.get(position).getDate();

        holder.tv_name.setText(name);
        holder.tv_comment.setText(comment);
        holder.tv_date.setText(date);

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return commentList != null ? commentList.size() : 0;
    }

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            Context context = view.getContext();
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.abc_fade_in);
            animation.setDuration(300);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_name;
        private final TextView tv_comment;
        private final TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.item_comment_tv_by);
            tv_comment = (TextView) itemView.findViewById(R.id.item_comment_tv_content);
            tv_date = (TextView) itemView.findViewById(R.id.item_comment_tv_date);
        }
    }
}
