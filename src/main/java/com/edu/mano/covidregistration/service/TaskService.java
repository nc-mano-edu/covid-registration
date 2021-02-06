package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.repository.TaskRepository;
import com.edu.mano.covidregistration.service.attributeService.AttributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            log.info("Task with id " + id + " not found");
            return null;
        }
    }

    public ResponseEntity<String> add(Task task) {
        for (Attribute attr : task.getAttributes()) {
            Long attrId = attr.getId();
            if (attributeService.find(attrId) == null) {
                return new ResponseEntity<>("Attribute with id " + attrId + " does not exist", HttpStatus.NOT_FOUND);
            }
        }

        Long taskId = taskRepository.save(task).getId();
        log.info("Task created with id " + taskId);
        return ResponseEntity.ok("Task created with id " + taskId);
    }

    public boolean delete(Long id) {
        log.info("Deleting an tasks with id " + id);
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public ResponseEntity<String> update(Long id, Task task) {
        List<Attribute> attributes = task.getAttributes();
        for (int i = 0; i < attributes.size(); i++) {
            Long attrId = attributes.get(i).getId();
            Attribute attribute = attributeService.find(attrId);
            if (attribute == null) {
                return new ResponseEntity<>("Attribute with id " + attrId + " does not exist", HttpStatus.NOT_FOUND);
            }
            attributes.set(i, attribute);
        }
        task.setAttributes(attributes);

        try {
            task.setId(taskRepository.findById(id).get().getId());
            Long taskId = taskRepository.save(task).getId();
            log.info("Task updated successfully");
            return ResponseEntity.ok("Task updated successfully");
        } catch (NoSuchElementException e) {
            log.info("Task with id " + id + " not found");
            return new ResponseEntity<>("Task with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

}
