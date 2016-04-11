package com.wisnu.photohunting.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wisnu.photohunting.R;
import com.wisnu.photohunting.adapter.ViewPagerAdapter;
import com.wisnu.photohunting.ui.fragment.FavouriteFragment;
import com.wisnu.photohunting.ui.fragment.MapFragment;
import com.wisnu.photohunting.ui.fragment.CategoryFragment;
import com.wisnu.photohunting.ui.fragment.PhotoFeedFragment;

public class HomeActivity extends AppCompatActivity
{
    Toolbar          toolbar;
    ViewPager        viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout        tabLayout;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Photo Feeds");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PhotoFeedFragment(), "Photo Feeds");
        viewPagerAdapter.addFragment(new MapFragment(), "Map Location");
        viewPagerAdapter.addFragment(new CategoryFragment(), "Explore Location");
        //viewPagerAdapter.addFragment(new FavouriteFragment(), "Favourite");

        viewPager = (ViewPager) findViewById(R.id.support_view_pager);
        viewPager.setAdapter(viewPagerAdapter);
        setOnViewPagerListener(viewPager, viewPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.support_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        //int[] tabIcon = new int[]{R.drawable.ic_tab_camera, R.drawable.ic_tab_location, R.drawable.ic_tab_category, R.drawable.ic_tab_favourite};
        int[] tabIcon = new int[]{R.drawable.ic_tab_camera, R.drawable.ic_tab_location, R.drawable.ic_tab_category};
        for (int i = 0; i < tabLayout.getTabCount(); i++)
        {
            tabLayout.getTabAt(i).setIcon(tabIcon[ i ]);
        }
    }

    private void setOnViewPagerListener (ViewPager viewPager, final ViewPagerAdapter viewPagerAdapter)
    {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected (int position)
            {
                String title = viewPagerAdapter.getItem(position).getArguments().getString("Title");
                toolbar.setTitle(title);
            }

            @Override
            public void onPageScrollStateChanged (int state)
            {

            }
        });
    }
}
