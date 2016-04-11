package com.wisnu.photohunting.savingstate;

public class Position {
    private static Position instance = new Position();
    private double latitude;
    private double longitude;

    private Position() {
    }

    public static Position getInstance() {
        return instance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
