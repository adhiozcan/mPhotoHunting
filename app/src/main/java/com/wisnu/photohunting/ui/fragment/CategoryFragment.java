package com.wisnu.photohunting.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wisnu.photohunting.R;
import com.wisnu.photohunting.adapter.CategoryAdapter;
import com.wisnu.photohunting.model.Category;
import com.wisnu.photohunting.system.CategoryReference;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private CategoryAdapter adapter;
    private List<Category> listCategory;

    private void setupCategory() {
        Category landscape = new Category();
        landscape.setCategoryID("1");
        landscape.setCategoryName(CategoryReference.CAT_LANDSCAPE);
        landscape.setCategoryDescription("Mode Landscape merupakan mode pengambilan foto dengan style pemandangan alam terbuka");

        Category building = new Category();
        building.setCategoryID("2");
        building.setCategoryName(CategoryReference.CAT_BUILDING);
        building.setCategoryDescription("Mode Building merupakan mode pengambilan foto dengan style bangunan");

        Category street = new Category();
        street.setCategoryID("3");
        street.setCategoryName(CategoryReference.CAT_STREET);
        street.setCategoryDescription("Mode Street merupakan mode pengambilan foto dengan style jalanan");

        Category hi = new Category();
        hi.setCategoryID("4");
        hi.setCategoryName(CategoryReference.CAT_HI);
        hi.setCategoryDescription("Mode Human Interest merupakan mode pengambilan foto dengan style manusia sebagai objek utama");

        Category macro = new Category();
        macro.setCategoryID("5");
        macro.setCategoryName(CategoryReference.CAT_MACRO);
        macro.setCategoryDescription("Mode Macro merupakan mode pengambilan foto dengan style fokus object yang dalam");

        Category heritage = new Category();
        heritage.setCategoryID("6");
        heritage.setCategoryName(CategoryReference.CAT_HERITAGE);
        heritage.setCategoryDescription("Mode Heritage merupakan mode pengambilan foto dengan style kebudayaan");

        Category wedding = new Category();
        wedding.setCategoryID("7");
        wedding.setCategoryName(CategoryReference.CAT_PREWED);
        wedding.setCategoryDescription("Mode Prewedding merupakan mode pengambilan foto dengan style pernikahan");

        listCategory.add(landscape);
        listCategory.add(building);
        listCategory.add(street);
        listCategory.add(hi);
        listCategory.add(macro);
        listCategory.add(heritage);
        listCategory.add(wedding);
    }

    private void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        if (listCategory == null) listCategory = new ArrayList<>();
        adapter = new CategoryAdapter(getActivity(), listCategory);

        RecyclerView local_recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        local_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        local_recyclerView.setItemAnimator(new DefaultItemAnimator());
        local_recyclerView.setAdapter(adapter);

        setupCategory();
        updateList();

        return view;
    }
}
