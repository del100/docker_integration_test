package com.ericsson.jmsconsumer;

import lombok.extern.slf4j.Slf4j;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnectionFactory;

@Slf4j
public class JmsConsumer {

    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://activemq:61616");
        connectionFactory.setUserName("artemis");
	connectionFactory.setPassword("simetraehcapa");
	Connection connection = null;
        Session session = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("VES_events");
            MessageConsumer consumer = session.createConsumer(destination);

            log.info("Listening for events...");

            while (true) {
                Message message = consumer.receive();
                String messageID = message.getJMSMessageID();
                log.info(messageID);
            }
        } catch (JMSException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                log.error(e.getMessage());
            }
        }
    }
}
