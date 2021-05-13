package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public interface RegistrationRepository extends CrudRepository <User, Long> {

    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);
}
