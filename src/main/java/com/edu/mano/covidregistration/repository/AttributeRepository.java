package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.Attribute;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public interface AttributeRepository extends CrudRepository<Attribute, Long> {
    List<Attribute> findAll();

    Optional<Attribute> findById(Long id);
}