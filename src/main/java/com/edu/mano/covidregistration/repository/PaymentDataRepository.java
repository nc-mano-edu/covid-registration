package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.PaymentData;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public interface PaymentDataRepository extends CrudRepository<PaymentData, Long> {

    List<PaymentData> findAll();

    Optional<PaymentData> findById(Long id);

}
