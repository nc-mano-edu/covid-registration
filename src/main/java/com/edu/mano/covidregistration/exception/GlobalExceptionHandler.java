package com.edu.mano.covidregistration.exception;

import com.edu.mano.covidregistration.exception.baseExceptions.CovidClinicException;
import com.edu.mano.covidregistration.exception.baseExceptions.InvalidDateException;
import com.edu.mano.covidregistration.exception.baseExceptions.SymptomNotFoundByIdException;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(CovidClinicException.class)
    public ResponseEntity handleException(CovidClinicException exception) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(exception.getMessage(), exception);
        }
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(EmptyResultDataAccessException exception) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(exception.getMessage(), exception);
        }
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
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

    @ExceptionHandler(SymptomNotFoundByIdException.class)
    protected ResponseEntity handleSymptomNotFoundByIdException(SymptomNotFoundByIdException exception) {
        return handleException(exception);
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

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException exception) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(exception.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.TEXT_PLAIN)
                .body(exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(exception.getMessage());
        }
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity handleInvalidDateException(InvalidDateException exception) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(exception.getMessage());
        }
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body(exception.getMessage());
    }
}
