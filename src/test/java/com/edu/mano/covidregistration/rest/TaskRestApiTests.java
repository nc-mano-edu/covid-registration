package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.TASKS_BASE_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TaskRestApiTests extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void checkFindAll() throws Exception {
        mockMvc.perform(get(TASKS_BASE_PREFIX + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkFind() throws Exception {
        MvcResult result = mockMvc.perform(get(TASKS_BASE_PREFIX + "/1"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/TaskRestApiTest_checkFind_response.json");

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
        Task task = new Task();
        task.setName("Another task");
        task.setSchedule("* * * * * *");

        MvcResult result = mockMvc.perform(post(TASKS_BASE_PREFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andReturn();

        return Long.parseLong(result.getResponse().getContentAsString());
    }

    public void checkUpdate(Long attributeId) throws Exception {
        Task task = new Task();
        task.setName("Another task update");
        task.setSchedule("* * * * *");

        mockMvc.perform(put(TASKS_BASE_PREFIX + "/" + attributeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isAccepted());
    }

    public void checkDelete(Long attributeId) throws Exception {
        mockMvc.perform(delete(TASKS_BASE_PREFIX + "/" + attributeId))
                .andExpect(status().isAccepted());
    }
}
