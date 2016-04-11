package com.wisnu.photohunting.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.wisnu.photohunting.R;

public class CategoryDetailActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorydetail);
        mToolbar = (Toolbar) findViewById(R.id.photo_support_toolbar);
        mToolbar.setTitle("Lokasi Detail Foto");
        setSupportActionBar(mToolbar);

    }
}
