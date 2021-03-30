package com.edu.mano.covidregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CovidRegistrationApplication {
    public static final String SPECIALIZATION_BASE_PREFIX = "/specialisation";
    public static final String SYMPTOMS_BASE_PREFIX = "/backend/symptom";
    public static final String USER_REQUEST_BASE_PREFIX = "/backend/userRequest";
    public static final String USER_BASE_PREFIX = "/users";
    public static final String ATTR_TYPES_BASE_PREFIX = "/attributeType";
    public static final String ATTRIBUTES_BASE_PREFIX = "/attribute";
    public static final String TASKS_BASE_PREFIX = "/task";

    public static void main(String[] args) {
        SpringApplication.run(CovidRegistrationApplication.class, args);
    }

}
