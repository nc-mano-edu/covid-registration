package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.jobs.JobScheduler;
import com.edu.mano.covidregistration.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final AttributeService attributeService;

    @Autowired
    public TaskService(TaskRepository taskRepository, AttributeService attributeService) {
        this.taskRepository = taskRepository;
        this.attributeService = attributeService;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task find(Long id) {
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

        return taskRepository.save(task).getId();
    }

    public void delete(Long id) {
        find(id);
        taskRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Task task) {
        find(id);
        task.setId(id);

        List<Attribute> attributes = task.getAttributes();
        for (int i = 0; i < attributes.size(); i++) {
            Attribute attribute = attributeService.find(attributes.get(i).getId());
            attributes.set(i, attribute);
        }
        task.setAttributes(attributes);

        taskRepository.save(task);
    }

}
