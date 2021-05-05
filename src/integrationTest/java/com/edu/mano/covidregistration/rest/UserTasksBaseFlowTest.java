package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootIntegrationTests;
import com.edu.mano.covidregistration.TestDataPreparation;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.TaskInstanceData;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.TASKS_INSTANCE_BASE_PREFIX;
import static com.edu.mano.covidregistration.CovidRegistrationApplication.USER_BASE_PREFIX;
import static com.edu.mano.covidregistration.CovidRegistrationApplication.USER_REQUEST_BASE_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTasksBaseFlowTest extends SpringBootIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestDataPreparation testDataPreparation;

    private User user = new User();

    private UserRequest userRequest = new UserRequest();

    private List<TaskInstance> tasks = new ArrayList<>();

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    public void init() {
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Samara"));
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(sdf);
    }

    @Test
    @Order(3)
    public void integrationFlow() throws Exception {
        createUser();
        createUserRequest();
        getTasks();
        checkTasksIsActive(true);
        getActiveTasks();
        updateTasks();
        checkTasksIsActive(false);

        //todo improve testing
        //checkScheduleJobs();
    }

    private void createUser() throws Exception {
        user.setFirstName("FirstName");
        user.setMiddleName("MiddleName");
        user.setLastName("LastName");
        user.setPassword("123456789abcdefg!");
        user.setUsername("userLogin");
        user.setEmail("e@e.com");

        MvcResult result = mockMvc.perform(
                post(USER_BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        user = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<User>() {
        });
    }

    private void createUserRequest() throws Exception {
        userRequest.setUser(user);

        MvcResult result = mockMvc.perform(
                post(USER_REQUEST_BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        userRequest = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<UserRequest>() {
        });
    }

    private void checkTasksIsActive(boolean activeExpected) throws Exception {
        Assertions.assertTrue(tasks.stream().allMatch(task -> task.isActive() == activeExpected));
    }

    private void getTasks() throws Exception {

        String userID = String.valueOf(user.getId());

        MvcResult result = mockMvc.perform(get(USER_BASE_PREFIX + "/" + userID + "/active-tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = testDataPreparation.getJson("json/getUserTasks_response.json");

        tasks = objectMapper.readValue(actualResult, new TypeReference<List<TaskInstance>>() {
        });

        actualResult = actualResult.replaceAll("\"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\"", "null");
        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }

    private void updateTasks() throws Exception {

        String userID = String.valueOf(user.getId());

        for (TaskInstance task : tasks) {
            List<TaskInstanceData> data = task.getData();
            for (TaskInstanceData d : data) {
                d.setStringValue(String.format("TaskInstanceData %d in TaskInstance %d", d.getId(), task.getId()));
                d.setNumericValue(d.getId().intValue());
            }
            task.setData(data);
            mockMvc.perform(put(TASKS_INSTANCE_BASE_PREFIX + "/" + task.getId())
                    .content(objectMapper.writeValueAsString(task))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isAccepted());
        }

        MvcResult result = mockMvc.perform(get(USER_BASE_PREFIX + "/" + userID + "/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = testDataPreparation.getJson("json/updateUserTasks_response.json");

        tasks = objectMapper.readValue(actualResult, new TypeReference<List<TaskInstance>>() {
        });

        actualResult = actualResult.replaceAll("\"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\"", "null");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }

    private void getActiveTasks() throws Exception {
        MvcResult result = mockMvc.perform(get(TASKS_INSTANCE_BASE_PREFIX + "/active")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = testDataPreparation.getJson("json/getUserTasks_response.json");

        actualResult = actualResult.replaceAll("\"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\"", "null");
        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }

    private void checkScheduleJobs() {
        try {
            System.out.println("[Test] Waiting for scheduled tasks");
            TimeUnit.SECONDS.sleep(5);
            MvcResult result = mockMvc.perform(get(TASKS_INSTANCE_BASE_PREFIX + "/all")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            List<TaskInstance> lt = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<TaskInstance>>() {
            });
            Assertions.assertTrue(tasks.size() < lt.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
