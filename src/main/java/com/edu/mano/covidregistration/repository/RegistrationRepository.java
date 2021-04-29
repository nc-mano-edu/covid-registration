package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends CrudRepository <User, Long> {

    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);
}
