package com.wisnu.photohunting.model;

public class Like {
    String name;
    String like_date;

    public Like(String name, String like_date) {
        this.name = name;
        this.like_date = like_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLikeDate() {
        return like_date;
    }

    public void setLikeDate(String like_date) {
        this.like_date = like_date;
    }
}
