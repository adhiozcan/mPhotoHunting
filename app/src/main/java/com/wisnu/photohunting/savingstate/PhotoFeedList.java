package com.wisnu.photohunting.savingstate;

import com.wisnu.photohunting.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoFeedList
{
    private static PhotoFeedList instance   = new PhotoFeedList();
    private        List<Photo>   mPhotoList = new ArrayList<>();

    private PhotoFeedList () {}

    public static PhotoFeedList getInstance ()
    {
        return instance;
    }

    public List<Photo> getPhotoList ()
    {
        return mPhotoList;
    }

    public void setPhotoList (List<Photo> photoListParam)
    {
        mPhotoList = photoListParam;
    }
}
