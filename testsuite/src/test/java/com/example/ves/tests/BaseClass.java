
package com.example.ves.tests;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Before;

public class BaseClass {

    private static String ACTIVEMQ_URI = "tcp://localhost:61616";

    private Connection connection = null;
    private Session session = null;
    protected MessageConsumer consumer = null;

    @Before
    public void setup() throws JMSException {
        final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URI);
        connectionFactory.setUserName("artemis");
        connectionFactory.setPassword("simetraehcapa");
        connection = connectionFactory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        final Destination destination = session.createQueue("VES_events");

        consumer = session.createConsumer(destination);
    }

    @After
    public void cleanup() throws JMSException {
        consumer.close();
        session.close();
        connection.close();
    }
}
