package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Role;
import com.edu.mano.covidregistration.repository.RoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockRoleRepository implements RoleRepository {

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public <S extends Role> S save(S s) {
        return null;
    }

    @Override
    public <S extends Role> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Role> findAllById(Iterable<Long> iterable) {
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
    public void delete(Role role) {

    }

    @Override
    public void deleteAll(Iterable<? extends Role> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
