package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.service.SymptomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SymptomRestApiTest extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SymptomService symptomService;

    private final Symptom s = new Symptom(1l,"кашель","боль в горле", null);

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    //***  получаю все симптомы  ***
    @Test
    @DisplayName("Check list of symptoms")
    void shouldReturnListOfSymptoms () throws Exception{
        when(symptomService.findAll()).thenReturn(Arrays.asList(new Symptom(1l,"кашель","боль в горле",null),
                new Symptom(2l,"ожог","покраснение вокруг руки",null)));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/backend/symptom/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("кашель"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("боль в горле"));
    }

    @Test
    @DisplayName("getSymptomById")
    public void shouldGetSymptomById1() throws Exception {

        when(symptomService.findSymptomById(1l)).thenReturn(new Symptom(1l, "кашель", "боль в горле", null));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/backend/symptom/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(get("/backend/symptom//2"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldDeleteSymptom2() throws Exception {
        mockMvc.perform(delete("/backend/symptom/" + 1l)).andExpect(status().isOk());
        mockMvc.perform(get("/backend/symptom/" + 1l)).andExpect(status().isNotFound());
    }




}