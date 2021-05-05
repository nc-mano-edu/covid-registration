package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.UserRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("!test")
public interface UserRequestRepository extends CrudRepository<UserRequest, Long> {
    List<UserRequest> findAll();

    UserRequest findUserRequestByRequestId(Long id);

    List<UserRequest> findUserRequestsByUserId(Long userId);

    List<UserRequest> findAllByEndDateIsNull();
}
