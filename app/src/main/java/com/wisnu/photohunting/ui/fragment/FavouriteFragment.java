package com.wisnu.photohunting.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wisnu.photohunting.R;

/**
 * Created by Wahyu Adi S on 02/12/2015.
 */
public class FavouriteFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        return view;
    }
}
