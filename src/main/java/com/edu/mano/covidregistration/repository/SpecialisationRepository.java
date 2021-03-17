package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.Specialisation;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

@Profile("!test")
public interface SpecialisationRepository extends CrudRepository<Specialisation, Long> {

    @NonNull
    List<Specialisation> findAll();

    @NonNull
    Optional<Specialisation> findById(@NonNull Long id);

}
