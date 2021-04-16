package com.edu.mano.covidregistration.repository;

import com.edu.mano.covidregistration.domain.TaskInstance;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public interface TaskInstanceRepository extends CrudRepository<TaskInstance, Long> {
    List<TaskInstance> findAll();

    Optional<TaskInstance> findById(Long id);

    List<TaskInstance> findByRequestRequestId(Long id);

    @Query("SELECT ti FROM task_instances ti WHERE ti.isActive = true")
    List<TaskInstance> findAllByActiveIsTrue();

    @Query(value = "SELECT t.task_instance_id,\n" +
            "       t.created_time,\n" +
            "       t.finished_time,\n" +
            "       t.is_active,\n" +
            "       t.request_id,\n" +
            "       t.task_id\n" +
            "FROM (SELECT row_number() over (PARTITION BY t.request_id) AS r,\n" +
            "             t.*\n" +
            "      FROM task_instances t\n" +
            "      WHERE t.task_id = ?1) t\n" +
            "WHERE t.r = 1",
            nativeQuery = true)
    List<TaskInstance> findOneForEachRequestByTaskId(Long taskId);
}