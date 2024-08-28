package org.example.control;

import org.example.model.Location;
import org.example.model.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class PlacesController {
    private final PlacesProvider provider;
    private final PlacesStore store;

    public PlacesController(PlacesStore store, PlacesProvider provider) {
        this.store = store;
        this.provider = provider;
    }

    public void runTask(){
        Task();
    }

    public void Task() {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                System.out.print("Doing the task...");
                List<Location> locationsList = initializeLocations();
                List<List<Place>> iPlaces = new ArrayList<>();

                for (int i = 0; i < 8; i++) {
                    iPlaces.add(provider.getPlaces(locationsList.get(i)));
                }

                for (List<Place> places : iPlaces) {
                    for (Place place : places) {
                        store.save(place);
                        System.out.println(place.toString());
                    }
                }
                System.out.println("The messages have been sent.");
            }
        };
        timer.schedule(task, 0, 1000*6*3600);
    }

    public List<Location> initializeLocations() {
        Location granCanaria = new Location(28.139421644403107, -15.629770604586072);
        Location tenerife = new Location(28.417265111220242, -16.545093079171146);
        Location lanzarote = new Location(29.014819077666854, -13.50023776954011);
        Location fuerteventura = new Location(28.15635320893622, -14.232874221077148);
        Location laPalma = new Location(28.67485954290264, -17.944184684582225);
        Location laGomera = new Location(28.04172650120714, -17.196680015527626);
        Location elHierro = new Location(27.641097920516973, -17.981739159206647);
        Location laGraciosa = new Location(29.261546327124854, -13.480270948293828);
        return new ArrayList<>(List.of(granCanaria, tenerife, lanzarote, fuerteventura, laPalma, laGomera, elHierro, laGraciosa));
    }
}