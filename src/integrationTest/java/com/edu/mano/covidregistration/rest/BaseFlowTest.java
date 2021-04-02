package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootIntegrationTests;
import com.edu.mano.covidregistration.TestDataPreparation;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.SPECIALIZATION_BASE_PREFIX;
import static com.edu.mano.covidregistration.CovidRegistrationApplication.SYMPTOMS_BASE_PREFIX;
import static com.edu.mano.covidregistration.CovidRegistrationApplication.USER_BASE_PREFIX;
import static com.edu.mano.covidregistration.CovidRegistrationApplication.USER_REQUEST_BASE_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BaseFlowTest extends SpringBootIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestDataPreparation testDataPreparation;

    @Test
    @Order(1)
    public void integrationFlow() throws Exception {
        createSpecializations();
        createRoles();
        createSymptoms();

        createUser();

        createUserRequest();

        //updateSymptom();
        updateSymptom2(1);
        deleteSymptom(1);
    }

    private void createRoles() {
        //todo make creation via test, and not in data.sql
    }

    private void createUser() throws Exception {
        String inputJsonRequest = testDataPreparation.getJson("json/createUser_request.json");

        mockMvc.perform(
                post(USER_BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result = mockMvc.perform(
                get(USER_BASE_PREFIX + "/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();

        String expectedResult = AppUtility.getContentFromResourceFile("json/getUsers_response.json");

        Assertions.assertEquals(objectMapper.readTree(actualResult), objectMapper.readTree(expectedResult));
    }

    private void createUserRequest() throws Exception {
        String inputJsonRequest = testDataPreparation.getJson("json/createUserRequest_request.json");
        String currentDate = testDataPreparation.getCurrentDate();
        inputJsonRequest = inputJsonRequest.replace("#startDate#", currentDate);

        MvcResult result = mockMvc.perform(
                post(USER_REQUEST_BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String actualResult = result.getResponse().getContentAsString();

        String expectedResult = AppUtility.getContentFromResourceFile("json/createUserRequest_response.json");
        expectedResult = expectedResult.replace("#startDate#", currentDate);

        Assertions.assertEquals(objectMapper.readTree(actualResult), objectMapper.readTree(expectedResult));
    }

    private void createSpecializations() throws Exception {

        createSpecialization("therapist");
        createSpecialization("immunologist");

        MvcResult result = mockMvc.perform(
                get(SPECIALIZATION_BASE_PREFIX + "/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/createSpecializations_response.json");

        Assertions.assertEquals(objectMapper.readTree(actualResult), objectMapper.readTree(expectedResult));
    }

    private void createSymptoms() throws Exception {

        createSymptom("cough");
        createSymptom("temperature");

        MvcResult result = mockMvc.perform(
                get(SYMPTOMS_BASE_PREFIX + "/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/createSymptoms_response.json");

        Assertions.assertEquals(objectMapper.readTree(actualResult), objectMapper.readTree(expectedResult));
    }

    private MvcResult createSymptom(String symptom) throws Exception {
        String inputJsonRequest = testDataPreparation.getJson("json/createSymptom_request.json");
        inputJsonRequest = inputJsonRequest.replace("#name_pattern#", symptom);

        MvcResult result = mockMvc.perform(
                post(SYMPTOMS_BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        return result;
    }

    private MvcResult createSpecialization(String spec) throws Exception {
        String inputJsonRequest = testDataPreparation.getJson("json/createSpecialization_request.json");
        inputJsonRequest = inputJsonRequest.replace("#name_pattern#", spec);

        MvcResult result = mockMvc.perform(
                post(SPECIALIZATION_BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        return result;
    }












    /** 1 try update **/

    private void updateSymptom () throws Exception {

        String inputJsonRequest = testDataPreparation.getJson("json/updateSymptom_request.json");

        mockMvc.perform(
                put("/backend/symptom/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result = mockMvc.perform(
                get("/backend/symptom/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();

        String expectedResult = AppUtility.getContentFromResourceFile("json/updateSymptom_response.json");

        Assertions.assertEquals(objectMapper.readTree(actualResult), objectMapper.readTree(expectedResult));

    }

    /**2 try update**/

    private void updateSymptom2 (int id) throws Exception {

        String inputJsonRequest = testDataPreparation.getJson("json/updateSymptom_request.json");

        mockMvc.perform(
                put(SYMPTOMS_BASE_PREFIX +"/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result = mockMvc.perform(
                get(SYMPTOMS_BASE_PREFIX + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();

        String expectedResult = AppUtility.getContentFromResourceFile("json/updateSymptom_response.json");

        Assertions.assertEquals(objectMapper.readTree(actualResult), objectMapper.readTree(expectedResult));

    }

    private void deleteSymptom(int id) throws Exception {

        mockMvc.perform(
                get(SYMPTOMS_BASE_PREFIX + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(
                delete(SYMPTOMS_BASE_PREFIX+ "/{id}", id))
                .andExpect(status().isOk());

        mockMvc.perform(
                get(SYMPTOMS_BASE_PREFIX + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }







}
