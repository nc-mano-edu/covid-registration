package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Specialisation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.SPECIALIZATION_BASE_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SpecialisationRestTest extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    final Specialisation s = new Specialisation(1, "MockSpec", "MockDisc");

    @Test
    public void getAllSpecialisationTest() throws Exception {
        List<Specialisation> specialisations = new ArrayList<>();
        specialisations.add(s);
        MvcResult result = mockMvc.perform(get(SPECIALIZATION_BASE_PREFIX + "/all"))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = objectMapper.writeValueAsString(specialisations);

        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void getOneSpecialisationTest() throws Exception {
        mockMvc.perform(get(SPECIALIZATION_BASE_PREFIX + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(s)));

        mockMvc.perform(get(SPECIALIZATION_BASE_PREFIX + "/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postSpecialisationTest() throws Exception {
        mockMvc.perform(
                post(SPECIALIZATION_BASE_PREFIX)
                        .content(objectMapper.writeValueAsString(s))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(s)));
    }

    @Test
    public void deleteSpecialisationTest() throws Exception {
        mockMvc.perform(delete(SPECIALIZATION_BASE_PREFIX + "/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete(SPECIALIZATION_BASE_PREFIX + "/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putSpecialisationTest() throws Exception {
        mockMvc.perform(
                put(SPECIALIZATION_BASE_PREFIX + "/1")
                        .content(objectMapper.writeValueAsString(s))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(s)));

        mockMvc.perform(
                put(SPECIALIZATION_BASE_PREFIX + "/2")
                        .content(objectMapper.writeValueAsString(s))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

}
