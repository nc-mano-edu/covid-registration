package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.Roles;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public interface RolesRepository extends CrudRepository<Roles, Long> {

    @Override
    List<Roles> findAll();

    Optional<Roles> findById(Long id);
}
