package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.service.SymptomService;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SymptomRestApiTest extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SymptomService symptomService;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    public void shouldFindAllSymptoms () throws Exception {
        mockMvc.perform(get("/backend/symptom/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkFind() throws Exception {
        MvcResult result = mockMvc.perform(get("/backend/symptom/1"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResult = result.getResponse().getContentAsString();
        String expectedResult = AppUtility.getContentFromResourceFile("json/AttributeRestApiTest_checkFind_response.json");

        Assertions.assertEquals(objectMapper.readTree(expectedResult), objectMapper.readTree(actualResult));
    }


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
    void shouldCreateSymptom() throws Exception {

        mockMvc.perform(post("/backend/symptom")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"symptomId\": \"1l\", \"name\": \"кашель\", \"description\": \"боль в горле\", \"userRequests\": \"null\"   }"))
                .andExpect(status().isCreated());
    }

}