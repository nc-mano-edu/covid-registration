package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.repository.AttributeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockAttributeRepository implements AttributeRepository {

    private List<Attribute> attributes;

    MockAttributeRepository() {
        this.attributes = new ArrayList<>(Collections.singletonList(
                new Attribute(1L, "User age",
                        new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?"))
        ));
    }

    @Override
    public List<Attribute> findAll() {
        return attributes;
    }

    @Override
    public Optional<Attribute> findById(Long id) {
        return attributes.stream()
                .filter(attr -> id.equals(attr.getId()))
                .findAny();
    }

    @Override
    public <S extends Attribute> S save(S s) {
        long id = attributes.get(attributes.size() - 1).getId() + 1;
        s.setId(id);
        attributes.add(s);
        return s;
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
        attributes.remove(attribute);
    }

    @Override
    public void deleteAll(Iterable<? extends Attribute> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
