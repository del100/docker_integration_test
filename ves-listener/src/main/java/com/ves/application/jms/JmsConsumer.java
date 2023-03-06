package com.ves.application.jms;

import com.ves.application.jms.model.RandomLongValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

//@Component
@Slf4j
public class JmsConsumer implements MessageListener {


    @Override
    @JmsListener(destination = "${active-mq.queue}")
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            RandomLongValue employee = (RandomLongValue)objectMessage.getObject();
            //do additional processing
            log.info("Received Message from Topic: "+ employee.toString());
        } catch(Exception e) {
            log.error("Received Exception while processing message: "+ e);
        }

    }
}

