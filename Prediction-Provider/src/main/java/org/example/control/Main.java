package org.example.control;




public class Main {
    public static void main(String[] args) {
        String apiKey = "ced1cd9273c7e3d5dcb4d0f2b59adab3"; /*args[0];*/
        OpenWeatherMapProvider myProvider = new OpenWeatherMapProvider(apiKey);
        ActiveMQSender mySender = new ActiveMQSender("prediction.Weather", "tcp://localhost:61616");
        WeatherController controller = new WeatherController(mySender, myProvider);
        controller.runTask();
    }
}
