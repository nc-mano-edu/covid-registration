package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.*;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskInstanceDataRestApiTests extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void checkFindAll() throws Exception {
        mockMvc.perform(get("/taskInstanceData/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkFind() throws Exception {
        MvcResult result = mockMvc.perform(get("/taskInstanceData/1"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/TaskInstanceDataRestApiTest_checkFind_response.json");

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
        TaskInstanceData taskInstanceData = new TaskInstanceData();
        Date createdDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("RUS"));
            createdDate = sdf.parse("2021-02-25 22:00:00");
        } catch (ParseException e) {
            createdDate = null;
        }
        TaskInstance taskInstance = new TaskInstance(1L,
                new Task(1L, "Some task", "* * * * *", "Some description", Collections.singletonList(
                        new Attribute(1L, "User age",
                                new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?")))
                ), 1L, createdDate, null, false);
        Attribute attribute = new Attribute(1L, "User age",
                new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?"));

        taskInstanceData.setTaskInstance(taskInstance);
        taskInstanceData.setAttribute(attribute);

        MvcResult result = mockMvc.perform(post("/taskInstanceData")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskInstanceData)))
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
        TaskInstanceData taskInstanceData = new TaskInstanceData();
        Date createdDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("RUS"));
            createdDate = sdf.parse("2021-02-25 22:00:00");
        } catch (ParseException e) {
            createdDate = null;
        }
        TaskInstance taskInstance = new TaskInstance(1L,
                new Task(1L, "Some task", "* * * * *", "Some description", Collections.singletonList(
                        new Attribute(1L, "User age",
                                new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?")))
                ), 1L, createdDate, null, false);
        Attribute attribute = new Attribute(1L, "User age",
                new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?"));

        taskInstanceData.setTaskInstance(taskInstance);
        taskInstanceData.setAttribute(attribute);

        mockMvc.perform(put("/taskInstanceData/" + attributeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskInstanceData)))
                .andExpect(status().isOk());
    }

    public void checkDelete(Long attributeId) throws Exception {
        mockMvc.perform(delete("/taskInstanceData/" + attributeId))
                .andExpect(status().isOk());
    }
}
