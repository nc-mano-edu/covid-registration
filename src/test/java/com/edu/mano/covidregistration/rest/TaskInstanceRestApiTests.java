package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.enums.TreatmentState;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.TASKS_INSTANCE_BASE_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TaskInstanceRestApiTests extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(TaskInstanceRestApiTests.class);


    private final UserRequest userRequest = new UserRequest(1L, null, null, TreatmentState.STARTED, "recommendations", null, null);

    @Test
    public void checkFindAll() throws Exception {
        mockMvc.perform(get(TASKS_INSTANCE_BASE_PREFIX + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkFind() throws Exception {
        MvcResult result = mockMvc.perform(get(TASKS_INSTANCE_BASE_PREFIX + "/1"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResult = result.getResponse().getContentAsString();

        logger.info(actualResult);

        String expectedResult = AppUtility.getContentFromResourceFile("json/TaskInstanceRestApiTest_checkFind_response.json");

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
        TaskInstance taskInstance = new TaskInstance();
        Date createdDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Samara"));
            createdDate = sdf.parse("2021-02-25 22:00:00");
        } catch (ParseException e) {
            createdDate = null;
        }
        taskInstance.setTask(new Task(1L, "Some task", "* * * * *", "Some description", Collections.singletonList(
                new Attribute(1L, "User age",
                        new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?")))
        ));
        taskInstance.setRequest(userRequest);
        taskInstance.setCreatedTime(createdDate);

        MvcResult result = mockMvc.perform(post(TASKS_INSTANCE_BASE_PREFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskInstance)))
                .andExpect(status().isOk())
                .andReturn();

        return Long.parseLong(result.getResponse().getContentAsString());
    }

    public void checkUpdate(Long attributeId) throws Exception {
        TaskInstance taskInstance = new TaskInstance();
        Date createdDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Samara"));
            createdDate = sdf.parse("2021-02-25 20:00:00");
        } catch (ParseException e) {
            createdDate = null;
        }
        taskInstance.setTask(new Task(1L, "Another task", "* * * * *", "Some description", Collections.singletonList(
                new Attribute(1L, "User age",
                        new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?")))
        ));
        taskInstance.setRequest(userRequest);
        taskInstance.setCreatedTime(createdDate);

        mockMvc.perform(put(TASKS_INSTANCE_BASE_PREFIX + "/" + attributeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskInstance)))
                .andExpect(status().isAccepted());
    }

    public void checkDelete(Long attributeId) throws Exception {
        mockMvc.perform(delete(TASKS_INSTANCE_BASE_PREFIX + "/" + attributeId))
                .andExpect(status().isAccepted());
    }
}
