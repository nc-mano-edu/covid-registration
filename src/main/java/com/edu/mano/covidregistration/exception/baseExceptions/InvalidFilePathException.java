package com.edu.mano.covidregistration.exception.baseExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidFilePathException extends CovidClinicException {
    public InvalidFilePathException() {
    }

    public InvalidFilePathException(String message) {
        super(message);
    }

    public InvalidFilePathException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFilePathException(Throwable cause) {
        super(cause);
    }

    public InvalidFilePathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
