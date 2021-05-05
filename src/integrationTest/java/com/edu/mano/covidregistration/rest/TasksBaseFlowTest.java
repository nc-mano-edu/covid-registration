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

import static com.edu.mano.covidregistration.CovidRegistrationApplication.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TasksBaseFlowTest extends SpringBootIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestDataPreparation testDataPreparation;

    @Test
    @Order(2)
    public void integrationFlow() throws Exception {
        createAttrTypes();
        checkGetAttributes();
        createTasks();
    }

    private void createTasks() throws Exception {
        createTask("firstTask", "0 * * * * *", "run every minute");
        createTask("secondTask", "* * * * * *", "run every second");

        MvcResult result = mockMvc.perform(
                get(TASKS_BASE_PREFIX + "/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();

        String expectedResult = AppUtility.getContentFromResourceFile("json/getTasks_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }

    private void createTask(String name, String schedule, String description) throws Exception {
        String inputJsonRequest = testDataPreparation.getJson("json/createTask_request.json");
        inputJsonRequest = inputJsonRequest.replace("#name_pattern#", name);
        inputJsonRequest = inputJsonRequest.replace("#schedule_pattern#", schedule);
        inputJsonRequest = inputJsonRequest.replace("#description#", description);

        mockMvc.perform(
                post(TASKS_BASE_PREFIX)
                        .content(inputJsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    private void checkGetAttributes() throws Exception {
        MvcResult result = mockMvc.perform(
                get(ATTRIBUTES_BASE_PREFIX + "/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();

        String expectedResult = AppUtility.getContentFromResourceFile("json/getAttributes_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }

    private void createAttrTypes() throws Exception {
        createAttributeType("stringAttr", "");
        createAttributeType("dateAttr", "");

        MvcResult result = mockMvc.perform(
                get(ATTR_TYPES_BASE_PREFIX + "/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();

        String expectedResult = AppUtility.getContentFromResourceFile("json/createAttrTypes_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));

    }

    private void createAttributeType(String attrTypeName, String checkMask) throws Exception {
        String inputJsonRequest = testDataPreparation.getJson("json/createAttrType_request.json");
        inputJsonRequest = inputJsonRequest.replace("#name_pattern#", attrTypeName);
        inputJsonRequest = inputJsonRequest.replace("#checkMask#", checkMask);

        mockMvc.perform(
                post(ATTR_TYPES_BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

}
