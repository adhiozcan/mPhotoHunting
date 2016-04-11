package com.wisnu.photohunting.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wisnu.photohunting.R;
import com.wisnu.photohunting.adapter.CommentAdapter;
import com.wisnu.photohunting.model.Comment;
import com.wisnu.photohunting.network.Request;
import com.wisnu.photohunting.network.Response;
import com.wisnu.photohunting.system.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class CommentListActivity extends AppCompatActivity {
    private List<Comment> commentList = new ArrayList<>();
    private RecyclerView       recyclerview;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        Utils.setUpToolbar(this, "Komentar Orang Lain");

        /**
         * Object recylerview menjadi container dari data per masing-masing item like
         * Binding Adapter      : CommentAdapter.class
         * Data                 : List<Comment> commentList
         */
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview_commentlist);
        recyclerview.setAdapter(new CommentAdapter(commentList));
        recyclerview.setLayoutManager(new LinearLayoutManager(getParent(), LinearLayoutManager.VERTICAL, false));
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        /**
         * Method untuk melakukan refresh data comment list.
         *
         * Ketika di swipe kebawah, aplikasi akan mengambil data terbaru dari server menggunakan
         * method getCommentList(int pid);
         */
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.comment_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String pid = "1";
                getCommentList(pid);
            }
        });

        String pid = "1";
        getCommentList(pid);
    }

    /**
     * Dijalankan untuk mendapatkan data comment dari sercer cloud
     *
     * @param pid
     */
    private void getCommentList(String pid) {
        refreshLayout.setRefreshing(true);

        Request.Photo.get_photo_comment_with_pid(pid).enqueue(new Callback<Response.Comment>() {
            @Override
            public void onResponse(Call<Response.Comment> call, retrofit2.Response<Response.Comment> response) {
                commentList.clear();
                for (Comment comment : response.body().getListComment()) {
                    Utils.showOnConsole("getCommentList", "Commented by : " + comment.getName());
                    Utils.showOnConsole("getCommentList", "Commented date : " + comment.getDate());
                    Utils.showOnConsole("getCommentList", "Commented content : " + comment.getContent());
                    commentList.add(comment);
                }
                refreshLayout.setRefreshing(false);
                recyclerview.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Response.Comment> call, Throwable t) {
                System.out.printf(" [ ERROR ] %s%n", t.getLocalizedMessage());
                refreshLayout.setRefreshing(false);
            }
        });
    }


}
