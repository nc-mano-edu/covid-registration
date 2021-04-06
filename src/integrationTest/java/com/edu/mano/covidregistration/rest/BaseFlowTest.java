package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootIntegrationTests;
import com.edu.mano.covidregistration.TestDataPreparation;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.tools.AppUtility;
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
import java.util.TimeZone;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.ROLE_BASE_PREFIX;
import static com.edu.mano.covidregistration.CovidRegistrationApplication.SPECIALIZATION_BASE_PREFIX;
import static com.edu.mano.covidregistration.CovidRegistrationApplication.SYMPTOMS_BASE_PREFIX;
import static com.edu.mano.covidregistration.CovidRegistrationApplication.USER_BASE_PREFIX;
import static com.edu.mano.covidregistration.CovidRegistrationApplication.USER_REQUEST_BASE_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BaseFlowTest extends SpringBootIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestDataPreparation testDataPreparation;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void init() {
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Samara"));
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(sdf);
    }

    @Test
    @Order(1)
    public void integrationFlow() throws Exception {
        createSpecializations();
        createRoles();
        createSymptoms();

        checkUser();

        createUserRequest();
    }

    private void createRoles() throws Exception {
        createRole("doctor", "doctor desc");
        createRole("patient", "patient desc");
        createRole("someone", "for removal");
        updateRole(1L, "Doctor", "Doctor descr");
        updateRole(2L, "Patient", "Patient descr");
        deleteRole(3L);
        MvcResult result = mockMvc.perform(
                get(ROLE_BASE_PREFIX + "/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();

        String expectedResult = AppUtility.getContentFromResourceFile("json/getRoles_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));


    }

    private String inputJsonRequestModificationForRoles(String name, String desc) {
        String inputJsonRequest = testDataPreparation.getJson("json/createRole_request.json");
        inputJsonRequest = inputJsonRequest.replace("#name#", name);
        inputJsonRequest = inputJsonRequest.replace("#desc#", desc);

        return inputJsonRequest;
    }

    private MvcResult createRole(String name, String desc) throws Exception {
        String inputJsonRequest = inputJsonRequestModificationForRoles(name, desc);

        MvcResult result = mockMvc.perform(
                post(ROLE_BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return result;

    }

    private void updateRole(Long id, String name, String desc) throws Exception {
        String inputJsonRequest = inputJsonRequestModificationForRoles(name, desc);

        mockMvc.perform(
                put(ROLE_BASE_PREFIX + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private void deleteRole(Long id) throws Exception {
        mockMvc.perform(
                delete(ROLE_BASE_PREFIX + "/" + id)
                        .accept("Object successfully removed"));

    }

    private void checkUser() throws Exception {
        createUser("Max", "Jo", "Petersen");
        User secondUser = createUser("Max2", "Jo2", "Petersen2");

        updateUser(secondUser.getId());

        deleteUser(secondUser.getId());

        MvcResult result = mockMvc.perform(
                get(USER_BASE_PREFIX + "/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/getUsers_response.json");
        Assertions.assertEquals(objectMapper.readTree(actualResult), objectMapper.readTree(expectedResult));
    }

    private User createUser(String firstName, String secondName, String lastName) throws Exception {
        String inputJsonRequest = testDataPreparation.getJson("json/createUser_request.json")
                .replace("#firstName#", firstName)
                .replace("#middleName#", secondName)
                .replace("#lastName#", lastName);

        MvcResult result = mockMvc.perform(
                post(USER_BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
    }

    private void updateUser(Long id) throws Exception {
        String inputJsonRequest = testDataPreparation.getJson("json/createUser_request.json")
                .replace("#firstName#", "MaxUpdated")
                .replace("#middleName#", "JoUpdated")
                .replace("#lastName#", "PetersonUpdated");

        mockMvc.perform(
                put(USER_BASE_PREFIX + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private void deleteUser(Long id) throws Exception {
        mockMvc.perform(
                delete(USER_BASE_PREFIX + "/" + id)
                        .accept("Object successfully removed"));

        MvcResult result = mockMvc.perform(
                get(USER_BASE_PREFIX + "/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();

        String expectedResult = testDataPreparation.getJson("json/getUsers_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }

    private void createUserRequest() throws Exception {
        String inputJsonRequest = testDataPreparation.getJson("json/createUserRequest_request.json");
        String currentDate = TestDataPreparation.getCurrentDate();
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

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
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

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
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

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
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
}
