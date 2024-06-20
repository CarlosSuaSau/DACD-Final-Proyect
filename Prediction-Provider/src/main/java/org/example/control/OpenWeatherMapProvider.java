package org.example.control;

import com.google.gson.*;
import org.example.model.Location;
import org.example.model.Weather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;;

public class OpenWeatherMapProvider implements WeatherProvider{

    private String password;
    public OpenWeatherMapProvider(String password){
        this.password = password;
    }

    @Override
    public List<Weather> getWeather(Location location) {
        try{
            String url = "https://api.openweathermap.org/data/2.5/forecast?" +
                    "lat=" + location.getLatitude() +
                    "&lon=" + location.getLongitude() +
                    "&appid=" + password;

            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String jsonResponse = doc.text();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            JsonArray list = jsonObject.getAsJsonArray("list");

            List<Weather> fiveDays = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                JsonObject prediction = list.get(i).getAsJsonObject();

                if (isMidday(prediction)) {
                    Weather weather = jsonToWeather(prediction, location);
                    fiveDays.add(weather);

                    if (fiveDays.size() == 5) {
                        break;
                    }
                }
            }
            return fiveDays;
        }catch (IOException error){
            throw new SecurityException("Wrong API-key introduced", error);
        }
    }

    private Weather jsonToWeather(JsonObject jsonData, Location location) {

        JsonObject mainData = jsonData.getAsJsonObject("main");
        Double temp = mainData.get("temp").getAsDouble();
        Integer humidity = mainData.get("humidity").getAsInt();
        JsonObject cloudsData = jsonData.getAsJsonObject("clouds");
        Integer cloudiness = cloudsData.get("all").getAsInt();
        JsonObject windData = jsonData.getAsJsonObject("wind");
        Double windSpeed = windData.get("speed").getAsDouble();
        JsonObject rainData = jsonData.getAsJsonObject("rain");
        Double pop = (rainData != null && rainData.has("3h")) ? rainData.get("3h").getAsDouble() : 0.0;
        String dt = String.valueOf(Instant.ofEpochSecond(jsonData.get("dt").getAsLong()));
        String ts = String.valueOf(Instant.now());
        String ss = "OpenWeatherMap";

        Weather weather = new Weather(temp, humidity, cloudiness, windSpeed, pop, dt, location, ts, ss);
        return weather;
    }
    private boolean isMidday(JsonObject data) {
        String dateTime = data.get("dt_txt").getAsString();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, format);

        return localDateTime.getHour() == 12;
    }
}
