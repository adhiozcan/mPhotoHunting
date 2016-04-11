package com.wisnu.photohunting.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.wisnu.photohunting.R;

public class CategoryListActivity extends AppCompatActivity {
    public static String mCategorySelected = null;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorylist);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.photo_support_toolbar);
        mToolbar.setTitle("Daftar Lokasi Menarik");
        setSupportActionBar(mToolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_referensi);
    }
}
