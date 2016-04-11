package com.wisnu.photohunting.savingstate;

import com.wisnu.photohunting.model.User;

public class UserData
{
    private static UserData instance = new UserData();
    private        User     user     = new User();

    private UserData () {}

    public static UserData getInstance ()
    {
        return instance;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }
}
