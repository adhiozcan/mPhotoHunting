package com.wisnu.photohunting.network;

import retrofit2.Call;

public class Request {
    /*public static final String BASE_URL    = "http://photohunting-technologist.rhcloud.com";
    public static final String API_SERVICE = "/application/api.php/";
    public static final String API_IMAGE   = "/application/photos/";*/

    public static final String BASE_URL    = "http://192.168.2.134";
    public static final String API_SERVICE = "/photohunt/application/api.php/";
    public static final String API_IMAGE   = "/photohunt/photos/";

    private static final API CallAPI = RestBuilder.openService(API.class);

    public static class User {
        private final static String LOGIN         = "user_login";
        private final static String REGISTER      = "user_register";
        private final static String UPDATE_STATUS = "user_update_status";

        public static Call<Response.User> login(String email, String password) {
            return CallAPI.user_login(LOGIN, email, password);
        }

        public static Call<Response.Basic> register(String userName, String userEmail, String userPassword, String userPhoto) {
            return CallAPI.user_register(REGISTER, userName, userEmail, userPassword, userPhoto);
        }

        public static Call<Response.Basic> update_status(String uid, String status) {
            return CallAPI.user_update_status(UPDATE_STATUS, uid, status);
        }
    }

    public static class Photo {
        private static final String GET_PHOTO_ALL              = "photo_get_all";
        private static final String GET_PHOTO_WITH_UID         = "photo_get_with_uid";
        private final static String GET_PHOTO_WITH_PID         = "photo_get_with_pid";
        private final static String GET_PHOTO_WITH_CID         = "photo_get_with_cid";
        private final static String GET_PHOTO_LIKE_WITH_PID    = "photo_get_likes_with_pid";
        private final static String GET_PHOTO_COMMENT_WITH_PID = "photo_get_comments_with_pid";
        private final static String INSERT_NEW_PHOTO           = "photo_insert_new";
        private final static String INSERT_PHOTO_LIKE          = "photo_insert_new_like";
        private final static String INSERT_PHOTO_COMMENT       = "photo_insert_new_comment";
        private final static String DELETE_PHOTO               = "photo_delete";
        private final static String DELETE_PHOTO_LIKE          = "photo_delete_like";
        private final static String DELETE_PHOTO_COMMENT       = "photo_delete_comment";
        private final static String UPLOAD_NEW_PHOTO           = "photo_insert_new_upload";

        public static Call<Response.Photo> get_all() {
            return CallAPI.photo_get_all(GET_PHOTO_ALL);
        }

        public static Call<Response.Photo> get_photo_with_uid(String uid) {
            return CallAPI.photo_get_with_uid(GET_PHOTO_WITH_UID, uid);
        }

        public static Call<Response.Photo> get_photo_with_pid(String pid) {
            return CallAPI.photo_get_with_pid(GET_PHOTO_WITH_PID, pid);
        }

        public static Call<Response.Photo> get_photo_with_cid(String cid) {
            return CallAPI.photo_get_with_cid(GET_PHOTO_WITH_CID, cid);
        }

        public static Call<Response.Like> get_photo_like_with_pid(String pid) {
            return CallAPI.photo_get_like_with_pid(GET_PHOTO_LIKE_WITH_PID, pid);
        }

        public static Call<Response.Comment> get_photo_comment_with_pid(String pid) {
            return CallAPI.photo_get_comment_with_pid(GET_PHOTO_COMMENT_WITH_PID, pid);
        }

        public static Call<Response.Basic> insert_new(String photoName, String photoDescription, String photoUrl, String photoLocation, String photoLatitude, String photoLongitude, String uid, String cid) {
            return CallAPI.photo_insert_new(INSERT_NEW_PHOTO, photoName, photoDescription, photoUrl, photoLocation, photoLatitude, photoLongitude, uid, cid);
        }

        public static void insert_new_upload() {

        }

        public static Call<Response.Basic> add_like(String pid, String uid) {
            return CallAPI.photo_like(INSERT_PHOTO_LIKE, pid, uid);
        }

        public static Call<Response.Basic> add_comment(String comment, String pid, String uid) {
            return CallAPI.photo_insert_comment(INSERT_PHOTO_COMMENT, comment, pid, uid);
        }

        public static Call<Response.Basic> delete(String pid) {
            return CallAPI.delete_photo(DELETE_PHOTO, pid);
        }

        public static Call<Response.Basic> unlike(String pid, String uid) {
            return CallAPI.photo_unlike(DELETE_PHOTO_LIKE, pid, uid);
        }

        public static Call<Response.Basic> delete_comment(String uid, String pid) {
            return CallAPI.photo_delete_comment(DELETE_PHOTO_COMMENT, uid, pid);
        }
    }

    public static class Categoy {
        private final static String GET_ALL_CATEGORY = "category_get_all";

        public static Call<Response.Category> get_all() {
            return CallAPI.category_get_all(GET_ALL_CATEGORY);
        }
    }
}
