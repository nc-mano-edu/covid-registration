package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAll();

    Optional<Task> findById(Long id);
}