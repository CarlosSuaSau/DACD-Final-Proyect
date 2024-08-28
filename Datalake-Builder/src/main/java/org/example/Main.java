package org.example;

public class Main {
    public static String url = "tcp://localhost:61616";
    public static String subject1 = "prediction.Weather";
    public static String subject2 = "prediction.Places";
    public static String weatherPath = "eventstore/prediction.Weather/%s/%s.events";
    public static String placesPath = "eventstore/location.Places/%s/%s.events";
    public static void main(String[] args){
        BrokerSuscriptor suscriptor1 = new BrokerSuscriptor(subject1, url);
        BrokerSuscriptor suscriptor2 = new BrokerSuscriptor(subject2, url);
        EventStorer weatherStorer = new EventStorer(weatherPath);
        EventStorer placesStorer = new EventStorer(placesPath);
        EventController weatherController = new EventController(suscriptor1, weatherStorer);
        EventController placesController = new EventController(suscriptor2, placesStorer);
        weatherController.run();
        placesController.run();
    }
}
