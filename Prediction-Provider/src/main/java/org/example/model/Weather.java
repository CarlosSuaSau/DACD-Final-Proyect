package org.example.model;



public class Weather {
    private final double temp;
    private final int humidity;
    private final int cloudiness;
    private final double windSpeed;
    private final double pop;
    private final String instant;
    private final Location location;
    private final String ts;
    private final String ss;

    public Weather(double temp, int humidity, int cloudiness, double windSpeed, double pop, String instant, Location location, String ts, String ss){
        this.temp = temp;
        this.pop = pop;
        this.humidity = humidity;
        this.cloudiness = cloudiness;
        this.windSpeed = windSpeed;
        this.instant = instant;
        this.location = location;
        this.ts = ts;
        this.ss = ss;

    }

    @Override
    public String toString() {
        return "Weather{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", cloudiness=" + cloudiness +
                ", windSpeed=" + windSpeed +
                ", pop=" + pop +
                ", instant='" + instant + '\'' +
                ", location=" + location +
                ", ts='" + ts + '\'' +
                ", ss='" + ss + '\'' +
                '}';
    }
}
