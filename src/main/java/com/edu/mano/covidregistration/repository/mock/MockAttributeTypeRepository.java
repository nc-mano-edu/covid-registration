package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.repository.AttributeTypeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockAttributeTypeRepository  implements AttributeTypeRepository {
    @Override
    public List<AttributeType> findAll() {
        return null;
    }

    @Override
    public Iterable<AttributeType> findAllById(Iterable<Long> iterable) {
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
    public void delete(AttributeType attributeType) {

    }

    @Override
    public void deleteAll(Iterable<? extends AttributeType> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends AttributeType> S save(S s) {
        return null;
    }

    @Override
    public <S extends AttributeType> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<AttributeType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
