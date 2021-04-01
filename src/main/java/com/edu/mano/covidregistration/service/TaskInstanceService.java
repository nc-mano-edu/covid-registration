package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.TaskInstanceData;
import com.edu.mano.covidregistration.exception.baseExceptions.InvalidDateException;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.repository.TaskInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskInstanceService {

    private final TaskInstanceRepository taskInstanceRepository;

    private final TaskService taskService;

    private final TaskInstanceDataService taskInstanceDataService;

    @Autowired
    public TaskInstanceService(TaskInstanceRepository taskInstanceRepository,
                               TaskService taskService,
                               TaskInstanceDataService taskInstanceDataService) {
        this.taskInstanceRepository = taskInstanceRepository;
        this.taskService = taskService;
        this.taskInstanceDataService = taskInstanceDataService;
    }

    private void checkFinishedTime(TaskInstance taskInstance) {
        Date finishedTime = taskInstance.getFinishedTime();
        if (finishedTime != null && finishedTime.before(taskInstance.getCreatedTime())) {
            throw new InvalidDateException("Finished time can`t be earlier than the creation time");
        }
    }

    public List<TaskInstance> findAll() {
        return taskInstanceRepository.findAll();
    }

    public TaskInstance find(Long id) {
        try {
            return taskInstanceRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(TaskInstance.class, id);
        }
    }

    public Long add(TaskInstance taskInstance) {
        taskService.find(taskInstance.getTask().getId());
        checkFinishedTime(taskInstance);

        Task task = taskInstance.getTask();
        List<Attribute> attributes = task.getAttributes();
        Long id = taskInstanceRepository.save(taskInstance).getId();

        attributes.forEach(attribute -> {
            TaskInstanceData taskInstanceData = new TaskInstanceData();
            taskInstanceData.setTaskInstance(taskInstance);
            taskInstanceData.setAttribute(attribute);
            taskInstanceDataService.add(taskInstanceData);
        });

        return id;
    }

    public void delete(Long id) {

        TaskInstance taskInstance = find(id);
        List<TaskInstanceData> data = taskInstance.getData();
        if (data != null) {
            data.forEach(d -> {
                taskInstanceDataService.delete(d.getId());
            });
        }

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

        List<TaskInstanceData> data = taskInstance.getData();
        if (data != null) {
            data.forEach(d -> {
                taskInstanceDataService.update(d.getId(), d);
            });
        }

        try {
            taskInstance.setId(taskInstanceRepository.findById(id).get().getId());
            taskInstanceRepository.save(taskInstance);
        } catch (NoSuchElementException e) {
            throw new NotFoundException(TaskInstance.class, id);
        }
    }

}
