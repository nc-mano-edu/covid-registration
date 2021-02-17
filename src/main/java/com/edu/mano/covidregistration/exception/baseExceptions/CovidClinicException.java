package com.edu.mano.covidregistration.exception.baseExceptions;

public class CovidClinicException extends RuntimeException {
    public CovidClinicException() {
    }

    public CovidClinicException(String message) {
        super(message);
    }

    public CovidClinicException(String message, Throwable cause) {
        super(message, cause);
    }

    public CovidClinicException(Throwable cause) {
        super(cause);
    }

    public CovidClinicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
