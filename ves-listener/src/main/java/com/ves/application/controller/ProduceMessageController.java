package com.ves.application.controller;

import com.ves.application.jms.model.RandomLongValue;
import com.veslistener.model.Event;
import com.ves.application.jms.VesMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class ProduceMessageController {

    @Autowired
    VesMessageProducer vesMessageProducer;

    @PostMapping("/eventListener/v1")
    public ResponseEntity<String> sendMessage(@RequestBody Event payload){
        RandomLongValue randomLongValue = new RandomLongValue();
        randomLongValue.setRandomId(String.valueOf(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        vesMessageProducer.sendMessage(randomLongValue);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
