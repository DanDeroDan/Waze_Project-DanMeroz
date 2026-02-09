package com.example.wazeproject_dan;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng telAviv = new LatLng(32.0853, 34.7818);
        googleMap.addMarker(new MarkerOptions().position(telAviv).title("Marker in Tel Aviv"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(telAviv, 12));
        
        // Enable zoom controls for easier testing
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
