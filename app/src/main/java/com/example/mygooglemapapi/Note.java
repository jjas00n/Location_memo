package com.example.mygooglemapapi;

import android.graphics.Bitmap;

public class Note {
    private double latitude;
    private double longitude;
    private String place;
    private String text;
    private float rating;
    private Bitmap image;

    private String imageName; // 사진 파일 이름 저장할 필드 추가


    public Note(double latitude, double longitude, String place, String text, float rating, Bitmap image, String imageName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.place = place;
        this.text = text;
        this.rating = rating;
        this.image = image;
        this.imageName=imageName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPlace() {
        return place;
    }

    public String getText() {
        return text;
    }

    public float getRating() {
        return rating;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getImageName() {
        return imageName;
    }

}
