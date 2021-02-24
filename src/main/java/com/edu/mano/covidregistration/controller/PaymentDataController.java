package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.PaymentData;
import com.edu.mano.covidregistration.service.PaymentDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/paymentData")
public class PaymentDataController {

    private static final Logger log = LoggerFactory.getLogger(PaymentDataController.class);
    private final PaymentDataService paymentDataService;

    @Autowired
    public PaymentDataController(PaymentDataService paymentDataService) {
        this.paymentDataService = paymentDataService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentData>> findAll() {
        log.info("Retrieving a list of payments data");
        List<PaymentData> paymentsData = paymentDataService.findAll();
        return new ResponseEntity<>(paymentsData, paymentsData.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentData> find(@PathVariable Long id) {
        log.info("Retrieving a payment data with id: " + id);
        PaymentData paymentData = paymentDataService.find(id);
        return new ResponseEntity<>(paymentData, paymentData == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentData> save(@RequestBody @Valid PaymentData paymentData) {
        log.info("Saving payment data: " + paymentData.toString());
        PaymentData out = paymentDataService.save(paymentData);
        log.info("Saved with id: " + out.getId());
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        log.info("Delete payment data with id: " + id);
        if (paymentDataService.delete(id)) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentData> update(@PathVariable Long id, @RequestBody @Valid PaymentData paymentData) {
        log.info("Updating specialisation with id " + id + " to: " + paymentData.toString());
        PaymentData out = paymentDataService.update(id, paymentData);
        return new ResponseEntity<>(out, out == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

}
