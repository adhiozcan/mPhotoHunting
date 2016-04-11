package com.wisnu.photohunting.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wisnu.photohunting.R;
import com.wisnu.photohunting.adapter.LikeAdapter;
import com.wisnu.photohunting.model.Like;
import com.wisnu.photohunting.network.Request;
import com.wisnu.photohunting.network.Response;
import com.wisnu.photohunting.system.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class LikeListActivity extends AppCompatActivity {
    private List<Like> likeList = new ArrayList<>();
    private RecyclerView recyclerview;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_list);
        Utils.setUpToolbar(this, "Yang Menyukai Foto Ini");

        /**
         * Object recylerview menjadi container dari data per masing-masing item like
         * Binding Adapter      : LikeAdapter.class
         * Data                 : List<Like> likeList
         */
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview_likelist);
        recyclerview.setAdapter(new LikeAdapter(likeList));
        recyclerview.setLayoutManager(new LinearLayoutManager(getParent(), LinearLayoutManager.VERTICAL, false));
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        /**
         * Method untuk melakukan refresh data like list.
         *
         * Ketika di swipe kebawah, aplikasi akan mengambil data terbaru dari server menggunakan
         * method getLikeList(int pid);
         */
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.like_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String pid = "1";
                getLikeList(pid);
            }
        });

        String pid = "1";
        getLikeList(pid);
    }

    /**
     * Method yang digunakan untuk mengambil data dari server cloud
     * Request      : Photo.get_photo_like_with_pid(String pid)
     *
     * @param pid photoId
     */
    private void getLikeList(String pid) {
        refreshLayout.setRefreshing(true);
        Request.Photo.get_photo_like_with_pid(pid).enqueue(new Callback<Response.Like>() {
            @Override
            public void onResponse(Call<Response.Like> call, retrofit2.Response<Response.Like> response) {
                likeList.clear();
                for (Like likeItem : response.body().getListLike()) {
                    Utils.showOnConsole("getLikeList", "Like by : " + likeItem.getName());
                    Utils.showOnConsole("getLikeList", "Like date : " + likeItem.getLikeDate());
                    likeList.add(likeItem);
                }
                recyclerview.getAdapter().notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Response.Like> call, Throwable t) {
                System.out.printf(" [ ERROR ] %s%n", t.getLocalizedMessage());
                refreshLayout.setRefreshing(false);
            }
        });
    }


}
