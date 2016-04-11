package com.wisnu.photohunting.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisnu.photohunting.ui.activity.CategoryActivity;
import com.wisnu.photohunting.R;
import com.wisnu.photohunting.model.Category;
import com.wisnu.photohunting.system.CategoryReference;
import com.wisnu.photohunting.ui.activity.CategoryListActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {
    private Activity activity;
    private List<Category> listCategory;

    public CategoryAdapter(Activity activity, List<Category> listCategory) {
        this.activity = activity;
        this.listCategory = listCategory;
    }

    @Override
    public CategoryAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_category, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Category category = listCategory.get(position);
        holder.categoryTitle.setText(category.getCategoryName());
        holder.categoryTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_category_building, 0, 0, 0);
        holder.categoryDesc.setText(category.getCategoryDescription());

        String title = holder.categoryTitle.getText().toString();
        System.out.println("title to select : " + title);
        if (title.equals(CategoryReference.CAT_BUILDING)) {
            holder.categoryTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_category_building, 0, 0, 0);
        } else if (title.equals(CategoryReference.CAT_HI)) {
            holder.categoryTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_category_hi, 0, 0, 0);
        } else if (title.equals(CategoryReference.CAT_HERITAGE)) {
            System.out.println("cat heritage true");
            holder.categoryTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_category_heritage, 0, 0, 0);
        } else if (title.equals(CategoryReference.CAT_LANDSCAPE)) {
            System.out.println("cat landscape true");
            holder.categoryTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_category_landscape, 0, 0, 0);
        } else if (title.equals(CategoryReference.CAT_MACRO)) {
            System.out.println("cat macro true");
            holder.categoryTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_category_macro, 0, 0, 0);
        } else if (title.equals(CategoryReference.CAT_STREET)) {
            System.out.println("cat street true");
            holder.categoryTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_category_street, 0, 0, 0);
        } else if (title.equals(CategoryReference.CAT_PREWED)) {
            System.out.println("cat prewed true");
            holder.categoryTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_category_wedding, 0, 0, 0);
        }
    }

    @Override
    public int getItemCount() {
        return listCategory != null ? listCategory.size() : 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView categoryTitle;
        TextView categoryDesc;
        TextView categoryExplore;

        public Holder(View itemView) {
            super(itemView);
            categoryTitle = (TextView) itemView.findViewById(R.id.category_tv_name);
            categoryDesc = (TextView) itemView.findViewById(R.id.category_tv_description);
            categoryExplore = (TextView) itemView.findViewById(R.id.category_btn_explore);
            categoryExplore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, CategoryListActivity.class));
                }
            });
        }
    }
}
