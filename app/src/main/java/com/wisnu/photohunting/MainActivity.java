package com.wisnu.photohunting;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.victor.loading.rotate.RotateLoading;
import com.wisnu.photohunting.savingstate.PhotoFeedList;
import com.wisnu.photohunting.model.Photo;
import com.wisnu.photohunting.network.Request;
import com.wisnu.photohunting.network.Response;
import com.wisnu.photohunting.savingstate.Position;
import com.wisnu.photohunting.system.Utils;
import com.wisnu.photohunting.ui.activity.WelcomeActivity;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RotateLoading rotateLoading;
    protected LocationManager locationManager;
    protected LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);

        final int TIME_SPLASH_ACTIVE = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rotateLoading.stop();
                fetchAllPhoto();
                //testUpload();
                finish();
            }
        }, TIME_SPLASH_ACTIVE);

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);*/
    }

    private void fetchAllPhoto() {
        Request.Photo.get_all().enqueue(new Callback<Response.Photo>() {
            @Override
            public void onResponse(Call<Response.Photo> call, retrofit2.Response<Response.Photo> response) {
                List<Photo> listPhotoFeeds = PhotoFeedList.getInstance().getPhotoList();
                if (listPhotoFeeds != null) {
                    listPhotoFeeds.clear();
                    listPhotoFeeds.addAll(response.body().getListPhotoFeeds());
                } else {
                    Utils.showOnConsole("SplashActivity", "onResponse : listPhotoFeeds is null");
                }

                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            }

            @Override
            public void onFailure(Call<Response.Photo> call, Throwable t) {
                Utils.showOnConsole("PhotoFeedFragment", "onFailure : " + t.getLocalizedMessage());
                Utils.showToast(MainActivity.this, "Cant reach server at the moment");
            }
        });
    }

    /*@Override
    public void onLocationChanged(Location location) {
        Position.getInstance().setLatitude(location.getLatitude());
        Position.getInstance().setLongitude(location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }*/

    /*private void testUpload() {
        File file = new File(Environment.getDataDirectory().getPath(), "Photohunt/ManiaceCastle_1920x1200.jpg");
        if (file.exists()) {
            Utils.showOnConsole("Ceck File", "Status : File ada");
        } else {
            Utils.showOnConsole("Cek File", "Status : File tidak ada");
        }
        Utils.showOnConsole("testUpload", "Lokasi : " + file.getPath());

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        Request.Photo.insert_new(file.getName(), "", file.getName(), "", "", "", "", "", requestBody).enqueue(new Callback<Response.Basic>() {
            @Override
            public void onResponse(retrofit.Response<Response.Basic> response, Retrofit retrofit) {
                Utils.showOnConsole("onResponse", response.body().getMessage());
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("ERROR " + t.getLocalizedMessage());
            }
        });
    }*/
}
