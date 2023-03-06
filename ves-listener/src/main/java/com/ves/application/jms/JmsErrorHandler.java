package com.ves.application.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ErrorHandler;

@Slf4j
public class JmsErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        log.error("Error Message when connecting to queue : {}", t.getMessage());
    }
}
