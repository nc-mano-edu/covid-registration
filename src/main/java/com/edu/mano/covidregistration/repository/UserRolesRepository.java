package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.UserRoles;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("!test")
public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {

    @Override
    List<UserRoles> findAll();

    UserRoles findById(long id);
}
