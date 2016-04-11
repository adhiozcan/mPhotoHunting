package com.wisnu.photohunting.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wisnu.photohunting.R;
import com.wisnu.photohunting.network.Request;
import com.wisnu.photohunting.network.Response;
import com.wisnu.photohunting.savingstate.UserData;
import com.wisnu.photohunting.system.Utils;
import com.wisnu.photohunting.ui.fragment.InsertComment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PhotoActivity extends AppCompatActivity {
    public static String photoId;
    public static String userId;

    TextView btn_Wishlist;
    TextView btn_Like;
    TextView btn_Comment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.photo_support_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Photo Detail");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        //btn_Wishlist = (TextView) findViewById(R.id.photo_detail_wishlist);
        btn_Like = (TextView) findViewById(R.id.photo_detail_like);
        btn_Comment = (TextView) findViewById(R.id.photo_detail_comment);

        //btn_Wishlist.setOnClickListener(addToWishlist());
        btn_Like.setOnClickListener(insertLike());
        btn_Comment.setOnClickListener(insertComment());

    }

    /*private View.OnClickListener addToWishlist() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showToast(PhotoActivity.this, "Menambahkan ke Wishlist berhasil");
            }
        };
    }*/

    private View.OnClickListener insertLike() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request.Photo.add_like(photoId, userId).
                        enqueue(new Callback<Response.Basic>() {
                            @Override
                            public void onResponse(Call<Response.Basic> call, retrofit2.Response<Response.Basic> response) {
                                if (response.isSuccessful())
                                    if (response.body().getData() != null) {
                                        if (!response.body().getStatus().equals("false")) {
                                            Utils.showToast(PhotoActivity.this, "Anda menyukai foto ini");
                                        } else {
                                            Utils.showOnConsole("PhotoFeedFragment", "onResponse : Gagal menambahkan like pada foto ini");
                                        }
                                    }
                            }

                            @Override
                            public void onFailure(Call<Response.Basic> call, Throwable t) {
                                Utils.showOnConsole("PhotoFeedFragment", "onFailure : " + t.getLocalizedMessage());
                            }
                        });
            }
        };
    }

    private View.OnClickListener insertComment() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertComment.newInstance(photoId, userId).show(getFragmentManager(), "INSERT_COMMENT");
            }
        };
    }
}
