package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("!test")
public interface SymptomRepository extends CrudRepository<Symptom,Long> {
    List<Symptom> findAll();

    Symptom findSymptomBySymptomId(Long id);

    void deleteById (Long id);

}
