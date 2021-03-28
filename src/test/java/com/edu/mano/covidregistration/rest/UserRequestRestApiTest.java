package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRequestRestApiTest extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    final UserRequest userRequest = new UserRequest(1l, null, null, "in progress", null, null);

    @Test
    void MockMvcTest() {
        assertNotNull(mockMvc);
    }

    @Test
    @Disabled
    public void findAllTest() throws Exception {
        List<UserRequest> userRequests = new ArrayList<>();
        userRequests.add(userRequest);

        MvcResult result = mockMvc.perform(get("/backend/userRequest?userId=1"))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.readTree(objectMapper.writeValueAsString(userRequests)), objectMapper.readTree(result.getResponse().getContentAsString()));
    }

    @Test
    public void getSymptomBySymptomIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/backend/userRequest/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userRequest)))
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();
        System.out.println("result:" + result.getResponse().getContentAsString());
        String expectedResult = AppUtility.getContentFromResourceFile("json/UserRequestRestApiTest_ReturnUserRequestById_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }

    @Test
    public void deleteSymptomByIdTest() throws Exception {
        mockMvc.perform(delete("/backend/symptom/1")).andExpect(status().isOk());
    }
}