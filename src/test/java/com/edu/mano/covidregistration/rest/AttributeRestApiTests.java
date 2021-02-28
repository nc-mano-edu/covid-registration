package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Attribute;
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

public class AttributeRestApiTests extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void checkFindAll() throws Exception {
        mockMvc.perform(get("/attribute/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkFind() throws Exception {
        MvcResult result = mockMvc.perform(get("/attribute/1"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/AttributeRestApiTest_checkFind_response.json");

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
        Attribute attr = new Attribute();
        attr.setName("Name");
        attr.setAttributeType(new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?"));

        MvcResult result = mockMvc.perform(post("/attribute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attr)))
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
        Attribute attr = new Attribute();
        attr.setId(attributeId);
        attr.setName("Surname");
        attr.setAttributeType(new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?"));

        mockMvc.perform(put("/attribute/" + attributeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attr)))
                .andExpect(status().isOk());
    }

    public void checkDelete(Long attributeId) throws Exception {
        mockMvc.perform(delete("/attribute/" + attributeId))
                .andExpect(status().isOk());
    }
}
