package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.service.SymptomService;
import com.edu.mano.covidregistration.tools.AppUtility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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


    //  ****   Пытаюсь получить по Id  *****
    //Попытка 1 - неудача

    @Test
    @DisplayName("getSymptomById")
    public void shouldGetSymptomById1() throws Exception {

        String exampleJson = "{\"id\":\"1l\",\"name\":\"name\",\"description\":\"description\",\"userRequests\":\"null\"}";

        Mockito.when(symptomService.findSymptomById(Mockito.anyLong()))
                .thenReturn(new Symptom(1l,"name","description",null));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/backend/symptom/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        String expected = "{id:1l, name: name, description : description, userRequest:null }";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),false);

    }

    // 2 попытка - тоже не сработала
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }


    @Test
    public void shouldGetSymptomById2() throws Exception {
        MvcResult  mvcResult =mockMvc.perform(get("/backend/symptom/" + 1l))
                .andExpect(status().isOk())
                .andReturn();
        Symptom output = mapFromJson(mvcResult.getResponse().getContentAsString(), Symptom.class);
        Assertions.assertEquals(output.getSymptomId(),"1l");
    }


    // ******  добавляю симптом     *****
    //1. Попытка - не сработала

    @Test
    public void createSymptom1() throws Exception {

        String exampleCourseJson = "{\"id\":\"1l\",\"name\":\"name\",\"description\":\"description\", \"userRequests\":\"null\"]}";

        Symptom symptom = new Symptom(1l,"name","description",null );


        Mockito.when(symptomService.saveSymptom(symptom)).thenReturn(symptom);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/backend/symptom/")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleCourseJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    //2 Попытка - неудача
    @Test
    public void createSymptom2() throws Exception {
        Symptom symptom = new Symptom();
        symptom.setSymptomId(1l);
        symptom.setName("name");
        symptom.setDescription("description");
        symptom.setUserRequests(null);

        String jsonRequest = objectMapper.writeValueAsString(symptom);

        MvcResult result = mockMvc.perform(post("/backend/symptom").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContext = result.getResponse().getContentAsString();



    }



    // *****  - удаляю симптом     *****

    @Test
    public void shouldDeleteSymptom2() throws Exception {
        mockMvc.perform(delete("/backend/symptom/" + 1l)).andExpect(status().isOk());
        mockMvc.perform(get("/backend/symptom/" + 1l)).andExpect(status().isNotFound());
    }




}