package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAll();

    Optional<Role> findById(Long id);
}
