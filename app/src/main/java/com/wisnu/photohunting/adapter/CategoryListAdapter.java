package com.wisnu.photohunting.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wisnu.photohunting.R;
import com.wisnu.photohunting.model.Photo;
import com.wisnu.photohunting.network.Request;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.Holder> {
    List<Photo> listPhotoCategory = new ArrayList<>();

    public CategoryListAdapter(List<Photo> listPhotoCategory) {
        this.listPhotoCategory = listPhotoCategory;
    }

    @Override
    public CategoryListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_category, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
