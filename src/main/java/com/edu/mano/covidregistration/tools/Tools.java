package com.edu.mano.covidregistration.tools;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Tools {

    /**
     * Get first error message from BindingResult
     */
    public String getErrorMessage(BindingResult bindingResult) {
        FieldError err = bindingResult.getFieldError();
        return err.getField() + ": " + err.getDefaultMessage();
    }
}
