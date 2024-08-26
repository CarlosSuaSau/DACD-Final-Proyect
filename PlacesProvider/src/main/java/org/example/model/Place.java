package org.example.model;

public class Place {
    private final String name;
    private final String timezone;
    private final Location location;

    public Place(String name, String timezone, Location location) {
        this.name = name;
        this.timezone = timezone;
        this.location = location;
    }
}
