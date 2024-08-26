package org.example.control;

import org.example.model.Location;
import org.example.model.Place;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class FoursquareProvider implements  PlacesProvider{

    private String password;
    public FoursquareProvider(String password){
        this.password = password;
    }

    @Override
    public List<Place> getPlaces(Location location) {
        List<Place> places = new ArrayList<>();
        try {
            String url = "https://api.foursquare.com/v3/places/search" + "?ll=" + location.getLatitude() + "," + location.getLongitude();

            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
            request.addHeader("Authorization", password);

            HttpResponse response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonArray("results");

            for (JsonElement element : results) {
                JsonObject placeJson = element.getAsJsonObject();
                String name = placeJson.get("name").getAsString();
                String timezone = placeJson.has("timezone") ? placeJson.get("timezone").getAsString() : "Unknown";

                JsonObject locationJson = placeJson.getAsJsonObject("geocodes").getAsJsonObject("main");
                double lat = locationJson.get("latitude").getAsDouble();
                double lon = locationJson.get("longitude").getAsDouble();

                Location placeLocation = new Location(lat, lon);
                Place place = new Place(name, timezone, placeLocation);
                places.add(place);
            }

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;

    }
}
