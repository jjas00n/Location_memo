package com.example.mygooglemapapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class NoteActivity extends AppCompatActivity {

    private double latitude;
    private double longitude;
    private ImageView imageViewLocation;
    private TextView textViewCoordinates;
    private EditText editTextPlace;
    private EditText editTextNote;
    private ImageButton buttonSaveNote;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());


        imageViewLocation = findViewById(R.id.imageViewLocation);
        textViewCoordinates = findViewById(R.id.textViewCoordinates);
        editTextPlace = findViewById(R.id.editTextPlace);
        editTextNote = findViewById(R.id.editTextNote);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);
        ratingBar = findViewById(R.id.Rating);

        latitude = getIntent().getDoubleExtra("latitude", 0.0);
        longitude = getIntent().getDoubleExtra("longitude", 0.0);

        textViewCoordinates.setText("위도: " + latitude + ", 경도: " + longitude);
        String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=20&size=600x300&markers=color:red%7C" + latitude + "," + longitude + "&key=AIzaSyBkOdxD47H2oVSzy4fmDPyC9JFBeCVX3xo";
        Picasso.get().load(imageUrl).into(imageViewLocation);

        buttonSaveNote.setOnClickListener(v -> {
            String place = editTextPlace.getText().toString();
            String note = editTextNote.getText().toString();
            float rating = ratingBar.getRating();
            saveNoteToInternalStorage(place, note, rating);
            // 메인 화면으로 돌아가기
            Intent intent = new Intent(NoteActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void saveNoteToInternalStorage(String place, String note, float rating) {
        String fileName = "note_" + System.currentTimeMillis() + ".txt";
        String imageFileName = "image_" + System.currentTimeMillis() + ".png";

        String fileContents = "Latitude: " + latitude + "\nLongitude: " + longitude + "\nPlace: " + place + "\nNote: " + note + "\nRating: " + rating;

        FileOutputStream fos = null;
        FileOutputStream imageFos = null;

        try {
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(fileContents.getBytes());

            // 이미지 저장
            BitmapDrawable drawable = (BitmapDrawable) imageViewLocation.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            imageFos = openFileOutput(imageFileName, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, imageFos);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (imageFos != null) {
                try {
                    imageFos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
