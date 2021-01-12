package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.domain.UserRoles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();

    User findById(long id);

}
