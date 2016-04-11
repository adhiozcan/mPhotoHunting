package com.wisnu.photohunting.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    /**
     * Define response handle when call ResRequest.Basic service
     */
    public static class Basic {
        String status;
        String message;
        String data;

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public String getData() {
            return data;
        }
    }

    /**
     * Define response handle when call RestRequest.User service
     */
    public static class User {
        @SerializedName("data")
        List<com.wisnu.photohunting.model.User> userData;

        public List<com.wisnu.photohunting.model.User> getUserData() {
            return userData;
        }
    }

    /**
     * Define response handle when call RestRequest.Category service
     */
    public static class Category {
        @SerializedName("data")
        List<com.wisnu.photohunting.model.Category> listCategory;

        public List<com.wisnu.photohunting.model.Category> getListCategory() {
            return listCategory;
        }
    }

    /**
     * Define response handle when call RestRequest.Photo service
     */
    public static class Photo {
        @SerializedName("data")
        List<com.wisnu.photohunting.model.Photo> listPhotoFeeds;

        public List<com.wisnu.photohunting.model.Photo> getListPhotoFeeds() {
            return listPhotoFeeds;
        }
    }

    /**
     * Define reponse handle when call RestRequest.Photo "Like" service
     */
    public static class Like {
        @SerializedName("data")
        List<com.wisnu.photohunting.model.Like> listLike;

        public List<com.wisnu.photohunting.model.Like> getListLike() {
            return listLike;
        }
    }

    /**
     * Define response handle when call RestRequest.Photo "Comment" service
     */
    public static class Comment {
        @SerializedName("data")
        List<com.wisnu.photohunting.model.Comment> listComment;

        public List<com.wisnu.photohunting.model.Comment> getListComment() {
            return listComment;
        }
    }
}
