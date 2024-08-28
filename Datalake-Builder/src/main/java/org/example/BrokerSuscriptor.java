package org.example;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;
import java.util.List;

public class BrokerSuscriptor {
    private final String subject;
    private final String url;

    public BrokerSuscriptor(String subject, String url) {
        this.subject = subject;
        this.url = url;
    }

    public List<String> obtainEvents(){
        List<String> events = new ArrayList<>();
        String event;
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);
            MessageConsumer consumer = session.createConsumer(destination);
            while (true){
                Message message = consumer.receive(1000);
                if (message == null) {
                    break;
                }
                if (message instanceof TextMessage textMessage) {
                    event = textMessage.getText();
                    message.acknowledge();
                    events.add(event);
                }
            }
            connection.close();
            return events;
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return null;
    }
}
