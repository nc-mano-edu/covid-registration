package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.repository.RolesRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockRolesRepository implements RolesRepository {

    private Roles newRole;

    private List<Roles> roles;

    public MockRolesRepository(){

        newRole = new Roles(1L, "MockRole", "MockDesc");

        roles = new ArrayList<>(Collections.singletonList(newRole));
    }

    @Override
    public List<Roles> findAll() {

        return roles;
    }

    @Override
    public Iterable<Roles> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {

        for (Roles role : roles){

            if (role.getRoleId() == id){

                roles.remove(role);
            }
        }
    }

    @Override
    public void delete(Roles roles) {

        this.roles.remove(roles);
    }

    @Override
    public void deleteAll(Iterable<? extends Roles> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Roles> S save(S s) {

        roles.add(s);
        return s;
    }

    @Override
    public <S extends Roles> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Roles> findById(Long id) {

        if (id.equals(newRole.getRoleId())) {

            return Optional.of(newRole);

        } else {

            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(Long id) {

        return roles.get(id.intValue()) != null;
    }
}
