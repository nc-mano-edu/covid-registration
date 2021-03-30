package com.edu.mano.covidregistration;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integrationTest")
public class SpringBootIntegrationTests {

    public static final String SPECIALIZATION_BASE_PREFIX = "/specialisation";
    public static final String SYMPTOMS_BASE_PREFIX = "/backend/symptom";
    public static final String USER_REQUEST_BASE_PREFIX = "/backend/userRequest";
    public static final String ATTR_TYPES_BASE_PREFIX = "/attributeType";
    public static final String ATTRIBUTES_BASE_PREFIX = "/attribute";

}
