package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    private final AttributeService attributeService;

    @Autowired
    public TaskService(TaskRepository taskRepository, AttributeService attributeService) {
        this.taskRepository = taskRepository;
        this.attributeService = attributeService;
    }

    public List<Task> findAll() {
        log.info("Retrieving a list of tasks");
        return taskRepository.findAll();
    }

    public Task find(Long id) {
        log.info("Retrieving an tasks with id " + id);
        try {
            return taskRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(Task.class, id);
        }
    }

    public Long add(Task task) {
        for (Attribute attr : task.getAttributes()) {
            attributeService.find(attr.getId());
        }
        Long taskId = taskRepository.save(task).getId();
        log.info("Task created with id " + taskId);
        return taskId;
    }

    public void delete(Long id) {
        log.info("Deleting an tasks with id " + id);
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(Task.class, id);
        }
    }

    public void update(Long id, Task task) {
        List<Attribute> attributes = task.getAttributes();
        for (int i = 0; i < attributes.size(); i++) {
            Attribute attribute = attributeService.find(attributes.get(i).getId());
            attributes.set(i, attribute);
        }
        task.setAttributes(attributes);

        try {
            task.setId(taskRepository.findById(id).get().getId());
            taskRepository.save(task).getId();
            log.info("Task updated successfully");
        } catch (NoSuchElementException e) {
            throw new NotFoundException(Task.class, id);
        }
    }

}
