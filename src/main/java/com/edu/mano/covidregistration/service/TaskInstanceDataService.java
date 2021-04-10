package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.TaskInstanceData;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.repository.TaskInstanceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskInstanceDataService {

    private final TaskInstanceDataRepository taskInstanceDataRepository;

    private final TaskInstanceService taskInstanceService;

    private final AttributeService attributeService;

    @Autowired
    public TaskInstanceDataService(TaskInstanceDataRepository taskInstanceDataRepository,
                                   @Lazy TaskInstanceService taskInstanceService,
                                   AttributeService attributeService) {
        this.taskInstanceDataRepository = taskInstanceDataRepository;
        this.taskInstanceService = taskInstanceService;
        this.attributeService = attributeService;
    }

    public List<TaskInstanceData> findAll() {
        return taskInstanceDataRepository.findAll();
    }

    public TaskInstanceData find(Long id) {
        try {
            return taskInstanceDataRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(TaskInstanceData.class, id);
        }
    }

    public Long add(TaskInstanceData taskInstanceData) {
        taskInstanceService.find(taskInstanceData.getTaskInstance().getId());
        attributeService.find(taskInstanceData.getAttribute().getId());
        return taskInstanceDataRepository.save(taskInstanceData).getId();
    }

    public void delete(Long id) {
        find(id);
        taskInstanceDataRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, TaskInstanceData taskInstanceData) {
        find(id);
        taskInstanceData.setId(id);

        TaskInstance taskInstance = taskInstanceService.find(taskInstanceData.getTaskInstance().getId());
        Attribute attribute = attributeService.find(taskInstanceData.getAttribute().getId());
        taskInstanceData.setTaskInstance(taskInstance);
        taskInstanceData.setAttribute(attribute);

        taskInstanceDataRepository.save(taskInstanceData);
    }

}
