package com.example.ramirezfmaps.activities;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ramirezfmaps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Zoom mínimo y máximo en el mapa.
        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(18);


        // Add a marker in Sydney and move the camera
        LatLng Peru = new LatLng(-12.078282934581605, -77.09572076797485);
        mMap.addMarker(new MarkerOptions().position(Peru).title("Hola desde Lima - San Miguel - Perú").draggable(true));
        CameraPosition camera = new CameraPosition.Builder()
                .target(Peru)
                .zoom(15)           // Límite 21
                .bearing(0)         // 0 - 365°
                .tilt(30)           // Efecto 3D / Lim = 90
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(Peru));

        // Al hacer clic en el mapa nos devuelva la latitud y longitud
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "Click on: \n"+
                        " Lat: "+latLng.latitude +
                        "\n Long: "+latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        // Pulsamos un poco mas largo
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "Long Click on: \n"+
                        " Lat: "+latLng.latitude +
                        "\n Long: "+latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        // Para tener un marcador y arrastrarlo
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(MapsActivity.this, "Marker Drager to: \n"+
                        " Lat: "+marker.getPosition().latitude +
                        "\n Long: "+marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
            }
        });

    }
}