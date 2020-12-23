package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
