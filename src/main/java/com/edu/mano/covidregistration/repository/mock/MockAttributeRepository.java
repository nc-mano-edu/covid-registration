package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.repository.AttributeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockAttributeRepository implements AttributeRepository {
    @Override
    public List<Attribute> findAll() {
        return null;
    }

    @Override
    public Optional<Attribute> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public <S extends Attribute> S save(S s) {
        return null;
    }

    @Override
    public <S extends Attribute> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Attribute> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Attribute attribute) {

    }

    @Override
    public void deleteAll(Iterable<? extends Attribute> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
