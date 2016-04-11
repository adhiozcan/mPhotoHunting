package com.wisnu.photohunting.model;

public class Comment {
    String name;
    String comment_content;
    String comment_date;

    public Comment(String name, String comment_content, String comment_date) {
        this.name = name;
        this.comment_content = comment_content;
        this.comment_date = comment_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return comment_content;
    }

    public void setContent(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getDate() {
        return comment_date;
    }

    public void setDate(String comment_date) {
        this.comment_date = comment_date;
    }
}
