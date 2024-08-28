package org.example;

import java.util.List;

public class EventController {
    private final BrokerSuscriptor suscriptor;
    private final EventStorer storer;

    public EventController(BrokerSuscriptor suscriptor, EventStorer storer) {
        this.suscriptor = suscriptor;
        this.storer = storer;
    }

    public void run(){
        try {
            List<String> events = suscriptor.obtainEvents();
            if (events.isEmpty()){
                System.out.println("There are not remaining events to get.");
            } else {
                for (String event: events){
                    storer.store(event);
                }
                System.out.println("Events stored successfully.");
            }
        } catch (Exception error){
            System.out.println(error);
        }
    }
}
