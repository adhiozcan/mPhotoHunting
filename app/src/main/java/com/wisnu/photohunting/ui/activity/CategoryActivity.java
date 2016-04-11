package com.wisnu.photohunting.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wisnu.photohunting.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar local_Toolbar = (Toolbar) findViewById(R.id.photo_support_toolbar);
        local_Toolbar.setTitle("Location Category");
        setSupportActionBar(local_Toolbar);

        TextView mSeeMore = (TextView) findViewById(R.id.button_look_more);
        mSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, CategoryListActivity.class));
            }
        });
    }
}
