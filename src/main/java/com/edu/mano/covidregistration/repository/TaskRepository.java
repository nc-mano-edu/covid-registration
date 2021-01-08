package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAll();

    Optional<Task> findById(Long id);
}