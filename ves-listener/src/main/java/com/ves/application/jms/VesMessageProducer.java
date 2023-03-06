package com.ves.application.jms;

import com.ves.application.jms.model.RandomLongValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VesMessageProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${active-mq.queue}")
    private String queue;

    public void sendMessage(RandomLongValue message){
        try{
            log.info("Sending message to queue: "+ queue);
            jmsTemplate.convertAndSend(queue, message);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
            log.error("Recieved Exception during send Message: ", e.getStackTrace());
        }
    }
}
