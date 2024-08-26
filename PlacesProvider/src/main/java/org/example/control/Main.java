package org.example.control;

public class Main {
    public static void main(String[] args) {
        String apiKey = "fsq315UEa3f9d8RUU1dspK73FwLE1f+FtKPCAFyds5QWvRk="; /*args[0];*/
        FoursquareProvider myProvider = new FoursquareProvider(apiKey);
        ActiveMQSender mySender = new ActiveMQSender("prediction.Places", "tcp://localhost:61616");
        PlacesController controller = new PlacesController(mySender, myProvider);
        controller.Task();
    }
}
