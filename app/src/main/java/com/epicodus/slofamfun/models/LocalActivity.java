package com.epicodus.slofamfun.models;

import org.parceler.Parcel;

@Parcel
public class LocalActivity {
    String name;
    String image;
    String website;
    String address;
    String phone;
    String strollerAccess;
    String comments;
    String ageRange;

    public LocalActivity() {}

    public LocalActivity(String name, String image, String website, String address, String phone, String strollerAccess, String comments, String ageRange) {
        this.name = name;
        this.image = image;
        this.website = website;
        this.address = address;
        this.phone = phone;
        this.strollerAccess = strollerAccess;
        this.comments = comments;
        this.ageRange = ageRange;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getWebsite() {
        return website;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getStrollerAccess() {
        return strollerAccess;
    }

    public String getComments() {
        return comments;
    }

    public String getAgeRange() {
        return ageRange;
    }
}

