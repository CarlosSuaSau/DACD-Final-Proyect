package org.example.control;

import com.google.gson.Gson;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.model.Place;

public class ActiveMQSender implements PlacesStore {
    private final String subject;
    private final String url;

    public ActiveMQSender(String subject, String url){
        this.subject = subject;
        this.url = url;
    }



    @Override
    public void save(Place place) {
        try{
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(placeToJson(place));
            producer.send(message);
            connection.close();
        } catch (JMSException error){
            throw new RuntimeException(error);
        }
    }

    public String placeToJson(Place place){
        Gson gson = new Gson();
        String jsonString = gson.toJson(place);
        return jsonString;
    }
}
