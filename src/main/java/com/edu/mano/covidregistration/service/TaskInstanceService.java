package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.exception.baseExceptions.InvalidDateException;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.repository.TaskInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskInstanceService {

    private static final Logger log = LoggerFactory.getLogger(TaskInstanceService.class);

    private final TaskInstanceRepository taskInstanceRepository;

    private final TaskService taskService;

    @Autowired
    public TaskInstanceService(TaskInstanceRepository taskInstanceRepository, TaskService taskService) {
        this.taskInstanceRepository = taskInstanceRepository;
        this.taskService = taskService;
    }

    private void checkFinishedTime(TaskInstance taskInstance) {
        Date finishedTime = taskInstance.getFinishedTime();
        if (finishedTime != null && finishedTime.before(taskInstance.getCreatedTime())) {
            throw new InvalidDateException("Finished time can`t be earlier than the creation time");
        }
    }

    public List<TaskInstance> findAll() {
        log.info("Retrieving a list of TaskInstances");
        return taskInstanceRepository.findAll();
    }

    public TaskInstance find(Long id) {
        log.info("Retrieving an taskInstance with id " + id);
        try {
            return taskInstanceRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(TaskInstance.class, id);
        }
    }

    public Long add(TaskInstance taskInstance) {
        taskService.find(taskInstance.getTask().getId());
        checkFinishedTime(taskInstance);
        Long taskInstanceId = taskInstanceRepository.save(taskInstance).getId();
        log.info("TaskInstance created with id " + taskInstanceId);
        return taskInstanceId;
    }

    public void delete(Long id) {
        log.info("Deleting a taskInstance with id " + id);
        try {
            taskInstanceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(TaskInstance.class, id);
        }
    }

    public void update(Long id, TaskInstance taskInstance) {
        Task task = taskService.find(taskInstance.getTask().getId());
        taskInstance.setTask(task);
        checkFinishedTime(taskInstance);

        try {
            taskInstance.setId(taskInstanceRepository.findById(id).get().getId());
            taskInstanceRepository.save(taskInstance).getId();
            log.info("TaskInstance updated successfully");
        } catch (NoSuchElementException e) {
            throw new NotFoundException(TaskInstance.class, id);
        }
    }

}
