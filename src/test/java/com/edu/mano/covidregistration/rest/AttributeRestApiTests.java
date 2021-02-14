package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AttributeRestApiTests extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Long attributeId;

    @Test
    @Order(1)
    public void checkFindAll() throws Exception {
        mockMvc.perform(get("/attribute/all"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void checkFind() throws Exception {
        MvcResult result = mockMvc.perform(get("/attribute/1"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/AttributeRestApiTest_checkFind_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }

    @Test
    @Order(3)
    public void checkAdd() throws Exception {
        Attribute attr = new Attribute();
        attr.setName("Name");
        attr.setAttributeType(new AttributeType());

        MvcResult result = mockMvc.perform(post("/attribute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attr)))
                .andExpect(status().isOk())
                .andReturn();

        Pattern p = Pattern.compile("id ([0-9]+)");
        Matcher m = p.matcher(result.getResponse().getContentAsString());
        if (m.find()) {
            attributeId = Long.parseLong(m.group(1));
        }
    }

    @Test
    @Order(4)
    public void checkUpdate() throws Exception {
        Attribute attr = new Attribute();
        attr.setId(attributeId);
        attr.setName("Surname");
        attr.setAttributeType(new AttributeType());

        mockMvc.perform(put("/attribute/" + attributeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attr)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void checkDelete() throws Exception {
        mockMvc.perform(delete("/attribute/" + attributeId))
                .andExpect(status().isOk());
    }
}
