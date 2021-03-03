package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Payment;
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

public class PaymentRestApiTests extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final Payment p = new Payment(1L, 1L,
            new PaymentData(1L, new User(), "1111222233334444", true),
            49.90); // Request ID замениться на сущность Request

    @Test
    public void getAllPaymentsTest() throws Exception {
        List<Payment> payments = new ArrayList<>();
        payments.add(p);
        mockMvc.perform(get("/payment/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(payments)));
    }

    @Test
    public void getOnePaymentTest() throws Exception {
        mockMvc.perform(get("/payment/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p)));

        mockMvc.perform(get("/payment/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postPaymentTest() throws Exception {
        mockMvc.perform(
                post("/payment")
                        .content(objectMapper.writeValueAsString(p))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p)));
    }

    @Test
    public void deletePaymentTest() throws Exception {
        mockMvc.perform(delete("/payment/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/payment/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putPaymentTest() throws Exception {
        mockMvc.perform(
                put("/payment/1")
                        .content(objectMapper.writeValueAsString(p))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p)));

        mockMvc.perform(
                put("/payment/2")
                        .content(objectMapper.writeValueAsString(p))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

}
