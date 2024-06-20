package org.example.control;

import org.example.model.Location;
import org.example.model.Place;

import java.util.List;

public class FoursquareProvider implements  PlacesProvider{

    private String password;
    public FoursquareProvider(String password){
        this.password = password;
    }

    @Override
    public List<Place> getPlaces(Location location) {
        return null;
    }
}
