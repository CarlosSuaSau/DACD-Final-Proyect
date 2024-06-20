package org.example.control;

import org.example.model.Location;
import org.example.model.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WeatherController {
    private final WeatherProvider provider;
    private final WeatherStore store;

    public WeatherController(WeatherStore store, WeatherProvider provider){
        this.store = store;
        this.provider = provider;
    }


    public void runTask() {
        Task();
    }
    public void Task(){
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Doing the task...");
                List<Location> locationsList = initializeLocations();
                List<List<Weather>> predictions = new ArrayList<>();

                for (int i=0; i < 8; i++){
                    predictions.add(provider.getWeather(locationsList.get(i)));
                }

                for (List<Weather> prediction : predictions) {
                    for (Weather weather : prediction) {
                        store.save(weather);
                        System.out.println(weather.toString());
                    }
                }
                System.out.println("The messages have been sent.");
            }
        };
        timer.schedule(task, 0, 1000*6*3600);
    }
    public List<Location> initializeLocations(){
        Location granCanaria = new Location(28.139421644403107, -15.629770604586072, "Gran Canaria");
        Location tenerife = new Location(28.417265111220242, -16.545093079171146, "Tenerife");
        Location lanzarote = new Location( 29.014819077666854, -13.50023776954011, "Lanzarote");
        Location fuerteventura = new Location( 28.15635320893622, -14.232874221077148, "Fuerteventura");
        Location laPalma = new Location( 28.67485954290264, -17.944184684582225, "La Palma");
        Location laGomera = new Location( 28.04172650120714, -17.196680015527626, "La Gomera");
        Location elHierro = new Location( 27.641097920516973, -17.981739159206647, "El Hierro");
        Location laGraciosa = new Location( 29.261546327124854, -13.480270948293828, "La Graciosa");
        return new ArrayList<>(List.of(granCanaria, tenerife, lanzarote, fuerteventura, laPalma, laGomera, elHierro, laGraciosa));
    }
}
