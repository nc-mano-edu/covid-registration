package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Specialisation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

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
        mockMvc.perform(get("/specialisation/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(specialisations)));
    }

    @Test
    public void getOneSpecialisationTest() throws Exception {
        mockMvc.perform(get("/specialisation/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(s)));

        mockMvc.perform(get("/specialisation/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postSpecialisationTest() throws Exception {
        mockMvc.perform(
                post("/specialisation")
                        .content(objectMapper.writeValueAsString(s))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(s)));
    }

    @Test
    public void deleteSpecialisationTest() throws Exception {
        mockMvc.perform(delete("/specialisation/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/specialisation/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putSpecialisationTest() throws Exception {
        mockMvc.perform(
                put("/specialisation/1")
                        .content(objectMapper.writeValueAsString(s))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(s)));

        mockMvc.perform(
                put("/specialisation/2")
                        .content(objectMapper.writeValueAsString(s))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

}
