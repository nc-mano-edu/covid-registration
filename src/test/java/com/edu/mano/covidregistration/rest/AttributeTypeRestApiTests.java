package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AttributeTypeRestApiTests extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void checkFindAll() throws Exception {
        mockMvc.perform(get("/attributeType/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkFind() throws Exception {
        MvcResult result = mockMvc.perform(get("/attributeType/1"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/AttributeTypeRestApiTest_checkFind_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }

    @Test
    public void checkLifeCycle() throws Exception {
        Long attributeId = checkAdd();
        Assertions.assertAll(
                () -> checkUpdate(attributeId),
                () -> checkDelete(attributeId)
        );
    }

    private Long checkAdd() throws Exception {
        AttributeType attributeType = new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?");

        MvcResult result = mockMvc.perform(post("/attributeType")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attributeType)))
                .andExpect(status().isOk())
                .andReturn();

        Pattern p = Pattern.compile("id ([0-9]+)");
        Matcher m = p.matcher(result.getResponse().getContentAsString());
        if (m.find()) {
            return Long.parseLong(m.group(1));
        }
        return null;
    }

    public void checkUpdate(Long attributeId) throws Exception {
        AttributeType attributeType = new AttributeType(1L, "Image value", "file:\\\\*");

        mockMvc.perform(put("/attributeType/" + attributeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attributeType)))
                .andExpect(status().isOk());
    }

    public void checkDelete(Long attributeId) throws Exception {
        mockMvc.perform(delete("/attributeType/" + attributeId))
                .andExpect(status().isOk());
    }
}
