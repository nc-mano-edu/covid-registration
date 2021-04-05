package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    Optional<User> findById(Long id);
}
