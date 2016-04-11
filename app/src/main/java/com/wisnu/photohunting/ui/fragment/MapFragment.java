package com.wisnu.photohunting.ui.fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wisnu.photohunting.R;
import com.wisnu.photohunting.model.Photo;
import com.wisnu.photohunting.savingstate.PhotoFeedList;
import com.wisnu.photohunting.savingstate.UserData;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;

    private void addMarkerOnMap() {
        for (Photo photo : PhotoFeedList.getInstance().getPhotoList()) {

            double latitude = Double.parseDouble(photo.getPhotoLatitude());
            double longitude = Double.parseDouble(photo.getPhotoLongitude());

            MarkerOptions marker = new MarkerOptions();
            marker.position(new LatLng(latitude, longitude))
                    .title(photo.getPhotoName());

            mMap.addMarker(marker);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_location);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange(Location arg0) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("Kamu disini !"));
            }
        });

        addMarkerOnMap();

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
