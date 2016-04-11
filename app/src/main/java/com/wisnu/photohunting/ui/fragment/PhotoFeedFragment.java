package com.wisnu.photohunting.ui.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wisnu.photohunting.R;
import com.wisnu.photohunting.adapter.PhotoFeedAdapter;
import com.wisnu.photohunting.camera.CameraActivity;
import com.wisnu.photohunting.savingstate.PhotoFeedList;
import com.wisnu.photohunting.savingstate.UserData;
import com.wisnu.photohunting.model.Photo;
import com.wisnu.photohunting.model.User;
import com.wisnu.photohunting.network.Request;
import com.wisnu.photohunting.network.Response;
import com.wisnu.photohunting.system.Utils;
import com.wisnu.photohunting.ui.activity.CommentListActivity;
import com.wisnu.photohunting.ui.activity.LikeListActivity;
import com.wisnu.photohunting.ui.activity.PhotoActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Retrofit;

public class PhotoFeedFragment extends Fragment {
    private SwipeRefreshLayout mRefresh;
    private List<Photo> mPhotoPostList;
    private PhotoFeedAdapter mPhotoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        setHasOptionsMenu(true);

        /**
         * Check apakah data photo list sudah diambil dari server
         * Jika ya, maka set mPhotoPostList dengan data yang sudah ada
         * Jika tidak, maka set mPhotoPostList sebagai list kosong yang siap digunakan selanjutnya
         */
        mPhotoPostList = PhotoFeedList.getInstance().getPhotoList();
        if (mPhotoPostList == null) mPhotoPostList = new ArrayList<>();

        /**
         * Membuat object baru dari kelas PhotoFeedAdapter yang akan digunakan untuk menghubungkan
         * data dengan view
         * Binding Adapter      : PhotoFeedAdapter.class
         * Data                 : List<Photo> mPhotoPostList
         */
        mPhotoAdapter = new PhotoFeedAdapter(mPhotoPostList);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mPhotoAdapter);
        mPhotoAdapter.onItemClickedListener(new PhotoFeedAdapter.onClickListener() {
            @Override
            public void onActionClickView(int itemCode, String pid) {
                User user = UserData.getInstance().getUser();
                String userId = user.getUserId();
                switch (itemCode) {
                    case PhotoFeedAdapter.ITEM_COUNT_LIKE:
                        Utils.showOnConsole("PhotoFeedFragment", "onActionClickView : photoId : " + pid);
                        PhotoActivity.photoId = pid;
                        startActivity(new Intent(getActivity(), LikeListActivity.class));
                        break;
                    case PhotoFeedAdapter.ITEM_COUNT_COMMENT:
                        Utils.showOnConsole("PhotoFeedFragment", "onActionClickView : photoId : " + pid);
                        PhotoActivity.photoId = pid;
                        startActivity(new Intent(getActivity(), CommentListActivity.class));
                        break;
                    case PhotoFeedAdapter.ITEM_BUTTON_LIKE:
                        Utils.showOnConsole("PhotoFeedFragment", "onActionClickView : userId : " + userId);

                        //TODO Delete this line if you've finished debugging method like.
                        userId = UserData.getInstance().getUser().getUserId();
                        PhotoActivity.userId = userId;
                        PhotoActivity.photoId = pid;
                        like(pid, userId);
                        break;
                    case PhotoFeedAdapter.ITEM_BUTTON_COMMENT:


                        //TODO Delete this line if you've finished debugging method comment.
                        userId = UserData.getInstance().getUser().getUserId();
                        PhotoActivity.userId = userId;
                        PhotoActivity.photoId = pid;
                        comment(pid, userId);
                        break;
                }
            }

            @Override
            public void onPhotoClickView(Photo photo) {
                onPhotoItemClicked(photo);
            }
        });

        /**
         * Method refresh yang akan memperbarui data dari server dengan menjalankan method
         * fetchAllFeeds
         */
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.dashboard_refresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchAllFeeds();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int option = item.getItemId();
        if (option == R.id.action_take_photo) {
            Toast.makeText(getActivity(), "Insert new photo post", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), CameraActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method yang bertugas untuk mengambil data dari server
     * Request      : Photo.get_all()
     */
    private void fetchAllFeeds() {
        mRefresh.setRefreshing(true);
        Request.Photo.get_all().enqueue(new Callback<Response.Photo>() {
            @Override
            public void onResponse(retrofit.Response<Response.Photo> response, Retrofit retrofit) {
                mRefresh.setRefreshing(false);
                if (mPhotoPostList != null) {
                    mPhotoPostList.clear();
                    mPhotoPostList.addAll(response.body().getListPhotoFeeds());
                } else {
                    Utils.showOnConsole("SplashActivity", "onResponse : listPhotoFeeds is null");
                }
                mPhotoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                mRefresh.setRefreshing(false);
                Utils.showOnConsole("PhotoFeedFragment", "onFailure : " + t.getLocalizedMessage());
                Utils.showToast(getActivity(), "Server tidak merespon, coba beberapa saat lagi");
            }
        });
    }

    /**
     * Dijalankan jika user memberikan like foto
     *
     * @param photoId
     * @param userId
     */
    private void like(String photoId, String userId) {
        Request.Photo.add_like(photoId, userId).
                enqueue(new Callback<Response.Basic>() {
                    @Override
                    public void onResponse(retrofit.Response<Response.Basic> response, Retrofit retrofit) {
                        if (response.body().getData() != null) {
                            if (!response.body().getStatus().equals("false")) {
                                Utils.showToast(getActivity(), "Anda menyukai foto ini");
                            } else {
                                Utils.showOnConsole("PhotoFeedFragment", "onResponse : Gagal menambahkan like pada foto ini");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Utils.showOnConsole("PhotoFeedFragment", "onFailure : " + t.getLocalizedMessage());
                    }
                });
    }

    /**
     * Dijalankan jika user menambahkan komentar foto
     *
     * @param userId
     * @param photoId
     */
    private void comment(String userId, String photoId) {
        InsertComment.newInstance(photoId, userId).show(getActivity().getFragmentManager(), "INSERT_COMMENT");
    }

    /**
     * Dijalankan jika user mengklik item foto yang ada di postfeed.
     * Method ini akan memindahkan user ke PhotoActivity.class
     *
     * @param photo
     */
    public void onPhotoItemClicked(Photo photo) {
        Utils.showOnConsole("onPhotoItemClicked", "PhotoItem Clicked : " + photo.getPhotoID());
        Utils.showOnConsole("onPhotoItemClicked", "PhotoItemClicked : " + photo.getPhotoName());
        getActivity().startActivity(new Intent(getActivity(), PhotoActivity.class));
    }
}
