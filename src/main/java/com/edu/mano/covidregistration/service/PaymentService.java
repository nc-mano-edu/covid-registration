package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Payment;
import com.edu.mano.covidregistration.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment find(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public boolean delete(Long id) {
        if(paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Payment update(Long id, Payment payment) {
        if (paymentRepository.existsById(id)) {
            payment.setId(id);
            return save(payment);
        } else {
            return null;
        }
    }

}
