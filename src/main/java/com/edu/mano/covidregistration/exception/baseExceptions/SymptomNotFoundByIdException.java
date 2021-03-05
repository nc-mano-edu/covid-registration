package com.edu.mano.covidregistration.exception.baseExceptions;

public class SymptomNotFoundByIdException extends CovidClinicException {
    public SymptomNotFoundByIdException() {
        super();
    }

    public SymptomNotFoundByIdException(String message) {
        super(message);
    }

    public SymptomNotFoundByIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public SymptomNotFoundByIdException(Throwable cause) {
        super(cause);
    }

    public SymptomNotFoundByIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
