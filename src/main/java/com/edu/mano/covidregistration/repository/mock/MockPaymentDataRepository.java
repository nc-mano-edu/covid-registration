package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.PaymentData;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.repository.PaymentDataRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockPaymentDataRepository implements PaymentDataRepository {

    private List<PaymentData> paymentsData = new ArrayList<>();

    MockPaymentDataRepository() {
        PaymentData pd = new PaymentData(1L, new User(), "1111222233334444", true);
        paymentsData.add(pd);
    }

    @Override
    public List<PaymentData> findAll() {
        return paymentsData;
    }

    @Override
    public Optional<PaymentData> findById(Long id) {
        if (id == 1) {
            return Optional.of(paymentsData.get(0));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public <S extends PaymentData> S save(S s) {
        s.setId(1);
        return s;
    }

    @Override
    public boolean existsById(Long aLong) {
        return aLong == 1;
    }

    @Override
    public Iterable<PaymentData> findAllById(Iterable<Long> iterable) {
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
    public void delete(PaymentData paymentData) {

    }

    @Override
    public void deleteAll(Iterable<? extends PaymentData> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends PaymentData> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }
}
