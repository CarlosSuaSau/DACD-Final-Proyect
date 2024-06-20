package org.example.control;

import org.example.model.Location;
import org.example.model.Place;

import java.util.List;

public interface PlacesProvider {
    List<Place> getPlaces(Location location);
}
