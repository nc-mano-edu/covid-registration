package com.edu.mano.covidregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CovidRegistrationApplication {
    public static final String SPECIALIZATION_BASE_PREFIX = "/backend/specialisation";
    public static final String SYMPTOMS_BASE_PREFIX = "/backend/symptom";
    public static final String USER_REQUEST_BASE_PREFIX = "/backend/userRequest";
    public static final String USER_BASE_PREFIX = "/backend/users";
    public static final String ATTR_TYPES_BASE_PREFIX = "/backend/attributeType";
    public static final String ATTRIBUTES_BASE_PREFIX = "/backend/attribute";
    public static final String TASKS_BASE_PREFIX = "/backend/task";
    public static final String TASKS_INSTANCE_BASE_PREFIX = "/backend/taskInstance";
    public static final String TASKS_INSTANCE_DATA_BASE_PREFIX = "/backend/taskInstanceData";
    public static final String ROLE_BASE_PREFIX = "/backend/Role";
    public static final String LOGIN_BASE_PREFIX = "/login";
    public static final String REGISTER_BASE_PREFIX = "/registeruser";

    public static void main(String[] args) {
        SpringApplication.run(CovidRegistrationApplication.class, args);
    }

}
