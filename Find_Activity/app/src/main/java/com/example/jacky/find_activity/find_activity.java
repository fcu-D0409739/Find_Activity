package com.example.jacky.find_activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class find_activity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    final String[] function={"新增","刪除"};
    AlertDialog.Builder dialog_list;
    static LatLng latLng_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        dialog_list = new AlertDialog.Builder(find_activity.this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng now_location = new LatLng(24.175492, 120.642226);
        mMap.addMarker(new MarkerOptions().position(now_location).title("Me"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(now_location));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setOnInfoWindowClickListener(find_activity.this);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                Toast.makeText(find_activity.this, "經度:"+latLng.latitude+"緯度:"+latLng.longitude, Toast.LENGTH_SHORT).show();

                addMarker(latLng);

            }
        });
    }

    private void addMarker(LatLng latLng) {
        setLatLng(latLng); //儲存在地圖上的座標
        Intent it = new Intent();
        it.setClass(find_activity.this, Marker_add_activity.class);
        int requestCode = 100;
        startActivityForResult(it , requestCode);
    }

    private void setLatLng(LatLng latLng) {
        latLng_pos = latLng;
    }

    private LatLng getLatlng() {
        return latLng_pos;
    }


    protected void onActivityResult(int requestCode,int resultCode,Intent it){
        if(resultCode == RESULT_OK){
            LatLng latLng;
            latLng = getLatlng();//取得之前在地圖上位置的座標
            AddMarker(latLng,
                    it.getStringExtra("Title"),
                    it.getStringExtra("Snippet"));
            Toast.makeText(this, "活動已建立", Toast.LENGTH_SHORT).show();
        }else{

        }
    }


    private void AddMarker(LatLng latLng,String title,String snippet) {

        CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(find_activity.this);
        mMap.setInfoWindowAdapter(adapter);
        Marker marker ;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng)
                .title(title)
                .snippet(snippet);
        marker = mMap.addMarker(markerOptions);


    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }
}
