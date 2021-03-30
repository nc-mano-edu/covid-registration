package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserById(long id);

}
