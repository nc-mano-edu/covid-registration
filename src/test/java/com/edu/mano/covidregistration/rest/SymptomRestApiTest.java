package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.SYMPTOMS_BASE_PREFIX;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class SymptomRestApiTest extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    final Symptom symptom = new Symptom(1L, "cough", "red throat", null);

    @Test
    void MockMvcTest() {
        assertNotNull(mockMvc);
    }

    @Test
    public void findAllTest() throws Exception {
        List<Symptom> symptoms = new ArrayList<>();
        symptoms.add(symptom);

        mockMvc.perform(get(SYMPTOMS_BASE_PREFIX + "/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(symptoms)));

    }

    @Test
    public void getSymptomBySymptomIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get(SYMPTOMS_BASE_PREFIX + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(symptom)))
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/SymptomRestApiTest_ReturnSymptomById_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }


    @Test
    public void createSymptomTest() throws Exception {

        String request = objectMapper.writeValueAsString(symptom);

        MvcResult result = mockMvc.perform(
                post(SYMPTOMS_BASE_PREFIX)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(objectMapper.readTree(request), objectMapper.readTree(result.getResponse().getContentAsString()));
    }

    @Test
    public void deleteSymptomByIdTest() throws Exception {
        mockMvc.perform(delete(SYMPTOMS_BASE_PREFIX + "/1")).andExpect(status().isOk());
    }


}