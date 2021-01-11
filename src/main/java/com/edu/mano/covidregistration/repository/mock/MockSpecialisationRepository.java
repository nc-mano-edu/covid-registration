package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Specialisation;
import com.edu.mano.covidregistration.repository.SpecialisationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockSpecialisationRepository implements SpecialisationRepository {

    Specialisation s = new Specialisation(1, "MockSpec", "MockDisc");

    @NonNull
    @Override
    public List<Specialisation> findAll() {
        List<Specialisation> specialisations = new ArrayList<>();
        specialisations.add(s);
        return specialisations;
    }

    @Override
    public Iterable<Specialisation> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <S extends Specialisation> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @NonNull
    @Override
    public Optional<Specialisation> findById(@NonNull Long id) {
        if (id == 1) {
            return Optional.of(s);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Specialisation save(Specialisation specialisation) {
        specialisation.setId(1);
        return specialisation;
    }

    @Override
    public boolean existsById(Long id) {
        return id == 1;
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public void delete(Specialisation specialisation) {

    }

    @Override
    public void deleteAll(Iterable<? extends Specialisation> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
