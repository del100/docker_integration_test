
package com.ves.application.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.jms.annotation.JmsListener;

import com.ves.application.jms.model.RandomLongValue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JmsConsumer implements MessageListener {

    @Override
    @JmsListener(destination = "${active-mq.queue}")
    public void onMessage(final Message message) {
        try {
            final ObjectMessage objectMessage = (ObjectMessage) message;
            final RandomLongValue value = (RandomLongValue) objectMessage.getObject();

            log.info("Received Message from Topic: " + value.toString());
        } catch (final Exception e) {
            log.error("Received Exception while processing message: " + e);
        }

    }
}
