package com.tenig.capturephoto;

public class Locations {
    //private variables
    int id;
    float latitude;
    float longitude;
    byte[] image;

    public Locations() {
    }

    public Locations(float latitude, float longitude, byte[] image) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }

    public Locations(int id, float latitude, float longitude, byte[] image) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
