package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RolesRepository extends CrudRepository<Roles, Long> {

    @Override
    List<Roles> findAll();

    Roles findById(long id);
}
