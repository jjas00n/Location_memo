package com.example.mygooglemapapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());

        Intent intent = getIntent();
        latitude = Double.parseDouble(intent.getStringExtra("latitude"));
        longitude = Double.parseDouble(intent.getStringExtra("longitude"));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        ImageButton buttonAddNote = findViewById(R.id.buttonAddNote);
        buttonAddNote.setOnClickListener(v -> {
            Intent noteIntent = new Intent(MapActivity.this, NoteActivity.class);
            noteIntent.putExtra("latitude", latitude);
            noteIntent.putExtra("longitude", longitude);
            startActivity(noteIntent);
        });
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this); // 클릭 리스너 등록

        // 초기 마커 설정
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        // 사용자가 지도를 클릭했을 때 호출되는 콜백 메서드
        double clickedLatitude = latLng.latitude;
        double clickedLongitude = latLng.longitude;

        // 클릭한 위치에 마커 추가
        mMap.clear(); // 이전 마커 제거
        mMap.addMarker(new MarkerOptions().position(latLng).title("Clicked Marker"));

        // 위도 경도 갱신
        latitude = clickedLatitude;
        longitude = clickedLongitude;

        Toast.makeText(getApplicationContext(), "현제 위도: " + clickedLatitude + ", 현제 경도: " + clickedLongitude, Toast.LENGTH_SHORT).show();
    }
}