package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Payment;
import com.edu.mano.covidregistration.domain.PaymentData;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.repository.PaymentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockPaymentRepository implements PaymentRepository {

    private final List<Payment> payments = new ArrayList<>();

    MockPaymentRepository() {
        Payment p = new Payment(1L, 1L,
                new PaymentData(1L, new User(), "1111222233334444", true),
                49.90); // Request ID замениться на сущность Request
        payments.add(p);
    }

    @Override
    public <S extends Payment> S save(S s) {
        s.setId(1L);
        return s;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        if (id == 1) {
            return Optional.of(payments.get(0));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public <S extends Payment> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return aLong == 1;
    }

    @Override
    public List<Payment> findAll() {
        return payments;
    }

    @Override
    public Iterable<Payment> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 1;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Payment payment) {

    }

    @Override
    public void deleteAll(Iterable<? extends Payment> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
