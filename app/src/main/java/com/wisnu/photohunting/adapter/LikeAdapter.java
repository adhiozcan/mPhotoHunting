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
import com.wisnu.photohunting.model.Like;

import java.util.List;

/**
 * ---------------------------------------------------------------------------------------------
 * Dibawah ini adalah kelas adapter yang bertindak sebagai penghubung antara data - view.
 * Data             : List<Like> likeList
 * Layout item      : R.layout.item_row_like
 * ---------------------------------------------------------------------------------------------
 *
 * @author Wisnu Nugroho
 * @version 0.1
 * @since 19/10/2015
 */
public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.ViewHolder> {
    private List<Like> likeList;
    private int lastPosition = -1;

    public LikeAdapter(List<Like> likeList) {
        this.likeList = likeList;
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int itemLayout = R.layout.item_row_like;
        return new ViewHolder(LayoutInflater.from(context).inflate(itemLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = likeList.get(position).getName();
        String date = "like pada tanggal " + likeList.get(position).getLikeDate();

        holder.tv_name.setText(name);
        holder.tv_date.setText(date);

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return likeList != null ? likeList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_name;
        private final TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.item_row_like_tv_like);
            tv_date = (TextView) itemView.findViewById(R.id.item_row_like_tv_date);
        }
    }
}
