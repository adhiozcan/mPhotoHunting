package com.wisnu.photohunting.network;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {
    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Category> category_get_all(@Field("request") String request);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.User> user_login(
            @Field("request") String request,
            @Field("userEmail") String userEmail, @Field("userPassword") String userPassword);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Basic> user_register(
            @Field("request") String request,
            @Field("userName") String userName,
            @Field("userEmail") String userEmail,
            @Field("userPassword") String userPassword, @Field("userPhoto") String userPhoto);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Basic> user_update_status(
            @Field("request") String request,
            @Field("uid") String uid, @Field("status") String status);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Photo> photo_get_all(@Field("request") String request);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Photo> photo_get_with_uid(
            @Field("request") String request, @Field("uid") String uid);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Photo> photo_get_with_pid(
            @Field("request") String request, @Field("pid") String pid);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Photo> photo_get_with_cid(
            @Field("request") String request, @Field("cid") String cid);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Like> photo_get_like_with_uid(
            @Field("request") String request, @Field("pid") String pid);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Like> photo_get_like_with_pid(
            @Field("request") String request, @Field("pid") String pid);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Comment> photo_get_comment_with_pid(
            @Field("request") String request, @Field("pid") String pid);

    @Multipart
    @POST(Request.API_SERVICE)
    Call<Response.Basic> photo_insert_new(
            @Field("request") String request,
            @Field("photoName") String photoName,
            @Field("photoDescription") String photoDescription,
            @Field("photoUrl") String photoUrl,
            @Field("photoLocation") String photoLocation,
            @Field("photoLatitude") String photoLatitude,
            @Field("photoLongitude") String photoLongitude,
            @Field("uid") String uid,
            @Field("cid") String cid);

    @Multipart
    @POST(Request.API_SERVICE)
    Call<Response.Basic> photo_insert_new_upload(
            @Part("photoImage") RequestBody photoImage);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Basic> photo_like(
            @Field("request") String request, @Field("pid") String pid, @Field("uid") String uid);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Basic> photo_insert_comment(
            @Field("request") String request,
            @Field("photoComment") String photoComment,
            @Field("pid") String pid, @Field("uid") String uid);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Basic> delete_photo(@Field("request") String request, @Field("pid") String pid);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Basic> photo_unlike(
            @Field("request") String request, @Field("uid") String uid, @Field("pid") String pid);

    @FormUrlEncoded
    @POST(Request.API_SERVICE)
    Call<Response.Basic> photo_delete_comment(
            @Field("request") String request, @Field("uid") String uid, @Field("pid") String pid);
}
