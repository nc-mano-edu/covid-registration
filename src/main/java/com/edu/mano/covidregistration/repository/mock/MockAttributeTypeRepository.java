package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.repository.AttributeTypeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockAttributeTypeRepository implements AttributeTypeRepository {

    private List<AttributeType> attributeTypes;

    MockAttributeTypeRepository() {
        this.attributeTypes = new ArrayList<>(Collections.singletonList(
                new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?")));
    }


    @Override
    public List<AttributeType> findAll() {
        return attributeTypes;
    }

    @Override
    public Iterable<AttributeType> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public Optional<AttributeType> findById(Long id) {
        return attributeTypes.stream()
                .filter(attr -> id.equals(attr.getId()))
                .findAny();
    }

    @Override
    public <S extends AttributeType> S save(S s) {
        long id = attributeTypes.get(attributeTypes.size() - 1).getId() + 1;
        s.setId(id);
        attributeTypes.add(s);
        return s;
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
        attributeTypes.remove(attributeType);
    }

    @Override
    public void deleteAll(Iterable<? extends AttributeType> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends AttributeType> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }


    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
