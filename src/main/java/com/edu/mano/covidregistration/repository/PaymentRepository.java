package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.Payment;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findAll();

    Optional<Payment> findById(Long id);

}
