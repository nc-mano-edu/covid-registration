package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Payment;
import com.edu.mano.covidregistration.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> findAll() {
        log.info("Retrieving a list of payments");
        List<Payment> payments = paymentService.findAll();
        return new ResponseEntity<>(payments, payments.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> find(@PathVariable Long id) {
        log.info("Retrieving a payment with id: " + id);
        Payment payment = paymentService.find(id);
        return new ResponseEntity<>(payment, payment == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Payment> save(@RequestBody @Valid Payment payment) {
        log.info("Saving payment: " + payment.toString());
        Payment out = paymentService.save(payment);
        log.info("Saved with id: " + out.getId());
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        log.info("Delete payment with id: " + id);
        if (paymentService.delete(id)) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> update(@PathVariable Long id, @RequestBody @Valid Payment payment) {
        log.info("Updating payment with id " + id + " to: " + payment.toString());
        Payment out = paymentService.update(id, payment);
        return new ResponseEntity<>(out, out == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

}
