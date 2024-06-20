package org.example.model;

public class Location {
    private final double latitude;
    private final double longitude;
    private final String island;

    public Location(double latitude, double longitude, String island) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.island = island;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

}