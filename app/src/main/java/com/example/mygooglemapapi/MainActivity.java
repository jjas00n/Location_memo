package com.example.mygooglemapapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private ImageButton buttonShowMap;
    private ImageButton buttonViewNotes;
    private EditText editTextPlace;
    private ImageButton buttonFindCoordinates;
    private TextView textViewCoordinates;


    private PlacesClient placesClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Places API
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        placesClient = Places.createClient(this);

        editTextLatitude = findViewById(R.id.editTextLatitude);
        editTextLongitude = findViewById(R.id.editTextLongitude);
        buttonShowMap = findViewById(R.id.buttonShowMap);
        buttonViewNotes = findViewById(R.id.buttonViewNotes);
        editTextPlace = findViewById(R.id.editTextPlace);
        buttonFindCoordinates = findViewById(R.id.buttonFindCoordinates);
        textViewCoordinates = findViewById(R.id.textViewCoordinates);

        buttonShowMap.setOnClickListener(v -> {
            String lat = editTextLatitude.getText().toString();
            String lng = editTextLongitude.getText().toString();
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            intent.putExtra("latitude", lat);
            intent.putExtra("longitude", lng);
            startActivity(intent);
        });

        buttonViewNotes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
            startActivity(intent);
        });
        buttonFindCoordinates.setOnClickListener(v -> {
            String placeName = editTextPlace.getText().toString();
            getPlaceCoordinates(placeName);
        });



    }

    private void getPlaceCoordinates(String placeName) {
        // Specify the fields to return.
        List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

        // Use FindAutocompletePredictionsRequest to find place IDs
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setQuery(placeName)
                .build();

        placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {
            if (response.getAutocompletePredictions().size() > 0) {
                String placeId = response.getAutocompletePredictions().get(0).getPlaceId();

                // Construct a request object, passing the place ID and fields array.
                FetchPlaceRequest placeRequest = FetchPlaceRequest.newInstance(placeId, placeFields);

                placesClient.fetchPlace(placeRequest).addOnSuccessListener((fetchPlaceResponse) -> {
                    Place place = fetchPlaceResponse.getPlace();
                    textViewCoordinates.setText("장소: " + place.getName() + "\n위도: " + place.getLatLng().latitude + "\n경도: " + place.getLatLng().longitude);
                    editTextLatitude.setText(String.valueOf(place.getLatLng().latitude));
                    editTextLongitude.setText(String.valueOf(place.getLatLng().longitude));
                    Toast.makeText(getApplicationContext(), "좌표값 입력 완료", Toast.LENGTH_SHORT).show();
                    textViewCoordinates.setVisibility(TextView.VISIBLE);
                }).addOnFailureListener((exception) -> {
                    exception.printStackTrace();
                    Toast.makeText(getApplicationContext(), "장소를 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
                });
            }
        }).addOnFailureListener((exception) -> {
            exception.printStackTrace();
            Toast.makeText(getApplicationContext(), "장소를 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
        });
    }

}
