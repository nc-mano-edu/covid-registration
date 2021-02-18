package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.repository.SymptomRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Profile("test")
public class MockSymptomRepository implements SymptomRepository {

    private List<Symptom> symptoms;

    public MockSymptomRepository () {
        this.symptoms = new ArrayList<>(Collections.singletonList(
                new Symptom(1L, "cough", "red throat",null )));
    }

    @Override
    public List<Symptom> findAll() {
        return symptoms;
    }

    @Override
    public Optional<Symptom> findById(Long id) {
        return symptoms.stream()
                .filter(symp -> id.equals(symp.getSymptomId()))
                .findAny();
    }

    @Override
    public <S extends Symptom> S save(S s) {
        Long symptomId = symptoms.get(symptoms.size()-1).getSymptomId() + 1;
        s.setSymptomId(symptomId);
        s.setDescription("new description");
        symptoms.add(s);
        return s;
    }

    @Override
    public <S extends Symptom> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Symptom findSymptomBySymptomId(Long id) {
        return null;
    }


    @Override
    public boolean existsById(Long aLong) {
        return false;
    }


    @Override
    public Iterable<Symptom> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        symptoms.remove(id);
    }

    @Override
    public void delete(Symptom symptom) {

    }

    @Override
    public void deleteAll(Iterable<? extends Symptom> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
