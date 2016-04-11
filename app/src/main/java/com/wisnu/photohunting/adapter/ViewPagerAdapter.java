package com.wisnu.photohunting.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wahyu Adi S on 29/11/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    private boolean        iconOption   = true;
    private List<Fragment> fragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return iconOption ? "" : fragmentList.get(position).getArguments().getString("Title");
    }

    @Override
    public int getCount()
    {
        return fragmentList != null ? fragmentList.size() : 0;
    }

    public void setIcon(boolean option)
    {
        this.iconOption = option;
    }

    public void addFragment(Fragment fragment, String title)
    {
        Bundle arguments = new Bundle();
        arguments.putString("Title", title);
        fragment.setArguments(arguments);
        fragmentList.add(fragment);
    }
}
