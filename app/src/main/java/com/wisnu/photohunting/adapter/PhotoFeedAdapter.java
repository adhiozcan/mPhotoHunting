package com.wisnu.photohunting.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wisnu.photohunting.R;
import com.wisnu.photohunting.model.Photo;
import com.wisnu.photohunting.network.Request;

import java.util.List;

public class PhotoFeedAdapter extends RecyclerView.Adapter<PhotoFeedAdapter.Holder> {
    public static final int ITEM_COUNT_LIKE = 0;
    public static final int ITEM_COUNT_COMMENT = 1;
    public static final int ITEM_BUTTON_LIKE = 2;
    public static final int ITEM_BUTTON_COMMENT = 3;

    private onClickListener itemClicked;
    private List<Photo> listPhotoFeeds;

    public PhotoFeedAdapter(List<Photo> listPhotoFeeds) {
        this.listPhotoFeeds = listPhotoFeeds;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_photo_post, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Photo photoFeeds = listPhotoFeeds.get(position);
        String postedBy = "dipost oleh " + photoFeeds.getPhotoBy();
        String totalLike = photoFeeds.getPhotoTotalLike() + " likes";
        String totalComment = photoFeeds.getPhotoTotalComment() + " comments";

        holder.postName.setText(photoFeeds.getPhotoName());
        holder.postedBy.setText(postedBy);
        holder.photoDate.setText(photoFeeds.getPhotoDate());
        holder.photoDescription.setText(photoFeeds.getPhotoDescription());
        holder.photoCountLike.setText(totalLike);
        holder.photoCountComment.setText(totalComment);

        String photoUrl = Request.BASE_URL + Request.API_IMAGE + photoFeeds.getPhotoUrl();
        Picasso.with(holder.photoImage.getContext()).load(photoUrl).into(holder.photoImage);
        System.out.println(photoUrl);
    }

    @Override
    public int getItemCount() {
        return listPhotoFeeds == null ? 0 : listPhotoFeeds.size();
    }

    public void onItemClickedListener(onClickListener onClickListenerParam) {
        itemClicked = onClickListenerParam;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView postName;
        TextView postedBy;
        ImageView photoImage;
        TextView photoDate;
        TextView photoDescription;
        TextView photoCountLike;
        TextView photoCountComment;
        TextView btnLike;
        TextView btnComment;

        public Holder(View itemView) {
            super(itemView);
            postName = (TextView) itemView.findViewById(R.id.postfeed_tv_name);
            postedBy = (TextView) itemView.findViewById(R.id.postfeed_tv_postedby);
            photoImage = (ImageView) itemView.findViewById(R.id.postfeed_img_photoImage);
            photoDate = (TextView) itemView.findViewById(R.id.postfeed_tv_date);
            photoDescription = (TextView) itemView.findViewById(R.id.postfeed_tv_description);
            photoCountLike = (TextView) itemView.findViewById(R.id.postfeed_tv_like);
            photoCountComment = (TextView) itemView.findViewById(R.id.postfeed_tv_comment);
            btnLike = (TextView) itemView.findViewById(R.id.postfeed_btn_like);
            btnComment = (TextView) itemView.findViewById(R.id.postfeed_btn_comment);

            photoCountLike.setOnClickListener(this);
            photoCountComment.setOnClickListener(this);
            photoImage.setOnClickListener(this);
            btnLike.setOnClickListener(this);
            btnComment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.postfeed_tv_like:
                    itemClicked.onActionClickView(ITEM_COUNT_LIKE, listPhotoFeeds.get(getAdapterPosition()).getPhotoID());
                    break;
                case R.id.postfeed_tv_comment:
                    itemClicked.onActionClickView(ITEM_COUNT_COMMENT, listPhotoFeeds.get(getAdapterPosition()).getPhotoID());
                    break;
                case R.id.postfeed_btn_like:
                    itemClicked.onActionClickView(ITEM_BUTTON_LIKE, listPhotoFeeds.get(getAdapterPosition()).getPhotoID());
                    break;
                case R.id.postfeed_btn_comment:
                    itemClicked.onActionClickView(ITEM_BUTTON_COMMENT, listPhotoFeeds.get(getAdapterPosition()).getPhotoID());
                    break;
                case R.id.postfeed_img_photoImage:
                    itemClicked.onPhotoClickView(listPhotoFeeds.get(getAdapterPosition()));
                    break;
            }
        }
    }

    public interface onClickListener {
        void onActionClickView(int itemCode, String pid);

        void onPhotoClickView(Photo photo);
    }
}
