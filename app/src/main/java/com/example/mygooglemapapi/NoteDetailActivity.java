package com.example.mygooglemapapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.IOException;

public class NoteDetailActivity extends AppCompatActivity {

    private ImageView imageViewNoteDetail;
    private TextView textViewCoordinatesDetail;
    private TextView textViewPlaceDetail;
    private TextView textViewNoteDetail;
    private RatingBar ratingBarDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());


        imageViewNoteDetail = findViewById(R.id.imageViewNoteDetail);
        textViewCoordinatesDetail = findViewById(R.id.textViewCoordinatesDetail);
        textViewPlaceDetail = findViewById(R.id.textViewPlaceDetail);
        textViewNoteDetail = findViewById(R.id.textViewNoteDetail);
        ratingBarDetail = findViewById(R.id.ratingBarDetail);

        double latitude = getIntent().getDoubleExtra("latitude", 0.0);
        double longitude = getIntent().getDoubleExtra("longitude", 0.0);
        String place = getIntent().getStringExtra("place");
        String note = getIntent().getStringExtra("note");
        float rating = getIntent().getFloatExtra("rating", 0.0f);
        String imageName = getIntent().getStringExtra("imageName"); // 사진 파일 이름 받기

        textViewCoordinatesDetail.setText("위도: " + latitude + ", 경도: " + longitude);
        textViewPlaceDetail.setText("장소: " + place);
        textViewNoteDetail.setText("메모: " + note);
        ratingBarDetail.setRating(rating);


        Bitmap image = null;
        try (FileInputStream fis = openFileInput(imageName)) {
            image = BitmapFactory.decodeStream(fis);
            imageViewNoteDetail.setImageBitmap(image);
        } catch (IOException e) {
            e.printStackTrace();
            imageViewNoteDetail.setImageResource(R.drawable.default_image);
        }
    }

}
