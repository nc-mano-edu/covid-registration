package com.edu.mano.covidregistration.exception;

import com.edu.mano.covidregistration.exception.baseExceptions.CovidClinicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CovidClinicException.class)
    public ResponseEntity handleException(CovidClinicException exception) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(exception.getMessage(), exception);
        }
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
