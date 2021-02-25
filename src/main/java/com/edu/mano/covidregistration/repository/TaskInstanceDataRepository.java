package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.TaskInstanceData;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public interface TaskInstanceDataRepository extends CrudRepository<TaskInstanceData, Long> {
    List<TaskInstanceData> findAll();

    Optional<TaskInstanceData> findById(Long id);
}