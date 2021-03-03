package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.PaymentData;
import com.edu.mano.covidregistration.domain.User;
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

public class PaymentDataRestApiTests extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final PaymentData pd = new PaymentData(1L, new User(), "1111222233334444", true);

    @Test
    public void getAllPaymentDataTest() throws Exception {
        List<PaymentData> paymentsData = new ArrayList<>();
        paymentsData.add(pd);
        mockMvc.perform(get("/paymentData/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(paymentsData)));
    }

    @Test
    public void getOnePaymentDataTest() throws Exception {
        mockMvc.perform(get("/paymentData/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pd)));

        mockMvc.perform(get("/paymentData/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postPaymentDataTest() throws Exception {
        mockMvc.perform(
                post("/paymentData")
                        .content(objectMapper.writeValueAsString(pd))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pd)));
    }

    @Test
    public void deletePaymentDataTest() throws Exception {
        mockMvc.perform(delete("/paymentData/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/paymentData/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putPaymentDataTest() throws Exception {
        mockMvc.perform(
                put("/paymentData/1")
                        .content(objectMapper.writeValueAsString(pd))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pd)));

        mockMvc.perform(
                put("/paymentData/2")
                        .content(objectMapper.writeValueAsString(pd))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

}
