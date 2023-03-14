
package com.ves.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ves.application.jms.VesMessageProducer;
import com.ves.application.jms.model.RandomLongValue;
import com.veslistener.model.Event;

@RestController
public class ProduceMessageController {

    @Autowired
    VesMessageProducer vesMessageProducer;
    @Autowired
    RandomLongValue randomLongValue;

    @PostMapping("/eventListener/v1")
    public ResponseEntity<String> sendMessage(@RequestBody final Event payload) {
        randomLongValue.setRandomId(String.valueOf(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        vesMessageProducer.sendMessage(randomLongValue);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
