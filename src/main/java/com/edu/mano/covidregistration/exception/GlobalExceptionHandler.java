package com.edu.mano.covidregistration.exception;

import com.edu.mano.covidregistration.exception.baseExceptions.CovidClinicException;
import com.edu.mano.covidregistration.tools.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final Tools tools = new Tools();

    @ExceptionHandler(CovidClinicException.class)
    public ResponseEntity handleException(CovidClinicException exception) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(exception.getMessage(), exception);
        }
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolation(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        String message = constraintViolations.iterator().next().getMessage();
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(message, exception);
        }
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        FieldError error = exception.getBindingResult().getFieldError();
        String message = error.getDefaultMessage();
        String name = error.getField();
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(name + ": " + message);
        }
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(name + ": " + message);
    }
}
