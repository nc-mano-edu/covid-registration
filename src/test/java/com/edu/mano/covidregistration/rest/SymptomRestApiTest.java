package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.service.SymptomService;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SymptomRestApiTest extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;



    final Symptom symptom = new Symptom(1L, "cough", "red throat",null );

    @Test
    void MockMvcTest() {
        assertNotNull(mockMvc);
    }

    @Test
    public void findAllTest() throws Exception {
        List <Symptom> symptoms = new ArrayList<>();
        symptoms.add(symptom);

       mockMvc.perform(get("/backend/symptom/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(symptoms)));

    }

    @Test
    public void getSymptomBySymptomIdTest () throws Exception {
        MvcResult result = mockMvc.perform(get("/backend/symptom/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(symptom)))
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/SymptomRestApiTest_ReturnSymptomById_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }


    @Test
    public void createSymptomTest() throws Exception {

        mockMvc.perform(
                post("/backend/symptom")
                        .content(objectMapper.writeValueAsString(symptom))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(symptom)));

    }

    @Test
    public void  deleteSymptomByIdTest () throws Exception {
        mockMvc.perform(delete("/backend/symptom/1" )).andExpect(status().isOk());
    }




}