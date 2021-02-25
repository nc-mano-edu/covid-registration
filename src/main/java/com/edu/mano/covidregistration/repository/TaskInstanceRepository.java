package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.TaskInstance;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public interface TaskInstanceRepository extends CrudRepository<TaskInstance, Long> {
    List<TaskInstance> findAll();

    Optional<TaskInstance> findById(Long id);
}