package com.epicodus.slofamfun;

import java.util.ArrayList;

public class Activity {
    private String mName;
    private String mImageUrl;
    private String mWebsite;
    private double mRating;
    private double mLatitude;
    private double mLongitude;
    private ArrayList<String> mAddress = new ArrayList<>();
    private String mPhone;
    private ArrayList<String> mCategories = new ArrayList<>();

    public Activity(String name, String imageUrl, String website, double rating, double latitude, double longitude, ArrayList<String> address, String phone, ArrayList<String> categories) {
        this.mName = name;
        this.mImageUrl = imageUrl;
        this.mWebsite = website;
        this.mRating = rating;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mAddress = address;
        this.mPhone = phone;
        this.mCategories = categories;
    }

    public String getName() {
        return mName;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public double getRating() {
        return mRating;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public ArrayList<String> getAddress() {
        return mAddress;
    }

    public String getPhone() {
        return mPhone;
    }

    public ArrayList<String> getCategories() {
        return mCategories;
    }
}
