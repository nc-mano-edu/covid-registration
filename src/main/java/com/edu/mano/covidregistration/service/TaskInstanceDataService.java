package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.TaskInstanceData;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.repository.TaskInstanceDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskInstanceDataService {

    private static final Logger log = LoggerFactory.getLogger(TaskInstanceDataService.class);

    private final TaskInstanceDataRepository taskInstanceDataRepository;

    private final TaskInstanceService taskInstanceService;

    private final AttributeService attributeService;

    @Autowired
    public TaskInstanceDataService(TaskInstanceDataRepository taskInstanceDataRepository,
                                   TaskInstanceService taskInstanceService, AttributeService attributeService) {
        this.taskInstanceDataRepository = taskInstanceDataRepository;
        this.taskInstanceService = taskInstanceService;
        this.attributeService = attributeService;
    }

    public List<TaskInstanceData> findAll() {
        log.info("Retrieving a list of taskInstanceData");
        return taskInstanceDataRepository.findAll();
    }

    public TaskInstanceData find(Long id) {
        log.info("Retrieving an taskInstanceData with id " + id);
        try {
            return taskInstanceDataRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(TaskInstanceData.class, id);
        }
    }

    public Long add(TaskInstanceData taskInstanceData) {
        taskInstanceService.find(taskInstanceData.getTaskInstance().getId());
        attributeService.find(taskInstanceData.getAttribute().getId());

        Long taskInstanceDataId = taskInstanceDataRepository.save(taskInstanceData).getId();
        log.info("TaskInstanceData created with id " + taskInstanceDataId);
        return taskInstanceDataId;
    }

    public void delete(Long id) {
        log.info("Deleting a taskInstanceData with id " + id);
        try {
            taskInstanceDataRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(TaskInstanceData.class, id);
        }
    }

    public void update(Long id, TaskInstanceData taskInstanceData) {

        TaskInstance taskInstance = taskInstanceService.find(taskInstanceData.getTaskInstance().getId());
        Attribute attribute = attributeService.find(taskInstanceData.getAttribute().getId());
        taskInstanceData.setTaskInstance(taskInstance);
        taskInstanceData.setAttribute(attribute);

        try {
            taskInstanceData.setId(taskInstanceDataRepository.findById(id).get().getId());
            taskInstanceDataRepository.save(taskInstanceData).getId();
            log.info("TaskInstanceData updated successfully");
        } catch (NoSuchElementException e) {
            throw new NotFoundException(TaskInstanceData.class, id);
        }
    }

}
