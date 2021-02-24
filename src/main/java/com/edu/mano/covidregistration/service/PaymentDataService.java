package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.PaymentData;
import com.edu.mano.covidregistration.repository.PaymentDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentDataService {

    private final PaymentDataRepository paymentDataRepository;

    @Autowired
    public PaymentDataService(PaymentDataRepository paymentDataRepository) {
        this.paymentDataRepository = paymentDataRepository;
    }

    public List<PaymentData> findAll() {
        return paymentDataRepository.findAll();
    }

    public PaymentData find(Long id) {
        return paymentDataRepository.findById(id).orElse(null);
    }

    public PaymentData save(PaymentData paymentData) {
        return paymentDataRepository.save(paymentData);
    }

    public boolean delete(Long id) {
        if(paymentDataRepository.existsById(id)) {
            paymentDataRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public PaymentData update(Long id, PaymentData paymentData) {
        if (paymentDataRepository.existsById(id)) {
            paymentData.setId(id);
            return save(paymentData);
        } else {
            return null;
        }
    }

}
